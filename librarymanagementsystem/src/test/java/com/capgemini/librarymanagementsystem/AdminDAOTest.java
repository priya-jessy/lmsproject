package com.capgemini.librarymanagementsystem;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dao.AdminDAOImplementation;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public class AdminDAOTest {
	private AdminDAO adminDao = new AdminDAOImplementation();

	@Test
	public void testAdminLogin() {
		boolean status = adminDao.adminLogin("priya@gmail.com", "Priya123@");
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
		boolean status = adminDao.addBook(bookBean);
		Assertions.assertTrue(status);

	}

	@Test
	public void testAddUser() {
		UserBean userBean = new UserBean();
		userBean.setUserid(123);
		userBean.setUsername("Priya");
		userBean.setEmail("priya@gmail.com");
		userBean.setPassword("Priya123@");
		boolean status = adminDao.addUser(userBean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testSearchBook() {
		BookBean bean = adminDao.searchBook(456);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testRemoveBook() {
		boolean status = adminDao.removeBook(456);
		Assertions.assertTrue(status);
	}

	@Test
	public void testGetAllBooks() {
		List<BookBean> bookInfo = adminDao.getAllBooks();
		Assertions.assertNotNull(bookInfo);
	}

	@Test
	public void testGetAllUsers() {
		List<UserBean> userBean = adminDao.getAllUsers();
		Assertions.assertNotNull(userBean);
	}

	@Test
	public void testIssueBook() {
		boolean status = adminDao.bookIssue(456, 123);
		Assertions.assertTrue(status);
	}

	@Test
	public void testReceiveBook() {
		boolean status = adminDao.isBookReceived(456, 123);
		Assertions.assertTrue(status);
	}

	@Test
	public void testGetAllRequests() {
		List<RequestBean> bean = adminDao.getAllRequests();
		Assertions.assertNotNull(bean);
	}

}
