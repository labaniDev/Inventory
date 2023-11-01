package com.example.demo.entity;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer type;
	@Enumerated(EnumType.STRING)
	private Status status;
	private Float subtotal;
	private Float tax;
	private Float shipping;
	private String promo;
	private Float discount;
	private Float grandtotal;
	private String created_at;
	private String update_at;   
	
	   @JsonIgnore
	   @OneToOne	  
	   @JoinColumn(name = "supplier_id", referencedColumnName = "id") 
	   private Supplier supplier;
	
	  @JsonIgnore
	@OneToMany(mappedBy="order",cascade = CascadeType.ALL)
    private Set<Orderitem> orderitems;

}
