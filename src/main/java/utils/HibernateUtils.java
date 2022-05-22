package utils;

import model.Address;
import model.Instructor;
import model.InstructorDetail;
import model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Student.class)
                            .addAnnotatedClass(Instructor.class)
                            .addAnnotatedClass(InstructorDetail.class)
                            .addAnnotatedClass(Address.class)
                            .buildSessionFactory();
        }
        return sessionFactory;
    }
}
