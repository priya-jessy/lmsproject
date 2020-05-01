package com.capgemini.librarymanagementsystemwithjdbc.dao;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;

import com.capgemini.librarymanagementsystemwithjdbc.exception.LMSException;

public class AdminDAOImplementation implements AdminDAO {

	@Override
	public boolean addUser(LibraryUserBean userBean) {

		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("addUser");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, userBean.getId());
					pstmt.setString(2, userBean.getUserName());
					pstmt.setString(3, userBean.getFirstName());
					pstmt.setString(4, userBean.getLastName());
					pstmt.setString(5, userBean.getEmailId());
					pstmt.setString(6, userBean.getPassword());
					pstmt.setString(7, userBean.getRole());

					int isRegistered = pstmt.executeUpdate();

				}
			}

		} catch (Exception e) {
			throw new LMSException("User already registered");
		}
		return true;

	}

	@Override
	public LibraryUserBean login(String emailId, String password) {
		LibraryUserBean adminBean = new LibraryUserBean();
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("login");
				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					pstmt.setString(1, emailId);
					pstmt.setString(2, password);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						adminBean.setEmailId(rs.getString("emailid"));
						adminBean.setPassword(rs.getString("password"));
						return adminBean;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new LMSException("Invalid Credentials");

	}

	@Override
	public boolean addBook(BookBean info) {

		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("addBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, info.getBookId());
					pstmt.setString(2, info.getBookName());
					pstmt.setString(3, info.getAuthorName());
					pstmt.setString(4, info.getPublisher());
					pstmt.setString(5, info.getCategory());
					pstmt.setBoolean(6, info.isAvaliable());

					int isAdded = pstmt.executeUpdate();

				}
			}

		} catch (Exception e) {
			throw new LMSException("Book is already existing");

		}

		return true;
	}

	@Override
	public boolean removeBook(int bookId) {

		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("removeBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, bookId);
					int res = pstmt.executeUpdate();
				}

			}
		} catch (Exception e) {
			throw new LMSException("book is already removed from library");
		}

		return true;
	}

	@Override
	public BookBean searchBook(int bookId) {
		BookBean bookBean = new BookBean();
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection connection = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("searchBook");
				try (PreparedStatement pstmt = connection.prepareStatement(query)) {
					pstmt.setInt(1, bookId);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						bookBean.setBookId(rs.getInt("bookid"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						return bookBean;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new LMSException("No such book found");
	}

	@Override
	public List<LibraryUserBean> showUsers() {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("getAllUsers");
				try (Statement pstmt = conn.createStatement()) {

					ResultSet rs = pstmt.executeQuery(query);
					List<LibraryUserBean> beans = new LinkedList<LibraryUserBean>();
					while (rs.next()) {
						LibraryUserBean userBean = new LibraryUserBean();

						userBean.setId(rs.getInt("id"));
						userBean.setUserName(rs.getString("username"));
						userBean.setFirstName(rs.getString("firstname"));
						userBean.setLastName(rs.getString("lastname"));
						userBean.setEmailId(rs.getString("emailid"));
						userBean.setPassword(rs.getString("password"));
						userBean.setRole(rs.getString("role"));
						userBean.setNoOfBooksBorrowed(rs.getInt("noOfBooksBorrowed"));
						userBean.setFine(rs.getInt("fine"));
						beans.add(userBean);

					}
					if(beans.isEmpty()) {
						throw new LMSException("No users present in the database");
					}
					return beans;
				}
			}
		} catch (Exception e) {
			throw new LMSException(e.getMessage());
		}
		

	}

	@Override
	public List<BookBean> showBooks() {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("getAllBookInfo");
				try (Statement pstmt = conn.createStatement()) {

					ResultSet rs = pstmt.executeQuery(query);
					List<BookBean> beans = new LinkedList<BookBean>();
					while (rs.next()) {
						BookBean bookBean = new BookBean();
						bookBean.setBookId(rs.getInt("bookid"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						bookBean.setPublisher(rs.getString("publisher"));
						bookBean.setCategory(rs.getString("category"));
						bookBean.setAvaliable(rs.getBoolean("isAvailable"));
						beans.add(bookBean);

					}
					if(beans.isEmpty()) {
						throw new LMSException("No books found in library");
					}else {
					
					return beans;
					}
				}

			}
		} catch (Exception e) {
			throw new LMSException(e.getMessage());

		}
		

	}

	@Override
	public List<RequestInfo> showRequests() {
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {
				String query = properties.getProperty("showRequest");
				try (Statement pstmt = conn.createStatement()) {

					ResultSet rs = pstmt.executeQuery(query);
					List<RequestInfo> beans = new ArrayList<RequestInfo>();
					while (rs.next()) {
						RequestInfo requestInfo = new RequestInfo();
						requestInfo.setRequestId(rs.getInt("requestid"));
						requestInfo.setUserId(rs.getInt("id"));
						requestInfo.setBookId(rs.getInt("bookid"));
						requestInfo.setIssueDate(rs.getDate("issuedDate"));
						requestInfo.setReturnDate(rs.getDate("returnedDate"));
						requestInfo.setExpectedReturnDate(rs.getDate("expectedReturnDate"));
						beans.add(requestInfo);

					}
					if(beans.isEmpty()) {
						throw new LMSException("No requests Found");
					}else {
						return beans;
					}
					
				}

			}
		} catch (Exception e) {
			throw new LMSException(e.getMessage());	
		}

		

	}

	@Override
	public boolean bookIssue(int requestId) {
		try (FileInputStream file = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(file);
			Class.forName(properties.getProperty("path"));

			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"));
					PreparedStatement getReqStmt = connection.prepareStatement(properties.getProperty("getRequest"));
					PreparedStatement getUserStmt = connection.prepareStatement(properties.getProperty("getUsersBooks"));
					PreparedStatement issueStmt = connection.prepareStatement(properties.getProperty("issueBookQuery"));
					PreparedStatement setBookAvailStmt = connection
							.prepareStatement(properties.getProperty("setAvailability"));
					PreparedStatement setBooksBorrowedStmt = connection
							.prepareStatement(properties.getProperty("setNoOfBooksBorrowed"));) {

				getReqStmt.setInt(1, requestId);
				try (ResultSet getReqResSet = getReqStmt.executeQuery();) {

					if (getReqResSet.next()) {
						int requestUserId = getReqResSet.getInt("id");
						int requestBookId = getReqResSet.getInt("bookid");
						getUserStmt.setInt(1, requestUserId);
						
						try (ResultSet getUserResSet = getUserStmt.executeQuery();) {

							if (getUserResSet.next()) {
								LibraryUserBean users = new LibraryUserBean();
								users.setNoOfBooksBorrowed(getUserResSet.getInt("noOfBooksBorrowed"));
								int noOfBooksBorrowed = users.getNoOfBooksBorrowed();

								issueStmt.setInt(1, requestId);
								int updateDate = issueStmt.executeUpdate();
								if (updateDate != 0) {
									// Update book availability as false as we are issuing

									setBookAvailStmt.setInt(1, requestBookId);
									setBookAvailStmt.executeUpdate();

									// Update User no of books borrowed
									noOfBooksBorrowed++;

									setBooksBorrowedStmt.setInt(1, noOfBooksBorrowed);
									setBooksBorrowedStmt.setInt(2, requestUserId);
									setBooksBorrowedStmt.executeUpdate();

								} else {
									throw new LMSException("This Book is Already Issued");
								}
							}
						} // End Of Gettinge User Result Set

					} else {
						throw new LMSException("InValid Request Id ");
					}
				} // End Of Connection

			}

		} catch (Exception e) {
			throw new LMSException(e.getMessage());

		}
		return true;
			}

	@Override
	public boolean isBookReceived(int requestId) {
		int noOfDaysDelayed = 0;
		int fine = 0;
		int userId = 0;
		int boodId = 0;

		try (FileInputStream file = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(file);

			Class.forName(properties.getProperty("path"));
			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"));
					PreparedStatement getReqStmt = connection.prepareStatement(properties.getProperty("getRequest"));
					PreparedStatement fineStmt = connection.prepareStatement(properties.getProperty("getfine"));
					PreparedStatement setFineStmt = connection.prepareStatement(properties.getProperty("userFine"));
					PreparedStatement setBookAvailStmt = connection
							.prepareStatement(properties.getProperty("setBookAvailability2"));
					PreparedStatement setNoOfBooksStmt = connection
							.prepareStatement(properties.getProperty("setNoOfBooksBorrowed2"));
					PreparedStatement removeReqStmt = connection
							.prepareStatement(properties.getProperty("removeRequest"));) {

				getReqStmt.setInt(1, requestId);
				try (ResultSet reqResSet = getReqStmt.executeQuery();) {
					while (reqResSet.next()) {
						Date returnedDate = reqResSet.getDate("returnedDate");
						Date expectedReturnedDate = reqResSet.getDate("expectedReturnDate");
						userId = reqResSet.getInt("id");
						boodId = reqResSet.getInt("bookid");

						if (returnedDate != null) {
							fineStmt.setDate(1, returnedDate);
							fineStmt.setDate(2, expectedReturnedDate);
							fineStmt.setInt(3, requestId);

							try (ResultSet fineResSet = fineStmt.executeQuery();) {
								while (fineResSet.next()) {
									noOfDaysDelayed = fineResSet.getInt(1);
								}
							}

							if (noOfDaysDelayed > 0) {
								fine = noOfDaysDelayed * 5;

								setFineStmt.setInt(1, fine);
								setFineStmt.setInt(2, userId);
								setFineStmt.executeUpdate();
							}

							// Make available in libaray books
							setBookAvailStmt.setInt(1, boodId);
							setBookAvailStmt.executeUpdate();

							// set No Of Books Borrowed
							setNoOfBooksStmt.setInt(1, userId);
							setNoOfBooksStmt.executeUpdate();

							removeReqStmt.setInt(1, requestId);
							removeReqStmt.executeUpdate();

							return true;

						}else {
							throw new LMSException("Book Not Yet Returned, So You Can't Receive");
						}

					} // End Of While Loop

				}

				throw new LMSException("Invalid Request Id");

			} // End of connection resource

		} catch (Exception e) {
			throw new LMSException(e.getMessage());
		}


			}

}
