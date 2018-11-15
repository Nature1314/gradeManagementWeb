package com.fdmgroup.gradeManagementWeb.controllers;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.gradeManagementWeb.dao.AdministratorDao;
import com.fdmgroup.gradeManagementWeb.dao.StudentDao;
import com.fdmgroup.gradeManagementWeb.dao.TeacherDao;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.entities.Administrator;
import com.fdmgroup.gradeManagementWeb.entities.PersonFactory;
import com.fdmgroup.gradeManagementWeb.entities.Teacher;



@Controller
public class LoginController {
	
	@Resource(name="studentBean")
	private StudentDao sDao;
	@Resource(name="teacherBean")
	private TeacherDao tDao;
	@Resource(name="adminBean")
	private AdministratorDao aDao;
	
	
	@RequestMapping(value = { "/studentlogin", "/studentLogin" }, method = RequestMethod.GET)
	public String goToStudentLogin(Model model, PersonFactory factory) {

		model.addAttribute("student_login_user", factory.getStudent());
		return "studentLogin";
	}
	
	@RequestMapping(value = { "/studentlogin", "/studentLogin" },method = RequestMethod.POST )
	public String loginStudentUser(Student student) {
		int idNumber =student.getIdNumber();
		if (sDao.searchStudent(idNumber)!=null) {
			return "student";
		}else {
			return "block";
		}
	}
	
	@RequestMapping(value = { "/teacherlogin", "/teacherLogin" }, method = RequestMethod.GET)
	public String goToTeacherLogin(Model model, PersonFactory factory) {

		model.addAttribute("teacher_login_user", factory.getTeacher());
		return "teacherLogin";
	}
	
	@RequestMapping(value = { "/teacherlogin", "/teacherLogin" },method = RequestMethod.POST )
	public String loginTeacherUser(Teacher teacher) {
		int idNumber =teacher.getIdNumber();
		if (tDao.searchTeacher(idNumber)!=null) {
			return "teacher";
		}else {
			return "block";
		}
	}
	
	@RequestMapping(value = {"/adminlogin", "/adminLogin" }, method = RequestMethod.GET)
	public String goToAdminLogin(Model model, PersonFactory factory) {

		model.addAttribute("admin_login_user", factory.getAdministrator());
		return "adminLogin";
	}
	
	@RequestMapping(value = { "/adminlogin", "/adminLogin" },method = RequestMethod.POST )
	public String loginAdminUser(Administrator admin) {
		int idNumber =admin.getIdNumber();
		if (aDao.searchAdmin(idNumber)!=null) {
			return "administrator";
		}else {
			return "block";
		}
	}
	

}
