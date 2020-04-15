package com.capgemini.librarymanagementsystemwithjdbc.dao;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;


public interface UserDAO {
	LibraryUserBean login(String email,String password);
	BookBean searchById(int id);
	boolean bookReturn(int userId,int bookId);
	RequestInfo bookRequest(int id,int bookId);
	
}
