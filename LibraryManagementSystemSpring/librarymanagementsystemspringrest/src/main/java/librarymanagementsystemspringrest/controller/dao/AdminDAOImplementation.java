package librarymanagementsystemspringrest.controller.dao;

import java.util.List;

import librarymanagementsystemspringrest.controller.dto.BookBean;
import librarymanagementsystemspringrest.controller.dto.LibraryUserBean;
import librarymanagementsystemspringrest.controller.dto.RequestBean;

public class AdminDAOImplementation implements AdminDAO {

	@Override
	public LibraryUserBean login(String emailId, String password) {
		
		return null;
	}

	@Override
	public boolean addUser(LibraryUserBean info) {
		
		return false;
	}

	@Override
	public List<LibraryUserBean> viewUsers() {
		
		return null;
	}

	@Override
	public boolean addBook(BookBean bookDetails) {
		
		return false;
	}

	@Override
	public BookBean search(int bookId) {
		
		return null;
	}

	@Override
	public List<BookBean> viewBooks() {
		
		return null;
	}

	@Override
	public List<RequestBean> viewRequests() {
		
		return null;
	}

	@Override
	public boolean issueBook(int rid) {
		
		return false;
	}

	@Override
	public boolean removeBook(int bookId) {
		
		return false;
	}

	@Override
	public boolean receivedBook(int requestId) {
		
		return false;
	}

}
