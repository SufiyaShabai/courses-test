package com.example.demo.repository;



import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory(String category);
    // CourseRepository.java
    Optional<Course> findByTitle(String title);

}
