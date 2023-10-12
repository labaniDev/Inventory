package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderitemDTO;
import com.example.demo.service.OrderItemService;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {
	@Autowired
	OrderItemService orderItemService;
	
	@PostMapping("/addOrderItem")
	public ResponseEntity<String> addOrderitem(@RequestBody OrderDTO orderDTO) {
		orderItemService.addOrderItem(orderDTO);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	@GetMapping("/getOrderitemByOrderId/{id}")
	public List<OrderitemDTO> getOrderitemByorderId(@PathVariable("id") Long id){
		return orderItemService.getOrderitemByOrderid(id);
	}

}
