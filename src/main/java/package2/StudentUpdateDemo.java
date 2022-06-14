package package2;

import jakarta.persistence.EntityManager;
import model.Student;
import org.hibernate.SessionFactory;
import package2.utils.StudentUtils;
import utils.HibernateUtils;

public class StudentUpdateDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            // define and persist student object
            Student tempStudent = new Student("Bruce", "Wayne", "bruce@email.com");
            entityManager.getTransaction().begin();
            entityManager.persist(tempStudent);
            entityManager.getTransaction().commit();

            System.out.println("Student saved with following details: " + tempStudent);
            System.out.println("Changing the lastname to Willis");
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, tempStudent.getId());
            student.setLastName("Willis");
            entityManager.getTransaction().commit();

            System.out.println("Student after performing update: " + student);
            System.out.println("Done!");

            System.out.println("Creating some dummy records");
            StudentUtils.createDummyStudents();
            entityManager.getTransaction().begin();
            System.out.println("Retrieving dummy students");
            int i = entityManager.createQuery("update Student set email='foo@email.com'")
                    .executeUpdate();
            System.out.println("Rows updated: " + i);
            entityManager.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
