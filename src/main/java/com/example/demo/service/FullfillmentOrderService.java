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

import com.example.demo.dto.FullfillmentOrderDTO;
import com.example.demo.entity.FullfillmentOrder;
import com.example.demo.repository.FullfillmentOrderRepo;

@Service
public class FullfillmentOrderService {
	
	@Autowired
	FullfillmentOrderRepo fullfillmentOrderRepo;
//	@Autowired
//	OrderRepo orderRepo;
	@Autowired
	ModelMapper modelMapper;
	private  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addfullfillmentOrder(FullfillmentOrderDTO fullfillmentOrderDTO) {
		try {
			LOGGER.debug("Inside addFullFillmentOrder::"+fullfillmentOrderDTO.toString());
			
			FullfillmentOrder fullfillmentOrder=modelMapper.map(fullfillmentOrderDTO, FullfillmentOrder.class);
			Integer quantity = fullfillmentOrderDTO.getFullfillmentquantity();
	        Float price = fullfillmentOrderDTO.getPrice();
	        Float totalPrice=quantity*price;
	        fullfillmentOrder.setTotal(totalPrice);
	        fullfillmentOrderRepo.save(fullfillmentOrder);
	        LOGGER.debug("Added Successfully");
			
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in addFullFillmentOrder ::"+ex.getMessage());
		}
		
			
	}

}

