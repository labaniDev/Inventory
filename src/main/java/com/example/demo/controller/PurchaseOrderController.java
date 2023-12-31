package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.PurchaseOrderDTO;
import com.example.demo.service.PurchaseOrderService;
@RestController
@RequestMapping("/api/purchaseOrder")
public class PurchaseOrderController {
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@PostMapping("/addPurchaseOrder")
	public ResponseEntity<String> addPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		purchaseOrderService.purchaseOrder(purchaseOrderDTO);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	@PostMapping("/recievePurchaseOrder")
	public ResponseEntity<String> recievePurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
		purchaseOrderService.recieveOrder(purchaseOrderDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@GetMapping("/getAllPurchaseOrder")
	public List<PurchaseOrderDTO> getAllPurchaseOrder(){
		return purchaseOrderService.getAllPurchaseOrder();
	}

}
