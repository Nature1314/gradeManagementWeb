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

import com.fdmgroup.gradeManagementWeb.daoFile.TeacherDao;
import com.fdmgroup.gradeManagementWeb.origin.Teacher;



public class TeacherDaoTest {

	@Test
	public void test_addItem() {
		System.out.println("The function tests for the add item function");

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		Teacher teacher = new Teacher("aa","bb",123);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.addItem(teacher);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(teacher);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	@Test
	public void test_getPassword() {
		System.out.println("The function tests getPassword method and sees if it return password");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Teacher teacher = new Teacher("aa","bb",123);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.getPassword(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_updatePassword() {
		System.out.println("The function tests updatePassword method");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Teacher teacher = new Teacher("aa","bb",123);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.updatePassword(1, 000);
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
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
		
		Teacher teacher = Mockito.spy(new Teacher("aa","bb",123));
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.updateName(1, "cc", "dd");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(teacher).setFirstName("cc");
		verify(teacher).setLastName("dd");
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	@Test
	public void test_removeEntry() {
		System.out.println("The function tests removeEntry method");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Teacher teacher = new Teacher("aa","bb",123);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.removeEntry(1);
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).remove(1);
		verify(mockEt).commit();
		verify(mockEm).close();		
	}

}
