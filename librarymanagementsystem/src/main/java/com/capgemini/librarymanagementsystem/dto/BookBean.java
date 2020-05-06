package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookBean implements Serializable {

	private int bookId;
	private String bookName;
	private String authorName;
	private String publisher;
	private String category;
	private boolean isAvaliable;

	public BookBean() {

	}

	public BookBean(int bookId, String bookName, String authorName, String publisher, String category,
			boolean isAvaliable) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publisher = publisher;
		this.category = category;
		this.isAvaliable = isAvaliable;
	}

	public boolean isAvaliable() {
		return isAvaliable;
	}

	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
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

	@Override
	public String toString() {
		return "BookBean [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", publisher="
				+ publisher + ", category=" + category + ", isAvaliable=" + isAvaliable + "]";
	}

}
