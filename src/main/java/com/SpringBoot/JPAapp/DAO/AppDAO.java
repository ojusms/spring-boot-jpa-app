package com.SpringBoot.JPAapp.DAO;

import com.SpringBoot.JPAapp.Entity.Instructor;
import com.SpringBoot.JPAapp.Entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
