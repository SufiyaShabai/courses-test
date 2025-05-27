package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category; // e.g., Cloud, DevOps

    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<User> enrolledUsers = new HashSet<>();
}