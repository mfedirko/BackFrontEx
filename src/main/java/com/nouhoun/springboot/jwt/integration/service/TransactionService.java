package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.Transaction;

import java.util.List;

import org.springframework.stereotype.Service;


public interface TransactionService {
	Transaction addTransaction(Transaction transaction);
	Transaction findById(long id);
	List<Transaction> findAll();
	List<Transaction> findByPostDate();
	List<Transaction> findByAmountRange(long min,long max);
	List<Transaction> findByUsername(String u);
}
