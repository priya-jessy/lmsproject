package com.capgemini.librarymanagementsystemwithjdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemwithjdbc.dao.UserDAO;
import com.capgemini.librarymanagementsystemwithjdbc.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;

public class UserDAOTest {
	
	private UserDAO dao=new UserDAOImplementation();
	@Test
	public void testUserLogin() {
		LibraryUserBean bean=dao.login("naveena@gmail.com", "Naveena123@");
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testSearchBook() {
		BookBean bean = dao.searchById(327);
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testBookRequest() {
		boolean status=dao.bookRequest(405,327 );
		Assertions.assertTrue(status);
	}
	@Test
	public void testBookReturn() {
		boolean status=dao.bookReturn(405, 327);
		Assertions.assertTrue(status);
	}

}
