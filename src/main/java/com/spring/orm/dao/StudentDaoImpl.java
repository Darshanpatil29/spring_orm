package com.spring.orm.dao;

import java.sql.SQLDataException;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring.orm.entities.Student;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

import org.hibernate.Transaction;
@EnableTransactionManagement(proxyTargetClass = true)
@Component
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public int insert(Student student) {
		Session session=sessionFactory.openSession();
		Transaction tcxs=session.beginTransaction();
		session.persist(student);
		tcxs.commit();
		session.close();
		return student.getStudentId();
	}
	
	@Override
	@Transactional()// spring manages session open/close
	public Student getStudents(int studentId) {
//		Session session=sessionFactory.openSession();
//		Transaction tcxs=session.beginTransaction();
		Session session=sessionFactory.getCurrentSession();
		Student student=session.get(Student.class,studentId);
//		tcxs.commit();
//		session.close();
		return student;
	}
	

	@Override
	public List<Student> getStudentsByCityAndName(String name, String city) {
		Session session=sessionFactory.openSession();
		try {
			if(!name.isBlank()&& !city.isBlank()) {
				String nameString="%"+name+"%";
				String hqlString="From Student s where s.studentCity =: city AND s.studentName LIKE :name";
				return session.createQuery(hqlString,Student.class)
						.setParameter("city", city)
						.setParameter("name",nameString)
						.getResultList();
			}
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("FROM Student",Student.class).getResultList();
	}

	@Override
	@Transactional
	public Boolean delete(int studentId) throws SQLDataException{
		// TODO Auto-generated method stub
		try {
			Student std=getStudents(studentId);
//			sessionFactory.getCurrentSession().createQuery("Delete From Student s where s.studentId=:id").setParameter("id", studentId).executeUpdate();
			sessionFactory.getCurrentSession().remove(std);
			return true;
		} catch (Exception e) {
			// TODO: handle 
			throw new SQLDataException("Deletion failed "+e.getMessage());
		}
	}

	@Override
	@Transactional
	public Boolean update(Student student) throws SQLDataException{
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().merge(student);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLDataException("Update Failed "+e.getMessage());
		}
	}
	
	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}	
	
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	public StudentDaoImpl() {
		super();
	}
}
