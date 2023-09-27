package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.repository.SupplierRepo;



@Service
public class SupplierService {
	
	@Autowired
	SupplierRepo supplierRepo;
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${service.url}")
	private String serviceUrl;
	@Value("${test.url}")
	private String testUrl;
	@Value("${endpoint.url}")
	private String endpointUrl;
	
	


	public void addSupplier(SupplierDTO supplierDTO)  {
		String apiUrl = serviceUrl + testUrl + "/" + endpointUrl;
		
		SupplierDTO supplier=new SupplierDTO();
		supplier.setSupplier_name(supplierDTO.getSupplier_name());
		supplier.setAddress(supplierDTO.getAddress());
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now(); 
		  
		  supplier.setCreated_at(dtf.format(now));
		  supplier.setUpdate_at(dtf.format(now));
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SupplierDTO> requestEntity = new HttpEntity<>(supplierDTO, headers);
		ResponseEntity<Void> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Void.class);
		if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
       }
}
}
