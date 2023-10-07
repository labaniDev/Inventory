package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Orderitem;

@Repository
public interface OrderItemRepo extends CrudRepository<Orderitem,Long>,JpaRepository<Orderitem,Long>{

}
