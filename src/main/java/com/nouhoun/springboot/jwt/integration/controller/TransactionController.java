package com.nouhoun.springboot.jwt.integration.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nouhoun.springboot.jwt.integration.domain.Account;
import com.nouhoun.springboot.jwt.integration.domain.Transaction;
import com.nouhoun.springboot.jwt.integration.securityconfig.FakeUserDetailsService;
import com.nouhoun.springboot.jwt.integration.service.GenericService;
import com.nouhoun.springboot.jwt.integration.service.TransactionService;
//import com.nouhoun.springboot.jwt.integration.service.impl.AppUserDetailsService;
/*
 * Created by mifedirko
 * on date 01/2018
 */
@Controller
@Scope("session")
@RequestMapping("/transaction")
public class TransactionController {
	
	 
	   private final TransactionService txnService;
	   private final GenericService genericService;
	   private final FakeUserDetailsService appUserDetailsService;
  
	    @Autowired
		public TransactionController(TransactionService txnService, GenericService genericService,
				FakeUserDetailsService appUserDetailsService) {
	    	System.out.println("Init transaction controller ----------------------------------------------");
			this.txnService = txnService;
			this.genericService=genericService;
			this.appUserDetailsService=appUserDetailsService;
			System.out.println(txnService);
			System.out.println(genericService);
	
		}
	
	    
	    

//	
	    
	    
	    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	    @RequestMapping(value="/")
	    ModelAndView handleFoo(HttpServletResponse response) throws IOException {
	    	ModelAndView mav = new ModelAndView("/WEB-INF/jsp/newuser");
	    	System.out.println(appUserDetailsService.getCurrentUser());
	    	System.out.println("--------------------------------------");
	    	mav.addObject("username", appUserDetailsService.getCurrentUser().getUsername()); // should be username
	    	mav.addObject("ok", "?@###############");
	    	
      	return mav;
	    	
	    }
	    
	 

	    //Method level authorization
	    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	    @RequestMapping(value ="/newF",method=RequestMethod.POST)
	 //   @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
//	    @Consumes("MediaType.APPLICATION_JSON")
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	    public Transaction createTransaction(
	    	//	@Valid @RequestBody  		Transaction transaction
//	    		@FormParam("amount") String amount,
//	    		@FormParam("date")String date,
//	    		@FormParam("fromAccountId")String fromAccountId,
//	    		@FormParam("toAccountId")String toAccountId
	    		@RequestParam("amount") String amount,
	    		@RequestParam("date")String date,
	    		@RequestParam("fromAccountId")String fromAccountId,
	    		@RequestParam("toAccountId")String toAccountId
	    		)
	    {
	    	
	    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	 Date ddate=new Date();   
	    	 try{ddate=sdf.parse(date);}
	    	    catch(Exception e){}
	    	Transaction transaction=new Transaction();
	    	transaction.setAmount(Long.parseLong(amount));
	    	transaction.setDate(ddate);
	    	
	    	transaction.setToAccount(genericService.findByAccountNumber(Long.parseLong(toAccountId)));
	    	transaction.setFromAccount(genericService.findByAccountNumber(Long.parseLong(fromAccountId)));
	    	
	    	//will use default account of logged in user if fromAccount is null
		 
	    	
	    	
	    	System.out.println("*****!#$!&$*!$(!*****");	
	    	System.out.println(transaction.getAmount());
	    		System.out.println(transaction.getDate());
	    		System.out.println(transaction.getFromAccount());
	    		System.out.println(transaction.getToAccount());
	    	if(validateTxn(transaction)){
	    	System.out.println("Created "+transaction.getId());
	        return txnService.addTransaction(transaction);
	    	}
	    	return null;
	    }
	    
	    
	    
	    
	    //Method level authorization
	    @RequestMapping(method=RequestMethod.POST)
	    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Transaction createTransaction(@Valid @RequestBody	Transaction transaction)
	    {
	    	System.out.println("*****!#$!&$*!$(!*****");	
	    	System.out.println(transaction.getAmount());
	    		System.out.println(transaction.getDate());
	    		System.out.println(transaction.getFromAccount());
	    		System.out.println(transaction.getToAccount());
	    	if(validateTxn(transaction)){
	    	System.out.println("Created "+transaction.getId());
	        return txnService.addTransaction(transaction);
	    	}
	    	return null;
	    }
	    
	    
	    @PreAuthorize("(hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER'))")// and #username=this.appUserDetailsService.getCurrentUser().getUsername()")
	    @RequestMapping(value="/{username}",method=RequestMethod.GET)
	    private List<Transaction> getTransactions(@PathVariable("username") String username){
	    	if (txnService == null) System.out.println("null txnService");;
	    	
	    return txnService.
	    		findByUsername(username);
	    }
	    
	private boolean validateTxn(Transaction txn){
		
		if ( txn.getFromAccount() == null) {
			txn.setFromAccount((Account)this.appUserDetailsService.getCurrentUser().getAccounts().toArray()[0]);
		}
		//post date must not be in the past.
		if (txn.getDate().before(new Date())){
		System.out.println("Transaction date must be in the future. Provided date: "+txn.getDate());	
		return false;
		}
		
		
		if (txn.getToAccount() == null) {
			System.out.println("To account not found");
			return false;
		}
		
		if (txn.getFromAccount().equals(txn.getToAccount())) {
			System.out.println("Cannot transfer from "+txn.getFromAccount()+ " to "+txn.getToAccount()+": same account.");
			return false;
		}
		
		
		
	

		//fromAccount must be registered to the current user
		if(! (this.appUserDetailsService.getCurrentUser().getAccounts().contains(txn.getFromAccount())) ){
			System.out.println("User accounts associated to "+this.appUserDetailsService.getCurrentUser().getUsername()+":");
			this.appUserDetailsService.getCurrentUser().getAccounts().forEach(a->System.out.println(a.getAcct_nb()));
			System.out.println("The account "+txn.getFromAccount()+" is not associated with user "+this.appUserDetailsService.getCurrentUser().getUsername());
			return false;

		}
		
		//from account must have sufficient balance to pay amount
		if(txn.getFromAccount().getBalance()<txn.getAmount()){
			System.out.println("Account having ID "+txn.getFromAccount()+" has insufficient balance "+txn.getFromAccount().getBalance()+" for transaction "
					+ "of amount "+txn.getAmount());		
			
			return false;
		}
		
		return true;
				
		}

}
