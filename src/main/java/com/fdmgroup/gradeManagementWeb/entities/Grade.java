package com.fdmgroup.gradeManagementWeb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student_Grade")
public class Grade {

	@Id
	@GeneratedValue
	private int idNumber;
	private int studentID;
	private String studentCourseName;
	private int score;
	private String resetState = "No information";
	private Integer resetScore = null;


	public Grade(int studentID, String studentCourseName, int score) {
		super();
		this.studentID = studentID;
		this.studentCourseName = studentCourseName;
		this.score = score;
	}


	public Grade() {
		super();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public int getStudentID() {
		return studentID;
	}


	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}


	public String getStudentCourseName() {
		return studentCourseName;
	}


	public void setStudentCourseName(String studentCourseName) {
		this.studentCourseName = studentCourseName;
	}

	
	public String getResetState() {
		return resetState;
	}
	
	
	public void setResetState(String resetState) {
		this.resetState = resetState;
	}
	
	
	public Integer getResetScore() {
		return resetScore;
	}
	
	
	public void setResetScore(Integer resetScore) {
		this.resetScore = resetScore;
	}
	
}
