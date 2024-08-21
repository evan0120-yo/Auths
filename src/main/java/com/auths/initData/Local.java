package com.auths.initData;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.auths.model.Account;
import com.auths.model.Role;
import com.auths.repository.RoleRepository;
import com.auths.repository.UserRepository;

@Component
public class Local {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Bean
	private void initLocal() throws Exception {
		insertDB();
	}
	
	private void insertDB() {
		saveAdminAccount();
	}
	
	private void saveAdminAccount() {
		Role role = Role.builder().authority("ADMIN").build();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		roleRepository.save(role);
		Role role01 = Role.builder().authority("USER").build();
		roles.add(role01);
		roleRepository.save(role01);
		
		Account account = Account.builder()
				.username("evan01203394@gmail.com")
				.accountName("柑仔")
				.password(new BCryptPasswordEncoder().encode("12345678"))
				.roles(roles)
				.build();
		userRepository.save(account);
		
	}
}
