package com.imaginnovate.schoolmngmntsytm.service;

import com.imaginnovate.schoolmngmntsytm.model.Student;

public interface StudentService {
	
	public int addStudent(Student emp);
	
	public int updateStudent(Student emp);
	
	public boolean studentDetailsValidation(Student stu);
	 

}
