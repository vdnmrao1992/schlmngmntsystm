package com.imaginnovate.schoolmngmntsytm.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.schoolmngmntsytm.model.Student;
import com.imaginnovate.schoolmngmntsytm.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepo repo;
	
	@Override
	public int addStudent(Student stu) {
		// TODO Auto-generated method stub
		stu.setTotal(stu.getMarks1() +stu.getMarks2()+stu.getMarks3());
		stu.setAverage(stu.getTotal()/3);
		if(stu.getMarks1()<35) {
			stu.setResult("Failed");
		}
		else if(stu.getMarks2()<35) {
			stu.setResult("Failed");
		}
		else if(stu.getMarks3()<35) {
			stu.setResult("Failed");
		}
		else {
			stu.setResult("Passed");
		}
		
		return repo.save(stu).getId();
		
	}

	@Override
	public int updateStudent(Student updateStu) {
		// TODO Auto-generated method stub
		
		Optional<Student> optStu = repo.findById(updateStu.getId());
		if(optStu.isPresent()) {
			Student stu = optStu.get();
			stu.setTotal(stu.getMarks1() +stu.getMarks2()+stu.getMarks3());
			stu.setAverage(stu.getTotal()/3);
			if(stu.getMarks1()<35) {
				stu.setResult("Failed");
			}
			else if(stu.getMarks2()<35) {
				stu.setResult("Failed");
			}
			else if(stu.getMarks3()<35) {
				stu.setResult("Failed");
			}
			else {
				stu.setResult("Passed");
			}
			
			return repo.save(stu).getId();
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean studentDetailsValidation(Student stu) {
		// TODO Auto-generated method stub
		
		if(stu.getFirstName().trim().length() >=3 
				&& stu.getLastName().trim().length() >=3
				&& (calculateAge(stu.getDob()) >= 15 && calculateAge(stu.getDob()) <= 20)  
				&& (stu.getMarks1() >=0 && stu.getMarks1()<=100)
				&& (stu.getMarks2() >=0 && stu.getMarks2()<=100)
				&& (stu.getMarks3() >=0 && stu.getMarks3()<=100)
				&& (stu.getSecton().equalsIgnoreCase("A") || stu.getSecton().equalsIgnoreCase("B") || stu.getSecton().equalsIgnoreCase("C")) 
				&& (stu.getGender().equalsIgnoreCase("M") || stu.getGender().equalsIgnoreCase("F"))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int calculateAge(Date dob) {
		
		LocalDate curDate = LocalDate.now();
		if ((dob != null) && (curDate != null))   
		{  
			return Period.between(dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), curDate).getYears();  
		}  
		else  
		{  
			return 0;  
		}  
		
	}
	
	

}
