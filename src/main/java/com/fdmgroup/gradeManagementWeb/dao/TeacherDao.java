package com.fdmgroup.gradeManagementWeb.daoFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.gradeManagementWeb.origin.Teacher;



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
	
	public int getPassword(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		int password = teacher.getPassword();
		em.close();	
		return password;
	}
	
	public void updatePassword(int idNumber, int newPassword) {
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
	
	public void removeEntry(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher = em.find(Teacher.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(teacher);
		et.commit();
		em.close();
	}
	
	public Teacher searchTeacher(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Teacher teacher =em.find(Teacher.class, idNumber);
		em.close();
		return teacher;	
	} 
	
}
