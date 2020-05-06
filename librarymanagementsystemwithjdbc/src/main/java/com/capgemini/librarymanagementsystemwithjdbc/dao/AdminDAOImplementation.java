package com.capgemini.librarymanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestBean;

import com.capgemini.librarymanagementsystemwithjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemwithjdbc.utility.LmsUtility;

public class AdminDAOImplementation implements AdminDAO {
	LmsUtility lmsUtility = new LmsUtility();

	@Override
	public boolean addUser(LibraryUserBean userBean) {

		try (Connection connection = lmsUtility.getConnection()) {

			try (PreparedStatement pstmt = connection.prepareStatement(lmsUtility.getQuery("addUser"))) {
				pstmt.setInt(1, userBean.getId());
				pstmt.setString(2, userBean.getUserName());
				pstmt.setString(3, userBean.getFirstName());
				pstmt.setString(4, userBean.getLastName());
				pstmt.setString(5, userBean.getEmailId());
				pstmt.setString(6, userBean.getPassword());
				pstmt.setString(7, userBean.getRole());

				pstmt.executeUpdate();

			}
		} catch (Exception e) {
			throw new LMSException("User already registered");
		}
		return true;

	}

	@Override
	public LibraryUserBean login(String emailId, String password) {
		LibraryUserBean adminBean = new LibraryUserBean();
		try (Connection conn = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(lmsUtility.getQuery("login"))) {
				pstmt.setString(1, emailId);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					adminBean.setEmailId(rs.getString("emailid"));
					adminBean.setPassword(rs.getString("password"));
					return adminBean;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new LMSException("Invalid login Credentials");

	}

	@Override
	public boolean addBook(BookBean info) {
		try (Connection connection = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(lmsUtility.getQuery("addBook"))) {
				pstmt.setInt(1, info.getBookId());
				pstmt.setString(2, info.getBookName());
				pstmt.setString(3, info.getAuthorName());
				pstmt.setString(4, info.getPublisher());
				pstmt.setString(5, info.getCategory());
				pstmt.setBoolean(6, info.isAvaliable());

				pstmt.executeUpdate();

			}
		} catch (Exception e) {
			throw new LMSException("Book with same is already exists in library");

		}

		return true;
	}

	@Override
	public boolean removeBook(int bookId) {
		try (Connection connection = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(lmsUtility.getQuery("removeBook"))) {
				pstmt.setInt(1, bookId);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			throw new LMSException("book is already removed from library");
		}

		return true;
	}

	@Override
	public BookBean searchBook(int bookId) {
		BookBean bookBean = new BookBean();
		try (Connection connection = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(lmsUtility.getQuery("searchBook"))) {
				pstmt.setInt(1, bookId);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					bookBean.setBookId(rs.getInt("bookid"));
					bookBean.setBookName(rs.getString("bookName"));
					bookBean.setAuthorName(rs.getString("authorName"));
					return bookBean;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new LMSException("No such book found");
	}

	@Override
	public List<LibraryUserBean> getAllUsers() {
		try (Connection conn = lmsUtility.getConnection()) {
			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(lmsUtility.getQuery("getAllUsers"));
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
				if (beans.isEmpty()) {
					throw new LMSException("No users present in the library");
				}
				return beans;
			}
		} catch (Exception e) {
			throw new LMSException(e.getMessage());
		}

	}

