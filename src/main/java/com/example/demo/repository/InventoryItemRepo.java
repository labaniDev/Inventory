package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.InventoryItem;

public interface InventoryItemRepo extends CrudRepository<InventoryItem,Long>,JpaRepository<InventoryItem,Long> {

}
