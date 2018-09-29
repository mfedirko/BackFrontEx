//package com.nouhoun.springboot.jwt.integration.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nouhoun.springboot.jwt.integration.domain.Role;
//import com.nouhoun.springboot.jwt.integration.domain.UserRole;
//
//
//public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
//
//	 @Modifying
//	  @Query(value="INSERT INTO user_role (user_id, role_id) VALUES (:userid, :roleid)",nativeQuery = true)
//	 @Transactional(rollbackFor=Exception.class)
//	 void associateRole(@Param("userid") long userId, @Param("roleid")long roleId);
//	  
//	
//	 @Modifying
//	 @Query(value="DELETE from user_role WHERE user_id=:userid"
//	 		+ " AND role_id=:roleId",nativeQuery=true)
//	 @Transactional(rollbackFor=Exception.class)
//	 void deleteRoles(@Param("userid")long userId, @Param("roleId")long roleId);
//	 
//	 @Query("SELECT r FROM User u JOIN u.roles r "
//	 		+ "WHERE u.id=:userId")
//	 List<Role> findByUserId(@Param("userId")long id);
//}
