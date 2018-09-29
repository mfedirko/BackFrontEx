package com.nouhoun.springboot.jwt.integration.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nouhoun.springboot.jwt.integration.domain.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	@Query("SELECT p FROM Transaction p WHERE date = :date")
	List<Transaction> findByTxnDate(@Param("date")Date date);
   
	@Query("SELECT p FROM Transaction p WHERE fromAccount.id = :fromAccountId")
	List<Transaction> findByAcctId(@Param("fromAccountId")long acct1);
	
//	@Query(value="SELECT * from txn t"
//			+ " INNER JOIN account a "
//			+ " ON a.acct_id=t.from_acct_id"
//			+ " INNER JOIN app_user u "
//			+ " ON u.id=a.user_id "
//			+ " WHERE u.username = :username",nativeQuery=true)
	@Query("from Transaction as t"
			+" INNER JOIN t.fromAccount as f"
			+ " INNER JOIN f.user as u"
			+ " WHERE u.username = :username")
	
	List<Transaction> findByUsername(@Param("username") String username);
//	 	@Modifying
//	    @Query("insert into Transaction (amount,toAccountId,fromAccountId,date) select :id,:amount,:toAccountId,:fromAccountId,:date from Dual")
//	    public int createTransaction(@Param("id")long id, @Param("toAccountId")long toAcctId, @Param("fromAccountId")long fromAcctId,@Param("date")Date date);
}
