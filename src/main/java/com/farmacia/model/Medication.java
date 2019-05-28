package com.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "medication")
public class Medication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String price;
	private int quantity;
	private String dataFab;
	private String dataVenc;
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name = "medication_type_medication",
	joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "type_medication_id" , referencedColumnName = "id"))
	private List<TypeMedication> typeMedication;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getDataFab() {
		return dataFab;
	}
	
	public void setDataFab(String dataFab) {
		this.dataFab = dataFab;
	}
	
	public String getDataVenc() {
		return dataVenc;
	}
	
	public void setDataVenc(String dataVenc) {
		this.dataVenc = dataVenc;
	}
	
	public List<TypeMedication> getTypeMedication() {
		return typeMedication;
	}
	
	public void setTypeMedication(List<TypeMedication> typeMedication) {
		this.typeMedication = typeMedication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medication other = (Medication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
