package com.fdmgroup.gradeManagementWeb.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fdmgroup.gradeManagementWeb.entities.Grade;
import com.fdmgroup.gradeManagementWeb.factories.GradeIdFactory;

public class GradeDao {

	@Resource(name ="emfBean")
	private EntityManagerFactory emf;

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
		Grade grade = new Grade(studentID, courseName, score);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(grade);
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

	public void updateGrade(int studentID, String courseName, int newScore, GradeIdFactory gradeIdFactory) {
		EntityManager em = emf.createEntityManager();
		Grade grade = checkGrade(studentID, courseName).get(0);
		EntityTransaction et = em.getTransaction();
		et.begin();
		grade.setScore(newScore);
		et.commit();
		em.close();
	}
	
	public void updateResetInformation(int studentID, String courseName, String resetState, int resetScore, GradeIdFactory gradeIdFactory) {
		EntityManager em = emf.createEntityManager();
		Grade grade = checkGrade(studentID, courseName).get(0);
		EntityTransaction et = em.getTransaction();
		et.begin();
		grade.setResetState(resetState);
		grade.setResetScore(resetScore);
		et.commit();
		em.close();
	}


}
