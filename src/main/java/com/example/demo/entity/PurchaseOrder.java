package com.example.demo.entity;

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
@Table(name="fullfillmentorder")
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long supplierid;
	private Long categoryid;
	private Long productid;
	private Long itemid;
	private Integer fullfillmentquantity;
	private float price;
	private float total;
	
	@OneToOne	  
	   @JoinColumn(name = "order_id", referencedColumnName = "id") 
	   private Order order;

	
	
}
