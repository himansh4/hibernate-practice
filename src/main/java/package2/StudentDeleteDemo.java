package package2;

import jakarta.persistence.EntityManager;
import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.HibernateUtils;

public class StudentDeleteDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            System.out.println("Creating student 'Jane Doe'");
            Student tempStudent = new Student("Jane", "Doe", "janedoe@email.com");
            entityManager.getTransaction().begin();
            entityManager.persist(tempStudent);
            entityManager.getTransaction().commit();

            System.out.println("Retrieving student object with id: " + tempStudent.getId());
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, tempStudent.getId());
            System.out.println("Going to delete student 'Jane Doe'");
            entityManager.remove(student);
            entityManager.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            sessionFactory.close();
        }
    }
}
