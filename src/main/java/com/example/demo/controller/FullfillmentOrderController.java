package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FullfillmentOrderDTO;
import com.example.demo.service.FullfillmentOrderService;

@RestController
@RequestMapping("/api/fullfillmentOrder")
public class FullfillmentOrderController {
	
	@Autowired
	FullfillmentOrderService fullfillmentOrderService;
	
	@PostMapping("/addPurchaseOrder")
	public ResponseEntity<String> addPurchaseOrder(FullfillmentOrderDTO fullfillmentOrderDTO) {
		fullfillmentOrderService.addfullfillmentOrder(fullfillmentOrderDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
