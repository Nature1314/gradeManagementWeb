package com.fdmgroup.gradeManagementWeb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String goToIndex() {
		return "index";
	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.POST)
	public String goToLoginPage(HttpServletRequest req) {
		String type = req.getParameter("status");
		System.out.println(type);
		if (type.equals("student")) {
			return "studentLogin";
		} else if (type.equals("teacher")) {
			return "teacherLogin";
		} else {
			return "adminLogin";
		}
	}
}
