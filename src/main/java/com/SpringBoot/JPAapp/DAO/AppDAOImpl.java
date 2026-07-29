package com.SpringBoot.JPAapp.DAO;

import com.SpringBoot.JPAapp.Entity.Course;
import com.SpringBoot.JPAapp.Entity.Instructor;
import com.SpringBoot.JPAapp.Entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        // update to remove association with Courses field and db entry.
        // If not done, throws and error for foreign key constraint violation
        List<Course> courses = tempInstructor.getCourses();
        for (Course tempCourse : courses)
            tempCourse.setInstructor(null);
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
        // remove associate between instructor detail and instructor.
        // Done after removing cascade for remove operation. If this is not done, the instructor detail alone
        // is not deleted
        instructorDetail.getInstructor().setInstructorDetail(null);
        // delete the instructor detail. This will also delete the associated instructor table entry
        // due to cascade all in instructor detail class
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);
        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                + "JOIN FETCH i.courses " // this does EAGER loading even if the fetch type is set to LAZY in the entity
                + "where i.id = :data", Instructor.class); // join reduces the number of queries;
                // i.e. one for select instructor, one for select courses, one for select instructor_detail.
                // Join fetch select uses same query to select instructor and whatever is join fetched (courses, detail)
        query.setParameter("data",theId);
        // execute query
        Instructor tempInstructor = query.getSingleResult();
        return tempInstructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    public Course findCoursebyId(int theId) {
        Course course = entityManager.find(Course.class, theId);
        return course;
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }
}
