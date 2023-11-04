package com.example.demo.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.example.demo.dto.InventoryItemDTO;
import com.example.demo.entity.InventoryItem;
import com.example.demo.repository.InventoryItemRepo;

public class InventoryItemService {
	@Autowired
	InventoryItemRepo inventoryItemRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${test.url}")
	private String testUrl;
	
	private static final String UPDATE_Item="/item/updateItem";
	public static final Logger LOGGER = LoggerFactory.getLogger(InventoryItemService.class);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void updateInventoryItemPrice(InventoryItemDTO inventoryItemDTO) {
		try {
			LOGGER.debug("Inside UpdateInventoryItemPrice::"+inventoryItemDTO.toString());
		String apiUrl=testUrl+UPDATE_Item;
		HttpHeaders headers=new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<InventoryItemDTO> requestEntity = new HttpEntity<>(inventoryItemDTO, headers);
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ResponseEntity.ok();
			} 
		Optional<InventoryItem> inventoryOptional=inventoryItemRepo.findById(inventoryItemDTO.getId());
		if(inventoryOptional.isPresent()) {
			InventoryItem inventory=inventoryOptional.get();
			inventory.setPrice(inventoryItemDTO.getPrice());
			inventoryItemRepo.save(inventory);
			LOGGER.debug("Inventory Item Price updated");
		}
       }catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in updateItemPrice::"+ex.getMessage());
			
		}
	}
	}

