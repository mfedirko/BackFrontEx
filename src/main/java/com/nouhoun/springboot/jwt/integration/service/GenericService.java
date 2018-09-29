package com.nouhoun.springboot.jwt.integration.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.nouhoun.springboot.jwt.integration.domain.Account;
import com.nouhoun.springboot.jwt.integration.domain.Role;
import com.nouhoun.springboot.jwt.integration.domain.Transaction;
import com.nouhoun.springboot.jwt.integration.domain.User;

/**
 * Created by nydiarra on 06/05/17.
 */


public interface GenericService {
	List<User> findByLastName(String lastName);
    User findByUsername(String username);
     Account findByAcctNumber(long acctNum);
    void deleteAccount(long acct_nb);
    
    List<User> findAllUsers();
    List<Account> findAllAccounts();
    List<Transaction> findAllTransactions();
    Set<Role> findAssignedRoles(long userId);
    List<Role> findByRolename(String roleName);
    List<Transaction> findByTxnDate(Date date);
    Account findByAccountNumber(long acctNum);
    //void deleteRoles(long id1,long id2);
    void createUserAndAccount(String username, String password, String firstName, String lastName,
    		long acct_nb, boolean acct_active, long balance,String rolename);
    Role findById(long id);
    void deleteRoles(User u, Role r);
}
