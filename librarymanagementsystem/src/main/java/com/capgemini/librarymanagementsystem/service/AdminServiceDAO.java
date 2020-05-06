package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public interface AdminServiceDAO {

	boolean addUser(UserBean userBean);

	boolean adminLogin(String adminEmail, String adminPassword);

	boolean addBook(BookBean info);

	boolean returnedBook(int bookId);

	boolean removeBook(int bookId);

	BookBean searchBook(int bookId);

	List<UserBean> getAllUsers();

	List<BookBean> getAllBooks();

	List<RequestBean> getAllRequests();

	boolean bookIssue(int userId, int bookId);

	boolean isBookReceived(int userId, int bookId);

}
