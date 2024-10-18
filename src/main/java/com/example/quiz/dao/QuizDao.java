package com.example.quiz.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer>{

    
} 
