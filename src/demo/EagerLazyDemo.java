package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EagerLazyDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        // Get courses by instructor
        try {
            session.beginTransaction();

            Query<Instructor> query =
                    session.createQuery("SELECT i FROM Instructor i "
                                    + "JOIN FETCH i.courseList "
                                    + "WHERE i.id=:instructorId",
                            Instructor.class);

            query.setParameter("instructorId", 2);
            Instructor instructor = query.getSingleResult();

            session.getTransaction().commit();
            session.close();


            System.out.println(instructor.getCourseList());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }


    }
}
