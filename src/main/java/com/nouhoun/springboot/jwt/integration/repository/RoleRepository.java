package com.nouhoun.springboot.jwt.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nouhoun.springboot.jwt.integration.domain.Role;

/**
 * Created by mifedirko 01/2018
 */

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("SELECT p FROM Role p WHERE LOWER(roleName) = LOWER(:roleName)")
	List<Role> findByRolename(@Param("roleName")String roleName);
}
