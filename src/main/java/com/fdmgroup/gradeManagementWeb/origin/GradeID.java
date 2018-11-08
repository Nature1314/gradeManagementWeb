package com.fdmgroup.gradeManagementWeb.origin;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GradeID {
	@ManyToOne
	@JoinColumn(name = "course_name")
	private Course course;
	@ManyToOne
	@JoinColumn(name = "student_ID")
	private Student student;

	
	
	
	public GradeID() {
		super();
	}


	public GradeID(Course course, Student student) {
		this.course = course;
		this.student = student;
	}
	
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	 
}