package com.example.apigateway.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.apigateway.dto.ClientDTO;

@RestController
public class ClientController {

	
	@GetMapping("/clients/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
		HashMap<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
		
		ClientDTO clientDTO = new RestTemplate().getForObject("http://localhost:8000/clients/{id}", ClientDTO.class, uriVariables);
		
		return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
	}
	
}	
