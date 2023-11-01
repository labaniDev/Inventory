package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/getOrderBySupplierId/{id}")
	public List<OrderDTO> getOrderBySupplierId(@PathVariable("id") Long id){
		return orderService.getOrderBySupplierId(id);
	}
	@PutMapping("/updateOrder")
	public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderDTO) {
		orderService.updateOrder(orderDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@GetMapping("/getAllOrders")
	public List<OrderDTO> getAllOrders(){
		return orderService.getAllOrder();
	}
	@DeleteMapping("/inactiveOrder/{id}")
	public String inactiveOrder(@PathVariable("id") Long id) {
		orderService.InactiveOrderById(id);
		return "Order Successfully Marked As Inactive";
	}

}
