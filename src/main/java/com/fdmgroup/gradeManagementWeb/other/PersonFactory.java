package com.fdmgroup.gradeManagementWeb.other;

import com.fdmgroup.gradeManagementWeb.entities.Administrator;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.entities.Teacher;

public class PersonFactory {

	public Student getStudent() {
		// TODO Auto-generated method stub
		return new Student();
	}

	public Teacher getTeacher() {
		// TODO Auto-generated method stub
		return new Teacher();
	}

	public Administrator getAdministrator() {
		// TODO Auto-generated method stub
		return new Administrator();
	}

}
