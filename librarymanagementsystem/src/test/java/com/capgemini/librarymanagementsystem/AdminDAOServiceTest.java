package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.service.AdminServiceDAO;
import com.capgemini.librarymanagementsystem.service.AdminServiceDAOImplementation;

public class AdminDAOServiceTest {
	
	private AdminServiceDAO dao=new AdminServiceDAOImplementation();
	
	@Test
	public void testAdminLogin() {
		boolean status = dao.adminLogin("priya@gmail.com", "Priya123@");
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddBook() {
		BookBean bookBean = new BookBean();
		bookBean.setBookId(456);
		bookBean.setBookName("java");
		bookBean.setAuthorName("James gosiling");
		bookBean.setPublisher("Neelima");
		bookBean.setCategory("Technical");
		bookBean.setAvaliable(true);
		boolean status = dao.addBook(bookBean);
		Assertions.assertTrue(status);

	}

	@Test
	public void testAddUser() {
		UserBean userBean = new UserBean();
		userBean.setUserid(123);
		userBean.setUsername("Priya");
		userBean.setEmail("priya@gmail.com");
		userBean.setPassword("Priya123@");
		boolean status = dao.addUser(userBean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testSearchBook() {
		BookBean bean = dao.searchBook(456);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testRemoveBook() {
		boolean status = dao.removeBook(456);
		Assertions.assertTrue(status);
	}

	@Test
	public void testGetAllBooks() {
		List<BookBean> bookInfo = dao.getAllBooks();
		Assertions.assertNotNull(bookInfo);
	}

	@Test
	public void testGetAllUsers() {
		List<UserBean> userBean = dao.getAllUsers();
		Assertions.assertNotNull(userBean);
	}

	@Test
	public void testIssueBook() {
		boolean status = dao.bookIssue(456, 123);
		Assertions.assertTrue(status);
	}

	@Test
	public void testReceiveBook() {
		boolean status = dao.isBookReceived(456, 123);
		Assertions.assertTrue(status);
	}

	@Test
	public void testGetAllRequests() {
		List<RequestBean> bean = dao.getAllRequests();
		Assertions.assertNotNull(bean);
	}


}
