package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class SupplierDetailsResponseDTO {

	private Long supplierid;
	private List<SupplierProductDTO> supplierProductList= new ArrayList<SupplierProductDTO>();
}
