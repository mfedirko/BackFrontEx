package com.nouhoun.springboot.jwt.integration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nouhoun.springboot.jwt.integration.domain.Transaction;
import com.nouhoun.springboot.jwt.integration.repository.AccountRepository;
import com.nouhoun.springboot.jwt.integration.repository.TransactionRepository;
import com.nouhoun.springboot.jwt.integration.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository txnRepository;
	
	
	@Autowired
	AccountRepository accountRepository;
	
	//rolls back transaction (both receive and give) if encounter any exception
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Transaction addTransaction(Transaction transaction) {
		System.out.println(transaction);
		System.out.print("-----------------------------------");
		accountRepository.transferGive(transaction.getAmount(), transaction.getFromAccount().getId());
		accountRepository.transferReceive(transaction.getAmount(), transaction.getToAccount().getId());
	
		return txnRepository.save(transaction);
		
		
		}
	
	public Transaction findById(long id){
		return txnRepository.findOne(id);
	}

	@Override
	public List<Transaction> findAll() {
		return txnRepository.findAll();
	}

	@Override
	public List<Transaction> findByPostDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findByAmountRange(long min, long max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findByUsername(String u) {
		return txnRepository.findByUsername(u);
    }
	
	
//	@Override
//	public Transaction addTransaction(Transaction transaction) {
//		accountService.
//		return txnRepository.save(transaction);
//		}

}
