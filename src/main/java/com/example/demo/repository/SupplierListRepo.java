package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.collection.SupplierList;

public interface SupplierListRepo extends MongoRepository<SupplierList,Long> {

}
