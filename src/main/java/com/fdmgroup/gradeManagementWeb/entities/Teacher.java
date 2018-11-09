package com.fdmgroup.gradeManagementWeb.origin;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Teacher_infomation")//---->use to rename
public class Teacher {
	
	@Id
	@GeneratedValue
	private int idNumber;
	private String firstName;
	private String lastName;
	private int password;
	@ManyToOne
	@JoinColumn(name = "Course_I_teach")
	private Course course;
	
	public Teacher() {
		super();
	}

	public Teacher(String firstName, String lastName, int password) {
		super();
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

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Teacher " + firstName + "." + lastName +"'s id number is "+idNumber;
	}
	
}
