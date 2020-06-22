package com.hibernate.business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class MainAppAddCourseStudent {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		
		try {
			
			
			//Start transaction 
			session.beginTransaction();
			
			//create a course 
			Student student = session.get(Student.class,1);
			
			
			//Create new courses for the student
			Course course1 = new Course("Video Games");
			Course course2 = new Course("Designing");
			Course course3 = new Course("Psychology");
			
			//add some students to the course
			course1.addStudents(student);
			course2.addStudents(student);
			course3.addStudents(student);
			
			//Save the students
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
			//Commit
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}

	}

}
