package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainOneToOneUni {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor instructor = new Instructor("Val", "Piho", "val.piho@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.pibox.ee/youtube", "Basketball");
            instructor.setInstructorDetail(instructorDetail);

            session.save(instructor);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
