package org.roy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	@NotEmpty(message = "Book name mandatory")
	private String name;

	@Column(name = "author")
	@NotEmpty(message = "Author name mandatory")
	private String author;

	@Column(name = "purchase")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchase;

	public Book() {

	}

	public Book(String name, String author, Date purchase) {
		this.name = name;
		this.author = author;
		this.purchase = purchase;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPurchase() {
		return purchase;
	}

	public void setPurchase(Date purchase) {
		this.purchase = purchase;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", purchase=" + purchase + "]";
	}

}
