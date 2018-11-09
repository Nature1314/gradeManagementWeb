package com.fdmgroup.gradeManangementWeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
//import org.mockito.InOrder;
import org.mockito.Mockito;

import com.fdmgroup.gradeManagementWeb.dao.CourseDao;
import com.fdmgroup.gradeManagementWeb.dao.TeacherDao;
import com.fdmgroup.gradeManagementWeb.entities.Course;
import com.fdmgroup.gradeManagementWeb.entities.Teacher;



public class TeacherDaoTest {
	
	private EntityManagerFactory mockEmf;
	private EntityManager mockEm;
	private EntityTransaction mockEt;

	@Before
	public void commonStubbing() {
		mockEmf = mock(EntityManagerFactory.class);
		mockEm = mock(EntityManager.class);
		mockEt = mock(EntityTransaction.class);

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}

	@Test
	public void test_addItem() {
		System.out.println("The function tests for the add item function");

		Teacher teacher = new Teacher("aa","bb","123");
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.addItem(teacher);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(teacher);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
/*
	@Test
	public void test_addItem_withoutMock() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		Teacher teacher = new Teacher("jimmy","Zhou","123");
		TeacherDao sDao = new TeacherDao(emf);
		sDao.addItem(teacher);
		Teacher jimmy = sDao.searchTeacher(1);
		assertTrue(jimmy != null);
	}
*/
	
	@Test
	public void test_getPassword() {
		System.out.println("The function tests getPassword method and sees if it return password");
		
		Teacher teacher = new Teacher("aa","bb","123");

		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.getPassword(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).close();		
	}
/**	
	@Test
	public void test_getPassword_without_mock() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		TeacherDao sDao = new TeacherDao(emf);
		int result = sDao.getPassword(1);
		int expect = 123;
		assertEquals(expect,result);
	}
*/	
	@Test
	public void test_updatePassword() {
		System.out.println("The function tests updatePassword method");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Teacher teacher = new Teacher("aa","bb","123");
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.updatePassword(1, "000");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
/**	
	@Test
	public void test_updatePassword_without_mock() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		TeacherDao sDao = new TeacherDao(emf);
		sDao.updatePassword(1, 111);
		int result = sDao.getPassword(1);
		int expect = 111;
		assertEquals(expect,result);
	}
*/	
	@Test
	public void test_updateName() {
		
		Teacher teacher = Mockito.spy(new Teacher("aa","bb","123"));

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
		Teacher mockTeacher = new Teacher("aa","bb","123");
	
		when(mockEm.find(Teacher.class, 0)).thenReturn(mockTeacher);
		
		TeacherDao sDao = new TeacherDao(mockEmf);
		sDao.removeEntry(0);
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 0);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).remove(mockTeacher);
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
	
/**
	@Test
	public void test_removeEntity_without_mock() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		TeacherDao sDao = new TeacherDao(emf);
		sDao.removeEntry(1);
		Teacher jimmy = sDao.searchTeacher(1);
		assertTrue(jimmy == null);	
	}
*/
}
