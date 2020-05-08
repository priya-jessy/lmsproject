package com.capgemini.librarymanagementsystemwithjdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.service.UserService;
import com.capgemini.librarymanagementsystemwithjdbc.service.UserServiceImplemenatation;

public class UserDAOServiceTest {
	
	private UserService service=new UserServiceImplemenatation();
	@Test
	public void testUserLogin() {
		LibraryUserBean bean=service.login("naveena@gmail.com", "Naveena123@");
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testSearchBook() {
		BookBean bean = service.searchById(327);
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testBookRequest() {
		boolean status=service.bookRequest(405,327 );
		Assertions.assertTrue(status);
	}
	@Test
	public void testBookReturn() {
		boolean status=service.bookReturn(405, 327);
		Assertions.assertTrue(status);
	}

}
