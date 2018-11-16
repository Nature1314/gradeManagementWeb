package com.fdmgroup.gradeManagementWeb.controllers;

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
	
	public String goToLogin(Model model, SelectUser option ) {
		model.addAttribute("select_user", option);
		String loginPage = option.getStatus();
		return loginPage;
	}

	@RequestMapping(value = { "/", "/index" },method = RequestMethod.POST )
	public String loginUser(SelectUser option) {
		String user = option.getStatus();
		if (user.equals("studentLogin")) {
			return "studentLogin";
		} else if (user.equals("teacherLogin")) {
			return "teacherLogin";
		} else {
			return "adminLogin";
		}
	}
}
