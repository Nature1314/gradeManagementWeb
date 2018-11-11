package com.fdmgroup.gradeManagementWeb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.gradeManagementWeb.entities.Teacher;



public class TeacherDao {
	
	private EntityManagerFactory emf;

	public TeacherDao(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}
	
	public void addItem(Teacher teacher) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(teacher);
		et.commit();
		em.close();
	}
	
	
	public String getPassword(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		String password = teacher.getPassword();
		em.close();	
		return password;
	}
	
	public void updatePassword(int idNumber, String newPassword) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		teacher.setPassword(newPassword);
		et.commit();
		em.close();
	} 
	
	public void updateName(int idNumber, String firstName, String lastName) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		et.commit();
		em.close();
	}
	
	public void updateState(int idNumber, String newState) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		teacher.setStateOfTeacher(newState);
		et.commit();
		em.close();
	}
	
	public Teacher searchTeacher(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		em.close();
		return teacher;	
	} 
	
	public List<Teacher> searchTeacher(String firstName, String lastName){
		EntityManager em =emf.createEntityManager();
		Query query = null;
		if(firstName!=null&&lastName!=null) {
			query =em.createQuery("SELECT s FROM Teacher s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
					Teacher.class);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
		}else if (firstName!=null && lastName== null) {
			query=em.createQuery("SELECT s FROM Teacher s WHERE s.firstName = :firstName ", 
					Teacher.class);
			query.setParameter("firstName", firstName);
		}else if(firstName==null && lastName != null) {
			query=em.createQuery("SELECT s FROM Teacher s WHERE s.lastName = :lastName ", 
					Teacher.class);
			query.setParameter("lastName", lastName);
		}else {
			System.out.println("No input");
		}
		List<Teacher> teacher = query.getResultList();
		return teacher;	
	}
	
}
