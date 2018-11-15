package com.fdmgroup.gradeManagementWeb.dao;


import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.gradeManagementWeb.entities.Student;




public class StudentDao {

	@Resource(name ="emfBean")
	private EntityManagerFactory emf;
	
	public void addItem(Student student) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(student);
		et.commit();
		em.close();
	}
	
	public String getPassword(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Student student =em.find(Student.class, idNumber);
		System.out.println(student);
		String password = student.getPassword();
		em.close();
		return password;
	}
	
	public void updatePassword(int idNumber, String newPassword) {
		EntityManager em =emf.createEntityManager();
		Student student =em.find(Student.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		student.setPassword(newPassword);
		et.commit();
		em.close();
	} 
	
	public void updateName(int idNumber, String firstName, String lastName) {
		EntityManager em =emf.createEntityManager();
		Student student =em.find(Student.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		et.commit();
		em.close();
	}
	
	public void updateState(int idNumber, String newState) {
		EntityManager em =emf.createEntityManager();
		Student student =em.find(Student.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		student.setStateOfStudent(newState);
		et.commit();
		em.close();
	}

	public Student searchStudent(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Student student =em.find(Student.class, idNumber);
		em.close();
		return student;	
	} 
	
	public List<Student> searchStudent(String firstName, String lastName){
		EntityManager em =emf.createEntityManager();
		TypedQuery<Student>  query = null;
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
