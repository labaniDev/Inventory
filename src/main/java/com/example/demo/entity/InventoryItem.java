package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="inventoryorder")
public class InventoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long categoryid;
	private Long productid;
	private Long itemid;
	private Integer quantity;
	private Float price;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fullfillment_id",referencedColumnName="id")
	private PurchaseOrder purchaseOrders;



}
