package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import java.util.Set;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderitemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Orderitem;
import com.example.demo.entity.Status;
import com.example.demo.repository.OrderItemRepo;
import com.example.demo.repository.OrderRepo;

@Service
public class OrderItemService {
	
	@Autowired
	OrderItemRepo orderItemRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	OrderRepo orderRepo;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderItemService.class);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addOrderItem(OrderDTO orderDTO) {
		
		try {
			LOGGER.debug("Inside add orderItem::"+orderDTO.toString());
			 Optional<Order> orderOptional=orderRepo.findById(orderDTO.getId());
			 if(orderOptional.isPresent()) {
				 Order order = orderOptional.get();
				 List<Orderitem> orderitems=modelMapper.map(orderDTO.getOrderitems(), new TypeToken<List<Orderitem>>()  {}.getType());
				 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				  LocalDateTime now = LocalDateTime.now(); 
				 orderitems.stream().forEach(ord->{
					  ord.setCreated_at(dtf.format(now));
					  ord.setUpdate_at(dtf.format(now));
					  ord.setStatus(Status.active);
					  ord.setOrder(order);
				 }); 
				 order.getOrderitems().addAll(orderitems);
				 orderRepo.save(order);
				 LOGGER.debug("OrderItem Added Successfully");
			 }
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in addOrderItem::"+ex.getMessage());
		}
	}
	public List<OrderitemDTO> getOrderitemByOrderid(Long id){
		try {
			LOGGER.info("Get OrderItem By OrderId");
			Optional<Order> order=orderRepo.findById(id);
			if(order.isPresent()) {
				Order orderItems=order.get();
				Set<Orderitem> items=orderItems.getOrderitems();
				List<OrderitemDTO> itemdtoList=modelMapper.map(items,new TypeToken<List<OrderitemDTO>>() {}.getType() );
				return itemdtoList;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
	}
		return null;
	
	}
}
