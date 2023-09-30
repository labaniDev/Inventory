package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.collection.Suppliers;
import com.example.demo.collection.SuppliersProduct;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.dto.SupplierDetailsResponseDTO;
import com.example.demo.dto.SupplierResponseDTO;
import com.example.demo.entity.Status;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepo;

import com.example.demo.repository.SuppliersRepo;

import lombok.extern.slf4j.Slf4j;

@Service
public class SupplierService {

	@Autowired
	SupplierRepo supplierRepo;
	@Autowired
	SuppliersRepo suppliersRepo;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ModelMapper modelMapper;
//	@Autowired
//	SuppliersProductRepo suppliersProductRepo;

	public static final Logger LOGGER = LoggerFactory.getLogger(SupplierService.class);

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

	public Long addSupplier(SupplierDTO supplierDTO) {
		
		Long supplierId=0L;

		try {

			LOGGER.info("Adding Supplier: " + supplierDTO);

			Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			supplier.setCreated_at(dtf.format(now));
			supplier.setUpdate_at(dtf.format(now));
			supplier.setStatus(Status.inactive);
			supplier = supplierRepo.save(supplier);

			LOGGER.info("Supplier Id: " + supplier.getSupplierid());

			if (supplier.getSupplierid() != null) {
				supplierDTO.setSupplierid(supplier.getSupplierid());
				Suppliers suppliers = modelMapper.map(supplierDTO, Suppliers.class);
				LOGGER.info("Supplier Data before saving to Mongo: " + suppliers);
				suppliersRepo.save(suppliers);
				
				return supplier.getSupplierid();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}

		return supplierId;
	}

	public List<SupplierResponseDTO> getActiveSupplier() {
		try {
			LOGGER.info("GetActive Supplier");
			List<Supplier> suppliers = supplierRepo.findByStatus(Status.active);
			List<SupplierResponseDTO> supplierResponseDTOList = modelMapper.map(suppliers,
					new TypeToken<List<SupplierResponseDTO>>() {
					}.getType());
			return supplierResponseDTOList;
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return null;

	}

	public SupplierDetailsResponseDTO getDetailsBySupplierId(Long supplierId) {

		SupplierDetailsResponseDTO supplierDTO;
		try {

			LOGGER.info("Inside getDetailsBySupplierId :: " + supplierId);

			Suppliers suppliers = suppliersRepo.findBySupplierid(supplierId);

			supplierDTO = modelMapper.map(suppliers, SupplierDetailsResponseDTO.class);

		} catch (Exception ex) {
			supplierDTO = new SupplierDetailsResponseDTO();
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return supplierDTO;

	}
}