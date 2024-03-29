package com.vti.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;

public class AccountRepository {
	private HibernateUtils hibernateUtils;

	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

	@SuppressWarnings("unchecked")
//	Lấy danh sách tất cả các Account trên hệ thống.
	public List<Account> get_FROM() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// create hql query
			Query<Account> query = session.createQuery("FROM Account ");

			return query.list();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//Giới hạn điều kiện trả về với WHERE theo id
	public Account get_ByID(short id) {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// create hql query
			String hql = "FROM Account AS u WHERE u.id = :id";
			Query<Account> query = session.createQuery(hql, Account.class);
			query.setParameter("id", id);
			// get result
			Account account = query.uniqueResult();
			return account;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	 Giới hạn số lượng cột cần lấy sử dụng mệnh đề SELECT.
	public String get_FullName(short id) {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// create hql query, hàm này sẽ về các đối tượng kiểu String, không phải kiểu
			// Account như bên trên.
			String hql = "SELECT a.fullname FROM Account a WHERE a.id = :id";
			Query<String> query = session.createQuery(hql, String.class);
			query.setParameter("id", id);
			// get result
			String fullname = query.uniqueResult();
			return fullname;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	// Giới hạn điều kiện trả về với WHERE: Lấy ra tất cả các Account được tạo trong
	// tháng hiện tại.

	public List<Account> get_ByCurrentMonth() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// Account như bên trên.
			String hql = "FROM Account a WHERE month(a.createDate) = month(sysdate())";
			Query<Account> query = session.createQuery(hql, Account.class);
			// get result
			List<Account> accounts = query.list();
			return accounts;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Sắp xếp các kết quả theo bất kỳ thuộc tính nào trên các đối tượng trong tập kết quả tăng dần (ASC) hoặc giảm dần (DESC).
//	Lấy danh sách Account được tạo trong tháng hiện tại và sắp xếp theo ngảy tạo giảm dần hoặc tăng dần.
	public List<Account> get_ByCurrentMonthOderBy() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// Account như bên trên.
			String hql = "FROM Account a WHERE month(a.createDate) = month(sysdate()) ORDER BY a.createDate DESC";
			Query<Account> query = session.createQuery(hql, Account.class);
			// get result
			List<Account> accounts = query.list();
			return accounts;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

// Group By	
//	Đếm số Account được tạo trong mỗi tháng của năm hiện tại
//	SELECT month(createDate) AS month, COUNT(createDate) AS SL FROM Account 
//	WHERE year(createDate) = year(sysdate())
//	 GROUP BY month(createDate)	
	@SuppressWarnings("unchecked")
	public List<Object[]> get_CountAccByMonthInYearCurrent() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// Account như bên trên.
			String hql = "SELECT month(createDate) AS month, COUNT(createDate) AS SL FROM Account WHERE year(createDate) = year(sysdate()) GROUP BY month(createDate)";
//			Kết quả trả về là 1 mảng kiểu đổi tượng
			Query<Object[]> query = session.createQuery(hql);
			// get result: trả về 1 list mảng các đối tượng
			List<Object[]> results = query.list();
			return results;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Group BY / Having
//	Đếm số Account được tạo trong mỗi tháng của năm hiện tại với điều kiện tháng có ít nhất 2 Account được tạo.
//	SELECT month(createDate) AS month, COUNT(createDate) AS SL FROM Account 
//	WHERE year(createDate) = year(sysdate())
//	 GROUP BY month(createDate)	
//	HAVING COUNT(createDate) >2
	@SuppressWarnings("unchecked")
	public List<Object[]> get_CountAccByMonthInYearCurrentgt2() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// Account như bên trên.
			String hql = "SELECT month(createDate) AS month, COUNT(createDate) AS SL FROM Account WHERE year(createDate) = year(sysdate()) GROUP BY month(createDate) HAVING COUNT(createDate) >2";
//			Kết quả trả về là 1 mảng kiểu đổi tượng
			Query<Object[]> query = session.createQuery(hql);
			// get result: trả về 1 list mảng các đối tượng
			List<Object[]> results = query.list();
			return results;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Update theo trường bất kỳ.
	@SuppressWarnings({ "unused", "rawtypes" })
	public void updateEmailUsernameAccountByID(String newEmail, String newUsername, short id) {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();
			session.beginTransaction();

			// update
			String hql = "UPDATE Account SET email = :email, username = :username WHERE id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("email", newEmail);
			query.setParameter("username", newUsername);
			query.setParameter("id", id);
			int affectedRows = query.executeUpdate();

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Delete theo trường bất kỳ
	@SuppressWarnings({ "unused", "rawtypes" })
	public void deleteAccount(String email) {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();
			session.beginTransaction();

			// Delete
			String hql = "DELETE FROM Account WHERE email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			int affectedRows = query.executeUpdate();

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

//	Phân trang bằng hibernate
	@SuppressWarnings("unchecked")
	public List<Account> get_AccountByPaging() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();
			String hql = "FROM Account ORDER BY id";
			// create hql query
			Query<Account> query = session.createQuery(hql);
			query.setFirstResult(5);
			query.setMaxResults(3);
			return query.list();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}