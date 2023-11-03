package com.example.demo.dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PurchaseOrderDTO {
	private Long id;
	private Long supplierid;
	private Long categoryid;
	private Long productid;
	private Long itemid;
	private Integer fullfillmentquantity;
	private float price;
	private float total;
	private OrderDTO order;

	
}
