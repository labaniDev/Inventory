package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemDTO {
	private Long id;
	private Long categoryid;
	private Long productid;
	private Long itemid;
	private Integer quantity;
	private Float price;
	private PurchaseOrderDTO purchaseorders;

}
