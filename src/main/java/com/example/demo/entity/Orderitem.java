package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orderitem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productid;
	private Long itemid;
	private Float price;
	private Float discount;
	private Integer quantity;
	private String created_at;
	private String update_at;
	
//	@OneToOne
//	@JoinColumn(name="order_id",referencedColumnName="id")
//	private Order order;

}
