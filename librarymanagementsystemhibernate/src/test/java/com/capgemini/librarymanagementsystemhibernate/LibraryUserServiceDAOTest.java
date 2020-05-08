package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestBean;
import com.capgemini.librarymanagementsystemhibernate.service.LibraryUserService;
import com.capgemini.librarymanagementsystemhibernate.service.LibraryUserServiceImplementation;

public class LibraryUserServiceDAOTest {
	
	private LibraryUserService service=new LibraryUserServiceImplementation();
	
	@Test
	public void testLogin() {
		LibraryUserBean bean=service.login("priya@gmail.com", "Priya123@");
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testAddUser() {
		LibraryUserBean bean=new LibraryUserBean();
		bean.setId(418);
		bean.setName("manish");
		bean.setEmailId("manish@gmail.com");
		bean.setPassword("Manish123@");
		bean.setRole("user");
		boolean status=service.addUser(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAddBook() {
		BookBean bean=new BookBean();
		bean.setBookId(415);
		bean.setBookName("Three Mistakes of my life");
		bean.setAuthor("harsh");
		bean.setCategory("Non technical");
		bean.setPublisher("Local");
		bean.setAvaliable(true);
		boolean status=service.addBook(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testGetAllBooks() {
		List<BookBean> bookInfo = service.viewBooks();
		Assertions.assertNotNull(bookInfo);
	}

	@Test
	public void testGetAllUsers() {
		List<LibraryUserBean> userBean = service.viewUsers();
		Assertions.assertNotNull(userBean);
	}
	@Test
	public void testGetAllRequests() {
		List<RequestBean> bean = service.viewRequests();
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testSearchBook() {
		BookBean bean = service.search(902);
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testBookRequest() {
		boolean status=service.bookRequest(982,435);
		Assertions.assertTrue(status);
	}
	@Test
	public void testIssueBook() {
		boolean status = service.issueBook(21);
		Assertions.assertTrue(status);
	}
	@Test
	public void testBookReturn() {
		boolean status=service.bookReturn(982,435);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReceiveBook() {
		boolean status = service.receivedBook(21);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook() {
		boolean status = service.removeBook(122);
		Assertions.assertTrue(status);
	}
	@Test
	public void testChangePassword() {
		boolean status=service.changePassword(213, "Navya123@", "Navya123#");
		Assertions.assertTrue(status);
	}


}
