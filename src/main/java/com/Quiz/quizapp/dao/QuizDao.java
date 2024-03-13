package com.Quiz.quizapp.dao;

import com.Quiz.quizapp.model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<QuizModel,Integer> {
}
