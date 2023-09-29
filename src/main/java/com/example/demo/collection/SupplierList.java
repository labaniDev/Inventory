package com.example.demo.collection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="supplierlist")
@Data
public class SupplierList {
	
	private Long supplierid;
	private Long categoryId;
	private String categoryName;
	private Long productId;
	private String productName;
	private List<ItemList> itemList=new ArrayList<>();

}
