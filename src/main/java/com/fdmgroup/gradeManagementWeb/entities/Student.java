package com.fdmgroup.gradeManagementWeb.origin;

import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student_infomation")//---->use to rename
public class Student {
	@Id
	@GeneratedValue
	private int idNumber;
	private String firstName;
	private String lastName;
	private int password;
	@ManyToMany
	@JoinTable(name = "Courses_I_have",
			    joinColumns=@JoinColumn(name="Courses_the_student_have", referencedColumnName="course_name"),
			    inverseJoinColumns=@JoinColumn(name="Course_name", referencedColumnName="course_name")
			)
	private List<Course> courses;
	
	

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Student() {
		
	}
	
	

	public Student(String firstName, String lastName, int password, List<Course> courses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.courses = courses;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student " + firstName + "." + lastName +"'s id number is "+idNumber;
	}
	
	
	
}
