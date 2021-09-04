package com.vti.service;

import java.util.List;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdating;

public interface IAccountService {
	public List<Account> getAllAccounts();
	
	public Account getAccountByID(short id);
	
	public void createAccount(AccountFormForCreating form);
	
	public void updateAccount(short id, AccountFormForUpdating form);
	
	public void deleteAccount(short id);
}