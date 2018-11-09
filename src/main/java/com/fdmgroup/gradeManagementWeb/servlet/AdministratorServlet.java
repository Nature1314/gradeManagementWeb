package com.fdmgroup.gradeManagementWeb.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.dao.TeacherDao;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.entities.Teacher;

public class AdministratorServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd =req.getRequestDispatcher("administrator.jsp");
		rd.forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String state1 = req.getParameter("status1");
		String firstName1 = req.getParameter("firstName1");
		String lastName1 = req.getParameter("lastName1");
		String password = req.getParameter("password");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grade_jpa");
		
		
		RequestDispatcher rd = null;
		if(state1.equals("student")) {
			Student student = new Student(firstName1,lastName1,password);
			StudentDao sDao = new StudentDao(emf);
			sDao.addItem(student);
			
		}else if(state1.equals("teacher")){
			Teacher teacher = new Teacher(firstName1,lastName1,password);
			TeacherDao sDao = new TeacherDao(emf);
			sDao.addItem(teacher);
			
		}
		
		String state2 = req.getParameter("status2");
		String firstName2 = req.getParameter("firstName2");
		String lastName2 = req.getParameter("lastName2");
		
		
		if(state2.equals("student")) {
			StudentDao sDao = new StudentDao(emf);
			List<Student>student = sDao.searchStudent(firstName2, lastName2);
			
			
		}else if(state2.equals("teacher")){
			TeacherDao sDao = new TeacherDao(emf);
			List<Teacher> teaacher = sDao.searchTeacher(firstName2, lastName2);	
		}

		rd.forward(req, resp);
	}
}
