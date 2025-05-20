package com.spring.orm.dao;

import java.sql.SQLDataException;
import java.util.List;

import com.spring.orm.entities.Student;

public interface StudentDao {
	public int insert(Student student);
	public Student getStudents(int id);
	public List<Student> getStudentsByCityAndName(String name, String city);
	public List<Student> getAllStudents();
	public Boolean delete(int id) throws SQLDataException;
	public Boolean update(Student student) throws SQLDataException;
}
