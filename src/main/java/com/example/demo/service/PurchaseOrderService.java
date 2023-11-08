package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.PurchaseOrderDTO;
import com.example.demo.dto.SupplierResponseDTO;
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
	public void purchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
		try {
			LOGGER.debug("Inside addFullFillmentOrder::"+purchaseOrderDTO.toString());
			Optional<Order> orderOptional=orderRepo.findById(purchaseOrderDTO.getOrder().getId());
			if(orderOptional.isPresent()) {
				Order order=orderOptional.get();
				PurchaseOrder purchaseOrder = modelMapper.map(purchaseOrderDTO, PurchaseOrder.class);
				Integer quantity = purchaseOrderDTO.getFullfillmentquantity();
		        Float price = purchaseOrderDTO.getPrice();
		        Float totalPrice=quantity*price;
		        purchaseOrder.setTotal(totalPrice);
		        purchaseOrderRepo.save(purchaseOrder);
		        LOGGER.debug("Added Successfully");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in addFullFillmentOrder ::"+ex.getMessage());
		}
		
			
	}
	
	public List<PurchaseOrderDTO> getAllPurchaseOrder(){
		try {
			LOGGER.info("Get All PurchaseOrder");
			List<PurchaseOrder> purchaseOrderList=purchaseOrderRepo.findAll();
			List<PurchaseOrderDTO> purchaseOrderDTOList=modelMapper.map(purchaseOrderList,
					new TypeToken<List<PurchaseOrderDTO>>() {
					}.getType());
			return purchaseOrderDTOList;
			
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in Get All PurchaseOrder ::"+ex.getMessage());
		}
		return null;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)

	public void recieveOrder(PurchaseOrderDTO purchaseOrderDTO) {
	try {
	LOGGER.debug("Inside updateselleritem::"+purchaseOrderDTO.toString());
	if (purchaseOrderDTO.getId() == null) {
	LOGGER.error("Id is null in PurchaseOrderDTO");
	return; }
	Optional<PurchaseOrder>orderItem = purchaseOrderRepo.findById(purchaseOrderDTO.getId());
	if(orderItem.isPresent()) {
	PurchaseOrder order = orderItem.get();
	PurchaseOrder updateitemOrder = modelMapper.map(purchaseOrderDTO,PurchaseOrder.class);
	Integer quantity = purchaseOrderDTO.getFullfillmentquantity();
	float price = purchaseOrderDTO.getPrice();
	float totalPrice=quantity*price;
	updateitemOrder.setCategoryid(purchaseOrderDTO.getCategoryid());
	updateitemOrder.setProductid(purchaseOrderDTO.getProductid());
	updateitemOrder.setFullfillmentquantity(purchaseOrderDTO.getFullfillmentquantity());
	updateitemOrder.setItemid(purchaseOrderDTO.getItemid());
	updateitemOrder.setTotal(totalPrice);
	purchaseOrderRepo.save(updateitemOrder);
	LOGGER.debug("UpdateSellerItem Succesfully");
	}
	}catch(Exception ex) {
	ex.printStackTrace();
	LOGGER.error("Exception in addseller::"+ex.getMessage());
	}
	}
	}



