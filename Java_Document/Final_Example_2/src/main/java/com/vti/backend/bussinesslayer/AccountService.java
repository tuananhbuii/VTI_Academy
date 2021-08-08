package com.vti.backend.bussinesslayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.vti.backend.datalayer.AccountResponsitory;
import com.vti.entity.Account;

public class AccountService implements IAccountService{
	AccountResponsitory accountResponsitory ;
	public AccountService() throws FileNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		accountResponsitory = new AccountResponsitory();
	}
	public boolean isEmailIfExists(String email) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return accountResponsitory.isEmailIfExists(email);
	}

	public boolean isLoginAdmin(String email, String password) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return accountResponsitory.isLoginAdmin(email, password);
	}

	public boolean createAccountByAdmin(String fullName, String email) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return accountResponsitory.createAccountByAdmin(fullName, email);
	}

	public boolean isLoginUser(String email, String password) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return accountResponsitory.isLoginUser(email, password);
	}

	public List<Account> getListMemberByProjectName(String projectName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return accountResponsitory.getListMemberByProjectName(projectName);
	}
	
}
