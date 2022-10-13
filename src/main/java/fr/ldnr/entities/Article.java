package fr.ldnr.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Article implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=5,max=50)
	private String description;
	
	@DecimalMin("50")
	private double price;
	
	@Min(value=1)
	private int quantity;
	
	@ManyToOne
	private Category category;	//plusieurs articles sont liés à une seule categorie	
	
	public Article() {
		this.category = new Category("");
		this.quantity = 1;
	}
	
	public Article(Long id, @NotNull @Size(min = 5, max = 50) String description, @DecimalMin("50") double price,
			@Min(1) int quantity, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", price=" + price + ", quantity=" + quantity
				+ ", category=" + category + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
