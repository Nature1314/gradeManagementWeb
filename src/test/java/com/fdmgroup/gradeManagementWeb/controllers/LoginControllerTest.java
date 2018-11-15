package com.fdmgroup.gradeManagementWeb.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.other.PersonFactory;

public class LoginControllerTest {

	private LoginController lc;
	private Model mockModel;
	private PersonFactory mockPeople;
	
	@Before
	public void commonRunning() {
		lc= new LoginController();
		mockModel = mock(Model.class);
		mockPeople = mock(PersonFactory.class);
	}
	
	@Test
	public void test_goToStudentLogin() {
		String expect="studentLogin";
		String trueValue =lc.goToStudentLogin(mockModel, mockPeople);
		verify(mockModel).addAttribute("student_login_user", mockPeople.getStudent());
		assertEquals(expect,trueValue);
	}
	
	@Test
	public void test_goToTeacherLogin() {
		String expect="teacherLogin";
		String trueValue =lc.goToTeacherLogin(mockModel, mockPeople);
		verify(mockModel).addAttribute("teacher_login_user", mockPeople.getTeacher());
		assertEquals(expect,trueValue);
	}
	
	@Test
	public void test_goToAdminLogin() {
		String expect="adminLogin";
		String trueValue =lc.goToAdminLogin(mockModel, mockPeople);
		verify(mockModel).addAttribute("admin_login_user", mockPeople.getAdministrator());
		assertEquals(expect,trueValue);
	}

	@Test
	public void test_loginStudentUser() {
		Student mockStudent = mock(Student.class);
		
	}
}
