package com.fdmgroup.gradeManagementWeb.factories;

import com.fdmgroup.gradeManagementWeb.entities.GradeID;

public class GradeIdFactory {

	public synchronized GradeID getGradeId() {
		return new GradeID();
	}

}
