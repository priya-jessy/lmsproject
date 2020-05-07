package com.capgemini.libraryspring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.libraryspring.dto.BookBean;
import com.capgemini.libraryspring.dto.LibraryResponse;
import com.capgemini.libraryspring.dto.LibraryUserBean;
import com.capgemini.libraryspring.dto.RequestBean;
import com.capgemini.libraryspring.service.LibraryUserService;

@RestController
public class LibraryManagementSystemController {
	@Autowired
	private LibraryUserService libraryUserService;

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse adminLogin(@RequestBody Map<String,String> inputJson) {
		LibraryResponse libraryResponse = new LibraryResponse();
		LibraryUserBean loggedIn = libraryUserService.login(inputJson.get("emailId"),inputJson.get("password"));
		if (loggedIn != null) {
			libraryResponse.setMessage("admin login successfully");
			libraryResponse.setLibaryUserBean(loggedIn);
		} else {
			libraryResponse.setError(true);
			libraryResponse.setMessage("Invalid Admin Credentails");
		}
		return libraryResponse;
	}

	@PostMapping(path = "/addBook", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse addBook(@RequestBody BookBean bookInfo) {
		boolean isAdded = libraryUserService.addBook(bookInfo);
		LibraryResponse libraryResponse = new LibraryResponse();
		if (isAdded) {
			libraryResponse.setMessage("Book Added Successfully");
		} else {
			libraryResponse.setError(true);
			libraryResponse.setMessage("Book Details which was given is already present. Unable to add book");
		}
		return libraryResponse;
	}

	@PostMapping(path = "/addUser", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse addUser(@RequestBody LibraryUserBean libraryUsers) {
		boolean isAdded = libraryUserService.addUser(libraryUsers);
		LibraryResponse response = new LibraryResponse();
		if (isAdded) {
			response.setMessage("User Added Successfully");
		} else {
			response.setError(true);
		}
		return response;
	}

	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getAllUsers() {
		List<LibraryUserBean> userList = libraryUserService.viewUsers();
		LibraryResponse response = new LibraryResponse();
		if (userList != null && !userList.isEmpty()) {
			response.setUserList(userList);
		} else {
			response.setError(true);
			response.setMessage("No Users Found");
		}

		return response;
	}

	@GetMapping(path = "/getAllBooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getAllBooks() {
		List<BookBean> bookBean = libraryUserService.viewBooks();
		LibraryResponse response = new LibraryResponse();
		if (bookBean != null && !bookBean.isEmpty()) {
			response.setBooksList(bookBean);
		} else {
			response.setError(true);
			response.setMessage("No Books Found In the Library");
		}

		return response;
	}

	@DeleteMapping(path = "/deleteBook/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse deleteBook(@PathVariable(name = "bookId") int bookId) {
		LibraryResponse response = new LibraryResponse();
		boolean isDeleted = libraryUserService.removeBook(bookId);
		if (isDeleted) {
			response.setMessage("record deleted for id" + bookId);
		} else {
			response.setError(true);
			response.setMessage("No Records found to delete/unble to delete " + bookId);
		}
		return response;
	}

	@GetMapping(path = "/searchBook/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse searchBook(@PathVariable(name = "bookId") int bookId) {
		LibraryResponse response = new LibraryResponse();
		BookBean bookBean = libraryUserService.search(bookId);
		if (bookBean != null) {
			response.setBookBean(bookBean);
		} else {
			response.setError(true);
			response.setMessage("No book found with that practicular bookId");
		}
		return response;
	}

	@GetMapping(path = "/bookRequest/{id}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse requestBook(@PathVariable(name = "id") int id, @PathVariable(name = "bookId") int bookId) {
		LibraryResponse response = new LibraryResponse();
		boolean requested = libraryUserService.bookRequest(id, bookId);
		if (requested) {
			response.setMessage("Request placed to admin succesfully");
		} else {
			response.setError(true);
			response.setMessage("Request not placed to admin");
		}
		return response;
	}

	@GetMapping(path = "/getAllRequests", produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getAllRequests() {
		List<RequestBean> requestBean = libraryUserService.viewRequests();
		LibraryResponse response = new LibraryResponse();
		if (requestBean != null && !requestBean.isEmpty()) {
			response.setRequestList(requestBean);
		} else {
			response.setError(true);
			response.setMessage("No requests found");
		}

		return response;
	}

	@GetMapping(path = "/issueBook/{rId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse issueBook(@PathVariable(name = "rId") int rId) {
		boolean isIssued = libraryUserService.issueBook(rId);

		LibraryResponse response = new LibraryResponse();
		if (isIssued) {
			response.setMessage("Book Issued Succesfully");
		} else {
			response.setError(true);
			response.setMessage("Book Cannot be Issued");
		}
		return response;
	}

	@GetMapping(path = "/returnBook/{id}/{bookId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse returnBook(@PathVariable(name = "id") int id, @PathVariable(name = "bookId") int bookId) {
		boolean isReturned = libraryUserService.bookReturn(id, bookId);
		LibraryResponse response = new LibraryResponse();
		if (isReturned) {
			response.setMessage("Book returned To Admin Successfully");
		} else {
			response.setError(true);
			response.setMessage(" Can't Returned The Book To Admin");
		}
		return response;
	}

	@GetMapping(path = "/receiveBook/{rId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public LibraryResponse receiveBook(@PathVariable(name = "rId") int rId) {
		boolean isReceived = libraryUserService.receivedBook(rId);

		LibraryResponse response = new LibraryResponse();
		if (isReceived) {
			response.setMessage("Book Received Successfully");
		} else {
			response.setError(true);
			response.setMessage("Unable to Receive, Invalid Request");
		}
		return response;
	}

}
