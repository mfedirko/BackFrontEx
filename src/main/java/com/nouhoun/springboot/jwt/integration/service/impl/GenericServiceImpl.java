package com.nouhoun.springboot.jwt.integration.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nouhoun.springboot.jwt.integration.domain.Account;
import com.nouhoun.springboot.jwt.integration.domain.Role;
import com.nouhoun.springboot.jwt.integration.domain.Transaction;
import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.repository.AccountRepository;
import com.nouhoun.springboot.jwt.integration.repository.RoleRepository;
import com.nouhoun.springboot.jwt.integration.repository.TransactionRepository;
import com.nouhoun.springboot.jwt.integration.repository.UserRepository;
import com.nouhoun.springboot.jwt.integration.service.GenericService;

/**
 * Created by nydiarra on 07/05/17.
 */
@Service
public class GenericServiceImpl implements GenericService {
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private AccountRepository acctRepository;
    
    @Autowired
    private TransactionRepository txnRepository;
    
//	@Autowired
//	private UserRoleRepository userRoleRepository;
	
	

	
   @Override
	public List<User> findByLastName(String lastName){
	   return userRepository.findByLastName(lastName);
   }
   
    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).size() > 0 ? userRepository.findByUsername(username).get(0) : null;
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }



	@Override
	public List<Role> findByRolename(String roleName) {
		List<Role> rr =roleRepository.findByRolename(roleName);
	//	rr.forEach(r->r=r.avoidRecursion());
		return rr;
	}

	@Override
	public List<Account> findAllAccounts() {
		
		return (List<Account>)acctRepository.findAll();
	}

	@Override
	public Role findById(long id){
		return roleRepository.findOne(id);//.avoidRecursion();
	}
	@Override
	public List<Transaction> findAllTransactions() {
		return (List<Transaction>)txnRepository.findAll();
	}

	@Override
	public List<Transaction> findByTxnDate(Date date) {
		
		return txnRepository.findByTxnDate(date);
	}

	@Override
	public Account findByAccountNumber(long acctNum) {
		return acctRepository.findByAccountNumber(acctNum);
	}
	@Override
	public Account findByAcctNumber(long acctNum){
		return acctRepository.findByAcctNumber(acctNum);
	}
	 public void associateRole(Long id,String rolename){
	    	
		 User u;
		 Role r;
		 try{
			 u=userRepository.findOneById(id);
			 r=roleRepository.findByRolename(rolename).get(0);
		 }
		  catch(NullPointerException e){return;}
		 u.getRoles().add(r);
		
	 }
	
	
	@Override
	public void createUserAndAccount(String username, String password, String firstName, String lastName, long acct_nb,
			boolean acct_active, long balance,String rolename) {
		if (!(userRepository.findByUsername(username)==null || userRepository.findByUsername(username).isEmpty())){
			System.out.println("User with username "+username+" already exists!");
			return;
		}
		//generate a random acct number
		do{
			acct_nb=generateRandom(4050);
		}
		while(acctRepository.findByAcctNumber(acct_nb)!=null);
		if(!rolename.isEmpty() && roleRepository.findByRolename(rolename)==null){
			System.out.println("Role named "+rolename+" not found!");
			return;
		}
		userRepository.createUser(username, new ShaPasswordEncoder(encodingStrength).encodePassword(password, ""), firstName, lastName);
		Long createdUser=userRepository.findByUsername(username).get(0).getId();
		if (!rolename.isEmpty())
			associateRole(createdUser, rolename);
		acctRepository.createAccount(acct_nb, acct_active, createdUser, balance);
		
		
	}


	
    public static long generateRandom(int prefix) {
        Random rand = new Random();

        long x = (long)(rand.nextDouble()*100000000000000L);

        String s = String.valueOf(prefix) + String.format("%014d", x);
        return Long.valueOf(s);
    }


	@Override
	public void deleteAccount(long id) {
		if (txnRepository.findByAcctId(id).isEmpty()){
			acctRepository.delete(id);
			return;
		}
			System.out.println("Account id "+id+" is associated with existing transactions!");
			acctRepository.disableAcct(id);
			System.out.println("Account was disabled instead.");
			
		
	}
	
//	public void deleteRoles(long userId, long roleId){
//		userRoleRepository.deleteRoles(userId,roleId);
//		
//	}
	
	@Transactional(rollbackFor=Exception.class)
	public void deleteRoles(User u, Role r){
				System.out.println("before");
				System.out.println(u.getRoles());
				
				u.getRoles().remove(r);
//				r.getUsers().remove(u);
				
				System.out.println("after");
				System.out.println(u.getRoles());
	}


	@Override
	public Set<Role> findAssignedRoles(long userId) {
		Set<Role> rr=userRepository.findOneById(userId).getRoles();
		//rr.forEach(r->r=r.avoidRecursion());
		System.out.println(rr.size()+"  is 0?");
		return rr;
	}








	
	
}
