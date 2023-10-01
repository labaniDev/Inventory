package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("/addOrder")
	public ResponseEntity<String> addOrder(@RequestBody OrderDTO orderDTO) {
		orderService.addOrder(orderDTO);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