	@Override
	public List<BookBean> getAllBooks() {

		try (Connection conn = lmsUtility.getConnection()) {
			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(lmsUtility.getQuery("getAllBookInfo"));
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
				if (beans.isEmpty()) {
					throw new LMSException("No books found in library");
				} else {

					return beans;
				}
			}

		} catch (Exception e) {
			throw new LMSException(e.getMessage());

		}

	}

	@Override
	public List<RequestBean> getAllRequests() {

		try (Connection conn = lmsUtility.getConnection()) {

			try (Statement pstmt = conn.createStatement()) {

				ResultSet rs = pstmt.executeQuery(lmsUtility.getQuery("showRequest"));
				List<RequestBean> beans = new ArrayList<RequestBean>();
				while (rs.next()) {
					RequestBean requestInfo = new RequestBean();
					requestInfo.setRequestId(rs.getInt("requestid"));
					requestInfo.setUserId(rs.getInt("id"));
					requestInfo.setBookId(rs.getInt("bookid"));
					requestInfo.setIssueDate(rs.getDate("issuedDate"));
					requestInfo.setReturnDate(rs.getDate("returnedDate"));
					requestInfo.setExpectedReturnDate(rs.getDate("expectedReturnDate"));
					beans.add(requestInfo);

				}
				if (beans.isEmpty()) {
					throw new LMSException("No requests Found");
				} else {
					return beans;
				}

			}

		} catch (Exception e) {
			throw new LMSException(e.getMessage());
		}

	}

	@Override
	public boolean isBookIssued(int requestId) {
		try (Connection connection = lmsUtility.getConnection();
				PreparedStatement requestpstmt = connection.prepareStatement(lmsUtility.getQuery("getRequest"));
				PreparedStatement userPstmt = connection.prepareStatement(lmsUtility.getQuery("getUsersBooks"));
				PreparedStatement issuePstmt = connection.prepareStatement(lmsUtility.getQuery("issueBookQuery"));
				PreparedStatement avaliabilityPstmt = connection
						.prepareStatement(lmsUtility.getQuery("setBookAvailability"));
				PreparedStatement setBooksBorrowedStmt = connection
						.prepareStatement(lmsUtility.getQuery("setNoOfBooksBorrowed"));) {

			requestpstmt.setInt(1, requestId);
			try (ResultSet requestResultSet = requestpstmt.executeQuery();) {

				if (requestResultSet.next()) {
					int requestUserId = requestResultSet.getInt("id");
					int requestBookId = requestResultSet.getInt("bookid");
					userPstmt.setInt(1, requestUserId);

					try (ResultSet userResultSet = userPstmt.executeQuery();) {

						if (userResultSet.next()) {
							LibraryUserBean users = new LibraryUserBean();
							users.setNoOfBooksBorrowed(userResultSet.getInt("noOfBooksBorrowed"));
							int noOfBooksBorrowed = users.getNoOfBooksBorrowed();

							issuePstmt.setInt(1, requestId);
							int updateDate = issuePstmt.executeUpdate();
							if (updateDate != 0) {
								avaliabilityPstmt.setInt(1, requestBookId);
								avaliabilityPstmt.executeUpdate();
								noOfBooksBorrowed++;
								setBooksBorrowedStmt.setInt(1, noOfBooksBorrowed);
								setBooksBorrowedStmt.setInt(2, requestUserId);
								setBooksBorrowedStmt.executeUpdate();

							} else {
								throw new LMSException("This Book is Already Issued");
							}
						}
					}

				} else {
					throw new LMSException("Invalid Request Id due to invaild bookid or userid ");
				}
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

		try (Connection connection = lmsUtility.getConnection();
				PreparedStatement getReqPstmt = connection.prepareStatement(lmsUtility.getQuery("getRequest"));
				PreparedStatement finePstmt = connection.prepareStatement(lmsUtility.getQuery("getfine"));
				PreparedStatement setFinePstmt = connection.prepareStatement(lmsUtility.getQuery("userFine"));
				PreparedStatement bookAvailabilityPstmt = connection
						.prepareStatement(lmsUtility.getQuery("setAvailabilityOfBook"));
				PreparedStatement noOfBooksPstmt = connection
						.prepareStatement(lmsUtility.getQuery("setNoOfBooksBorrowed1"));
				PreparedStatement removeRequest = connection.prepareStatement(lmsUtility.getQuery("removeRequest"));) {

			getReqPstmt.setInt(1, requestId);
			try (ResultSet reqResSet = getReqPstmt.executeQuery();) {
				while (reqResSet.next()) {
					Date returnedDate = reqResSet.getDate("returnedDate");
					Date expectedReturnedDate = reqResSet.getDate("expectedReturnDate");
					userId = reqResSet.getInt("id");
					boodId = reqResSet.getInt("bookid");

					if (returnedDate != null) {
						finePstmt.setDate(1, returnedDate);
						finePstmt.setDate(2, expectedReturnedDate);
						finePstmt.setInt(3, requestId);

						try (ResultSet fineResSet = finePstmt.executeQuery();) {
							while (fineResSet.next()) {
								noOfDaysDelayed = fineResSet.getInt(1);
							}
						}

						if (noOfDaysDelayed > 0) {
							fine = noOfDaysDelayed * 5;

							setFinePstmt.setInt(1, fine);
							setFinePstmt.setInt(2, userId);
							setFinePstmt.executeUpdate();
						}
						bookAvailabilityPstmt.setInt(1, boodId);
						bookAvailabilityPstmt.executeUpdate();
						noOfBooksPstmt.setInt(1, userId);
						noOfBooksPstmt.executeUpdate();
						removeRequest.setInt(1, requestId);
						removeRequest.executeUpdate();

						return true;

					} else {
						throw new LMSException("Book Not Yet Returned, So You Can't Receive");
					}

				}

			}

			throw new LMSException("Invalid Request Id");

		} catch (Exception e) {
			throw new LMSException(e.getMessage());
		}

	}

}
