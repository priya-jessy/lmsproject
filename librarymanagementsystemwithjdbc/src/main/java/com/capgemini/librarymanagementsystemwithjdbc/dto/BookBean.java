package com.capgemini.librarymanagementsystemwithjdbc.dto;

import java.io.Serializable;

public class BookBean implements Serializable {

	private int slno;
	private int bookId;
	private String bookName;
	private String authorName;
	private String publisher;
	private String category;
	private boolean isAvaliable;

	public int getSlno() {
		return slno;
	}

	public boolean isAvaliable() {
		return isAvaliable;
	}

	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

}
