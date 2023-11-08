package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InventoryItemDTO;
import com.example.demo.dto.ItemResponseDTO;
import com.example.demo.service.InventoryItemService;

@RestController
@RequestMapping("/api/inventoryItem")
public class InventoryItemController {
	@Autowired
	InventoryItemService inventoryItemService;
	
	@PostMapping("/addInventoryItem")
	public ResponseEntity<String>addSeller( @RequestBody InventoryItemDTO iventoryItemDTO){
		inventoryItemService.addInventoryitem(iventoryItemDTO);
		return new ResponseEntity<String>(HttpStatus.OK);	
	   }
	// to Get all inventoryItem
	@GetMapping("/getAllInventoryItem")
	public  List<InventoryItemDTO>getAllinventoryItem(){
		return inventoryItemService.getAllinventoyItem();
	   }
	@PutMapping("/updateInventoryItemPrice")
	public ResponseEntity<String> updateInventoryItemPrice(@RequestBody ItemResponseDTO itemResponseDTO) {
		inventoryItemService.updateInventoryItemPrice(itemResponseDTO);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
