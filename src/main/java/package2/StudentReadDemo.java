package package2;

import jakarta.persistence.EntityManager;
import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import package2.utils.StudentUtils;
import utils.HibernateUtils;

import java.util.List;

public class StudentReadDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            Student tempStudent = new Student("Jane", "Doe", "janedoe@email.com");
            entityManager.getTransaction().begin();
            entityManager.persist(tempStudent);
            entityManager.getTransaction().commit();

            System.out.println("Retrieving student object with id: " + tempStudent.getId());
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, tempStudent.getId());
            System.out.println("Student retrieved: " + student);
            entityManager.getTransaction().commit();

            System.out.println("Creating some dummy records");
            StudentUtils.createDummyStudents();
            entityManager.getTransaction().begin();
            System.out.println("Retrieving dummy students");
            List<Student> students = (List<Student>) entityManager.createQuery("from Student").getResultList();
            for (Student s : students) {
                System.out.println(s);
            }


        } finally {
            sessionFactory.close();
        }
    }
}
