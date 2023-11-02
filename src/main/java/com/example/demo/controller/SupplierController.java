package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dto.SupplierDTO;
import com.example.demo.dto.SupplierDetailsResponseDTO;
import com.example.demo.dto.SupplierResponseDTO;
import com.example.demo.service.SupplierService;



@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;

	
	@PostMapping("/addSupplier")
	public ResponseEntity<String> addSupplier(@RequestBody SupplierDTO supplierDTO) {
		Long supplierId=supplierService.addSupplier(supplierDTO);
		if(supplierId!=0L)
		return new ResponseEntity<String>("Supplier with "+supplierId+" Added Successfully",HttpStatus.CREATED);	
		else
		return new ResponseEntity<String>("Supplier not added",HttpStatus.OK);
	}
	@GetMapping("/getActiveSupplier")
	public List<SupplierResponseDTO> getActiveSupplier(){
		 return supplierService.getActiveSupplier();
		
	}
	@GetMapping("/getSupplierDetails/{supplierId}")
	public ResponseEntity<SupplierDetailsResponseDTO> getDetailsBySupplierid(@PathVariable Long supplierId){
		SupplierDetailsResponseDTO supplierDTO=supplierService.getDetailsBySupplierId(supplierId);
		return new ResponseEntity<SupplierDetailsResponseDTO>(supplierDTO,HttpStatus.OK);	
	}
	@PutMapping("/updateSupplier")
	public ResponseEntity<String> updateSupplier(@RequestBody SupplierDTO supplierDTO) {
		supplierService.updateSupplier(supplierDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	

}
