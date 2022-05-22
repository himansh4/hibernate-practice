package package3;

import jakarta.persistence.EntityManager;
import model.Instructor;
import model.InstructorDetail;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import java.util.List;

public class MainClassInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            Instructor instructor = new Instructor("Andrew", "Symonds", "andrew@email.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://youtube.com", "Cricket");
            instructor.setInstructorDetail(instructorDetail);
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();

            System.out.println("Fetching all the instructors...");
            entityManager.getTransaction().begin();
            List<Instructor> instructors = (List<Instructor>) entityManager.createQuery("from Instructor").getResultList();
            instructors.forEach(System.out::println);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
            sessionFactory.close();
        }
    }
}
