package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.collection.SupplierList;
import com.example.demo.repository.SupplierListRepo;

@Service
public class SupplierListService {
	@Autowired
	SupplierListRepo supplierListRepo;
	
	public void addSupplierList(SupplierList supplierList) {
		Optional<SupplierList> supplierListOptional=supplierListRepo.findById(supplierList.getSupplierid());
				if(supplierListOptional.isPresent()) {
					supplierList.setProductId(supplierList.getProductId());
					supplierList.setProductName(supplierList.getProductName());
					supplierList.setCategoryId(supplierList.getCategoryId());
					supplierList.setCategoryName(supplierList.getCategoryName());
					supplierList.setItemList(supplierList.getItemList());
					supplierListRepo.save(supplierList);
			
		}
	}
}


