package com.fdmgroup.gradeManangementWeb;

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

import com.fdmgroup.gradeManagementWeb.dao.GradeDao;
import com.fdmgroup.gradeManagementWeb.entities.Course;
import com.fdmgroup.gradeManagementWeb.entities.Grade;
import com.fdmgroup.gradeManagementWeb.entities.GradeID;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.factories.GradeIdFactory;

public class GradeDaoTest {

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
	public void test_addItem_when_param_is_Grade() {
		Grade mockGrade = mock(Grade.class);

		GradeDao sDao = new GradeDao(mockEmf);
		sDao.addItem(mockGrade);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockGrade);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void test_addItem_using_studentID_And_CourseName() {
		int studentID = 0;
		String courseName = "Java";
		int score = 12;

		GradeIdFactory mockFactory = mock(GradeIdFactory.class);
		GradeID mockGradeID = mock(GradeID.class);
		Student mockStudent = mock(Student.class);
		Course mockCourse = mock(Course.class);
		Grade mockGrade = mock(Grade.class);

		when(mockFactory.getGradeId()).thenReturn(mockGradeID);
		when(mockEm.find(Course.class, courseName)).thenReturn(mockCourse);
		when(mockEm.find(Student.class, studentID)).thenReturn(mockStudent);
		when(mockEm.find(Grade.class, mockGradeID)).thenReturn(mockGrade);

		GradeDao sDao = new GradeDao(mockEmf);
		sDao.addItem(studentID, courseName, score, mockFactory);

		verify(mockEm).find(Student.class, studentID);
		verify(mockEm).find(Course.class, courseName);
		verify(mockEmf).createEntityManager();
		verify(mockFactory).getGradeId();
		verify(mockGradeID).setCourse(mockCourse);
		verify(mockGradeID).setStudent(mockStudent);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(mockGrade);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void test_updateGrade() {

		String courseName = "Java";
		int studentId = 0;
		int newScore = 12;

		GradeIdFactory mockFactory = mock(GradeIdFactory.class);
		GradeID mockGradeID = mock(GradeID.class);
		Student mockStudent = mock(Student.class);
		Course mockCourse = mock(Course.class);
		Grade mockGrade = mock(Grade.class);

		when(mockFactory.getGradeId()).thenReturn(mockGradeID);
		when(mockEm.find(Course.class, courseName)).thenReturn(mockCourse);
		when(mockEm.find(Student.class, studentId)).thenReturn(mockStudent);
		when(mockEm.find(Grade.class, mockGradeID)).thenReturn(mockGrade);

		GradeDao sDao = new GradeDao(mockEmf);
		sDao.updateGrade(studentId, courseName, newScore, mockFactory);

		verify(mockEm).find(Course.class, courseName);
		verify(mockEm).find(Student.class, studentId);
		verify(mockEmf).createEntityManager();
		verify(mockFactory).getGradeId();
		verify(mockGradeID).setCourse(mockCourse);
		verify(mockGradeID).setStudent(mockStudent);
		verify(mockEm).find(Grade.class, mockGradeID);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockGrade).setScore(newScore);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void test_removeEntry() {
		System.out.println("The function tests removeEntry method");
		String courseName = "Java";
		int studentId = 0;

		GradeIdFactory mockFactory = mock(GradeIdFactory.class);
		GradeID mockGradeID = mock(GradeID.class);
		Student mockStudent = mock(Student.class);
		Course mockCourse = mock(Course.class);
		Grade mockGrade = mock(Grade.class);

		when(mockFactory.getGradeId()).thenReturn(mockGradeID);
		when(mockEm.find(Course.class, courseName)).thenReturn(mockCourse);
		when(mockEm.find(Student.class, studentId)).thenReturn(mockStudent);
		when(mockEm.find(Grade.class, mockGradeID)).thenReturn(mockGrade);

		GradeDao sDao = new GradeDao(mockEmf);
		sDao.removeEntry(studentId, courseName, mockFactory);

		verify(mockEm).find(Course.class, courseName);
		verify(mockEm).find(Student.class, studentId);
		verify(mockEmf).createEntityManager();
		verify(mockFactory).getGradeId();
		verify(mockGradeID).setCourse(mockCourse);
		verify(mockGradeID).setStudent(mockStudent);
		verify(mockEm).find(Grade.class, mockGradeID);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).remove(mockGrade);
		verify(mockEt).commit();
		verify(mockEm).close();
	}

	@Test
	public void test_checkGrade_courseNameIsNotNull() {

		String jpql = "SELECT s FROM Grade s WHERE s.id.student_ID = :studentID AND s.id.course_name = :courseName ";
		int studentID = 1;
		String courseName = "Java";
		List<Grade> testlist = new ArrayList<Grade>();

		TypedQuery<Grade> mockQuery = mock(TypedQuery.class);

		when(mockEm.createQuery(jpql, Grade.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(testlist);
		GradeDao sDao = new GradeDao(mockEmf);
		sDao.checkGrade(studentID, courseName);

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
		List<Grade> testlist = new ArrayList<Grade>();

		TypedQuery<Grade> mockQuery = mock(TypedQuery.class);

		when(mockEm.createQuery(jpql, Grade.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(testlist);
		GradeDao sDao = new GradeDao(mockEmf);
		sDao.checkGrade(studentID, courseName);

		verify(mockEm).createQuery(jpql, Grade.class);
		verify(mockQuery).setParameter("studentID", studentID);
		verify(mockQuery).getResultList();
	}

}
