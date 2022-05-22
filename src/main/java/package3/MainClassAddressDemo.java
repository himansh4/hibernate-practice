package package3;

import jakarta.persistence.EntityManager;
import model.Address;
import model.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

public class MainClassAddressDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            Instructor instructor = new Instructor("Chester", "Bennington", "chester@lp.com");
            instructor.setAddress(new Address("Beverly Hills", "CA", "USA"));
            System.out.println("Going to save the instructor..");
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();

            System.out.println("Fetching the instructor with id: " + instructor.getId());
            entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            instructor = entityManager.find(Instructor.class, instructor.getId());
            System.out.println(instructor);
            entityManager.getTransaction().commit();

            System.out.println("Fetching the address by id: 1");
            int addressId = 1;
            entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            System.out.println(address);
            System.out.println(address.getInstructor());
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
            sessionFactory.close();
        }
    }
}
