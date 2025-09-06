package com.learning.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.quiz.entity.Question;
import com.learning.quiz.entity.QuestionWrapper;
import com.learning.quiz.entity.Quiz;
import com.learning.quiz.entity.QuizResponse;
import com.learning.quiz.repository.QuestionRepository;
import com.learning.quiz.repository.QuizRepository;

@Service
public class QuizService {

    private QuizRepository quizRepo;
    private QuestionRepository questionRepo;

    public QuizService(QuizRepository quizRepo, QuestionRepository questionRepo) {
        this.quizRepo=quizRepo;
        this.questionRepo=questionRepo;
    }

    public ResponseEntity<HttpStatus> createQuiz(String category, int numQues, String title) {
         System.out.println("category--"+category);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<Question> quizQuestion=questionRepo.fetchQuizQuestions(category, numQues);
        System.out.println("RetrievedQuestions-"+quizQuestion);
        quiz.setQuestionList(quizQuestion);
        System.out.println("quiz--"+quiz);
        quizRepo.save(quiz);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> fetchQuiz(int id) {
        Quiz fetchedQuiz=quizRepo.findById(id).get();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
        for(Question q:fetchedQuiz.getQuestionList()){
            QuestionWrapper questionForUser = new QuestionWrapper(q.getId(), q.getQuestionDesc(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(questionForUser);
        }
        
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public int submitAndCalculateScore(int quizId, List<QuizResponse> questionResponse) {
        int score=0;
        Quiz quiz=quizRepo.findById(quizId).get();
        List<Question> questionList= quiz.getQuestionList();
        for(QuizResponse questResp: questionResponse){
           String rightAnswer= fetchRightAnswer(questResp.getQuestionId(),questionList);
           if(rightAnswer.equals(questResp.getQuestionResponse())){
                score++;
           }
        }
        return score;
    }

    private String fetchRightAnswer(int questionId, List<Question> questionList) {
        return questionList.stream().filter(q-> q.getId().equals(questionId)).findAny().get().getRightAnswer();
       
    }

}
