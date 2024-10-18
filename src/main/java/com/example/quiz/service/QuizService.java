package com.example.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quiz.dao.QuestionDao;
import com.example.quiz.dao.QuizDao;
import com.example.quiz.model.QuestionWrapper;
import com.example.quiz.model.Questions;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Response;

@Service
public class QuizService {

   @Autowired
   QuizDao quizDao;
   @Autowired
   QuestionDao questionDao;

   public ResponseEntity<String> createQuiz(String category, int numQ,String title) {


      List<Questions> questions= questionDao.findRandomQuestionsByCategory(category,numQ);


      Quiz quiz = new Quiz();
      quiz.setTitle(title);
      quiz.setQuestions(questions);
      quizDao.save(quiz);

      return new ResponseEntity<>("success", HttpStatus.CREATED);
   }

   public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
      Optional<Quiz> quiz= quizDao.findById(id);
      List<Questions> questionsFromDb =quiz.get().getQuestions();
      List<QuestionWrapper> questionForUser = new ArrayList<>();
      for(Questions  q : questionsFromDb){
         QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestion());
         questionForUser.add(qw);
      }
      return new ResponseEntity<>(questionForUser, HttpStatus.OK) ;
   }

   public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> response) {
     Quiz quiz = quizDao.findById(id).get();
     List<Questions> questions = quiz.getQuestions();

     int i=0;
     int right=0;
      for(Response  resp : response){
         if(resp.getAns().equals(questions.get(i).getRightanswer())){
            right++;
         }
         i++;
      }
      return new ResponseEntity<>(right,HttpStatus.OK);
   }
}