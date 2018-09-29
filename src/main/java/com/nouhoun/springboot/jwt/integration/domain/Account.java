package com.nouhoun.springboot.jwt.integration.domain;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="account")
//@JsonDeserialize( using = AccountDeserializer.class )
public class Account {
	
		
		public Account(Long id) {
			this.id=id;
		}
		public Account() {}
		
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name="acct_id")
		@JsonProperty("id")
	 	private Long id;
	 	
	 	
	 	@Column(name="acct_nb")
	 	private Long acct_nb;
	 	
	 	@Column(name="acct_active_in")
	 	private boolean acct_active_in;
	 	
	 	@JsonIgnore
	 	@OneToMany(mappedBy="fromAccount")
	 	private Set<Transaction> txns;
	 	
	 	@JsonIgnore
	 	 @OneToMany(mappedBy="toAccount")
	 	private Set<Transaction> toTxns;
	 	
	 @Min(0)
	 @Column(name="balance")
	 private Long balance;
	
	 
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;
	 	
	
	
	
public Set<Transaction> getToTxns() {
			return toTxns;
		}

		public void setToTxns(Set<Transaction> toTxns) {
			this.toTxns = toTxns;
		}

		
public Set<Transaction> getTxns() {
			return txns;
		}

		public void setTxns(Set<Transaction> txns) {
			this.txns = txns;
		}

public boolean isAcct_active_in() {
			return acct_active_in;
		}

		public void setAcct_active_in(boolean acct_active_in) {
			this.acct_active_in = acct_active_in;
		}

	 	
	
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getAcct_nb() {
			return acct_nb;
		}

		public void setAcct_nb(Long acct_nb) {
			this.acct_nb = acct_nb;
		}

		

		public Long getBalance() {
			return balance;
		}

		public void setBalance(Long balance) {
			this.balance = balance;
		}
		
		@Override
		public String toString(){
			return "Account #: "+this.getAcct_nb()+" having balance "
					+this.getBalance();
		}
}
