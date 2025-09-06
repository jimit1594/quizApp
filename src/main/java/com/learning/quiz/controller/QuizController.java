package com.learning.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.quiz.entity.QuestionWrapper;
import com.learning.quiz.entity.QuizResponse;
import com.learning.quiz.service.QuizService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("quiz")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService){
        this.quizService=quizService;
    }
    @PostMapping("create")
    public ResponseEntity<HttpStatus> createQuiz(@RequestParam String category, @RequestParam int numQues, @RequestParam String title) {
        return quizService.createQuiz(category, numQues, title);
    }

    @GetMapping("fetch/{id}")
    public ResponseEntity<List<QuestionWrapper>> fetchQuiz(@PathVariable int id) {
        return quizService.fetchQuiz(id);
    }
    
    @GetMapping("submit/{quizId}")
    public ResponseEntity<String> submitQuiz(@PathVariable int quizId,@RequestBody List<QuizResponse> questionResponse) {
        int score=quizService.submitAndCalculateScore(quizId, questionResponse);
        return new ResponseEntity<>("You have passed and scored " + score ,HttpStatus.OK);
    }
    
    
}

