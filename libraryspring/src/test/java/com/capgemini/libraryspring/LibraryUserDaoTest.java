package com.capgemini.libraryspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.libraryspring.dao.LibraryUserDAO;
import com.capgemini.libraryspring.dao.LibraryUserDAOImplementation;
import com.capgemini.libraryspring.dto.BookBean;
import com.capgemini.libraryspring.dto.LibraryUserBean;
import com.capgemini.libraryspring.dto.RequestBean;

public class LibraryUserDaoTest {
	@Autowired
	private LibraryUserDAO dao=new LibraryUserDAOImplementation();
	@Test
	public void testLogin() {
		LibraryUserBean bean=dao.login("priya@gmail.com", "Priya123@");
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testAddUser() {
		LibraryUserBean bean=new LibraryUserBean();
		bean.setId(410);
		bean.setName("Ravali");
		bean.setEmailId("abc@gmail.com");
		bean.setPassword("Ravali123@");
		bean.setRole("user");
		boolean status=dao.addUser(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAddBook() {
		BookBean bean=new BookBean();
		bean.setBookId(430);
		bean.setBookName("Oops i fell in love");
		bean.setAuthor("harsh");
		bean.setCategory("Non technical");
		bean.setPublisher("Local");
		bean.setAvaliable(true);
		boolean status=dao.addBook(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testGetAllBooks() {
		List<BookBean> bookInfo = dao.viewBooks();
		Assertions.assertNotNull(bookInfo);
	}

	@Test
	public void testGetAllUsers() {
		List<LibraryUserBean> userBean = dao.viewUsers();
		Assertions.assertNotNull(userBean);
	}
	@Test
	public void testGetAllRequests() {
		List<RequestBean> bean = dao.viewRequests();
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testSearchBook() {
		BookBean bean = dao.search(902);
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testBookRequest() {
		boolean status=dao.bookRequest(982,435);
		Assertions.assertTrue(status);
	}
	@Test
	public void testIssueBook() {
		boolean status = dao.issueBook(21);
		Assertions.assertTrue(status);
	}
	@Test
	public void testBookReturn() {
		boolean status=dao.bookReturn(982,435);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReceiveBook() {
		boolean status = dao.receivedBook(21);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook() {
		boolean status = dao.removeBook(122);
		Assertions.assertTrue(status);
	}
	


}
