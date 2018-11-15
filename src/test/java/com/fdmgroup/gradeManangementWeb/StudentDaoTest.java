package com.fdmgroup.gradeManangementWeb;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.entities.Student;



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

}
