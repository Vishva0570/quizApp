package com.example.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.dao.QuizDao;
import com.example.quiz.model.Questions;
import com.example.quiz.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allquestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }
    
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Questions>> getQuestionByDifficulty(@PathVariable String difficulty) {
        return questionService.getQuestionByDifficulty(difficulty);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestions(@RequestBody Questions questions) {
         return questionService.addQuestions(questions);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable Integer id) {
        return questionService.deleteQuestions(id);
    }
    
    @Autowired
    QuizDao quizDao;
}
