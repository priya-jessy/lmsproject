package librarymanagementsystemspringrest.controller.service;

import java.util.List;

import librarymanagementsystemspringrest.controller.dto.BookBean;
import librarymanagementsystemspringrest.controller.dto.LibraryUserBean;
import librarymanagementsystemspringrest.controller.dto.RequestBean;

public class AdminServiceDAOImplementation implements AdminServiceDAO {

	@Override
	public LibraryUserBean login(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(LibraryUserBean info) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LibraryUserBean> viewUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBook(BookBean bookDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BookBean search(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookBean> viewBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestBean> viewRequests() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean issueBook(int rid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receivedBook(int requestId) {
		// TODO Auto-generated method stub
		return false;
	}

}
