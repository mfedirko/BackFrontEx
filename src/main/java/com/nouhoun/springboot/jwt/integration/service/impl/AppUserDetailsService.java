//package com.nouhoun.springboot.jwt.integration.service.impl;
//
//import com.nouhoun.springboot.jwt.integration.domain.User;
//import com.nouhoun.springboot.jwt.integration.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by nydiarra on 06/05/17.
// */
//@Component
//public class AppUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    User overridenCurrentUser;
//    
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(s);
//
//        if(user == null) {
//            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
//        }
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        });
//
//        UserDetails userDetails = new org.springframework.security.core.userdetails.
//                User(user.getUsername(), user.getPassword(), authorities);
//
//        return userDetails;
//    }
//    
//
//public String getUsername() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        if (authentication == null)
//            return null;
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();
//        } else {
//            return principal.toString();
//        }
//    }
//
//
//    public User getCurrentUser() {
//        
//		if (overridenCurrentUser != null) {
//            return overridenCurrentUser;
//        }
//        User user = userRepository.findByUsername(getUsername());
//
//            return user;
//        
//    }
//    
//}
