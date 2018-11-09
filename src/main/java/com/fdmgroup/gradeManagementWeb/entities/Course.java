package com.fdmgroup.gradeManagementWeb.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course_infomation") // ---->use to rename
public class Course {

	@Id
	@GeneratedValue
	private int courseIdNumber;
	@Column(unique=true)
	private String NameOfCourse;
	@ManyToMany(mappedBy = "courses")
	private List<Student> students;

	public Course(String nameOfCourse) {
		super();
		this.NameOfCourse = nameOfCourse;
	}

	public Course() {
		super();
	}

	public String getNameOfCourse() {
		return NameOfCourse;
	}

	public int getIdNumber() {
		return courseIdNumber;
	}

	public void setIdNumber(int idNumber) {
		this.courseIdNumber = idNumber;
	}

	public void setNameOfCourse(String nameOfCourse) {
		this.NameOfCourse = nameOfCourse;
	}

}
