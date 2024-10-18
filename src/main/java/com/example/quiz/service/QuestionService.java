package com.example.quiz.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz.dao.QuestionDao;
import com.example.quiz.model.Questions;

@Service
public class QuestionService {

   @Autowired
   QuestionDao questionDao;

   // READ - get all questions
   public ResponseEntity<List<Questions>> getAllQuestions(){
      try{

         return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK) ;
      }
      catch(Exception e){
         e.printStackTrace();
      }
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;

   }

   // READ BY CATEGORY
public ResponseEntity<List<Questions>> getQuestionByCategory(String category) {
     try{
      return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
      }
    catch(Exception e){
      e.printStackTrace();
   }
   return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
      

}

// READ BY DIFFICULTY
public ResponseEntity<List<Questions>> getQuestionByDifficulty(String difficulty) {
     try{
      return new ResponseEntity<>(questionDao.findByDifficulty(difficulty),HttpStatus.OK); 
   }
   catch(Exception e){
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
      
     }
      
// CREATE 
public ResponseEntity<String> addQuestions(Questions questions) {
    
   try {
      questionDao.save(questions);
      return new ResponseEntity<>("successfully created", HttpStatus.CREATED);
  } catch (Exception e) {
  
      e.printStackTrace();
  }
  return new ResponseEntity<>("failed to create",HttpStatus.BAD_REQUEST);
}

// DELETE
public ResponseEntity<String> deleteQuestions(Integer id) {
   try {
      questionDao.deleteById(id);
      return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
  } catch (Exception e) {
  
      e.printStackTrace();
  }
  return new ResponseEntity<>("failed to delete",HttpStatus.BAD_REQUEST);
}


}
