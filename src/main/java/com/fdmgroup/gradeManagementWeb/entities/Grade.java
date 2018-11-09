package com.fdmgroup.gradeManagementWeb.origin;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Student_Grade")
public class Grade {

	@EmbeddedId
	private GradeID id;
	private int score;

	public Grade(Student student, Course course, int score) {
		GradeID newGrade = new GradeID(course, student);
		this.id = newGrade;
		this.score = score;
	}
	
	public Grade(GradeID id, int score) {
		super();
		this.id = id;
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
	
	public GradeID getId() {
		return id;
	}

	public void setId(GradeID id) {
		this.id = id;
	}

}
