package com.SpringBoot.JPAapp.DAO;

import com.SpringBoot.JPAapp.Entity.Course;
import com.SpringBoot.JPAapp.Entity.Instructor;
import com.SpringBoot.JPAapp.Entity.InstructorDetail;
import com.SpringBoot.JPAapp.Entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);

    Course findCoursebyId(int theId);

    void update(Course tempCourse);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course getCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    void update(Student tempStudent);
}
