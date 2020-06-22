package com.hibernate.business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class MainAppCourseStudent {

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
			Course course = new Course("Mathematics-Love of statistics");
			
			//Save the course
			session.save(course);
			
			//Create new Students
			Student student1= new Student("Jason", "Mark", "jm@mnet.com");
			Student student2= new Student("Gabe", "Turner", "gturner@mnet.com");
			Student student3= new Student("Mickeal", "Darl", "dark@net.com");
			
			//add some students to the course
			course.addStudents(student1);
			course.addStudents(student2);
			course.addStudents(student3);
			
			//Save the students
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
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
