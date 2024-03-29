package com.vti.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.Position.PositionName;
import com.vti.utils.HibernateUtils;

public class AccountRepository {
	private HibernateUtils hibernateUtils;

	public AccountRepository() {
		hibernateUtils = HibernateUtils.getInstance();
	}

//	Lấy danh sách tất cả các Account trên hệ thống.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Account> get_FROM() {
		Session session = null;
		try {
			// get session
			session = hibernateUtils.openSession();

			// create Native query
			// Câu lênh sql như câu lệnh viết trong Workbench

			NativeQuery query = session.createNativeQuery(
					"SELECT a.AccountID, a.Email, a.Username, a.FullName, d.DepartmentName, p.PositionName FROM account a \r\n"
							+ "INNER JOIN department d ON a.DepartmentID = d.DepartmentID\r\n"
							+ "INNER JOIN position p ON a.PositionID = p.PositionID;");
			// Phần query này sẽ tạo ra 1 list các array object, sử dụng vòng lặp foreach để
			// truy vấn tới các phần tử trong array, lấy các giá trị trong DB theo Index của
			// array bắt đầu từ 0.
			List<Account> accountsList = new ArrayList<Account>();
			List<Object[]> accounts = query.getResultList();
			for (Object[] objects : accounts) {
				Account account = new Account();
				account.setId(Short.parseShort(objects[0].toString()));
				account.setEmail(objects[1].toString());
				account.setUsername(objects[2].toString());
				account.setFullname(objects[3].toString());
//				Chý ý cần tạo mới hàm tạo 1 tham số cho Department
				account.setDepartment(new Department(objects[4].toString()));
				account.setPosition(new Position(PositionName.valueOf(objects[5].toString())));
				accountsList.add(account);
//				Cách khác.
//				NativeQuery query = session.createNativeQuery("SELECT * FROM account", Account.class );
//				List<Account> accountList = query.getResultList();
			}
			return accountsList;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// Giới hạn điều kiện trả về với WHERE theo id
	@SuppressWarnings("rawtypes")
	public Account get_ByID(short id) {
		Session session = null;
		try {
			session = hibernateUtils.openSession();
			NativeQuery query = session.createNativeQuery(
					"SELECT a.AccountID, a.Email, a.Username, a.FullName, d.DepartmentName, p.PositionName FROM account a \r\n"
							+ "INNER JOIN department d ON a.DepartmentID = d.DepartmentID\r\n"
							+ "INNER JOIN position p ON a.PositionID = p.PositionID\r\n" + "WHERE a.AccountID = ?;");
			query.setParameter(1, id);
			Object[] objects = (Object[]) query.getSingleResult();
			Account account = new Account();
			account.setId(Short.parseShort(objects[0].toString()));
			account.setEmail(objects[1].toString());
			account.setUsername(objects[2].toString());
			account.setFullname(objects[3].toString());
			account.setDepartment(new Department(objects[4].toString()));
			account.setPosition(new Position(PositionName.valueOf(objects[5].toString())));
			return account;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	 Giới hạn số lượng cột cần lấy sử dụng mệnh đề SELECT.
	@SuppressWarnings("rawtypes")
	public String get_FullName(short id) {

		Session session = null;

		try {
			// get session
			session = hibernateUtils.openSession();
			// create Native query
//			Câu lênh sql như câu lệnh viết trong Workbench
			NativeQuery query = session
					.createNativeQuery("SELECT a.FullName FROM account a \r\n" + "WHERE a.AccountID = ?;");
			query.setParameter(1, id);
			Object object = query.getSingleResult();

			return object.toString();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	// Giới hạn điều kiện trả về với WHERE: Lấy ra tất cả các Account được tạo trong
	// tháng hiện tại.

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Account> get_ByCurrentMonth() {

		Session session = null;

		try {
			// get session
			session = hibernateUtils.openSession();
			// create Native query
			NativeQuery query = session.createNativeQuery(
					"SELECT a.AccountID, a.Email, a.Username, a.FullName, d.DepartmentName, p.PositionName FROM account a \r\n"
							+ "					INNER JOIN department d ON a.DepartmentID = d.DepartmentID\r\n"
							+ "					INNER JOIN position p ON a.PositionID = p.PositionID WHERE month(a.CreateDate) = ? AND year(a.CreateDate) = ?;");

			query.setParameter(1, LocalDate.now().getMonthValue());
			query.setParameter(2, LocalDate.now().getYear());
			List<Account> accountList = new ArrayList<Account>();
			List<Object[]> objectList = query.getResultList();

			for (Object[] objects : objectList) {
				Account account = new Account();
				account.setId(Short.parseShort(objects[0].toString()));
				account.setEmail(objects[1].toString());
				account.setUsername(objects[2].toString());
				account.setFullname(objects[3].toString());
				account.setDepartment(new Department(objects[4].toString()));
				account.setPosition(new Position(PositionName.valueOf(objects[5].toString())));
				accountList.add(account);
			}

			return accountList;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Sắp xếp các kết quả theo bất kỳ thuộc tính nào trên các đối tượng trong tập kết quả tăng dần (ASC) hoặc giảm dần (DESC).
//	Lấy danh sách Account được tạo trong tháng hiện tại và sắp xếp theo ngảy tạo giảm dần hoặc tăng dần.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Account> get_ByCurrentMonthOderBy() throws ParseException {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();
			// create Native query
			NativeQuery query = session.createNativeQuery(
					"SELECT a.AccountID, a.Email, a.Username, a.FullName, d.DepartmentName, p.PositionName, a.CreateDate FROM account a \r\n"
							+ "					INNER JOIN department d ON a.DepartmentID = d.DepartmentID\r\n"
							+ "					INNER JOIN position p ON a.PositionID = p.PositionID WHERE month(a.CreateDate) = ? AND year(a.CreateDate) = ?\r\n"
							+ "                    ORDER BY a.CreateDate DESC;");

			query.setParameter(1, LocalDate.now().getMonthValue());
			query.setParameter(2, LocalDate.now().getYear());
			List<Account> accountList = new ArrayList<Account>();
			List<Object[]> objectList = query.getResultList();

			for (Object[] objects : objectList) {
				Account account = new Account();
				account.setId(Short.parseShort(objects[0].toString()));
				account.setEmail(objects[1].toString());
				account.setUsername(objects[2].toString());
				account.setFullname(objects[3].toString());
				account.setDepartment(new Department(objects[4].toString()));
				account.setPosition(new Position(PositionName.valueOf(objects[5].toString())));
				String dateString = objects[6].toString();
				Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
				account.setCreateDate(date1);
				accountList.add(account);
			}

			return accountList;

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Update theo trường bất kỳ.
	@SuppressWarnings("rawtypes")
	public void updateEmailUsernameAccountByID(String newEmail, String newUsername, short id) {

		Session session = null;

		try {
			// get session
			session = hibernateUtils.openSession();
			session.beginTransaction();

			// create Native query
			NativeQuery query = session.createNativeQuery(
					"     UPDATE `account` SET `Email` = ?, `Username` = ? WHERE (`AccountID` =?);\r\n" + "");

			query.setParameter(1, newEmail);
			query.setParameter(2, newUsername);
			query.setParameter(3, id);
			query.executeUpdate();

			session.getTransaction().commit();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

//	Delete theo trường bất kỳ
	@SuppressWarnings("rawtypes")
	public void deleteAccount(String email) {

		Session session = null;

		try {
			session = hibernateUtils.openSession();
			session.beginTransaction();
			// create Native query
			NativeQuery query = session.createNativeQuery("DELETE FROM `account` WHERE (`Email` = ?);");
			query.setParameter(1, email);
			query.executeUpdate();

			session.getTransaction().commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

//	Phân trang bằng hibernate
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Account> get_AccountByPaging() {

		Session session = null;

		try {

			// get session
			session = hibernateUtils.openSession();

			NativeQuery query = session.createNativeQuery(
					"SELECT a.AccountID, a.Email, a.Username, a.FullName, d.DepartmentName, p.PositionName FROM account a \r\n"
							+ "INNER JOIN department d ON a.DepartmentID = d.DepartmentID\r\n"
							+ "INNER JOIN position p ON a.PositionID = p.PositionID\r\n" + "ORDER BY a.AccountID\r\n"
							+ "LIMIT 3\r\n" + "OFFSET 5;");
//Phần query này sẽ tạo ra 1 list các array object, sử dụng vòng lặp foreach để truy vấn tới các phần tử trong array, lấy các giá trị trong DB theo Index của array bắt đầu từ 0.
			List<Account> accountList = new ArrayList<Account>();
			List<Object[]> accounts = query.getResultList();
			for (Object[] objects : accounts) {
				Account account = new Account();
				account.setId(Short.parseShort(objects[0].toString()));
				account.setEmail(objects[1].toString());
				account.setUsername(objects[2].toString());
				account.setFullname(objects[3].toString());
//				Chý ý cần tạo mới hàm tạo 1 tham số cho Department
				account.setDepartment(new Department(objects[4].toString()));
				account.setPosition(new Position(PositionName.valueOf(objects[5].toString())));
				accountList.add(account);
			}
			return accountList;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}