package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProduct {
	private Long id;
	private Long productid;
	private Long itemid;
	private Long supplierid;
	private Integer noofitemsafterorder;

}
