package com.learning.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.quiz.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

} 
