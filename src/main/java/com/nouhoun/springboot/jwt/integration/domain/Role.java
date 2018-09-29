package com.nouhoun.springboot.jwt.integration.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by nydiarra on 06/05/17.
 */
@Entity
@Table(name="app_role")
public class Role {
	

    private static final Long serialVersionUID = 1L;
    
	public Role () {}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
    		mappedBy = "roles", targetEntity = User.class)
        private Set<User> users;

    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
//	   public Role avoidRecursion(){
//	    	Role copy= new Role();
//	    	copy.setAccess(this.getAccess());
//	    	copy.setDescription(this.getDescription());
//	    	copy.setId(this.getId());
//	    	copy.setRoleName(this.getRoleName());
//	    	copy.setUsers(null);
//	    	
//	    	return copy;
//	    }

	//    //@ManyToOne
//    //@Column(name="id")
//    private UserRole userRole;
//    
//    //@Column(name="access")
//    private Long access;
//    
//    public Long getAccess() {
//		return access;
//	}
//
//	public void setAccess(Long access) {
//		this.access = access;
//	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int hashCode(){
    	long i=this.getId();
    	return (int) i;
    }
}
