package com.vti.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.vti.entity.Account;
import com.vti.utils.HibernateUtils;

public class AccountRepository {
	private HibernateUtils hibernateUtils;

	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

//	Lấy danh sách tất cả các Account trên hệ thống.
	public List<Account> get_FROM() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			// create Criteria query
			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM Account
			query.select(root); // SELECT
			List<Account> listAccounts = session.createQuery(query).list();

			return listAccounts;

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

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM Account
			query.select(root); // SELECT
			query.where(builder.equal(root.get("id"), id)); // WHERE id = id
			Account account = session.createQuery(query).uniqueResult();

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
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String> query = builder.createQuery(String.class);
			Root<Account> root = query.from(Account.class); // FROM
			query.multiselect(root.get("fullname")); // SELECT fullname
			query.where(builder.equal(root.get("id"), id));
			String fullname = session.createQuery(query).uniqueResult();

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

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM
			query.select(root);
//			// Create expressions that extract date parts:
//			  Expression<Integer> year = cb.function("year", Integer.class, date);
//			  Expression<Integer> month = cb.function("month", Integer.class, date);
//			  Expression<Integer> day = cb.function("day", Integer.class, ts);
//
//			  // Create expressions that extract time parts:
//			  Expression<Integer> hour = cb.function("hour", Integer.class, time);
//			  Expression<Integer> minute = cb.function("minute", Integer.class, time);
//			  Expression<Integer> second = cb.function("second", Integer.class, ts); 

			Expression<Integer> monthCreateDate = builder.function("month", Integer.class, root.get("createDate"));
			Expression<Integer> monthCurent = builder.function("month", Integer.class, builder.currentDate());
//			query.where(builder.equal(root.get("createDate"), builder.currentDate()));
			query.where(builder.equal(monthCreateDate, monthCurent));
			List<Account> accounts = session.createQuery(query).list();
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
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM
			query.select(root);
			Expression<Integer> monthCreateDate = builder.function("month", Integer.class, root.get("createDate"));
			Expression<Integer> monthCurent = builder.function("month", Integer.class, builder.currentDate());
			query.where(builder.equal(monthCreateDate, monthCurent));
			query.orderBy(builder.desc(root.get("createDate")));
			List<Account> accounts = session.createQuery(query).list();
			return accounts;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Update theo trường bất kỳ.
	public void updateEmailUsernameAccountByID(String newEmail, String newUsername, short id) {

		Session session = null;

		try {
			// get session
			session = hibernateUtils.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaUpdate<Account> query = builder.createCriteriaUpdate(Account.class);
			Root<Account> root = query.from(Account.class); // FROM
			query.set("email", newEmail);
			query.set("username", newUsername);
			query.where(builder.equal(root.get("id"), id));

			session.createQuery(query).executeUpdate();

			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Delete theo trường bất kỳ
	public void deleteAccount(String email) {

		Session session = null;

		try {
			// get session
			session = hibernateUtils.openSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM
			query.select(root);
			query.where(builder.equal(root.get("email"), email));
			Account account = session.createQuery(query).uniqueResult();
			session.delete(account);

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

//	Phân trang bằng hibernate
	public List<Account> get_AccountByPaging() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Account> query = builder.createQuery(Account.class);
			Root<Account> root = query.from(Account.class); // FROM Account
			query.select(root); // SELECT
			query.orderBy(builder.asc(root.get("id")));
			List<Account> listAccounts = session.createQuery(query).setFirstResult(5).setMaxResults(3).list();

			return listAccounts;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}