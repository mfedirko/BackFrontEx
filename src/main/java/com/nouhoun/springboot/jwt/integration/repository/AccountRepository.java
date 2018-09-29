package com.nouhoun.springboot.jwt.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nouhoun.springboot.jwt.integration.domain.Account;
import com.nouhoun.springboot.jwt.integration.domain.User;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT p FROM Account p WHERE id = :id")
	Account findByAccountNumber(@Param("id")long acct_id);
	
	@Query("SELECT p FROM Account p WHERE acct_nb = :acct_nb")
	Account findByAcctNumber(@Param("acct_nb") long acct_nb);
	
	@Modifying
	@Query(value="UPDATE Account c SET c.acct_active_in=0 WHERE c.id= :id")
	@Transactional(rollbackFor=Exception.class)
	void disableAcct(@Param("id")long id);
	
		
	  @Modifying
	    @Query("UPDATE Account c SET c.balance = (c.balance - :amount) WHERE c.id = :id")
	  @Transactional(rollbackFor=Exception.class)
	 void transferGive(@Param("amount")long amount,@Param("id")long fromId);
	  
	  @Modifying
	  @Query(value="INSERT INTO account (acct_nb,acct_active_in,balance,user_id) VALUES (:acct_nb, :acct_active, :balance, :user_id)",nativeQuery = true)
	  @Transactional(rollbackFor=Exception.class)
	  void createAccount(@Param("acct_nb") long acct_nb, @Param("acct_active") boolean acct_active,@Param("user_id") long userid, @Param("balance") long balance);
	  
	  @Modifying
	    @Query("UPDATE Account c SET c.balance = (c.balance + :amount) WHERE c.id = :id")
	 void transferReceive(@Param("amount")long amount,@Param("id")long toId);

	  
	  
}
