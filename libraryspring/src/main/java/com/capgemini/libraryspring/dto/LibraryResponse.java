package com.capgemini.libraryspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryResponse {

	private boolean error;
	private String message;
	private LibraryUserBean libaryUserBean;
	private List<LibraryUserBean> userList;
	private BookBean bookBean;
	private List<BookBean> booksList;
	private RequestBean requestBean;
	private List<RequestBean> requestList;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LibraryUserBean getLibaryUserBean() {
		return libaryUserBean;
	}

	public void setLibaryUserBean(LibraryUserBean libaryUserBean) {
		this.libaryUserBean = libaryUserBean;
	}

	public List<LibraryUserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<LibraryUserBean> userList) {
		this.userList = userList;
	}

	public BookBean getBookBean() {
		return bookBean;
	}

	public void setBookBean(BookBean bookBean) {
		this.bookBean = bookBean;
	}

	public List<BookBean> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<BookBean> booksList) {
		this.booksList = booksList;
	}

	public RequestBean getRequestBean() {
		return requestBean;
	}

	public void setRequestBean(RequestBean requestBean) {
		this.requestBean = requestBean;
	}

	public List<RequestBean> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<RequestBean> requestList) {
		this.requestList = requestList;
	}

}
