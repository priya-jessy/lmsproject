package com.capgemini.librarymanagementsystemhibernate.fatory;

import com.capgemini.librarymanagementsystemhibernate.dao.LibraryUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.LibraryUserDAOImplementation;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestBean;
import com.capgemini.librarymanagementsystemhibernate.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemhibernate.service.LibraryUserService;
import com.capgemini.librarymanagementsystemhibernate.service.LibraryUserServiceImplementation;
import com.capgemini.librarymanagementsystemhibernate.validation.LibraryManagementSystemValidation;

public class LibraryManagementSystemFactory {

	private LibraryManagementSystemFactory() {

	}

	public static LibraryUserBean getUserInfo() {

		return new LibraryUserBean();
	}

	public static BookBean getBookInfo() {

		return new BookBean();
	}

	public static RequestBean getRequestInfo() {

		return new RequestBean();
	}

	public static LibraryUserDAO getLibraryUserDAO() {

		return new LibraryUserDAOImplementation();
	}

	public static LibraryUserService getLibraryUserService() {

		return new LibraryUserServiceImplementation();
	}

	public static LibraryManagementSystemValidation getLibraryManagementSystemValidation() {
		return new LibraryManagementSystemValidation();

	}

}
