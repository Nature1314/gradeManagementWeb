package com.fdmgroup.gradeManangementWeb.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.gradeManagementWeb.dao.GradeDao;
import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.entities.Course;
import com.fdmgroup.gradeManagementWeb.entities.Grade;
import com.fdmgroup.gradeManagementWeb.entities.GradeID;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.factories.GradeIdFactory;

public class GradeDaoTest {

	@Mock
	private EntityManagerFactory mockEmf;
	private EntityManager mockEm;
	private EntityTransaction mockEt;
	private GradeIdFactory mockFactory;
	private Grade mockGrade;

	@InjectMocks
	private GradeDao gDao = new GradeDao();
	
	@Before
	public void commonStubbing() {
		MockitoAnnotations.initMocks(this);
		
		mockEm = mock(EntityManager.class);
		mockEt = mock(EntityTransaction.class);
		
		mockGrade = mock(Grade.class);


		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}

	@Test
	public void test_addItem() {
		gDao.addItem(mockGrade);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockGrade);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void test_checkGrade_courseNameIsNotNull() {
		
		String jpql = "SELECT s FROM Grade s WHERE s.id.student_ID = :studentID AND s.id.course_name = :courseName ";
		int studentID = 1;
		String courseName = "Java";
		
		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery(jpql, Grade.class)).thenReturn(mockQuery);
		
		gDao.checkGrade(studentID, courseName);

		verify(mockEm).createQuery(jpql, Grade.class);
		verify(mockQuery).setParameter("studentID", studentID);
		verify(mockQuery).setParameter("courseName", courseName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_checkGrade_courseNameIsNull() {
		
		String jpql = "SELECT s FROM Grade s WHERE s.id.student_ID = :studentID";
		int studentID = 1;
		String courseName = null;
		
		TypedQuery<Grade> mockQuery = mock(TypedQuery.class);	
		when(mockEm.createQuery(jpql, Grade.class)).thenReturn(mockQuery);

		gDao.checkGrade(studentID, courseName);
		
		verify(mockEm).createQuery(jpql, Grade.class);
		verify(mockQuery).setParameter("studentID", studentID);
		verify(mockQuery).getResultList();
	}

	@Test
	public void test_updateGrade() {

		String courseName = "Java";
		int studentId = 0;
		int newScore = 12;
		List<Grade> mockGradeList = mock(List.class); 
		
		GradeDao mockDao = Mockito.spy(new GradeDao());
		
		Mockito.doReturn(mockGradeList).when(mockDao).checkGrade(studentId, courseName); 
		//when(mockDao.checkGrade(studentId, courseName)).thenReturn(mockGradeList);
		when(mockGradeList.get(0)).thenReturn(mockGrade);
		
		mockDao.updateGrade(studentId, courseName, newScore);


		verify(mockEmf).createEntityManager();
		verify(mockFactory).getGradeId();
		verify(mockDao).checkGrade(studentId, courseName).get(0);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockGrade).setScore(newScore);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	public void test_updateResetInformation() {

		String courseName = "Java";
		int studentId = 0;
		String resetState= "Resulted"; 
		int resetScore = 60;
		List<Grade> mockGradeList = mock(List.class); 
		
		GradeDao mockDao = Mockito.spy(new GradeDao());
		
		Mockito.doReturn(mockGradeList).when(mockDao).checkGrade(studentId, courseName); 
		//when(mockDao.checkGrade(studentId, courseName)).thenReturn(mockGradeList);
		when(mockGradeList.get(0)).thenReturn(mockGrade);
		
		mockDao.updateResetInformation(studentId, courseName, resetState, resetScore);
		
		verify(mockEmf).createEntityManager();
		verify(mockFactory).getGradeId();
		verify(mockDao).checkGrade(studentId, courseName).get(0);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockGrade).setResetState(resetState);
		verify(mockGrade).setResetScore(resetScore);
		verify(mockEt).commit();
		verify(mockEm).close();
	}



}
