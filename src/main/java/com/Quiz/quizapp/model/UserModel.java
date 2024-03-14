package com.Quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private  String name;

    private String username;
    private String password;
    private String role;

    public UserModel(String name, String username, String password, String role) {
    }
}
