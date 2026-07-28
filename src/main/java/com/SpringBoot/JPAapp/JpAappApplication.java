package com.SpringBoot.JPAapp;

import com.SpringBoot.JPAapp.DAO.AppDAO;
import com.SpringBoot.JPAapp.Entity.Course;
import com.SpringBoot.JPAapp.Entity.Instructor;
import com.SpringBoot.JPAapp.Entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpAappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpAappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			findInstructorWithCourses(appDAO);
		};
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding instructor with ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println(tempInstructor);
		System.out.println("Courses for instructor: " + tempInstructor.getCourses());

	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// define instructor
		Instructor tempInstructor = new Instructor("Susan", "Medum", "susan@luv2code.com");
		// define instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube.com","Video Games");
		// set the instructor detail to the instructor
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		// add some courses
		Course tempCourse1 = new Course("Guitar - The ultimate guide");
		Course tempCourse2 = new Course("Air Hockey - Beginner's guide");
		// add the courses the instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		// save the instructor, which will also save the courses because of CascadeType.PERSIST
		System.out.println("Saving instructor. . ");
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void createInstructor(AppDAO appDAO) {
		// define instructor
		Instructor tempInstructor = new Instructor("John", "Doe", "john@luv2code.com");
		// define instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("John Doe YT","Being a John Doe");
		// set the instructor detail to the instructor
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		// save the instructor. This will persist entries in both the tables
		appDAO.save(tempInstructor);
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with ID: " + id);
		Instructor tempInstructor =	appDAO.findInstructorById(1);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("instructor detail only: " + tempInstructor.getInstructorDetail());
	}


	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("deleting instructor with ID: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding InstructorDetail with ID: " + id);
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("Instructor detail: " + tempInstructorDetail);
		System.out.println("Only instructor info: " + tempInstructorDetail.getInstructor());

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		System.out.println("deleting instructor detail with ID: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}
}
