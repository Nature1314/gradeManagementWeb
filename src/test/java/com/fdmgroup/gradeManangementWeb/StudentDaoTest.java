package com.fdmgroup.gradeManangementWeb;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
//import org.mockito.InOrder;
import org.mockito.Mockito;

import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.entities.Student;



public class StudentDaoTest {
	
	@Test
	public void test_addItem() {
		System.out.println("The function tests for the add item function");

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		Student student = new Student("aa","bb","123",null);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		StudentDao sDao = new StudentDao(mockEmf);
		sDao.addItem(student);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(student);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	@Test
	public void test_getPassword() {
		System.out.println("The function tests getPassword method and sees if it return password");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Student student = new Student("aa","bb","123",null);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
		StudentDao sDao = new StudentDao(mockEmf);
		sDao.getPassword(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Student.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_updatePassword() {
		System.out.println("The function tests updatePassword method");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Student student = new Student("aa","bb","123",null);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
		StudentDao sDao = new StudentDao(mockEmf);
		sDao.updatePassword(1, "000");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Student.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
	
	
	@Test
	public void test_updateName() {
		
		System.out.println("The function tests updateName method");
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Student student = Mockito.spy(new Student("aa","bb","123",null));
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
		StudentDao sDao = new StudentDao(mockEmf);
		sDao.updateName(1, "cc", "dd");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Student.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(student).setFirstName("cc");
		verify(student).setLastName("dd");
		verify(mockEt).commit();
		verify(mockEm).close();
	}

}
