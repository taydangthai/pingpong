package com.pingpong.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pingpong.common.entity.Role;

@Repository
public interface RoleReposiroty extends CrudRepository<Role, Integer>{

	
}
