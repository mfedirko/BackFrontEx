package com.nouhoun.springboot.jwt.integration.service;

import org.springframework.stereotype.Service;

import com.nouhoun.springboot.jwt.integration.domain.Account;


public interface AccountService{
	//void transfer(long toAccountId,long fromAccountId,long balance);
	
	Account getById(long accountId);
}
