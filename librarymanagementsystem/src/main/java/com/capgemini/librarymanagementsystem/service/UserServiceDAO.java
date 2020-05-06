package com.capgemini.librarymanagementsystem.service;



import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public interface UserServiceDAO {

	UserBean login(String email, String password);

	BookBean searchById(int id);

	boolean bookRequest(int userId, int bookId);

	boolean bookReturn(int userId, int bookId);
	
	boolean changePassword(String emailId, String oldPassword, String newPassword);

}
