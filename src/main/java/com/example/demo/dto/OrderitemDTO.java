package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderitemDTO {
	
	private Long id;
	private Long productid;
	private Long itemid;
	private Long orderid;
	private Float price;
	private Float discount;
	private Integer quantity;
	private String created_at;
	private String update_at;

}
