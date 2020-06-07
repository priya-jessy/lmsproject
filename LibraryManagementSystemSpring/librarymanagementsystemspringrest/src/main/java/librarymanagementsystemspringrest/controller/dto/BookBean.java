package librarymanagementsystemspringrest.controller.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class BookBean implements Serializable {

	@Id
	@Column
	private int bookId;
	@Column
	private String bookName;
	@Column
	private String author;
	@Column
	private String publisher;
	@Column
	private String category;
	@Column
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
