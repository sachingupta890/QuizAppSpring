package com.Quiz.quizapp.controller;

import com.Quiz.quizapp.model.QuestionWrapper;
import com.Quiz.quizapp.model.Response;
import com.Quiz.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    QuizService quizService;

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> sumitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses ){
        return quizService.calculateResult(id,responses);
    }

}