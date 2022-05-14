package package2.utils;

import jakarta.persistence.EntityManager;
import model.Student;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

public class StudentUtils {
    public static void createDummyStudents() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Student[] students = {
                    new Student("John", "Doe", "john@email.com"),
                    new Student("Daffy", "Duck", "daffy@email.com"),
                    new Student("Paul", "Wall", "paul@email.com"),
                    new Student("Bonita", "Applebum", "Bonita@email.com"),
                    new Student("Jane", "Doe", "jane@email.com")
        };
        for (Student student: students) {
            entityManager.persist(student);
        }
        entityManager.getTransaction().commit();
    }
}
