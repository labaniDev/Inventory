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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.example.demo.collection.Suppliers;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.dto.SupplierDetailsResponseDTO;
import com.example.demo.dto.SupplierResponseDTO;
import com.example.demo.entity.Status;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepo;
import com.example.demo.repository.SuppliersRepo;
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

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public Long addSupplier(SupplierDTO supplierDTO) {
		
		Long supplierId=0L;

		try {

			LOGGER.debug("Inside addSupplier::"+supplierDTO.toString());

			Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			supplier.setCreated_at(dtf.format(now));
			supplier.setUpdate_at(dtf.format(now));
			supplier.setStatus(Status.active);
			supplier = supplierRepo.save(supplier);
			LOGGER.info("Supplier Id: " + supplier.getId());
			if (supplier.getId()!= null) {
				Suppliers suppliers=new Suppliers();
				suppliers.setSupplierid(supplier.getId());
				suppliers.setSupplierProductList(supplierDTO.getSupplierProductList());
				LOGGER.info("Supplier Data before saving to Mongo: " + suppliers);
				suppliersRepo.save(suppliers);
				LOGGER.debug("Complete AddSupplier");
				return supplier.getId();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exceptionn in addSupplier::"+ex.getMessage());
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

	public SupplierDetailsResponseDTO getDetailsBySupplierId(Long id) {

		SupplierDetailsResponseDTO supplierDTO;
		try {

			LOGGER.info("Inside getDetailsBySupplierId :: " + id);
			Suppliers suppliers = suppliersRepo.findBySupplierid(id);
			supplierDTO = modelMapper.map(suppliers, SupplierDetailsResponseDTO.class);
		} catch (Exception ex) {
			supplierDTO = new SupplierDetailsResponseDTO();
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return supplierDTO;

	}
	
	//Update Supplier
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void updateSupplier(SupplierDTO supplierDTO) {
		try {
			LOGGER.debug("Inside updateSupplier::" +supplierDTO.toString());
		Optional<Supplier> supplierOptional=supplierRepo.findById(supplierDTO.getId());
		if(supplierOptional.isPresent()) {
			Supplier supplier=supplierOptional.get();
			supplier.setSupplier_name(supplierDTO.getSupplier_name());
			supplier.setAddress(supplierDTO.getAddress());
			supplierRepo.save(supplier);
			if(supplier.getId()!=null) {
				Suppliers suppliers=new Suppliers();
				suppliers.setSupplierid(supplierDTO.getId());
				suppliers.setSupplierProductList(supplierDTO.getSupplierProductList());
				LOGGER.info("Supplier Data before saving to Mongo: " + suppliers);
				suppliersRepo.save(suppliers);
			}
			
		}
	}catch (Exception ex) {
		ex.printStackTrace();
		LOGGER.error("Exception in updateSupplier::"+ex.getMessage());
	}
}
}

