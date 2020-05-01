package com.capgemini.librarymanagementsystem.database;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.AdminBean;
import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserBean;

public class LibraryManagementSystemDataBase {
	public static final List<UserBean> users = new LinkedList<UserBean>();
	public static final List<AdminBean> admin = new LinkedList<AdminBean>();
	public static final List<BookBean> book = new LinkedList<BookBean>();
	public static final List<RequestInfo> request = new LinkedList<RequestInfo>();
}
