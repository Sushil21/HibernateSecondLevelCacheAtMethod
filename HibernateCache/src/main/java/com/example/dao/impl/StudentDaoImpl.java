package com.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.StudentDao;
import com.example.model.Student;

@Component
public class StudentDaoImpl implements StudentDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Student getStudent(long id) {
		 
		try{
			 System.out.println("*********** session1*******");
			 Session session1=sessionFactory.openSession();
			 Student stud = session1.get(Student.class, id);
			// System.out.println("first query result student is::"+stud);
			 session1.close();
			 
//			 System.out.println("*********** session2*******");
//			 Session session2=sessionFactory.openSession();
//			 Student stud2 = session2.get(Student.class, id);
//			 System.out.println("second query result student is::"+stud2);
//			 session2.close();
			 
			 return stud;
		}catch(Exception e){
			System.out.println(e);
			 
		} 
		return null;
	}

}
