package com.auths.object.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupDTO {

	private String username;
	
	private String password;
	
	private String accountName;
	
}
