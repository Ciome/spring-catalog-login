package it.springcataloglogin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="catalog")
public class CatalogEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="img")
	private String img;

	public CatalogEntity() {}

	public CatalogEntity(String name, String description, String img) {
		this.name = name;
		this.description = description;
		this.img = img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String picturePath) {
		this.img = picturePath;
	}

	@Override
	public String toString() {
		return "CatalogEntity [id=" + id + ", name=" + name + ", description=" + description + ", img="
				+ img + "]";
	}
	
}
