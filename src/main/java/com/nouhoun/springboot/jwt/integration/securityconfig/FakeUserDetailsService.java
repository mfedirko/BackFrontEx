package com.nouhoun.springboot.jwt.integration.securityconfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.service.GenericService;

@Service
public class FakeUserDetailsService implements UserDetailsService {

	User overridenCurrentUser = null;
	
	@Autowired
	private GenericService genericService;


	@Override
	public UserDetails loadUserByUsername(String username) throws
	UsernameNotFoundException {
		System.out.println("loading --___!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(username);
	User user = genericService.findByUsername(username); //load all users
	
	if (user == null) {
		throw new UsernameNotFoundException("Username " + username + " not found");
	}
	
List<GrantedAuthority> authorities=new ArrayList<>();
user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
System.out.println(user.getRoles().size());
return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}

public String getUsername() {
SecurityContext context = SecurityContextHolder.getContext();

Authentication authentication = context.getAuthentication();

if (authentication == null)
  return null;

Object principal = authentication.getPrincipal();


if (principal instanceof UserDetails) {
	UserDetails p=((UserDetails) principal);
	System.out.println(p.getUsername());
	System.out.println("User detail---------");
  return (p.getUsername());
}
else {
  return principal.toString();
	}
}


public User getCurrentUser() {

if (overridenCurrentUser != null) {
  return overridenCurrentUser;
}

System.out.println(getUsername());
System.out.println("how it find????????");
  return genericService.findByUsername(getUsername());
}


}


