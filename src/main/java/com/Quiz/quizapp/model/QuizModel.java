package com.Quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "quizs")
public class QuizModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String title;

    @ManyToMany
    private List<QuestionModel> questions;
}
