package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class SupplierDTO {
	
	private Long id;
	private String supplier_name;
	private String address;
	private String created_at;
	private String update_at;
	private Status status; 
	private List<SupplierProductDTO> supplierProductList= new ArrayList<SupplierProductDTO>();
	


}
