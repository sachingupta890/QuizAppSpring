package com.Quiz.quizapp.controller;

import com.Quiz.quizapp.model.QuestionModel;
import com.Quiz.quizapp.model.QuestionWrapper;
import com.Quiz.quizapp.model.Response;
import com.Quiz.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title){

        return quizService.createQuiz(category,numQ,title);

    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> sumitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses ){
        return quizService.calculateResult(id,responses);
    }
}
