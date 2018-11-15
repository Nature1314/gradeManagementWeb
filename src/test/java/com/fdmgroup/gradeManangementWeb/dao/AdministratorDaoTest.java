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
import org.mockito.MockitoAnnotations;

import com.fdmgroup.gradeManagementWeb.dao.AdministratorDao;
import com.fdmgroup.gradeManagementWeb.entities.Administrator;



public class AdministratorDaoTest {
	@Mock
	private EntityManagerFactory mockEmf;
	private EntityManager mockEm;
	private EntityTransaction mockEt;
	
	@InjectMocks
	private AdministratorDao aDao= new AdministratorDao();
	
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
		Administrator admin = new Administrator("aa","bb","123");
		
		aDao.addItem(admin);
		verify(mockEmf).createEntityManager();
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEm).persist(admin);
		verify(mockEt).commit();
		verify(mockEm).close();
	}
	
	@Test
	public void test_getPassword() {
		Administrator admin = new Administrator("aa","bb","123");
		
		when(mockEm.find(Administrator.class, 1)).thenReturn(admin);
		
		aDao.getPassword(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Administrator.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_updatePassword() {
		Administrator admin = new Administrator("aa","bb","123");
		
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Administrator.class, 1)).thenReturn(admin);

		aDao.updatePassword(1, "000");
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Administrator.class, 1);
		verify(mockEm).getTransaction();
		verify(mockEt).begin();
		verify(mockEt).commit();
		verify(mockEm).close();		
	}
	
	@Test
	public void test_searchByID() {
		Administrator admin = new Administrator("aa","bb","123");

		when(mockEm.find(Administrator.class, 1)).thenReturn(admin);
		
		aDao.searchAdmin(1);
		
		verify(mockEmf).createEntityManager();
		verify(mockEm).find(Administrator.class, 1);
		verify(mockEm).close();		
	}
	
	@Test
	public void test_searchByFullName() {
		String firstName = "first";
		String lastName ="last";
		String jdbc ="SELECT s FROM Administrator s WHERE s.firstName = :firstName AND s.lastName = :lastName ";
		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery(jdbc, Administrator.class)).thenReturn(mockQuery);
		aDao.searchAdmin(firstName, lastName);
		verify(mockEm).createQuery(jdbc, Administrator.class);
		verify(mockQuery).setParameter("firstName", firstName);
		verify(mockQuery).setParameter("lastName", lastName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_searchByFirstName() {
		String firstName = "first";
		String lastName =null;
		String jdbc = "SELECT s FROM Administrator s WHERE s.firstName = :firstName ";

		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery(jdbc, Administrator.class)).thenReturn(mockQuery);
		aDao.searchAdmin(firstName, lastName);
		verify(mockEm).createQuery(jdbc, Administrator.class);
		verify(mockQuery).setParameter("firstName", firstName);
		verify(mockQuery).getResultList();
	}
	
	@Test
	public void test_searchByLastName() {
		String firstName = null;
		String lastName ="last";
		String jdbc = "SELECT s FROM Administrator s WHERE s.lastName = :lastName ";
		TypedQuery mockQuery =  mock(TypedQuery.class);
		when(mockEm.createQuery(jdbc, Administrator.class)).thenReturn(mockQuery);
		aDao.searchAdmin(firstName, lastName);
		verify(mockEm).createQuery(jdbc, Administrator.class);
		verify(mockQuery).setParameter("lastName", lastName);
		verify(mockQuery).getResultList();
	}

}
