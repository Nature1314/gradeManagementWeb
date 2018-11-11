package com.fdmgroup.gradeManagementWeb.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.gradeManagementWeb.entities.Course;



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
	
	public void updateState(String courseName, Boolean active) {
		EntityManager em =emf.createEntityManager();
		Course course =em.find(Course.class, courseName);
		EntityTransaction et = em.getTransaction();
		et.begin();
		if(active) course.setStateOfCourse("active");
		else course.setStateOfCourse("closed");
		et.commit();
		em.close();
		
	}
	
	public Course searchCourse(String courseName) {
		EntityManager em =emf.createEntityManager();
		Course course =em.find(Course.class, courseName);
		em.close();
		return course;	
	} 
}
