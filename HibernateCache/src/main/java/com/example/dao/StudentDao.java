package com.example.dao;

import org.springframework.stereotype.Component;

import com.example.model.Student;

@Component
public interface StudentDao {
	Student getStudent(long id);
}
