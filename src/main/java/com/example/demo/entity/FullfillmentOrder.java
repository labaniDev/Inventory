package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name="fullfillmentorder")
public class FullfillmentOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderid;
	private Long supplierid;
	private Long categoryid;
	private Long productid;
	private Long itemid;
	private Integer fullfillmentquantity;
	private Float price;
	private Float total;
	
	

	
	
}
