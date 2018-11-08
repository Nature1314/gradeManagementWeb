package com.fdmgroup.gradeManagementWeb.origin;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course_infomation")//---->use to rename
public class Course {
	
	@Id
	private String NameOfCourse;
	@ManyToMany(mappedBy="courses")
	private List<Student> students;
	
	public Course(String nameOfCourse) {
		super();
		NameOfCourse = nameOfCourse;
	}

	public Course() {
		super();
	}

	public String getNameOfCourse() {
		return NameOfCourse;
	}

	public void setNameOfCourse(String nameOfCourse) {
		NameOfCourse = nameOfCourse;
	}
	
	
	

}
