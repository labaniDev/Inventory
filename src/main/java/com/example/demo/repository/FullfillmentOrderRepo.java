package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.FullfillmentOrder;

public interface FullfillmentOrderRepo extends CrudRepository<FullfillmentOrder,Long>,JpaRepository<FullfillmentOrder,Long>{

}
