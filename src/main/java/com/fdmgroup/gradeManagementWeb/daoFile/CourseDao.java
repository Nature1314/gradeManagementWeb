package com.fdmgroup.gradeManagementWeb.daoFile;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.gradeManagementWeb.origin.Course;
import com.fdmgroup.gradeManagementWeb.origin.Student;



public class CourseDao {
	private EntityManagerFactory emf;
	
	public CourseDao(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	
	public void addItem(Course course) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(course);
		et.commit();
		em.close();
	}
	
	public void updateName(String oldName, String newName) {
		EntityManager em =emf.createEntityManager();
		Course course =em.find(Course.class, oldName);
		EntityTransaction et = em.getTransaction();
		et.begin();
		course.setNameOfCourse(newName);
		et.commit();
		em.close();
	} 
	
	public void removeEntry(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Course courseToRemove = em.find(Course.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(courseToRemove);
		et.commit();
		em.close();
	}
	
	public Course searchCourse(String courseName) {
		EntityManager em =emf.createEntityManager();
		Course course =em.find(Course.class, courseName);
		em.close();
		return course;	
	} 
	
	public List<Student> searchStudent(String firstName, String lastName){
		EntityManager em =emf.createEntityManager();
		Query query = null;
		if(firstName!=null&&lastName!=null) {
			query =em.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
					Student.class);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
		}else if (firstName!=null && lastName== null) {
			query=em.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName ", 
					Student.class);
			query.setParameter("firstName", firstName);
		}else if(firstName==null && lastName != null) {
			query=em.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName ", 
					Student.class);
			query.setParameter("lastName", lastName);
		}else {
			System.out.println("No input");
		}
		List<Student> student = query.getResultList();
		return student;	
	}
	
}
