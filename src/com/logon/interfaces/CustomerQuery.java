package com.logon.interfaces;

import java.util.List;

import org.hibernate.SessionFactory;

public interface CustomerQuery {
	public void createStudent(int id,String name,String lastName,String password,String email) ;
	public boolean deleteStudentByName(String name);
	public boolean deleteStudentByLastName(String lastName);
	public boolean deleteStudentByEmail(String email);
	public void updateSignatureId(String email);
	public String findSignatureId(String email);
	public boolean findByName(String name);
	public boolean findByLastName(String lastName);
	public boolean findByEmail(String email);
	public String findAndReturnMail(String email) ;
	public boolean updateStudentName(String email,String name);
	public boolean updateStudentLastName(String email, String lastName);
	public boolean updateStudentEmail(String email);
	public void viewAllStudents();
	public void displayStudents(List<Object> theStudents);
	public SessionFactory sessionFactory();
	public boolean findPasswordByEmail(String email, String password);
	
	public void findMeAStudent(String something);
	
	
}
