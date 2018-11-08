package com.fdmgroup.gradeManagementWeb.factories;

import com.fdmgroup.gradeManagementWeb.origin.GradeID;

public class GradeIdFactory {

	public synchronized GradeID getGradeId() {
		return new GradeID();
	}

}
