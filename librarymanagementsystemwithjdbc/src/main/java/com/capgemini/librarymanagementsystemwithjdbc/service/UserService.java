package com.capgemini.librarymanagementsystemwithjdbc.service;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;


public interface UserService {
	
	LibraryUserBean login(String email,String password);
	BookBean searchById(int id);
	boolean bookReturn(int UserId,int bookId);
	boolean bookRequest(int id,int bookId);
	boolean changePassword(String emailId,String oldPassword,String newPassword);
	
	
}
