package com.Quiz.quizapp.dao;

import com.Quiz.quizapp.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<QuestionModel,Integer> {

    List<QuestionModel> findByCategory(String category);
    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<QuestionModel> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);


    List<QuestionModel> findByDifficultyLevel(String level);

}