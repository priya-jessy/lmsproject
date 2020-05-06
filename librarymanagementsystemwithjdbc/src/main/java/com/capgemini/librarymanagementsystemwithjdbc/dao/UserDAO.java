package com.capgemini.librarymanagementsystemwithjdbc.dao;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;

public interface UserDAO {
	LibraryUserBean login(String email, String password);

	BookBean searchById(int id);

	boolean bookReturn(int userId, int bookId);

	boolean bookRequest(int id, int bookId);

	boolean changePassword(String emailId, String oldPassword, String newPassword);

}
