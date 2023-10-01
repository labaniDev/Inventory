package com.example.demo.dto;

import com.example.demo.entity.Status;

import lombok.Data;
@Data
public class OrderDTO {
	
	private Long id;
	private Long supplierid;
	//private Long userid;
	private Integer type;
	private Status status;
	private Float subtotal;
	private Float itemdiscount;
	private Float tax;
	private Float shipping;
	private Float total;
	private String promo;
	private Float discount;
	private Float grandtotal;
	private String created_at;
	private String update_at;

}
