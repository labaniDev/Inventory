package com.example.demo.collection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.SupplierProductDTO;
import com.example.demo.entity.Status;

import lombok.Data;

@Document(collection="supplierlist")
@Data
public class Suppliers {
	
	private Long supplierid;
	private List<SupplierProductDTO> supplierProductList= new ArrayList<SupplierProductDTO>();

}
