package com.nouhoun.springboot.jwt.integration.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nouhoun.springboot.jwt.integration.domain.User;

/**
 * Created by mifedirko 01/2018
 */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	 
//----------------Non-access-filtered queries (for system use only)-------------------

	@Query("from User as u WHERE UPPER(u.username) = UPPER( :username )"
	 	)
	List<User> findByUsername(@Param("username")String username);
	

	
	@Query(value="SELECT au.* FROM app_user au "
	 		,nativeQuery=true)
	List<User> findAllFiltered();
	
	  @Modifying
	  @Query("UPDATE User c SET c.firstName=:firstName, c.lastName=:lastName,"+
	  "c.username=:username,c.password=:password WHERE c.id = :id")
	  @Transactional(rollbackFor=Exception.class)
	 void update(@Param("id") long id,@Param("firstName") String firstname,@Param("lastName") String lastname,
		 @Param("username") String username,@Param("password") String password);
	  
	  @Query(value="SELECT au.* FROM app_user au "
		 			
		 		+ " WHERE LOWER(au.first_name) LIKE %:firstName%"
		 		,nativeQuery=true)
		List<User> findByFirstName( @Param("firstName") String key);
	  
	  @Query(value="SELECT au.* from app_user au "
			 	+ " WHERE username=:username"
		 		,nativeQuery=true)
	  User findOneByUsername(@Param("username") String username);
	  
	  
	  @Query(value="SELECT au.* from app_user au "
				+ " WHERE au.id=:id"
		 	,nativeQuery=true)
	  User findOneById(@Param("id") long id);
	  
	  
	
	 @Modifying
	  @Query(value="INSERT INTO app_user (username, password, first_name, last_name) VALUES (:username, :password, :firstName, :lastName)",nativeQuery = true)
	 @Transactional(rollbackFor=Exception.class)
	 void createUser(@Param("username") String username, @Param("password")String password,@Param("firstName") String firstName,@Param("lastName")  String lastName);
	  
	
	 @Query(value="SELECT au.* FROM app_user au "
	 					 		
	 		+ " WHERE LOWER(au.last_name) LIKE %:lastName%"
	 		,nativeQuery=true)
	List<User> findByLastName(@Param("lastName")String lastName);

	 
	 
	 
}
