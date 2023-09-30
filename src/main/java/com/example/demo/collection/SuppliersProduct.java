package com.example.demo.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="supplierlist")
@Data
public class SuppliersProduct {
	
	private Long supplierid;
	private Long productId;
	private Long productName;

}
