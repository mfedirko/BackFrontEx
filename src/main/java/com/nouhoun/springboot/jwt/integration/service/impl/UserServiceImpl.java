package com.nouhoun.springboot.jwt.integration.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.repository.UserRepository;
import com.nouhoun.springboot.jwt.integration.service.UserService;

/**
 * Created by mifedirko on 02/24/18.
 */

@Service
public class UserServiceImpl implements UserService {
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
	

	@Autowired
    private UserRepository userRepository;
	

	
    @Override
	public List<User> findByLastName(String lastName){
	   List<User> res= userRepository.findByLastName(lastName);
	   //res.forEach(u->u=u.avoidRecursion());
       return res;
   }
    
    @Override
    public List<User> findByFirstName(String lastName){
 	   List<User> res= userRepository.findByFirstName(lastName);
 	 //  res.forEach(u->u=u.avoidRecursion());
        return res;
    }
    
    @Override
   public User findOneByUsername(String user){
    	return userRepository.findOneByUsername(user);
    }
    
    @Override
    public List<User> findByUsername(String username) {
        List<User> res= userRepository.findByUsername(username);
       // res.forEach(u->u=u.avoidRecursion());
        return res;
    }

    @Override
    public List<User> findAllUsers() {
       List<User> res= (List<User>)userRepository.findAllFiltered();
       //res.forEach(u->u=u.avoidRecursion());
       return res;
    }

	@Override
	public User findById(long i) {
		
		return userRepository.findOneById(i);//.avoidRecursion();//findOne(i).avoidRecursion();
	}


	@Override
	public void updateUser(User u, long id) {
		//validate user
		if (u.getUsername().isEmpty()){
			System.out.println("Username cannot be empty!");
			return;
		}
//		if (u.getPassword().isEmpty()){
//			System.out.println("Password cannot be empty!");
//			return;
//		}
		if (u.getLastName().isEmpty()){
			System.out.println("Last name cannot be empty!");
			return;
		}
		if (u.getFirstName().isEmpty()){
			System.out.println("First name cannot be empty!");
			return;
		}
		
		
		
		userRepository.update(id,u.getFirstName(), u.getLastName(), u.getUsername(),new ShaPasswordEncoder(encodingStrength).encodePassword(u.getPassword(), ""));
	}


	@Override
	public List<User> findFreeForm(String criteria, String key) {
		System.out.println(criteria);
		System.out.println(key);
		if (criteria.equals("username")){
			return findByUsername(key);
		}
		
		if (criteria.equals("firstName")){
			return findByFirstName(key);
			}
		if (criteria.equals("lastName")){
			return findByLastName(key);
		}
		if (criteria.equals("id")){
			return new ArrayList<User>(Arrays.asList(findById(Long.parseLong(key))));
			
		}
		System.out.println("Invalid criteria");
		return null;
	}

   //new ShaPasswordEncoder(encodingStrength).encodePassword(password, "")

	


	









	
	
}
