package com.Quiz.quizapp.dao;

import com.Quiz.quizapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {

    UserModel findByUsername(String email);
}
