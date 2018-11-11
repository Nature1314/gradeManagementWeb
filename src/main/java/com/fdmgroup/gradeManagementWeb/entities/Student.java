package com.fdmgroup.gradeManagementWeb.entities;

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
	private String password;
	private String stateOfStudent = "active";

	@ManyToMany
	@JoinTable(name = "Courses_of_student",
			    joinColumns=@JoinColumn(name="student_id", referencedColumnName="idNumber"),
			    inverseJoinColumns=@JoinColumn(name="Course_id", referencedColumnName="courseIdNumber")
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
	
	public String getStateOfStudent() {
		return stateOfStudent;
	}
	
	public void setStateOfStudent(String stateOfStudent) {
		this.stateOfStudent = stateOfStudent;
	}
	

	public Student(String firstName, String lastName, String password, List<Course> courses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.courses = courses;
	}
	
	public Student(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student " + firstName + "." + lastName +"'s id number is "+idNumber;
	}
	
	
	
}
