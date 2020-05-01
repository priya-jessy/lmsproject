package com.capgemini.librarymanagementsystem.service;

import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public interface AdminServiceDAO {

	boolean addUser(UserBean userBean);

	boolean adminLogin(String adminEmail, String adminPassword);

	boolean addBook(BookBean info);

	boolean returnedBook(int bookId);

	boolean removeBook(int bookId);

	BookBean searchBook(int bookId);

	List<UserBean> showUsers();

	List<BookBean> showBooks();

	List<RequestInfo> showRequests();

	boolean bookIssue(int userId, int bookId);

	boolean isBookReceived(int userId, int bookId);

}