package com.capgemini.librarymanagementsystemwithjdbc;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemwithjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemwithjdbc.dao.AdminDAOImplementation;
import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestBean;

public class AdminDaoTest  {
	private AdminDAO dao=new AdminDAOImplementation();
	@Test
	public void testAddUser() {
		LibraryUserBean bean=new LibraryUserBean();
		bean.setId(140);
		bean.setUserName("Priya");
		bean.setFirstName("Priya");
		bean.setLastName("Bijjala");
		bean.setEmailId("bijjalapriya@gmail.com");
		bean.setPassword("Priya123@");
		bean.setRole("admin");
		boolean status=dao.addUser(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAddUsers() {
		LibraryUserBean bean=new LibraryUserBean();
		bean.setId(145);
		bean.setUserName("pinky");
		bean.setFirstName("pinky");
		bean.setLastName("Bijjala");
		bean.setEmailId("pinky@gmail.com");
		bean.setPassword("pinky123@");
		bean.setRole("user");
		boolean status=dao.addUser(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAdminLogin() {
		LibraryUserBean bean=dao.login("bijjalapriya@gmail.com", "Priya123@");
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testAddBook() {
		BookBean bean=new BookBean();
		bean.setBookId(670);
		bean.setBookName("Every One Had A Story");
		bean.setAuthorName("Savir");
		bean.setCategory("Technical");
		bean.setPublisher("Pearson");
		bean.setAvaliable(true);
		boolean status=dao.addBook(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAddBooks() {
		BookBean bean=new BookBean();
		bean.setBookId(678);
		bean.setBookName("Three Mistakes of my life");
		bean.setAuthorName("Robbin");
		bean.setCategory("Technical");
		bean.setPublisher("Pearson");
		bean.setAvaliable(true);
		boolean status=dao.addBook(bean);
		Assertions.assertTrue(status);
	}
	@Test
	public void testGetAllBooks() {
		List<BookBean> bookInfo = dao.getAllBooks();
		Assertions.assertNotNull(bookInfo);
	}

	@Test
	public void testGetAllUsers() {
		List<LibraryUserBean> userBean = dao.getAllUsers();
		Assertions.assertNotNull(userBean);
	}
	@Test
	public void testSearchBook() {
		BookBean bean = dao.searchBook(678);
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testRemoveBook() {
		boolean status = dao.removeBook(678);
		Assertions.assertTrue(status);
	}
	@Test
	public void testGetAllRequests() {
		List<RequestBean> bean = dao.getAllRequests();
		Assertions.assertNotNull(bean);
	}
	@Test
	public void testIssueBook() {
		boolean status = dao.isBookIssued(11);
		Assertions.assertTrue(status);
	}

	@Test
	public void testReceiveBook() {
		boolean status = dao.isBookReceived(11);
		Assertions.assertTrue(status);
	}

	
}

