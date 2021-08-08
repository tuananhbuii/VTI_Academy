package com.vti.backend.datalayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vti.Ultis.jdbcUltis;
import com.vti.entity.Account;

public class AccountResponsitory implements IAccountResponsitory {
	private jdbcUltis jdbc;

	public AccountResponsitory() throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		jdbc = new jdbcUltis();
	}

	public boolean isEmailIfExists(String email) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `Account` WHERE Email = (?)";
		PreparedStatement preparedStatement = jdbc.createPrepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			jdbc.disConnection();
			return true;
		} else {
			jdbc.disConnection();
			return false;
		}
	}

	public boolean isLoginAdmin(String email, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM `Account` WHERE (Email = (?) AND Password = (?) AND Role = 'ADMIN')";
		PreparedStatement preparedStatement = jdbc.createPrepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			jdbc.disConnection();
			return true;
		} else {
			jdbc.disConnection();
			return false;
		}
	}

	public boolean createAccountByAdmin(String fullName, String email) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO `Account` (FullName, Email, Password, Role)"
				+ "VALUES 				( (?), (?), ('1234567'), ('USER'))";
		PreparedStatement preparedStatement = jdbc.createPrepareStatement(sql);
		preparedStatement.setString(1, fullName);
		preparedStatement.setString(2, email);
		if (preparedStatement.executeUpdate() == 1) {
			jdbc.disConnection();
			return true;
		} else {
			jdbc.disConnection();
			return false;
		}
	}

	public boolean isLoginUser(String email, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM `Account` WHERE Email = (?) AND Password = (?)";
		PreparedStatement preparedStatement = jdbc.createPrepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			jdbc.disConnection();
			return true;
		}else {
			jdbc.disConnection();
			return false;
		}
	}

	public List<Account> getListMemberByProjectName(String projectName) throws ClassNotFoundException, SQLException {
		List<Account> listAccounts = new ArrayList<Account>();
		String sql = "SELECT A.Email, A.FullName,A.Category\r\n"
				+ "FROM `Account` A\r\n"
				+ "JOIN `AccountProject` AP USING (AccountID)\r\n"
				+ "JOIN `Project` P USING (ProjectID)\r\n"
				+ "WHERE  P.ProjectName = (?)";
		PreparedStatement preparedStatement = jdbc.createPrepareStatement(sql);
		preparedStatement.setString(1, projectName);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Account account = new Account();
			System.out.println();
			System.out.println(resultSet.getString("Email"));
			System.out.println(resultSet.getString("FullName"));
			System.out.println(resultSet.getString("Category"));
			listAccounts.add(account);
		}
		return listAccounts;
	}

}
