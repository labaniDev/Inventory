package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Orderitem;
public interface OrderItemRepo extends CrudRepository<Orderitem,Long>,JpaRepository<Orderitem,Long>{

}
