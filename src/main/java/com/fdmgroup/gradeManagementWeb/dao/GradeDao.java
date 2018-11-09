package com.fdmgroup.gradeManagementWeb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.gradeManagementWeb.entities.Course;
import com.fdmgroup.gradeManagementWeb.entities.Grade;
import com.fdmgroup.gradeManagementWeb.entities.GradeID;
import com.fdmgroup.gradeManagementWeb.entities.Student;
import com.fdmgroup.gradeManagementWeb.factories.GradeIdFactory;

public class GradeDao {

	private EntityManagerFactory emf;

	public GradeDao(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	public void addItem(Grade grade) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(grade);
		et.commit();
		em.close();
	}

	public void addItem(int studentID, String courseName, int score, GradeIdFactory gradeIdFactory) {
		EntityManager em = emf.createEntityManager();
		GradeID gradeID = gradeIdFactory.getGradeId();
		Course course = em.find(Course.class, courseName);
		Student student = em.find(Student.class, studentID);
		gradeID.setCourse(course);
		gradeID.setStudent(student);

		Grade grade = em.find(Grade.class, gradeID);

		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(grade);
		et.commit();
		em.close();
	}

	public void updateGrade(int studentID, String courseName, int newScore, GradeIdFactory gradeIdFactory) {
		EntityManager em = emf.createEntityManager();
		GradeID gradeID = gradeIdFactory.getGradeId();
		Course course = em.find(Course.class, courseName);
		Student student = em.find(Student.class, studentID);
		gradeID.setCourse(course);
		gradeID.setStudent(student);

		Grade grade = em.find(Grade.class, gradeID);
		EntityTransaction et = em.getTransaction();
		et.begin();
		grade.setScore(newScore);
		et.commit();
		em.close();
	}

	public void removeEntry(int studentID, String courseName, GradeIdFactory gradeIdFactory) {
		EntityManager em = emf.createEntityManager();
		GradeID gradeID = gradeIdFactory.getGradeId();
		Course course = em.find(Course.class, courseName);
		Student student = em.find(Student.class, studentID);
		gradeID.setCourse(course);
		gradeID.setStudent(student);

		Grade grade = em.find(Grade.class, gradeID);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(grade);
		et.commit();
		em.close();
	}

	public List<Grade> checkGrade(int studentID, String courseName) {

		EntityManager em = emf.createEntityManager();
		Query query = null;
		if (courseName != null) {
			query = em.createQuery(
					"SELECT s FROM Grade s WHERE s.id.student_ID = :studentID AND s.id.course_name = :courseName ",
					Grade.class);
			query.setParameter("studentID", studentID);
			query.setParameter("courseName", courseName);
		} else if (courseName == null) {
			query = em.createQuery("SELECT s FROM Grade s WHERE s.id.student_ID = :studentID", Grade.class);
			query.setParameter("studentID", studentID);
		}

		List<Grade> grade = query.getResultList();
		return grade;
	}
}
