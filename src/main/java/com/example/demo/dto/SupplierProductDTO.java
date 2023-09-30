package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class SupplierProductDTO {
	
	private CategoryDTO category;
	private ProductDTO product;
	private List<ItemDTO> itemList =new ArrayList<ItemDTO>();

}
