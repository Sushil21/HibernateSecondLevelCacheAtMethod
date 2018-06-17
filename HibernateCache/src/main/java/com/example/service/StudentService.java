package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDao;
import com.example.model.Student;

@Service
public class StudentService {
	
	@Autowired
	StudentDao studentDao;
	
	@Cacheable(value="students",key="#id")
	public Student getStudentData(long id) {
		Student stud = studentDao.getStudent(id);
		return stud;
	}
}
