package com.example.demo.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.PurchaseOrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.PurchaseOrder;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.PurchaseOrderRepo;

@Service
public class PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepo purchaseOrderRepo;
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	ModelMapper modelMapper;
	private  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addfullfillmentOrder(PurchaseOrderDTO purchaseOrderDTO) {
		try {
			LOGGER.debug("Inside addFullFillmentOrder::"+purchaseOrderDTO.toString());
			Optional<Order> orderOptional=orderRepo.findById(purchaseOrderDTO.getOrder().getId());
			if(orderOptional.isPresent()) {
				Order order=orderOptional.get();
				PurchaseOrder purchaseOrder = modelMapper.map(purchaseOrderDTO, PurchaseOrder.class);
				Integer quantity = purchaseOrderDTO.getFullfillmentquantity();
		        float price = purchaseOrderDTO.getPrice();
		        float totalPrice=quantity*price;
		        purchaseOrder.setTotal(totalPrice);
		        purchaseOrderRepo.save(purchaseOrder);
		        LOGGER.debug("Added Successfully");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in addFullFillmentOrder ::"+ex.getMessage());
		}
		
			
	}

}

