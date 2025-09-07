package com.learning.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.quiz.entity.Question;
import com.learning.quiz.repository.QuestionRepository;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepo){
        this.questionRepository=questionRepo;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            // Traditional Java way of handling the exceptions.
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
       
        return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
