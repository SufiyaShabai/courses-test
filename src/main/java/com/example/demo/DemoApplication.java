
package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override

	public void run(String... args) throws Exception {
		// WARNING: This deletes all existing courses. Do it only once!
		courseRepo.deleteAll();

		insertCourseIfMissing("AWS Solution Architect- Professional", "Advanced-level cloud course", "Cloud");
		insertCourseIfMissing("GCP Associate Cloud Engineer", "Associate-level cloud course", "Cloud");
		insertCourseIfMissing("Azure Fundamentals", "Intro to Microsoft Azure services", "Cloud");

		insertCourseIfMissing("Git Essentials", "Learn version control", "DevOps");
		insertCourseIfMissing("Linux", "Learn scripting", "DevOps");
		insertCourseIfMissing("Terraform", "Learn IAC", "DevOps");
		insertCourseIfMissing("Docker Essentials", "Learn container basics", "DevOps");
		insertCourseIfMissing("Jenkins CICD", "Learn CICD", "DevOps");
		insertCourseIfMissing("Kubernetes for Beginners", "Deploy and manage apps with Kubernetes", "DevOps");
		insertCourseIfMissing("Prometheus/Grafana", "Learn monitoring tools", "DevOps");
		insertCourseIfMissing("ELK", "Learn Tracing", "DevOps");

		System.out.println("✅ Sample courses inserted after wiping existing ones.");
	}

	private void insertCourseIfMissing(String title, String description, String category) {
		if (courseRepo.findByTitle(title).isEmpty()) {
			courseRepo.save(new Course(null, title, description, category, new HashSet<>()));
		}
	}


	}

