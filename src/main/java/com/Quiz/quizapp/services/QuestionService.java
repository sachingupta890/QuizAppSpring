package com.Quiz.quizapp.services;

import com.Quiz.quizapp.dao.QuestionDao;
import com.Quiz.quizapp.model.QuestionModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questiondao;
    public ResponseEntity<List<QuestionModel>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionModel>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<QuestionModel> addQuestion(QuestionModel question) {
        try{
            return new ResponseEntity<>(questiondao.save(question),HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        System.out.println("deleting questions");
        try{
            questiondao.deleteById(id);
            return new ResponseEntity<>("Deletion Succesfull",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Cannot delete",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionModel>> findBylevel(String level) {
        try{
            return new ResponseEntity<>(questiondao.findByDifficultyLevel(level),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
