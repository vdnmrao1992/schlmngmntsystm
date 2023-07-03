package com.imaginnovate.schoolmngmntsytm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.schoolmngmntsytm.model.Student;
import com.imaginnovate.schoolmngmntsytm.service.StudentService;

@RestController
@RequestMapping("studentmarks")
public class MarksCalculator {
	
	@Autowired
	StudentService stuServ;
	
	@RequestMapping(method = RequestMethod.POST, path = "/newStudentDetails")
	public ResponseEntity<String> saveStudent(@Valid @RequestBody Student stu) {	
		if(stuServ.studentDetailsValidation(stu)) {
			int stuId = stuServ.addStudent(stu);
			System.out.println("New Student created with Student Id : "+stuId);
			return ResponseEntity.ok("New Student Created with ID :" + stuId);
		}
		else {
			return ResponseEntity.ok("Student Details Validation Failed");
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/updateStudentMarks")
	public ResponseEntity<String> updateStudent(@Valid @RequestBody Student stu) {	
		if(stuServ.studentDetailsValidation(stu)) {
			int stuId = stuServ.updateStudent(stu);
			if(stuId == 0) {
				System.out.println("Student Marks Not Updated : Student doesn't Exist");
				return ResponseEntity.ok("Student Marks Updated : Student doesn't Exist");
			}
			else {
				System.out.println("Student Marks Updated : "+stuId);
				return ResponseEntity.ok("Student Marks Updated :" + stuId);
			}
		}
		else {
			return ResponseEntity.ok("Student Details Validation Failed");
		}
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put("error", "Validation Failed");
	    responseBody.put("details", ex.getBindingResult().getAllErrors());
	    return ResponseEntity.badRequest().body(responseBody);
	}
}
