package com.capgemini.libraryspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.libraryspring.dto.BookBean;
import com.capgemini.libraryspring.dto.LibraryUserBean;
import com.capgemini.libraryspring.dto.RequestBean;
import com.capgemini.libraryspring.service.LibraryUserService;


public class LibraryUserServiceDaoTest {
	@Autowired
	private LibraryUserService service;

	@Test
	public void testLogin() {
		LibraryUserBean bean = service.login("priya@gmail.com", "Priya123@");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testAddUser() {
		LibraryUserBean bean = new LibraryUserBean();
		bean.setId(407);
		bean.setName("Ravali");
		bean.setPassword("Ravali123@");
		bean.setRole("user");
		boolean status = service.addUser(bean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddBook() {
		BookBean bean = new BookBean();
		bean.setBookId(426);
		bean.setBookName("Oops i fell in love");
		bean.setAuthor("harsh");
		bean.setCategory("Non technical");
		bean.setPublisher("Local");
		bean.setAvaliable(true);
		boolean status = service.addBook(bean);
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
	 boolean status = service.bookRequest(982, 435);
	 Assertions.assertTrue(status);
	 }
	
	 @Test
	 public void testIssueBook() {
	 boolean status = service.issueBook(21);
	 Assertions.assertTrue(status);
	 }
	
	 @Test
	 public void testBookReturn() {
	 boolean status = service.bookReturn(982, 435);
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
	
	

}
