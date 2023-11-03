package com.example.demo.dto;
import java.util.Set;

import com.example.demo.entity.Orderstatus;
import com.example.demo.entity.Status;
import lombok.Data;
@Data
public class OrderDTO {
	
	private Long id;
	private Integer type;
	private Status status;
	private Orderstatus orderstatus;
	private Float subtotal;
	private Float tax;
	private Float shipping;
	private String promo;
	private Float discount;
	private Float grandtotal;
	private String created_at;
	private String update_at;
	private SupplierDTO supplier;
	private Set<OrderitemDTO> orderitems;

}
