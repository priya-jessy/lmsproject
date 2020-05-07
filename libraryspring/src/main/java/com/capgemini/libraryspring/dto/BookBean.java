package com.capgemini.libraryspring.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@Entity
@Table(name = "library_books")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookBean implements Serializable{

	@Id
	@Column
	@GeneratedValue
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String author;
	@Column
	private String publisher;
	@Column
	private String category;
	@JsonProperty
	@Column(columnDefinition = "TINYINT")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean isAvaliable;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isAvaliable() {
		return isAvaliable;
	}

	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}

}
