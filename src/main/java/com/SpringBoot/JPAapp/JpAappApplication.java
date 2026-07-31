package com.SpringBoot.JPAapp;

import com.SpringBoot.JPAapp.DAO.AppDAO;
import com.SpringBoot.JPAapp.Entity.Course;
import com.SpringBoot.JPAapp.Entity.Instructor;
import com.SpringBoot.JPAapp.Entity.InstructorDetail;
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteCourse(appDAO);
		};
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
