package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.SupplierRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	SupplierRepo supplierRepo;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	
	public void addOrder(OrderDTO orderDTO) {
		try {
			LOGGER.info("Add Order");
		Optional<Supplier> supplierOptional=supplierRepo.findById(orderDTO.getSupplierid());
		//Optional<Order> order=orderRepo.findById(orderDTO.getUserid());
		if(supplierOptional.isPresent()) {
			Supplier supplier=supplierOptional.get();
			Order order=new Order();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now(); 
			  order.setCreated_at(dtf.format(now));
			  order.setUpdate_at(dtf.format(now));
			  order.setOrderstatus(null);
			  orderRepo.save(order);
			
		}
	}catch(Exception ex) {
		ex.printStackTrace();
		LOGGER.error(ex.getMessage());
	}

}
}
