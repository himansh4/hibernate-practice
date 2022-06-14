package package4;

import jakarta.persistence.EntityManager;
import model.Course;
import model.Instructor;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

public class OneToManyBidirectionalDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            // define courses object
            Course course1 = new Course("Java - The Complete Reference");
            Course course2 = new Course("Swift 4");

            // define instructor object
            Instructor instructor = new Instructor();
            instructor.setFirstName("Himanshu");
            instructor.setLastName("Gupte");

            // set courses for this instructor
            instructor.add(course1);
            instructor.add(course2);

            entityManager.getTransaction().begin();
            System.out.println("Going to save instructor with courses");
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            System.out.println("Going to fetch instructor with id: " + instructor.getId());
            Instructor tempInstructor = entityManager.find(Instructor.class, instructor.getId());
            System.out.println("Fetched instructor is:");
            System.out.println(tempInstructor);
            System.out.println(instructor.getCourses());

        } finally {
            entityManager.close();
            sessionFactory.close();
        }
    }
}
