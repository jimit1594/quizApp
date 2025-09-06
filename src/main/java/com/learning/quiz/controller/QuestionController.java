package com.learning.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.quiz.entity.Question;
import com.learning.quiz.service.QuestionService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("questions")
public class QuestionController {
    
    private QuestionService questionService;

    public QuestionController(QuestionService questionAService){
        this.questionService=questionAService;
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("question/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }
    
    @PostMapping("addQuestion")
    public ResponseEntity<HttpStatus> addQuestion(@RequestBody Question question) {
         return questionService.addQuestion(question);
         
    }
    
    
}
