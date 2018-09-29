package com.nouhoun.springboot.jwt.integration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by mifedirko on 01/2018
 */
//@XmlRootElement
@Entity
@Table(name = "txn")
@JsonIgnoreProperties(ignoreUnknown = false)
public class Transaction {

	  
		public Transaction() {}
		
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id")
      private Long id;
	
	    
	   @Column(name="amount")
	   private Long amount;
	   
	   @Temporal(TemporalType.DATE)
	   @Column(name="post_date")
	   private Date date;
	    
	
	   
		@ManyToOne
	    @JoinColumn(name="to_acct_id", nullable=false)
	  private Account toAccount;
	   
		@ManyToOne
	    @JoinColumn(name="from_acct_id", nullable=false)
	  private Account fromAccount;
	   
	   
	   
	   public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

//	public Long getToAccountId() {
//		return toAccountId;
//	}
//
//	public void setToAccountId(Long toAccountId) {
//		this.toAccountId = toAccountId;
//	}
//
//	public Long getFromAccountId() {
//		return fromAccountId;
//	}
//	
//	public void setFromAccountId(Long fromAccountId) {
//		this.fromAccountId = fromAccountId;
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "[" + id + "  "
				//+toAccountId + "  "+ fromAccountId+" "
				+date+toAccount+fromAccount+"]";
	}
	
	
}
