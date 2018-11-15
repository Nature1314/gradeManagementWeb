package com.fdmgroup.gradeManagementWeb.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.gradeManagementWeb.other.SelectUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IndexControllerTest {
	
	private IndexController ic;
	@Before
	public void commonRunning() {
		ic= new IndexController();
		
	}
	
	@Test
	public void test_goToIndex() {
		assertEquals("index",ic.goToIndex());
	}
	
	@Test
	public void test_goToLogin() {
		Model mockModel = mock(Model.class);
		String expect = "value";
		SelectUser mockOption = mock(SelectUser.class);
		when(mockOption.getStatus()).thenReturn(expect);
		
		String returnValue =ic.goToLogin(mockModel, mockOption);
		verify(mockModel).addAttribute("select_user", mockOption);
		verify(mockOption).getStatus();
		assertEquals(expect,returnValue);		
	}
	
	@Test
	public void test_loginUser_student() {
		String user = "student";
		String expect = "studentLogin";
		SelectUser mockOption = mock(SelectUser.class);
		when(mockOption.getStatus()).thenReturn(user);
		
		String returnValue = ic.loginUser(mockOption);
		verify(mockOption).getStatus();
		assertEquals(expect,returnValue);		
	}
	
	@Test
	public void test_loginUser_teacher() {
		String user = "teacher";
		String expect = "teacherLogin";
		SelectUser mockOption = mock(SelectUser.class);
		when(mockOption.getStatus()).thenReturn(user);
		
		String returnValue = ic.loginUser(mockOption);
		verify(mockOption).getStatus();
		assertEquals(expect,returnValue);		
	}
	
	@Test
	public void test_loginUser_admin() {
		String user = "admin";
		String expect = "adminLogin";
		SelectUser mockOption = mock(SelectUser.class);
		when(mockOption.getStatus()).thenReturn(user);
		
		String returnValue = ic.loginUser(mockOption);
		verify(mockOption).getStatus();
		assertEquals(expect,returnValue);		
	}

}
