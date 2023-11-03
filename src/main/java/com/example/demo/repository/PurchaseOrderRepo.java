package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.PurchaseOrder;

public interface PurchaseOrderRepo extends CrudRepository<PurchaseOrder,Long>,JpaRepository<PurchaseOrder,Long>{

}
