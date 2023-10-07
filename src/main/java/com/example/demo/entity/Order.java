package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private Long userid;
	private Integer type;
	@Enumerated(EnumType.STRING)
	private Status status;
	private Float subtotal;
	//private Float itemdiscount;
	private Float tax;
	private Float shipping;
	//private Float total;
	private String promo;
	private Float discount;
	private Float grandtotal;
	private String created_at;
	private String update_at;
	
	@OneToOne
	@JoinColumn(name="supplierid",referencedColumnName="id")
	private Supplier supplier;

//	@OneToMany(mappedBy="order")
//    private Set<Orderitem> orderitems;

}
