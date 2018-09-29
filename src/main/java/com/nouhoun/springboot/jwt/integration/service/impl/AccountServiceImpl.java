package com.nouhoun.springboot.jwt.integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nouhoun.springboot.jwt.integration.domain.Account;
import com.nouhoun.springboot.jwt.integration.repository.AccountRepository;
import com.nouhoun.springboot.jwt.integration.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account getById(long accountId) {
	
		return accountRepository.findOne(accountId);
	}



}
