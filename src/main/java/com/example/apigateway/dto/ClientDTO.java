package com.example.apigateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

	private Long id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String salt;
	private boolean isDeleted;
	private Long cartId;
	private boolean isBlocked;

}
