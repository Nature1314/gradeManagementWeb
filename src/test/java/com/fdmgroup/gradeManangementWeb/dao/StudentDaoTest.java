package com.fdmgroup.gradeManangementWeb.dao;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.entities.Teacher;



public class StudentDaoTest {
	
	@Mock
	private EntityManagerFactory mockEmf;
	private EntityManager mockEm;
	private EntityTransaction mockEt;
	
	@InjectMocks
	private StudentDao sDao = new StudentDao();
	
	@Before
	public void startInjectMocks() {
		MockitoAnnotations.initMocks(this);
		mockEm = mock(EntityManager.class);
		mockEt = mock(EntityTransaction.class);
		

		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
	}
	
	
	@Test
	public void test_addItem() {
		Student student = new Student("aa","bb","123",null);
		
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
		Student student = new Student("aa","bb","123",null);

		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
		sDao.getPassword(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Student.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_updatePassword() {
		Student student = new Student("aa","bb","123",null);
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Student.class, 1)).thenReturn(student);

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
		
		Student student = Mockito.spy(new Student("aa","bb","123",null));

		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
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
	
	@Test
	public void test_update_status() {
		Student student = Mockito.spy(new Student("aa","bb","123",null));

		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
		sDao.updateState(1, "leaving");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Student.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(student).setStateOfStudent("leaving");
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
	
	@Test
	public void test_searchByID() {
		Student student = new Student("aa","bb","123",null);

		when(mockEm.find(Student.class, 1)).thenReturn(student);
		
		sDao.searchStudent(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Student.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_searchByFullName() {
		String firstName = "first";
		String lastName ="last";

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
					Student.class)).thenReturn(mockQuery);
		sDao.searchStudent(firstName, lastName);
		verify(mockEm).createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.lastName = :lastName ", 
				Student.class);
		verify(mockQuery).setParameter("firstName", firstName);
		verify(mockQuery).setParameter("lastName", lastName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_searchByFirstName() {
		String firstName = "first";
		String lastName =null;

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName ",Student.class)).thenReturn(mockQuery);
		sDao.searchStudent(firstName, lastName);
		verify(mockEm).createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName ", Student.class);
		verify(mockQuery).setParameter("firstName", firstName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_searchByLastName() {
		String firstName = null;
		String lastName ="last";

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName ",Student.class)).thenReturn(mockQuery);
		sDao.searchStudent(firstName, lastName);
		verify(mockEm).createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName ", Student.class);
		verify(mockQuery).setParameter("lastName", lastName);
		verify(mockQuery).getResultList();
	}

}
