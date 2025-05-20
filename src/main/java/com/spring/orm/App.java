package com.spring.orm;

import java.awt.DisplayMode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.sql.SQLDataException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
import com.spring.orm.dao.StudentDao;
import com.spring.orm.dao.StudentDaoImpl;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLDataException
    {
        System.out.println( "Hello World!" );
        ApplicationContext context=new ClassPathXmlApplicationContext("upgradeConfig.xml");
        StudentDao studentDao=context.getBean("studentDaoImpl", StudentDaoImpl.class);
//        System.out.println(studentDao.insert(new Student(567,"Sansa stark","Winterfell")));
        Student stdStudent=studentDao.getStudents(345);
        System.out.println(stdStudent);
//        List<Student> resultList=studentDao.getStudentsByCityAndName("stark","Winterfell");
//        List<Student> resultList=studentDao.getAllStudents();
//        System.out.println(studentDao.update(new Student(345,"Jon Snow","Winterfell")));
        	System.out.println(studentDao.delete(234));
        	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1. Create Student Record");
            System.out.println("2. Display All Students");
            System.out.println("3. Display Student with ID");
            System.out.println("4. Display Student with name and city");
            System.out.println("5. Update Student Record");
            System.out.println("6. Delete Student Record");
           try {
			int choice=Integer.parseInt(br.readLine());
			switch (choice) {
			case 1:
				insertStudent();
				break;
			case 2:
				displayStudents();
				break;
			case 3:
				displayStudents();
				break;
			case 4:
				displayStudents();
				break;
			case 5:
				updateStudents();
				break;
			case 6:
				deleteStudents();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
//        System.out.println(resultList); 
    }
    
    private static void insertStudent() {
    	
    }
    
    private static void displayStudents() {
    	
    }
    
    private static void updateStudents() {
    	
    }
    
    private static void deleteStudents() {
    	
    }
}
