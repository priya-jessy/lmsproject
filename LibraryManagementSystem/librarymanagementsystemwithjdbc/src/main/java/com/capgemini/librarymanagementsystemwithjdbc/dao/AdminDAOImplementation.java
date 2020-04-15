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
					if (isRegistered != 0) {
						return true;
					}

				}
			}

		} catch (Exception e) {
			throw new LMSException("User already registered");
		}
		return false;

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
		throw new LMSException("User can't login");

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
					if (isAdded != 0) {
						return true;
					}

				}
			}

		} catch (Exception e) {
			throw new LMSException("Book is already existing");

		}

		return false;
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
					if (res != 0) {
						return true;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new LMSException("book cannot be removed");
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
						beans.add(userBean);

					}
					return beans;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new LMSException("No users present in the database");

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
						beans.add(bookBean);

					}
					return beans;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new LMSException("No books present in database");

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
						requestInfo.setUserName(rs.getString("username"));
						requestInfo.setBookName(rs.getString("bookname"));
						beans.add(requestInfo);

					}
					return beans;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new LMSException("No requests found");

	}

	@Override
	public boolean bookIssue(int requestId) {
		PreparedStatement pstmt = null;
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();

			String dburl = properties.getProperty("dburl");

			try (Connection conn = DriverManager.getConnection(dburl)) {

				pstmt = conn.prepareStatement(properties.getProperty("getRequest"));

				pstmt.setInt(1, requestId);
				ResultSet resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					RequestInfo info = new RequestInfo();
					info.setUserId(resultSet.getInt("id"));
					info.setBookId(resultSet.getInt("bookid"));

					int requestUserId = info.getUserId();
					System.out.println("Requested user " + info.getUserId());

					int requestBookId = resultSet.getInt("bookid");
					System.out.println("Requested book " + requestBookId);

					if (requestUserId != 0) {
						pstmt = conn.prepareStatement(properties.getProperty("getUsersBooks"));
						pstmt.setInt(1, requestUserId);
						resultSet = pstmt.executeQuery();

						if (resultSet.next()) {
							LibraryUserBean info2 = new LibraryUserBean();
							info2.setNoOfBooksBorrowed(resultSet.getInt("noOfBooksBorrowed"));
							int noOfBooksBorrowed = info2.getNoOfBooksBorrowed();
							System.out.println("no of books Before issue	" + noOfBooksBorrowed);
							if (noOfBooksBorrowed < 3) {

								String query3 = properties.getProperty("issueBookQuery");
								pstmt = conn.prepareStatement(query3);

								pstmt.setInt(1, requestId);

								int updateDate = pstmt.executeUpdate();
								if (updateDate != 0) {

									String query4 = properties.getProperty("setAvailability");
									pstmt = conn.prepareStatement(query4);
									pstmt.setInt(1, requestUserId);
									pstmt.executeUpdate();

									noOfBooksBorrowed++;
									String query5 = properties.getProperty("setNoOfBooksBorrowed");
									pstmt = conn.prepareStatement(query5);
									pstmt.setInt(1, noOfBooksBorrowed);
									pstmt.setInt(2, requestUserId);
									pstmt.executeUpdate();

								}

								return true;

							}

						}

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new LMSException("Book cannot be issued");

	}

	@Override
	public boolean isBookReceived(int requestId) {
		PreparedStatement pstmt = null;
		String query = null;
		ResultSet resultSet = null;
		int noOfDaysDelayed = 0;
		int fine = 0;
		int userId = 0;
		int bookId = 0;
		int result = 0;
		try (FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl = properties.getProperty("dburl");
			try (Connection conn = DriverManager.getConnection(dburl)) {

				query = properties.getProperty("receiveBook");
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, requestId);
				resultSet = pstmt.executeQuery();

				while (resultSet.next()) {
					Date returnedDate = resultSet.getDate("returnedDate");
					Date expectedReturnedDate = resultSet.getDate("expectedreturnDate");
					userId = resultSet.getInt("id");
					bookId = resultSet.getInt("bookid");

					if (returnedDate != null) {
						query = properties.getProperty("getfine");
						pstmt = conn.prepareStatement(query);
						pstmt.setDate(1, returnedDate);
						pstmt.setDate(2, expectedReturnedDate);
						pstmt.setInt(3, requestId);

						resultSet = pstmt.executeQuery();

						while (resultSet.next()) {
							noOfDaysDelayed = resultSet.getInt(1);
						}

						System.out.println("No Of Days Delayed " + noOfDaysDelayed);

						if (noOfDaysDelayed > 0) {
							fine = noOfDaysDelayed * 5;
							query = properties.getProperty("userFine");
							pstmt = conn.prepareStatement(query);
							pstmt.setDouble(1, fine);
							pstmt.setInt(2, userId);

							result = pstmt.executeUpdate();
							if (result != 0) {
								System.out.println("fine updated" + fine);
							}

						}

						// Make available in library books
						query = properties.getProperty("setBookAvailability2");
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, bookId);
						result = pstmt.executeUpdate();

						// set No Of Books Borrowed
						query = properties.getProperty("setNoOfBooksBorrowed2");
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, userId);
						result = pstmt.executeUpdate();

						query = properties.getProperty("removeRequest");
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, requestId);

						result = pstmt.executeUpdate();

						return true;

					}

				} // End Of While Loop
				return false;

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		throw new LMSException("Book is not received");

	}

}
