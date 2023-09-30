package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.collection.Suppliers;

public interface SuppliersRepo extends MongoRepository<Suppliers,Long> {
	
	Suppliers findBySupplierid(Long supplierid);

}
