package com.example.demo.service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Orderstatus;
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
	
	private  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addOrder(OrderDTO orderDTO) {
		try {
			LOGGER.debug("addOder::"+orderDTO.toString());
			Order order=modelMapper.map(orderDTO, Order.class);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			  LocalDateTime now = LocalDateTime.now(); 
			  order.setCreated_at(dtf.format(now));
			  order.setUpdate_at(dtf.format(now));
			  order.setStatus(Status.active);
			  order.setOrderstatus(Orderstatus.place);
			  Float promoDiscount = Float.valueOf(orderDTO.getPromo());
			  Float grandtotal=orderDTO.getSubtotal()-orderDTO.getDiscount()+orderDTO.getTax()+orderDTO.getShipping()-promoDiscount;
			  order.setGrandtotal(grandtotal);
			  orderRepo.save(order);
			  LOGGER.debug("Order added Successfully");
	}catch(Exception ex) {
		ex.printStackTrace();
		LOGGER.error("Exception in Add Order::"+ex.getMessage());
	}

}
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void updateOrder(OrderDTO orderDTO) {
		try {
			LOGGER.debug("Inside UpdateOrder::"+orderDTO.toString());
		Optional<Supplier> supplierOptional=supplierRepo.findById(orderDTO.getSupplier().getId());
		Optional<Order> orderOptional=orderRepo.findById(orderDTO.getId());
		if(supplierOptional.isPresent()&&orderOptional.isPresent()) {
			Order order=orderOptional.get();
			Supplier supplier=supplierOptional.get();
			order.setDiscount(orderDTO.getDiscount());
			order.setSubtotal(orderDTO.getSubtotal());
			order.setTax(orderDTO.getTax());
			order.setShipping(orderDTO.getShipping());
			order.setType(orderDTO.getType());
			Float promoDiscount = Float.valueOf(orderDTO.getPromo());
			Float grandtotal=orderDTO.getSubtotal()-orderDTO.getDiscount()+orderDTO.getTax()+orderDTO.getShipping()-promoDiscount;
			order.setSupplier(supplier);
			orderRepo.save(order);
			LOGGER.debug("Order Update Successfully");
		}}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error("Exception in update order::"+ex.getMessage());
		}
	}
	
	public String InactiveOrderById(Long id) {
		try {
			LOGGER.info("Inactive Order By OrderId");
			Optional<Order> orderOptional=orderRepo.findById(id);
			if(orderOptional.isPresent()) {
				Order order=orderOptional.get();
				order.setStatus(Status.inactive);
				orderRepo.save(order);
				return "Order Succesfully marked as inactive";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
		return "Order not found";
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
	public List<OrderDTO> getAllOrder(){
		try {
			LOGGER.info("Get All Order");
			List<Order> orderList=orderRepo.findAll();
			List<OrderDTO> orderDTOList= modelMapper.map(orderList,new TypeToken<List<OrderDTO>>() {}.getType() );
			return orderDTOList;
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
	}
		return null;
		
	}
}
