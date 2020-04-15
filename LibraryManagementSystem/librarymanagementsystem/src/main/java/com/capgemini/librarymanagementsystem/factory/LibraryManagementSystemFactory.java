package com.capgemini.librarymanagementsystem.factory;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dao.AdminDAOImplementation;
import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.service.AdminServiceDAO;
import com.capgemini.librarymanagementsystem.service.AdminServiceDAOImplementation;
import com.capgemini.librarymanagementsystem.service.UserServiceDAO;
import com.capgemini.librarymanagementsystem.service.UserServiceDAOImplementation;
import com.capgemini.librarymanagementsystem.validation.LibraryManagementSystemValidation;

public class LibraryManagementSystemFactory {
	private LibraryManagementSystemFactory() {

	}

	public static UserBean getUserBean() {

		return new UserBean();
	}

	public static AdminBean getAdminBean() {

		return new AdminBean();
	}

	public static BookBean getBookBean() {

		return new BookBean();
	}

	public static UserDAO getUserDao() {

		return new UserDAOImplementation();

	}

	public static UserServiceDAO getUserService() {

		return new UserServiceDAOImplementation();
	}

	public static AdminDAO getAdminDAO() {

		return new AdminDAOImplementation();
	}

	public static AdminServiceDAO getAdminServiceDAO() {

		return new AdminServiceDAOImplementation();
	}

	public static LibraryManagementSystemValidation getValidation() {
		return new LibraryManagementSystemValidation();
	}

}
