package com.Quiz.quizapp.controller;

import com.Quiz.quizapp.model.QuestionModel;
import com.Quiz.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionModel>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionModel>> getByCategory(@PathVariable("cat") String category){
        return questionService.getQuestionByCategory(category);
    }
    @GetMapping("level/{level}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionModel>> findBylevel(@PathVariable String level){
        return questionService.findBylevel(level);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
   @PostMapping("add")
    public ResponseEntity<QuestionModel>addQuestion(@RequestBody QuestionModel question){
        return questionService.addQuestion(question);
    }
 @DeleteMapping("delete/{id}")
 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);

    }


}
