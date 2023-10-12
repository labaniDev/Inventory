package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Status;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.SupplierRepo;

@Service
public class OrderService {
	
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	SupplierRepo supplierRepo;
	@Autowired
	ModelMapper modelMapper;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	
	public void addOrder(OrderDTO orderDTO) {
		try {
			LOGGER.info("Add Order");
//		Optional<Supplier> supplierOptional=supplierRepo.findById(orderDTO.getSupplier().getId());
//		if(supplierOptional.isPresent()) {
//			Supplier supplier=supplierOptional.get();
			Order order=modelMapper.map(orderDTO, Order.class);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now(); 
			  order.setCreated_at(dtf.format(now));
			  order.setUpdate_at(dtf.format(now));
			  order.setStatus(Status.active);
			  Float promoDiscount = Float.valueOf(orderDTO.getPromo());
			  Float grandtotal=orderDTO.getSubtotal()-orderDTO.getDiscount()+orderDTO.getTax()+orderDTO.getShipping()-promoDiscount;
			  order.setGrandtotal(grandtotal);
			  orderRepo.save(order);
	}catch(Exception ex) {
		ex.printStackTrace();
		LOGGER.error(ex.getMessage());
	}

}
	
	public List<OrderDTO> getOrderBySupplierId(Long id){
		try {
		LOGGER.info("Get Order By Supplier id");	
		Optional<Supplier> supplierOptional=supplierRepo.findById(id);
		if(supplierOptional.isPresent()) {
			Supplier orderSupplier=supplierOptional.get();
			List<Order> orders=orderSupplier.getOrders();
			List<OrderDTO> orderDTOlist=modelMapper.map(orders, new TypeToken<List<OrderDTO>>() {}.getType());
			return orderDTOlist;
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
	}
		return null;
}
}
