package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.Status;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepo;



@Service
public class SupplierService {
	
	@Autowired
	SupplierRepo supplierRepo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ModelMapper modelMapper;
	
	
//	@Value("${test.url}")
//	private String testUrl;
//	
//	private static final String GET_ALL_CATEGORY="/category/getallcategory";
//	private static final String GET_ALL_PRODUCT="/product/getProductByid/{categoryid}";
//	private static final String GET_ALL_ITEM="/item//getItemByProductId/{productid}";
//	
//	
//	
//
//
//	public void getSupplier(SupplierDTO supplierDTO)  {
//		String apiUrl=testUrl+GET_ALL_CATEGORY+GET_ALL_PRODUCT+GET_ALL_ITEM;
//		 
//		HttpHeaders headers=new HttpHeaders();
//	headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<SupplierDTO> requestEntity = new HttpEntity<>(supplierDTO, headers);
//		ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
//		if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
//			ResponseEntity.ok();
//			}            
//       }
	
	public void addSupplier(SupplierDTO supplierDTO) {
		
	
		Supplier supplier=modelMapper.map(supplierDTO, Supplier.class);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now();
			  supplier.setCreated_at(dtf.format(now));
			  supplier.setUpdate_at(dtf.format(now));
			  supplier.setStatus(Status.active);
			  supplierRepo.save(supplier);
		}
		
		
		
	}
	

	
	

