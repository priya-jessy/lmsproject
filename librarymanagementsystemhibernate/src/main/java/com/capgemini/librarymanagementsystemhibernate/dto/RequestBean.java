package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_request")
public class RequestBean implements Serializable {

	@Id
	@Column
	@GeneratedValue
	private int rId;
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "bookId", unique = true)
	private int bookId;
	@Column
	private Date issueDate;
	@Column
	private Date expectedReturnDate;
	@Column
	private Date returnedDate;

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}
	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "book_request", joinColumns = { @JoinColumn(name = "rId")
	// },inverseJoinColumns = { @JoinColumn(name = "id")})
	// private List<LibraryUserBean> users;
	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "book_request", joinColumns = { @JoinColumn(name = "rId")
	// },inverseJoinColumns = { @JoinColumn(name = "bookId")})
	// private List<BookBean> books;

}
