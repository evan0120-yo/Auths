package com.auths.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auths.model.Account;
import com.auths.object.dto.JwtAuthenticationDTO;
import com.auths.object.dto.RefreshTokenDTO;
import com.auths.object.dto.SigninDTO;
import com.auths.object.dto.SignupDTO;
import com.auths.repository.RoleRepository;
import com.auths.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;
	@Autowired
	private RoleRepository roleRepository;
	
	public Account signup(SignupDTO signupDTO) {
		Account account = Account.builder()
				.username(signupDTO.getUsername())
				.password(passwordEncoder.encode(signupDTO.getPassword()))
				.accountName(signupDTO.getAccountName())
//				.roles("Role")
				.build();
		
		return userRepository.save(account);
	}
	
	public JwtAuthenticationDTO signin(SigninDTO signinDTO) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinDTO.getUsername(), signinDTO.getPassword()));
		
		Account account = userRepository.findByUsername(signinDTO.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		String jwt = jwtService.generateToken(account);
		String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), account);
		
		JwtAuthenticationDTO jwtAuthenticationDTO = new JwtAuthenticationDTO();
		
		jwtAuthenticationDTO.setToken(jwt);
		jwtAuthenticationDTO.setRefreshToken(refreshToken);
		
		return jwtAuthenticationDTO;
	}
	
	public JwtAuthenticationDTO refreshToken(RefreshTokenDTO refreshTokenDTO) {
		String userEmail = jwtService.extractUsername(refreshTokenDTO.getToken());
		Account account = userRepository.findByUsername(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenDTO.getToken(), account)) {
			String jwt = jwtService.generateToken(account);
			
			JwtAuthenticationDTO jwtAuthenticationDTO = new JwtAuthenticationDTO();
			
			jwtAuthenticationDTO.setToken(jwt);
			jwtAuthenticationDTO.setRefreshToken(refreshTokenDTO.getToken());
			
			return jwtAuthenticationDTO;
		}
		return null;
	}
	
}
