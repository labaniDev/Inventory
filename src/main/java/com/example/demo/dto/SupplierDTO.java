package com.example.demo.dto;

import java.sql.Timestamp;
import com.example.demo.entity.Status;

import lombok.Data;

@Data
public class SupplierDTO {
	
	private Long supplierid;
	private String supplier_name;
	private String address;
	private String created_at;
	private String update_at;
	private Status status;
	
	private Long productid;
	private Long categoryid;

}
