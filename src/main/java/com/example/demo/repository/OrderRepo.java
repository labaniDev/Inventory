package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Order;

public interface OrderRepo extends CrudRepository<Order,Long>,JpaRepository<Order,Long>{

}
