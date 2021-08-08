package com.vti.backend.presentationlayer;

import java.sql.SQLException;
import java.util.List;

import com.vti.entity.Account;

public interface IAccountController {
	public boolean isEmailIfExists(String email) throws ClassNotFoundException, SQLException;

	public boolean isLoginAdmin(String email, String password) throws ClassNotFoundException, SQLException;

	public boolean createAccountByAdmin(String fullName, String email) throws ClassNotFoundException, SQLException;

	public boolean isLoginUser(String email, String password) throws ClassNotFoundException, SQLException;

	public List<Account> getListMemberByProjectName(String projectName) throws ClassNotFoundException, SQLException;
}
