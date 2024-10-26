package com.example.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz.model.Questions;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {
    public List<Questions> findByCategory(String category);

    public List<Questions> findByDifficulty(String difficulty);

    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    public List<Questions> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);

}
