package com.fdmgroup.gradeManangementWeb;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.mockito.Mockito;

import com.fdmgroup.gradeManagementWeb.daoFile.CourseDao;
import com.fdmgroup.gradeManagementWeb.origin.Course;





public class CourseDaoTest {

	@Test
	public void test_addItem() {
		System.out.println("The function tests for the add item function");

		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		Course course = new Course("aa");
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		CourseDao sDao = new CourseDao(mockEmf);
		sDao.addItem(course);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(course);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	
	@Test
	public void test_updateName() {
		
		System.out.println("The function tests updateName method");
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Course course = Mockito.spy(new Course("aa"));

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Course.class, "aa")).thenReturn(course);
		
		CourseDao sDao = new CourseDao(mockEmf);
		sDao.updateName("aa", "a");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Course.class, "aa");
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(course).setNameOfCourse("a");
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	@Test
	public void test_removeEntry() {
		System.out.println("The function tests removeEntry method");
		
		EntityManagerFactory mockEmf = mock(EntityManagerFactory.class);
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Course course = new Course("aa");
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Course.class, 1)).thenReturn(course);
		
		CourseDao sDao = new CourseDao(mockEmf);
		sDao.removeEntry(1);
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Course.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).remove(1);
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
	
}
