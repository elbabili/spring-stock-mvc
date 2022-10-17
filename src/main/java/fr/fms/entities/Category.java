package fr.fms.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private Collection<Article> articles;	//une categorie est liée à plusieurs articles
	
	public Category(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}	
}
