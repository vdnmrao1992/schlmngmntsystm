package com.imaginnovate.schoolmngmntsytm.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imaginnovate.schoolmngmntsytm.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{


}
