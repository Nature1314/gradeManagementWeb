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
import com.fdmgroup.gradeManagementWeb.entities.Teacher;



@Controller
public class LoginController {
	
	@Resource(name="studentBean")
	private StudentDao sDao;
	@Resource(name="teacherBean")
	private TeacherDao tDao;
	@Resource(name="adminBean")
	private AdministratorDao aDao;
	
	@RequestMapping(value = { "/login", "/Login" }, method = RequestMethod.GET)
	public String goToStudentLogin(Model model) {

		model.addAttribute("blank_login_user", new Student());
		return "login";
	}
	
	@RequestMapping(value = { "/login", "/Login" },method = RequestMethod.POST )
	public String loginStudentUser(Student student) {
		int idNumber =student.getIdNumber();
		if (sDao.searchStudent(idNumber)!=null) {
			return "home";
		}else {
			return "block";
		}
	}
	

}
