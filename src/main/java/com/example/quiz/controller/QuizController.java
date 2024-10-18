package com.example.quiz.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.model.QuestionWrapper;
import com.example.quiz.model.Questions;
import com.example.quiz.model.Response;
import com.example.quiz.service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizservice;

    @PostMapping(" ")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title) {
        return quizservice.createQuiz(category, numQ, title);
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer id) {
        return quizservice.getQuestions(id);   
    }
     
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response)  {
        
        return quizservice.submitQuiz(id,response);
    }
    
}   
