package com.capgemini.libraryspring.service;

import java.util.List;

import com.capgemini.libraryspring.dto.BookBean;
import com.capgemini.libraryspring.dto.LibraryUserBean;
import com.capgemini.libraryspring.dto.RequestBean;


public interface LibraryUserService {
	
	LibraryUserBean login(String emailId, String password);

	boolean addUser(LibraryUserBean info);

	List<LibraryUserBean> viewUsers();

	boolean addBook(BookBean bookDetails);

	BookBean search(int bookId);

	List<BookBean> viewBooks();

	List<RequestBean> viewRequests();

	boolean issueBook(int rid);

	boolean removeBook(int bookId);

	boolean receivedBook(int requestId);

	boolean bookRequest(int id, int bookId);

	boolean bookReturn(int userId, int bookId);

	


}
