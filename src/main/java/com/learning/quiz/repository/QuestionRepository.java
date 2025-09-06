package com.learning.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.quiz.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

    public List<Question> findByCategory(String category);

    @Query(value="Select * from question q where q.category= :category order by Random() limit :numQues", nativeQuery = true)
    public List<Question> fetchQuizQuestions(String category, int numQues);

}
