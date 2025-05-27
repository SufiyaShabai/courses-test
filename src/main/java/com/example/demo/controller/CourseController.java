package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseRepository courseRepo;

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Course> getCoursesByCategory(@PathVariable String category) {
        return courseRepo.findByCategory(category);
    }

    @GetMapping("/enrolled-count")
    public Map<String, Integer> getEnrolledCounts() {
        List<Course> courses = courseRepo.findAll();
        Map<String, Integer> response = new HashMap<>();
        for (Course course : courses) {
            response.put(course.getTitle(), course.getEnrolledUsers().size());
        }
        return response;
    }
}
