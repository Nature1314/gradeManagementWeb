package com.fdmgroup.gradeManagementWeb.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.gradeManagementWeb.entities.Administrator;

public class AdministratorDao {

	@Resource(name ="emfBean")
	private EntityManagerFactory emf;
	
	public void addItem(Administrator admin) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(admin);
		et.commit();
		em.close();
	}
	
	public String getPassword(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Administrator admin =em.find(Administrator.class, idNumber);
		String password = admin.getPassword();
		em.close();
		return password;
	}
	
	public void updatePassword(int idNumber, String newPassword) {
		EntityManager em =emf.createEntityManager();
		Administrator admin =em.find(Administrator.class, idNumber);
		EntityTransaction et = em.getTransaction();
		et.begin();
		admin.setPassword(newPassword);
		et.commit();
		em.close();
	} 
	
	public Administrator searchAdmin(int idNumber) {
		EntityManager em =emf.createEntityManager();
		Administrator admin=em.find(Administrator.class, idNumber);
		em.close();
		return admin;	
	} 
	
	public List<Administrator> searchAdmin(String firstName, String lastName){
		EntityManager em =emf.createEntityManager();
		TypedQuery<Administrator>  query = null;
		if(firstName!=null&&lastName!=null) {
			query =em.createQuery("SELECT s FROM Administrator s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
					Administrator.class);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
		}else if (firstName!=null && lastName== null) {
			query=em.createQuery("SELECT s FROM Administrator s WHERE s.firstName = :firstName ", 
					Administrator.class);
			query.setParameter("firstName", firstName);
		}else if(firstName==null && lastName != null) {
			query=em.createQuery("SELECT s FROM Administrator s WHERE s.lastName = :lastName ", 
					Administrator.class);
			query.setParameter("lastName", lastName);
		}
		List<Administrator> admin = query.getResultList();
		return admin;	
	}
}
