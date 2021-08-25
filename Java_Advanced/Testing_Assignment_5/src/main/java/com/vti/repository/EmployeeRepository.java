package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Employee;
import com.vti.utils.HibernateUtils;

public class EmployeeRepository {
	private HibernateUtils hibernateUtils;
	
	public EmployeeRepository() {
		// TODO Auto-generated constructor stub
		hibernateUtils =  HibernateUtils.getInstance();
	}
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees(){
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			
			Query<Employee> query = session.createQuery("FROM Employee");
			
			return query.list();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public void createEmployee(Employee Employee) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			session.beginTransaction();
			
			session.save(Employee);
			session.getTransaction().commit();
		} finally {
			if (session !=  null) {
				session.close();
			}
		}
	}
	
	
	
}