package com.fdmgroup.gradeManangementWeb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fdmgroup.gradeManagementWeb.dao.CourseDao;
import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.entities.Course;

public class CourseDaoTest {

	@Mock
	private EntityManagerFactory mockEmf;
	private EntityManager mockEm;
	private EntityTransaction mockEt;

	@InjectMocks
	private CourseDao sDao = new CourseDao();

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
		Course course = new Course("aa");

		sDao.addItem(course);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(course);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
/**
	@Test
	public void test_addItem_withoutMock() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		CourseDao sDao = new CourseDao(emf);
		Course course = new Course("Java");
		sDao.addItem(course);
		Course java = sDao.searchCourse("Java");
		assertTrue(java != null);

	}
*/	
	@Test
	public void test_updateName() {
		Course course = Mockito.spy(new Course("aa"));
		when(mockEm.find(Course.class, "aa")).thenReturn(course);

		sDao.updateName("aa", "a");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Course.class, "aa");
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(course).setNameOfCourse("a");
		verify(mockEt).commit();
		verify(mockEm).close();
	}
/*	
	@Test
	public void test_updateName_without_mock() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		CourseDao sDao = new CourseDao(emf);
		sDao.updateName("Java", "Cpp");
		Course cpp = sDao.searchCourse("Cpp");
		assertTrue(cpp != null);
	}
*/
}
