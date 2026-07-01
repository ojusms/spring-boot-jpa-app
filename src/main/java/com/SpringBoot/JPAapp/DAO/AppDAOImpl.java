package com.SpringBoot.JPAapp.DAO;

import com.SpringBoot.JPAapp.Entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
}
