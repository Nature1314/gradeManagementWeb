package com.fdmgroup.gradeManagementWeb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.gradeManagementWeb.other.SelectUser;


@Controller
public class IndexController {

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String goToIndex() {
		return "index";
	}
	
	@RequestMapping(value = {"/", "/index"  }, method = RequestMethod.GET)
	public String goToLogin(Model model, SelectUser option ) {
		model.addAttribute("select_user", option);
		String loginPage = option.getStatus();
		return loginPage;
	}
	
	@RequestMapping(value = { "/", "/index" },method = RequestMethod.POST )
	public String loginUser(SelectUser option) {
		String user = option.getStatus();
		if (user.equals("student")) {
			return "studentLogin";
		} else if (user.equals("teacher")) {
			return "teacherLogin";
		} else {
			return "adminLogin";
		}
	}
}
