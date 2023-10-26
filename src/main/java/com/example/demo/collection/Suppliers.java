package com.example.demo.collection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.example.demo.dto.SupplierProductDTO;
import lombok.Data;

@Document(collection="supplierlist")
@Data
public class Suppliers {
	@Field("id")
	private Long supplierid;
	private List<SupplierProductDTO> supplierProductList= new ArrayList<SupplierProductDTO>();

}
