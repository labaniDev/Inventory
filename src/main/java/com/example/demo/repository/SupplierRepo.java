package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Status;
import com.example.demo.entity.Supplier;
import java.util.List;


@Repository
public interface SupplierRepo extends CrudRepository<Supplier,Long>,JpaRepository<Supplier,Long> {
	
	List<Supplier> findByStatus(Status status);

}
