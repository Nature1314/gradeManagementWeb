package com.fdmgroup.gradeManagementWeb.controllers;

import static org.junit.Assert.*;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.gradeManagementWeb.dao.AdministratorDao;
import com.fdmgroup.gradeManagementWeb.dao.CourseDao;
import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.dao.TeacherDao;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.other.PersonFactory;

public class LoginControllerTest {

	@Mock
	private Model mockModel;
	@Mock
	private PersonFactory mockPeople;

	@Mock
	private AdministratorDao aDao = new AdministratorDao();
	@Mock
	private TeacherDao tDao = new TeacherDao();
	@Mock
	private StudentDao sDao = new StudentDao();
	@InjectMocks
	private LoginController lc = new LoginController();

	@Before
	public void commonRunning() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_goToStudentLogin() {
		String expect = "studentLogin";
		String trueValue = lc.goToStudentLogin(mockModel, mockPeople);
		verify(mockModel).addAttribute("student_login_user", mockPeople.getStudent());
		assertEquals(expect, trueValue);
	}

	@Test
	public void test_goToTeacherLogin() {
		String expect = "teacherLogin";
		String trueValue = lc.goToTeacherLogin(mockModel, mockPeople);
		verify(mockModel).addAttribute("teacher_login_user", mockPeople.getTeacher());
		assertEquals(expect, trueValue);
	}

	@Test
	public void test_goToAdminLogin() {
		String expect = "adminLogin";
		String trueValue = lc.goToAdminLogin(mockModel, mockPeople);
		verify(mockModel).addAttribute("admin_login_user", mockPeople.getAdministrator());
		assertEquals(expect, trueValue);
	}

	@Test
	public void test_loginStudentUser() {
		int idNumber = 0;
		String expect = "student";
		String password = "123";
		Student student = new Student("a","b","123");
		
		String trueValue = lc.loginStudentUser(student);
		when(sDao.searchStudent(idNumber)).thenReturn(student);
		verify(student).getIdNumber();
		verify(sDao).searchStudent(idNumber);
		assertEquals(expect, trueValue);
	}
}
