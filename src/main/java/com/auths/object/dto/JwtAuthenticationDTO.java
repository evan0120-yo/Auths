package com.auths.object.dto;

import lombok.Data;

@Data
public class JwtAuthenticationDTO {

	private String token;
	private String refreshToken;
}
