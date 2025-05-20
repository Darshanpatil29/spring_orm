package com.spring.orm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Service;
import com.spring.orm.entities.Student;

import org.hibernate.Transaction;

public class StudentDaoImpl implements StudentDao{
	
	private SessionFactory sessionFactory;
	
	@Override
	public int insert(Student student) {
		Session session=sessionFactory.openSession();
		Transaction tcxs=session.beginTransaction();
		session.persist(student);
		tcxs.commit();
		session.close();
		return student.getStudentId();
	}
	
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public StudentDaoImpl() {
		super();
	}
}
