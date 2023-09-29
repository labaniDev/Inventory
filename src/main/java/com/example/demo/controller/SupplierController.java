package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.collection.SupplierList;
import com.example.demo.dto.SupplierDTO;
import com.example.demo.service.SupplierListService;
import com.example.demo.service.SupplierService;



@RestController
@RequestMapping("/api/inventory")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	@Autowired
	SupplierListService supplierListService;
	
	@PostMapping("/addSupplier")
	public ResponseEntity<SupplierDTO> addSupplier(@RequestBody SupplierDTO supplierDTO) {
		supplierService.addSupplier(supplierDTO);
		return new ResponseEntity<SupplierDTO>(HttpStatus.CREATED);	
	}
	
	@PostMapping("/addSupplierList")
	public ResponseEntity<SupplierList> addSupplierList(@RequestBody SupplierList supplierList){
		supplierListService.addSupplierList(supplierList);
		return new ResponseEntity<SupplierList>(HttpStatus.OK);
	}
	

}
