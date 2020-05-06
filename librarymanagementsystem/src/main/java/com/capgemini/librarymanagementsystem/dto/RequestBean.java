package com.capgemini.librarymanagementsystem.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class RequestBean implements Serializable {

	private int userId;
	private int bookId;
	private boolean isIssued;
	private boolean isReturned;
	private Date issuedDate;
	private Date returnedDate;
	private Date expectedReturnedDate;

	public Date getExpectedReturnedDate() {
		return expectedReturnedDate;
	}

	public void setExpectedReturnedDate(Date expectedReturnedDate) {
		this.expectedReturnedDate = expectedReturnedDate;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

}
