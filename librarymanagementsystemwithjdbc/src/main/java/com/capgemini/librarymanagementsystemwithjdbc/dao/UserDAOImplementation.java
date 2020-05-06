package com.capgemini.librarymanagementsystemwithjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestBean;
import com.capgemini.librarymanagementsystemwithjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemwithjdbc.utility.LmsUtility;

public class UserDAOImplementation implements UserDAO {

	LmsUtility lmsUtility = new LmsUtility();

	@Override
	public LibraryUserBean login(String email, String password) {

		LibraryUserBean adminBean = new LibraryUserBean();
		try (Connection conn = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(lmsUtility.getQuery("userLogin"))) {
				pstmt.setString(1, email);
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
		throw new LMSException("Invalid Credentials");

	}

	@Override
	public BookBean searchById(int id) {
		BookBean bookBean = new BookBean();
		try (Connection connection = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(lmsUtility.getQuery("searchBook"))) {
				pstmt.setInt(1, id);
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

		throw new LMSException("No book found");
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {

		try (Connection connection = lmsUtility.getConnection();
				PreparedStatement getReqStmt = connection.prepareStatement(lmsUtility.getQuery("bookReturn"));
				PreparedStatement updateDateStmt = connection
						.prepareStatement(lmsUtility.getQuery("updateReturnDate"));) {
			getReqStmt.setInt(1, userId);
			getReqStmt.setInt(2, bookId);

			try (ResultSet reqResSet = getReqStmt.executeQuery();) {
				if (reqResSet.next()) {
					int requestId = reqResSet.getInt("requestid");

					updateDateStmt.setInt(1, requestId);
					updateDateStmt.executeUpdate();

				} else {
					throw new LMSException("Invalid Return");
				}

			}

		} catch (Exception e) {
			throw new LMSException(e.getMessage());
		}
		return true;

	}

	@Override
	public boolean bookRequest(int id, int bookId) {
		boolean isAvailable = false;
		int reqestedBookId = 0;
		int validBookId = 0;
		int noOfRequests = 0;

		try (Connection connection = lmsUtility.getConnection();
				Statement isReqExists = connection.createStatement();
				PreparedStatement countReqPstmt = connection.prepareStatement(lmsUtility.getQuery("countRequests"));
				PreparedStatement checkAvailPstmt = connection
						.prepareStatement(lmsUtility.getQuery("checkAvailability"));
				PreparedStatement reqPstmt = connection.prepareStatement(lmsUtility.getQuery("insertBookRequest"));) {

			try (ResultSet resultSet1 = isReqExists.executeQuery(lmsUtility.getQuery("showRequest"))) {
				while (resultSet1.next()) {
					reqestedBookId = resultSet1.getInt("bookid");
					if (reqestedBookId == bookId) {
						throw new LMSException("Book is already requested so request can't be placed");
					}
				}

			}
			countReqPstmt.setInt(1, id);

			try (ResultSet countResultSet = countReqPstmt.executeQuery()) {
				if (countResultSet.next()) {
					noOfRequests = countResultSet.getInt(1);

				}

				if (noOfRequests < 3) {
					checkAvailPstmt.setInt(1, bookId);

					try (ResultSet availResultSet = checkAvailPstmt.executeQuery();) {
						while (availResultSet.next()) {
							validBookId = availResultSet.getInt("bookid");
							isAvailable = availResultSet.getBoolean("isAvailable");
						}

						if (validBookId != 0) {
							if (isAvailable) {
								reqPstmt.setInt(1, id);
								reqPstmt.setInt(2, bookId);
								reqPstmt.executeUpdate();

								RequestBean requestInfo = new RequestBean();
								requestInfo.setUserId(id);
								requestInfo.setBookId(bookId);

								return true;
							} else {
								throw new LMSException("Book Is Not Available For Borrowing");
							}
						} else {
							throw new LMSException("The book you are requested is not Present in library");
						}
					} // End Of ResultSet
				} else {
					throw new LMSException("Can't Place More Than 3 Requests");
				}
			} // End Of Count ResultSet

		} catch (Exception e) {
			// e.printStackTrace();
			throw new LMSException(e.getMessage());

		}
	}

	@Override
	public boolean changePassword(String emailId, String oldPassword, String newPassword) {

		try (Connection connection = lmsUtility.getConnection()) {
			try (PreparedStatement pstmt = connection.prepareStatement(lmsUtility.getQuery("changePassword"))) {
				pstmt.setString(1, newPassword);
				pstmt.setString(2, emailId);
				pstmt.setString(3, oldPassword);
				int count = pstmt.executeUpdate();
				if (count != 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new LMSException("Unable to update password");

	}

}
