package com.auths.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auths.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
