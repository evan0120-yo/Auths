package com.auths.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auths.object.dto.RefreshTokenDTO;
import com.auths.object.dto.SigninDTO;
import com.auths.object.dto.SignupDTO;
import com.auths.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
		return ResponseEntity.ok().body(authenticationService.signup(signupDTO));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody SigninDTO signinDTO) {
		return ResponseEntity.ok().body(authenticationService.signin(signinDTO));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
		return ResponseEntity.ok().body(authenticationService.refreshToken(refreshTokenDTO));
	}
}
