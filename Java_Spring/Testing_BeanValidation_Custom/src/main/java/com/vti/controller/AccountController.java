package com.vti.controller;

import java.util.List;

import javax.validation.Valid;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.AccountService;
import com.vti.utils.ValidationUtils;

public class AccountController {
	private AccountService accService;

	public AccountController() {
		accService = new AccountService();
	}

	public List<Account> getAllAccount() {

		return accService.getAllAccount();
	}

	public void createAccount(@Valid AccountDTO accDTO) {
		if (ValidationUtils.validate(accDTO)) {
			accService.createAccount(accDTO);
		}

	}

}

