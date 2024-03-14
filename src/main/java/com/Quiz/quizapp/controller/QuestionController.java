package com.Quiz.quizapp.controller;

import com.Quiz.quizapp.model.QuestionModel;
import com.Quiz.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<QuestionModel>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<QuestionModel>> getByCategory(@PathVariable("cat") String category){
        return questionService.getQuestionByCategory(category);
    }
    @GetMapping("level/{level}")

    public ResponseEntity<List<QuestionModel>> findBylevel(@PathVariable String level){
        return questionService.findBylevel(level);
    }

   @PostMapping("add")
    public ResponseEntity<QuestionModel>addQuestion(@RequestBody QuestionModel question){
        return questionService.addQuestion(question);
    }
 @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);

    }


}
