package com.SpringBoot.JPAapp;

import com.SpringBoot.JPAapp.DAO.AppDAO;
import com.SpringBoot.JPAapp.Entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpAappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpAappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//saveCourseAndReviews(appDAO);
			//findCourseWithReviews(appDAO);
			//deleteCourseAndReviews(appDAO);
			//createCourseAndStudents(appDAO);
			//findCourseWithStudents(appDAO);
			findStudentWithCourses(appDAO);
		};
	}

	private void findStudentWithCourses(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding Student with ID: "+theId);
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Found student: "+tempStudent);
		System.out.println("Found courses: "+tempStudent.getCourses());
	}

	private void findCourseWithStudents(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Finding course with ID: "+theId);
		Course theCourse = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println(theCourse);
		System.out.println("Students: " + theCourse.getStudents());

	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create course
		Course tempCourse = new Course("Pacman - How to score many points");

		// create students
		Student tempStudent = new Student("John","Doe","john@student.com");
		Student tempStudent2 = new Student("Mary","Sue","mary@student.com");

		// add students to course
		tempCourse.addStudent(tempStudent);
		tempCourse.addStudent(tempStudent2);

		// save course
		System.out.println("Saving course: "+tempCourse);
		System.out.println("saving students: "+tempCourse.getStudents());
		appDAO.save(tempCourse);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course with ID: "+theId);
		appDAO.deleteCourseById(theId); // cascade type ALL for course.reviews ensures all associate reviews get deleted
		System.out.println("Done");
	}

	private void findCourseWithReviews(AppDAO appDAO) {
		int theId = 10;
		Course course = appDAO.getCourseAndReviewsByCourseId(theId);
		System.out.println(course);
		System.out.println(course.getReviews());
	}

	private void saveCourseAndReviews(AppDAO appDAO) {
		// create course
		Course tempCourse = new Course("Space Pinball - How to score many points");
		// add reviews
		tempCourse.addReview(new Review("Great course"));
		tempCourse.addReview(new Review("I like it"));
		tempCourse.addReview(new Review("What a dumb course, you suck"));
		// save the course
		System.out.println("Saving");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		appDAO.save(tempCourse);
		System.out.println("Done");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course with ID: " +theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		// find course
		System.out.println("Finding course with ID: " +theId);
		Course tempCourse = appDAO.findCoursebyId(theId);
		System.out.println(tempCourse);
		// update course
		System.out.println("Updating course");
		tempCourse.setTitle("Foosball 101");
		appDAO.update(tempCourse);
		System.out.println("Done");

	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 2;
		// find instructor
		System.out.println("Finding instructor with ID: " +theId);
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("Instructor found: " + instructor);
		// update instructor
		System.out.println("Updating instructor");
		instructor.setLastName("Sue");
		appDAO.update(instructor);
		System.out.println("Done");

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 2;
		// find instructor
		System.out.println("Finding instructor with ID: " + theId);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("Instructor: " + instructor);
		System.out.println("Instructor Detail: " + instructor.getInstructorDetail());
		System.out.println("Associated courses: " + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 2;
		// find instructor
		System.out.println("Finding instructor with ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println(tempInstructor);
		// find courses for instructor
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		// associate courses and instructor
		tempInstructor.setCourses(courses);
		System.out.println("The courses are: " + tempInstructor.getCourses());
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
		int id = 2;
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
