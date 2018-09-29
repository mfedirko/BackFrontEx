package com.nouhoun.springboot.jwt.integration.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by nydiarra on 06/05/17.
 */

@Entity
@Table(name = "app_user")
public class User {
	
	public User() {}
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

   

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "first_name")
    private String firstName;

    
    @Column(name = "username")
    private String username;
    
    
    @Column(name = "last_name")
    @Length(min=2,message="Last Name must be 2 or more chars long.")
    private String lastName;

    
    @OneToMany(mappedBy="user")
    private Set<Account> accounts;
    
    
    public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	/*
     * Roles are being lazily loaded here to avoid recursion 
     * and standard out overload problem
     */
    @ManyToMany(fetch = FetchType.LAZY,
    		cascade={ 
         CascadeType.ALL
      })
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
	
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public User avoidRecursion(){
    	User copy= new User();
    	copy.setAccounts(this.getAccounts());
    	copy.setFirstName(this.getFirstName());
    	copy.setId(this.getId());
    	copy.setLastName(this.getLastName());
    	copy.setPassword(this.getPassword());
    	copy.setRoles(this.getRoles());
    	copy.setUsername(this.getUsername());
    	try{
    		copy.getAccounts().forEach(a->a.setUser(null));
    	}
    	catch(NullPointerException e){}
    	return copy;
    }
    
//    public Long maxAccess(){
//    	if(this.getRoles().isEmpty())
//    		return -1L;
//    	Set<Role> rs=this.getRoles();
//    	Long access=-1L;
//    	for(Role r:rs){
//    		if (r.getAccess()>access)
//    			access=r.getAccess();
//    	}
//    	return access;
//    }

    @Override
    public String toString(){
//    	if(this.getAccounts()==null){
//    		this.setAccounts(new HashSet<Account>());
//    	}
//    	avoidRecursion();
    	 return ""+this.getId()
     	+" Username: "+this.getUsername()+"User: "+this.getFirstName()+" "+this.getLastName(	);
    	
    	}
    
//    @Override
//    public int hashCode(){
//    	long i=this.getId();
//    	return (int)i;
//    }
    
    @Override
    public boolean equals(Object o) {
    	if (o == null || this == null)
    		return false;
    	if (! (o instanceof User) )
    		return false;
    	
    	User other = (User) o;
    	return other.getId() == (this).getId();
    }
}

