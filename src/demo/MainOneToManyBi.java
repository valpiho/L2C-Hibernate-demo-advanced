package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainOneToManyBi {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        // Add courses for instructor
        try {
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 2);

            Course course1 = new Course("Air Guitar - The Ultimate Guide");
            Course course2 = new Course("The pinball Masterclass");
            instructor.addCourse(course1);
            instructor.addCourse(course2);

            session.save(course1);
            session.save(course2);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

        // Get courses by instructor
        try {
            session.beginTransaction();

            Instructor instructor = session.get(Instructor.class, 2);

            System.out.println(instructor.getCourseList());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

        // Delete Course
        try {
            session.beginTransaction();

            Course course = session.get(Course.class, 2);

            session.delete(course);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
