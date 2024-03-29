package com.vti.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInfoDTO {

	private short id;

	private String fullName;
	
	public LoginInfoDTO(short id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
}
