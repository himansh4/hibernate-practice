package package2;

import jakarta.persistence.EntityManager;
import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utils.HibernateUtils;

public class StudentCreateDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            Student tempStudent = new Student("John", "Doe", "johndoe@email.com");
            entityManager.getTransaction().begin();
            entityManager.persist(tempStudent);
            entityManager.getTransaction().commit();

            System.out.println("Student saved. Id is: " + tempStudent.getId());
        } finally {
            sessionFactory.close();
        }
    }
}
