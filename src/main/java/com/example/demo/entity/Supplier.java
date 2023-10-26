package com.example.demo.entity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String supplier_name;
	private String address;
	private String created_at;
	private String update_at;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy="supplier")
	private List<Order> orders;
	

	

}
