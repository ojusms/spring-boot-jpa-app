package com.SpringBoot.JPAapp.DAO;

import com.SpringBoot.JPAapp.Entity.Instructor;
import com.SpringBoot.JPAapp.Entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {
    // define field for EntityManager
    private EntityManager entityManager;

    // define constructor for dependency injection. @Autowired annotation is optional here
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
        // this will also save InstructorDetails object because of CascadeType.ALL
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // find the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        // delete th instructor. This will also delete the instructor detail table entry
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // find the instructor detail
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
        // delete the instructor detail. This will also delete the associated instructor table entry
        // due to cascade all in instructor detail class
        entityManager.remove(instructorDetail);
    }
}
