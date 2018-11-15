package com.fdmgroup.gradeManangementWeb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.gradeManagementWeb.dao.CourseDao;
import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.dao.TeacherDao;
import com.fdmgroup.gradeManagementWeb.entities.Course;
import com.fdmgroup.gradeManagementWeb.entities.Teacher;



public class TeacherDaoTest {
	@Mock
	private EntityManagerFactory mockEmf;
	private EntityManager mockEm;
	private EntityTransaction mockEt;

	@InjectMocks
	private TeacherDao sDao = new TeacherDao();
	
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

		Teacher teacher = new Teacher("aa","bb","123");
		
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
	
		Teacher teacher = new Teacher("aa","bb","123");

		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		sDao.getPassword(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_updatePassword() {
	
		
		Teacher teacher =Mockito.spy( new Teacher("aa","bb","123"));

		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		sDao.updatePassword(1, "000");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEt).commit();
		verify(teacher).setPassword("000");
		verify(mockEm).close();		
	}

	@Test
	public void test_updateName() {
		
		Teacher teacher = Mockito.spy(new Teacher("aa","bb","123"));

		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
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
	public void test_update_status() {
		Teacher teacher = Mockito.spy(new Teacher("aa","bb","123"));

		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		sDao.updateState(1, "leaving");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(teacher).setStateOfTeacher("leaving");
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
	
	@Test
	public void test_searchByID() {
		Teacher teacher = new Teacher("aa","bb","123");

		when(mockEm.find(Teacher.class, 1)).thenReturn(teacher);
		
		sDao.searchTeacher(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Teacher.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_searchByFullName() {
		String firstName = "first";
		String lastName ="last";

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery("SELECT s FROM Teacher s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
					Teacher.class)).thenReturn(mockQuery);
		sDao.searchTeacher(firstName, lastName);
		verify(mockEm).createQuery("SELECT s FROM Teacher s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
				Teacher.class);
		verify(mockQuery).setParameter("firstName", firstName);
		verify(mockQuery).setParameter("lastName", lastName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_searchByFirstName() {
		String firstName = "first";
		String lastName =null;

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery("SELECT s FROM Teacher s WHERE s.firstName = :firstName ",Teacher.class)).thenReturn(mockQuery);
		sDao.searchTeacher(firstName, lastName);
		verify(mockEm).createQuery("SELECT s FROM Teacher s WHERE s.firstName = :firstName ", Teacher.class);
		verify(mockQuery).setParameter("firstName", firstName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_searchByLastName() {
		String firstName = null;
		String lastName ="last";

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery("SELECT s FROM Teacher s WHERE s.lastName = :lastName ",Teacher.class)).thenReturn(mockQuery);
		sDao.searchTeacher(firstName, lastName);
		verify(mockEm).createQuery("SELECT s FROM Teacher s WHERE s.lastName = :lastName ", Teacher.class);
		verify(mockQuery).setParameter("lastName", lastName);
		verify(mockQuery).getResultList();
	}
	
	
}
