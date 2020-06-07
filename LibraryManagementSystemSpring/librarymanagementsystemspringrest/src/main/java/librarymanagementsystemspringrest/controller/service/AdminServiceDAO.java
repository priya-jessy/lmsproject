package librarymanagementsystemspringrest.controller.service;

import java.util.List;

import librarymanagementsystemspringrest.controller.dto.BookBean;
import librarymanagementsystemspringrest.controller.dto.LibraryUserBean;
import librarymanagementsystemspringrest.controller.dto.RequestBean;

public interface AdminServiceDAO {
	
	LibraryUserBean login(String emailId, String password);
	boolean addUser(LibraryUserBean info);
	List<LibraryUserBean> viewUsers();
	boolean addBook(BookBean bookDetails);
	BookBean search(int bookId);
	List<BookBean> viewBooks();
	List<RequestBean> viewRequests();
	boolean issueBook(int rid);
	boolean removeBook(int bookId);
	boolean receivedBook(int requestId);

}
