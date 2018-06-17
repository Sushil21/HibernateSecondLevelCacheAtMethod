package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.StudentDao;
import com.example.model.Student;
import com.example.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value="/getStudent/{id}",method=RequestMethod.GET)
	public @ResponseBody String getStudent(@PathVariable long id){
		System.out.println("inside");
		Student stud = studentService.getStudentData(id);
		System.out.println(stud);
		return stud.getStudentName();
	 
}
}
