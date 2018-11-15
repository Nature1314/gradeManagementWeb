package com.fdmgroup.gradeManangementWeb.dao;

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
import org.mockito.MockitoAnnotations;

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
		MockitoAnnotations.initMocks(this);

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
	
	@Test
	public void test_updateStatus() {
		
		String courseName = "aa";
		String stateOfCourse = "finish";
		Course course = Mockito.spy(new Course(courseName));
		when(mockEm.find(Course.class, courseName)).thenReturn(course);

		sDao.updateState(courseName , "finish");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Course.class, courseName);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(course).setStateOfCourse(stateOfCourse);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void test_searchCourse() {
		String courseName="anyCourse";
		
		Course course = Mockito.spy(new Course(courseName));
		when(mockEm.find(Course.class, courseName)).thenReturn(course);
		
		sDao.searchCourse(courseName);
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Course.class, courseName);
		verify(mockEm).close();
		
	}
}
