package package1;

import jakarta.persistence.EntityManager;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        try {
            EntityManager entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Student student = new Student("Daffy", "Duck", "daffy@email.com");
            entityManager.persist(student);
            entityManager.getTransaction().commit();

            entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            System.out.println("Fetching student by id: " + student.getId());
            Student savedStudent = entityManager.find(Student.class, student.getId());
            System.out.println(savedStudent);
            entityManager.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
