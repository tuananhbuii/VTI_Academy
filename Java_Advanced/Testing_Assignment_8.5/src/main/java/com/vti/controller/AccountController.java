package com.vti.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accounts")

public class AccountController {

	@Autowired
	private IAccountService service;

	@GetMapping()
	public ResponseEntity<?> getAllAccounts(@RequestParam(required = false) String search) throws ParseException {
		List<Account> entities = service.getAllAccounts(search);
		return new ResponseEntity<List<Account>>(entities, HttpStatus.OK);
	}
}