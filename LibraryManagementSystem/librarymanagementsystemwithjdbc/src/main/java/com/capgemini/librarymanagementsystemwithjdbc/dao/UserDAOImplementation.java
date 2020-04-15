package com.capgemini.librarymanagementsystemwithjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;
import com.capgemini.librarymanagementsystemwithjdbc.exception.LMSException;


public class UserDAOImplementation implements UserDAO{

	@Override
	public LibraryUserBean login(String email, String password) {
		
		LibraryUserBean adminBean = new LibraryUserBean();
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			
			String dburl=properties.getProperty("dburl");
			
			try(Connection conn = DriverManager.getConnection(dburl)){
				String query =properties.getProperty("userLogin") ;
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setString(1, email);
					pstmt.setString(2, password); 
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
					adminBean.setEmailId(rs.getString("emailid"));
					adminBean.setPassword(rs.getString("password"));
							return adminBean;
						}else {
							System.out.println();
							return null;
					}
					}	
				}
			}catch (Exception e) {
			e.printStackTrace();	
			}
		throw new LMSException("User can't login");
		
	}

	@Override
	public BookBean searchById(int id) {
		BookBean bookBean=new BookBean();
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("searchBook");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1, id);
					ResultSet rs=pstmt.executeQuery();
					if(rs.next()) {
						bookBean.setBookId(rs.getInt("bookid"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						return bookBean;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		throw new LMSException("No book found");
	}

		@Override
	public boolean bookReturn(int userId, int bookId) {
		ResultSet rs=null;
		PreparedStatement pstmt1=null;
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				
				try(PreparedStatement pstmt=connection.prepareStatement(properties.getProperty("bookReturn"))){
					pstmt.setInt(1, userId);
					pstmt.setInt(2, bookId);

					rs = pstmt.executeQuery();

					if (rs.next() != false) {
						int requestId = rs.getInt("requestId");
						System.out.println("Request Id....." + requestId);

					
//						query = properties.getProperty("updateReturnDate");
						pstmt1 = connection.prepareStatement(properties.getProperty("updateReturnDate"));
						pstmt1.setInt(1, requestId);

						int count = pstmt1.executeUpdate();
						if (count != 0) {
							return true;
						}
					}
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		throw new LMSException("Book cannot be returned");
	}

	@Override
	public RequestInfo bookRequest(int id, int bookId) {
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		
		ResultSet resultSet = null;
		boolean isavail = false;
		
		try (FileInputStream file = new FileInputStream("LibraryManagementSystemDataBase.properties")) {
			Properties properties = new Properties();
			properties.load(file);
			Class.forName(properties.getProperty("path"));
			try (Connection connection = DriverManager.getConnection(properties.getProperty("dburl"))) {
								

				statement = connection.prepareStatement(properties.getProperty("checkAvailability"));
				statement.setInt(1, bookId);
				resultSet = statement.executeQuery();

				System.out.println("outof avial");
				while (resultSet.next()) {
					isavail = resultSet.getBoolean(1);
					System.out.println(isavail);
				}
				if (isavail) {
					statement1 = connection.prepareStatement(properties.getProperty("insertBookRequest"));
					statement1.setInt(1, id);
					statement1.setInt(2, bookId);

					 int count = statement1.executeUpdate();
					if (count != 0) {
						RequestInfo requestInfo = new RequestInfo();

						requestInfo.setUserId(id);
						requestInfo.setBookId(bookId);

						return requestInfo;

					} 
				} 

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		throw new LMSException("Request Cannot Be Placed");
	}

	}

