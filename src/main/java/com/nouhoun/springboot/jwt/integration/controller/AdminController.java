package com.nouhoun.springboot.jwt.integration.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.ModelAndView;

import com.nouhoun.springboot.jwt.integration.domain.Role;
import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.securityconfig.FakeUserDetailsService;
import com.nouhoun.springboot.jwt.integration.service.GenericService;
import com.nouhoun.springboot.jwt.integration.service.UserService;

/*
 * Created by mifedirko on 02/24/2018.
 */
@Controller
@Scope("session")
@RequestMapping("/admin")
public class AdminController {
//	@Autowired
//	public FakeUserDetailsService appUserDetailsService;
//
//	@Autowired
//    private GenericService userService;
//    
//    @Autowired
//    private UserService realUserService;
	
	   private final FakeUserDetailsService appUserDetailsService;
	   private  final GenericService userService;
	   private final UserService realUserService;
  
	    @Autowired
		public AdminController(FakeUserDetailsService appUserDetailsService, GenericService genericService,
				UserService realUserService) {
	    	  SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	    	System.out.println("Init admin controller ----------------------------------------------");
	    		this.appUserDetailsService=appUserDetailsService;
	    		this.userService=genericService;
	    		this.realUserService=realUserService;
			System.out.println(genericService);
	
	
		}

//    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @RequestMapping("/")
    ModelAndView handleFoo(HttpServletResponse response) throws IOException {
//    	System.out.println(getCurrentAccess()//this.appUserDetailsService.getCurrentUser().getUsername()
//    			);
    	ModelAndView mav = new ModelAndView("/WEB-INF/jsp/users");
    	return mav;
    }
    
    
    //*******************************************
    @RequestMapping(value ="/users/{id}/role", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public void updateRole(@Valid @RequestBody List<Role> roles, @PathVariable("id") long id){
    	try{
    		realUserService.findById(id).getId();
    	}
    	catch(NullPointerException e){
    		System.out.println("User with id "+id+" not found!");
    		return;
    	}
    	
     }
 
    @RequestMapping(value ="/users/{id}/role/{rid}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public void dleteRole(@PathVariable("rid") long roleId, @PathVariable("id") long id){
    	
    	User u;
    	Role r;
    	try{
    		u=realUserService.findById(id);
    		r=	userService.findById(roleId);
    	}
    	catch(NullPointerException e){
    		System.out.println("User with id "+id+" or role with id "+roleId+" not found!");
    		return;
    	}
    	System.out.println(u);
    	System.out.println(r.getRoleName());
    	userService.deleteRoles(u, r);
    	
    	
     }
 
    
    
    //****************************************************

    
    
    
//    @RequestMapping(value ="/newuser", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('ADMIN_USER')")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public User createUser(
//    		@RequestParam("username") String username,
//    		@RequestParam("password")String password,
//    		@RequestParam("firstName")String firstName,
//    		@RequestParam("lastName")String lastName,
//    		@RequestParam("acct_nb") String acct_nb,
//    		@RequestParam("role") String role
//    		){
//    	
//    userService.createUserAndAccount(username, password, firstName, lastName, Long.parseLong(acct_nb), true, 0,role);	
//    
//    	return realUserService.findByUsername(username).get(0);
//        	
//    }
    // filters user results visible to current user as those with lower access or themselves
//    private long getCurrentAccess(){
//    	Set<Role> availRoles=userService.findByUsername(this.appUserDetailsService.getUsername()).getRoles();
//    	long access=-1;
//    	for (Role r:availRoles){
//    		if (r.getAccess()>access)
//    			access=r.getAccess();
//    	}
//    	return access;
//    }
//    private List<User> filter(List<User> users){
////    	for (int i=0;i<users.size();i++){
////    		if (users.get(i).maxAccess()>getCurrentAccess() && ! (users.get(i).getId()).equals(this.appUserDetailsService.getCurrentUser().getId()))
////    			users.remove(i);
////    	}
//    	return users;
//    }
//    
    
    @RequestMapping(value="/users/freefor"
    		+ "m/{crit}/{key}",method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> freeFormSearch(@PathVariable("crit") String criteria,@PathVariable("key") String key){
    	
    	System.out.println(criteria+" "+key);
    	List<User> res=realUserService.findFreeForm(criteria, key);
    	System.out.println(res.size());
    	return res;
    }
  
    
    @RequestMapping(value ="/accounts/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public void deleteByAcctNb(@PathVariable("id") long acct_nb){
    	System.out.println(userService.findByAcctNumber(acct_nb).getId());
    	userService.deleteAccount(userService.findByAcctNumber(acct_nb).getId());
    
    }
    
   
    
    
    @RequestMapping(value ="/users/{id}/role", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Role> findRoles(@PathVariable("id") long id ){
    	  	return userService.findAssignedRoles(id);
    }
    
    
    @RequestMapping(value ="/users/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User update(@Valid @RequestBody User user, @PathVariable("id") long id ){
    	System.out.println("---: "+user.avoidRecursion());
    		
    	
    		
    	realUserService.updateUser(user, id);
    	return realUserService.findById(id);
    }
    
    
    
    
    
    @RequestMapping(value ="/users", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User createJSONUser(@Valid @RequestBody User user){
    	System.out.println("---: "+user.avoidRecursion());
    	
//    	 System.out.println(user.avoidRecursion());
//    	 userService.createUserAndAccount(body.username, body.password, body.firstName, body.lastName, Long.parseLong(body.acct_nb), true, 0,"");	
//    	
    		
    			userService.createUserAndAccount(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), 0L, true, 0, "");
    			return realUserService.findByUsername(user.getUsername()).get(0);
    }
    
    
      
    
    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
    return realUserService.findAllUsers();
     }
   
    @RequestMapping(value ="/users/username/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') and #name == this.appUserDetailsService.getCurrentUser().getUsername()")
        public User getUsersByUsername(@PathVariable("username") String username){
        return realUserService.findByUsername(username).get(0);
     }
    
    @RequestMapping(value ="/users/id/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
        public User getUsersById(@PathVariable("id") Long id){
        return realUserService.findById(id);
     }
    
    @RequestMapping(value ="/users/lastname/{lastname}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsersByLastName(@PathVariable("lastname") String lastname){
    	return realUserService.findByLastName(lastname);
    	
    }
    
    @RequestMapping(value ="/roles/{rolename}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<Role> getRoles(@PathVariable("rolename") String rolename){
    	return userService.findByRolename(rolename);
    }
    
}
