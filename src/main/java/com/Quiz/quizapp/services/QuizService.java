package com.Quiz.quizapp.services;

import com.Quiz.quizapp.dao.QuestionDao;
import com.Quiz.quizapp.dao.QuizDao;
import com.Quiz.quizapp.model.QuestionModel;
import com.Quiz.quizapp.model.QuestionWrapper;
import com.Quiz.quizapp.model.QuizModel;
import com.Quiz.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try{
            List<QuestionModel> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
            QuizModel quiz = new QuizModel();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

            return new ResponseEntity<>("Succesfully created the Quiz", HttpStatus.CREATED);

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Cannot create Quiz",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<QuizModel> quiz = quizDao.findById(id);
        List<QuestionModel> questionsfromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(QuestionModel q:questionsfromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }



        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        QuizModel quiz = quizDao.findById(id).get();

        List<QuestionModel> questions = quiz.getQuestions();
        System.out.println("Questions ids are");
        for(QuestionModel q:questions){
            System.out.println(q.getId()+"   ");
        }
        int right = 0;
        int i = 0;
        System.out.println("responses Questions ids are");
        for(Response response:responses){
            System.out.println(response.getId()+"     ");
        }
        for(Response response:responses){
            System.out.println("response is "+response.getResponse()+"Right answer is "+questions.get(i).getRight_answer());
            if(response.getResponse().equals(questions.get(i).getRight_answer())){
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
