package com.spring.orm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.dao.StudentDaoImpl;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context=new ClassPathXmlApplicationContext("upgradeConfig.xml");
        StudentDao studentDao=context.getBean("studentDaoImpl", StudentDaoImpl.class);
        System.out.println(studentDao.insert(new Student(123,"Obreyn","Dorne")));
    }
}
