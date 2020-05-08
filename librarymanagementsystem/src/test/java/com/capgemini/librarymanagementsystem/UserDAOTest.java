package com.capgemini.librarymanagementsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public class UserDAOTest {

	private UserDAO userDAO = new UserDAOImplementation();

	@Test
	public void testUserLogin() {
		UserBean bean = userDAO.login("priya@gmail.com", "Priya123@");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchById() {
		BookBean bean = userDAO.searchById(123);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testBookRequest() {
		boolean status = userDAO.bookRequest(456, 123);
		Assertions.assertTrue(status);
	}

	@Test
	public void testReturnedBook() {
		boolean status = userDAO.bookReturn(456, 123);
		Assertions.assertTrue(status);
	}

	@Test
	public void testChangePassword() {
		boolean status = userDAO.changePassword("priya@gmail.com", "Priya123@", "Priya123#");
		Assertions.assertTrue(status);
	}
}
