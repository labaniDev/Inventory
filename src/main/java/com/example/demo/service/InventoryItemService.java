package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.example.demo.dto.InventoryItemDTO;
import com.example.demo.dto.ItemResponseDTO;
import com.example.demo.entity.InventoryItem;
import com.example.demo.entity.PurchaseOrder;
import com.example.demo.repository.InventoryItemRepo;
import com.example.demo.repository.PurchaseOrderRepo;

@Service
public class InventoryItemService {
	@Autowired
	InventoryItemRepo inventoryItemRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	PurchaseOrderRepo purchaseOrderRepo;
	
	@Value("${test.url}")
	private String testUrl;
	
	private static final String UPDATE_ItemPrice="/item/updateItemPrice";
	
	public void addInventoryitem(InventoryItemDTO inventoryItemDTO) {
		try {
			LOGGER.debug("Inside Add Inventoryitem::"+inventoryItemDTO.toString());
		    Optional<PurchaseOrder>	purchaseOrderoptional = purchaseOrderRepo.findById(inventoryItemDTO.getPurchaseorders().getId());
			if(purchaseOrderoptional.isPresent()) {
			PurchaseOrder purchaseOrder = purchaseOrderoptional.get();       
	        InventoryItem inventoryItem = new InventoryItem();
	        inventoryItem.setCategoryid(inventoryItemDTO.getCategoryid());
	        inventoryItem.setProductid(inventoryItemDTO.getProductid());
	        inventoryItem.setItemid(inventoryItemDTO.getItemid());
	        inventoryItem.setQuantity(inventoryItemDTO.getQuantity());
//	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//			LocalDateTime now = LocalDateTime.now();
//			inventoryItem.sesetCreated_at(dtf.format(now));
//			inventoryItem.setUpdate_at(dtf.format(now));
	        inventoryItem.setPurchaseOrders(purchaseOrder);
	        // Save the InventoryItem
	        inventoryItemRepo.save(inventoryItem);
	        LOGGER.debug("InventoryItem added successfully.");
		}else {
			LOGGER.debug("PurchaseOrder not found with ID: " + inventoryItemDTO.getPurchaseorders().getId());
		  }		
		}catch (Exception e) {
	        LOGGER.error("Error adding InventoryItem: " + e.getMessage());
	      }
	   }

	public List<InventoryItemDTO>getAllinventoyItem(){
		try {
			LOGGER.debug("Inside Getall InventoryItem");
			List<InventoryItem>inventoryitemList = inventoryItemRepo.findAll();
			List<InventoryItemDTO>inventoryitemDTOList = modelMapper.map(inventoryitemList,new TypeToken<List<InventoryItemDTO>>() {}.getType());
			return inventoryitemDTOList;
		}catch (Exception ex){
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}return null;				
	  }
	
	
	public static final Logger LOGGER = LoggerFactory.getLogger(InventoryItemService.class);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void updateInventoryItemPrice(ItemResponseDTO itemResponseDTO) {
		try {
			LOGGER.debug("Inside UpdateInventoryItemPrice::"+itemResponseDTO.toString());
		String apiUrl=testUrl+UPDATE_ItemPrice;
		HttpHeaders headers=new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ItemResponseDTO> requestEntity = new HttpEntity<>(itemResponseDTO, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.PUT, requestEntity, String.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ResponseEntity.ok();
			} 
//		Optional<InventoryItem> inventoryOptional=inventoryItemRepo.findById(itemResponseDTO.getPurchaseorders().getId());
//		if(inventoryOptional.isPresent()) {
//			InventoryItem inventory=inventoryOptional.get();
//			inventory.setPrice(itemResponseDTO.getPrice());
//		inventoryItemRepo.save(inventory);
//			LOGGER.debug("Inventory Item Price updated");
//		}
       }catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in updateItemPrice::"+ex.getMessage());
			
		}
	}
	}

