package com.Quiz.quizapp.controller;

import com.Quiz.quizapp.model.QuestionWrapper;
import com.Quiz.quizapp.model.Response;
import com.Quiz.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    QuizService quizService;

    @GetMapping("get/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Integer> sumitQuiz(@PathVariable Integer id , @RequestBody List<Response> responses ){
        return quizService.calculateResult(id,responses);
    }

}