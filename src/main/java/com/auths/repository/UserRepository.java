package com.auths.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auths.model.Account;
import com.auths.model.Role;

public interface UserRepository extends JpaRepository<Account, String> {

	Optional<Account> findByUsername(String username);
	Account findByRoles(Role role);
}
