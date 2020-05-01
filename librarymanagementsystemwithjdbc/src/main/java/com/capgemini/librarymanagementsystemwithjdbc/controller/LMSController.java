package com.capgemini.librarymanagementsystemwithjdbc.controller;


import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemwithjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemwithjdbc.dto.RequestInfo;
import com.capgemini.librarymanagementsystemwithjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemwithjdbc.factory.LibraryManagementSystemFactory;
import com.capgemini.librarymanagementsystemwithjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemwithjdbc.service.UserService;
import com.capgemini.librarymanagementsystemwithjdbc.validation.LibraryManagementSystemValidation;

public class LMSController {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		lmsController();
	}

	public static int checkChoice() {
		boolean flag = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Choice Should Contain Only Digits");
				scanner.next();
			}
		} while (!flag);
		return choice;
	}

	public static boolean checkAvailability() {
		boolean isAvailable = false;
		boolean flag = false;
		do {
			try {
				isAvailable = scanner.nextBoolean();
				flag = true;
			} catch (InputMismatchException e) {
				System.err.println("Enter Boolean value either True or False");
				flag = false;
				scanner.next();
			}
		} while (!flag);
		return isAvailable;
	}

	public static String checkRole() {
		String role = null;
		boolean flag = false;
		do {
			role = scanner.next();
			if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user")) {
				flag = true;
			} else {
				System.out.println("Enter role value either user or admin");
				flag = false;
			}
		} while (!flag);
		return role.toLowerCase();
	}

	public static int checkRequestId() {
		boolean flag = false;
		int requestId = 0;
		do {
			try {
				requestId = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Request Id Should Contain Only Digits");
				scanner.next();
			}
		} while (!flag);
		return requestId;
	}

	public static void lmsController() {
    	AdminService adminService = LibraryManagementSystemFactory.getAdminService();
		UserService userService = LibraryManagementSystemFactory.getUserService();
		LibraryManagementSystemValidation lmsValidation = LibraryManagementSystemFactory
				.getLibraryManagementSystemValidation();
		int choice, check, userChoice;

		try {

			do {
				System.out.println("--------WELCOME TO LIBRARY MANAGEMENT SYSTEM--------");
				System.out.println("Press 1 to Amin Login");
				System.out.println("Press 2 to User Login");
				System.out.println("Enter your choice");
				choice = checkChoice();

				switch (choice) {

				case 1:
					try {
						System.out.println("-----------------");
						System.out.println("Enter Admin Email id eg:abc@gmail.com");
						String emailId = scanner.next();
						boolean validateByEmailId = lmsValidation.validateByEmail(emailId);
						while (!validateByEmailId) {
							try {
								throw new LMSException("Please enter valid Email Id");
							} catch (LMSException lmse) {
								System.err.println("Please valid Email");
								emailId = scanner.next();
								if (lmsValidation.validateByEmail(emailId)) {
									break;
								}
							}
						}
						System.out.println("Enter Admin password");
						String password = scanner.next();
						boolean validateByPassword = lmsValidation.passwordValidation(password);
						while (!validateByPassword) {
							try {
								throw new LMSException("Please enter valid password");
							} catch (LMSException lmse) {
								System.err.println("Please valid password");
								password = scanner.next();
								if (lmsValidation.passwordValidation(password)) {
									break;
								}
							}
						}

						LibraryUserBean bean = adminService.login(emailId, password);
						if (bean != null) {
							System.out.println("Admin Login Successfull");

							do {
								System.out.println("1. Register");
								System.out.println("2. Search");
								System.out.println("3. Add Book");
								System.out.println("4. Show All Books");
								System.out.println("5. Show Users");
								System.out.println("6. Remove Book");
								System.out.println("7. Show Requests");
								System.out.println("8. Book Issue");
								System.out.println("9. Receive Returned Book");
								System.out.println("0. Exit");

								System.out.println("Enter your choice");
								check = checkChoice();
								switch (check) {
								case 1:
									System.out.println("Enter user id");
									String userId = scanner.next();
									boolean validateByUserId = lmsValidation.ValidateByUserId(userId);
									while (!validateByUserId) {
										try {
											throw new LMSException("Please enter valid user Id");
										} catch (LMSException lmse) {
											System.out.println("Please enter valid user id");
											userId = scanner.next();
											if (lmsValidation.ValidateByUserId(userId)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter username");
									String userName = scanner.next();
									boolean validateByName = lmsValidation.validateByName(userName);
									while (!validateByName) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											userName = scanner.nextLine();
											if (lmsValidation.validateByName(userName)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter Firstname");
									String firstName = scanner.next();
									boolean validateFirstName = lmsValidation.validateByName(firstName);
									while (!validateFirstName) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											firstName = scanner.nextLine();
											if (lmsValidation.validateByName(firstName)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter Lastname");
									String lastName = scanner.next();
									boolean validateLastName = lmsValidation.validateByName(lastName);
									while (!validateLastName) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											lastName = scanner.nextLine();
											if (lmsValidation.validateByName(lastName)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter email id");
									String email = scanner.next();
									boolean validateByEmailId1 = lmsValidation.validateByEmail(email);
									while (!validateByEmailId1) {
										try {
											throw new LMSException("Please enter valid email id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid email id");
											email = scanner.nextLine();
											if (lmsValidation.validateByEmail(email)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter password");
									String userPassword = scanner.next();
									boolean validatePassword = lmsValidation.passwordValidation(userPassword);
									while (!validatePassword) {
										try {
											throw new LMSException("Please valid password");
										} catch (LMSException lmse) {
											System.out.println("Please enter valid password");
											userPassword = scanner.nextLine();
											if (lmsValidation.passwordValidation(userPassword)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter role");
									String role = checkRole();
									LibraryUserBean user1 = new LibraryUserBean();
									user1.setId(Integer.parseInt(userId));
									user1.setUserName(userName);
									user1.setFirstName(firstName);
									user1.setLastName(lastName);
									user1.setEmailId(email);
									user1.setPassword(userPassword);
									user1.setRole(role);
									try {

										boolean res = adminService.addUser(user1);

										if (res) {
											System.out.println("user registered succesfully");
										}
									} catch (LMSException lmse) {
										System.out.println(lmse.getMessage());
									}

									break;

								case 2:
									System.out.println("Search a Book");
									System.out.println("Enter book Id");
									String searchBookId = scanner.next();
									boolean validateByBookId = lmsValidation.validateByBookId(searchBookId);
									while (!validateByBookId) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											searchBookId = scanner.next();
											if (lmsValidation.validateByBookId(searchBookId)) {
												break;
											}
										}
									}

									try {
										BookBean bookInfo = adminService.searchBook(Integer.parseInt(searchBookId));
										if (bookInfo != null) {
											System.out.println("Book is found");
											System.out.println(bookInfo.getBookId());
											System.out.println(bookInfo.getBookName());
											System.out.println(bookInfo.getAuthorName());

										}
									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}

									break;

								case 3:
									System.out.println("Enter book id");
									String bookId = scanner.next();
									boolean validateBookId = lmsValidation.validateByBookId(bookId);
									while (!validateBookId) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											bookId = scanner.next();
											if (lmsValidation.validateByBookId(bookId)) {
												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter Book name");
									String bookname = scanner.next();
									boolean validateByBookName = lmsValidation.validateByName(bookname);
									while (!validateByBookName) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											bookname = scanner.nextLine();
											if (lmsValidation.validateByName(bookname)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter author name");
									String authorname = scanner.next();
									boolean validateByAuthorName = lmsValidation.validateByName(authorname);
									while (!validateByAuthorName) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											authorname = scanner.nextLine();
											if (lmsValidation.validateByName(authorname)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter publishers name");
									String bookPublisher = scanner.next();
									boolean validateByPublisherName = lmsValidation.validateByName(bookPublisher);
									while (!validateByPublisherName) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											bookPublisher = scanner.nextLine();
											if (lmsValidation.validateByName(bookPublisher)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Enter category");
									String category = scanner.next();
									boolean validateByCategory = lmsValidation.validateByName(category);
									while (!validateByCategory) {
										try {
											throw new LMSException("Please enter only alphabets");
										} catch (LMSException lmse) {
											System.out.println("Please enter only alphabets");
											category = scanner.next();
											if (lmsValidation.validateByName(category)) {

												break;
											}
										}
									}
									scanner.nextLine();
									System.out.println("Is Avaliable");
									boolean isAvaliable = checkAvailability();

									BookBean bookBean1 = new BookBean();

									bookBean1.setBookId(Integer.parseInt(bookId));
									bookBean1.setAuthorName(authorname);
									bookBean1.setBookName(bookname);
									bookBean1.setPublisher(bookPublisher);
									bookBean1.setCategory(category);
									bookBean1.setAvaliable(isAvaliable);
									try {
										boolean bookAdded = adminService.addBook(bookBean1);
										System.out.println(bookAdded);

										if (bookAdded) {
											System.out.println("book is added");
										}
									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}

									break;
								case 4:
									try {

										List<BookBean> allBooks = adminService.showBooks();
										Iterator<BookBean> iterator = allBooks.iterator();
										while (iterator.hasNext()) {
											BookBean book = (BookBean) iterator.next();
											System.out.println("Books present in library are :");
											System.out.println("-------------------------------");
											System.out.println("Book id ---------->" + book.getBookId());
											System.out.println("Book Name --------> " + book.getBookName());
											System.out.println("Book Authour------> " + book.getAuthorName());
											System.out.println("Book publisher------->" + book.getPublisher());
											System.out.println("Book Category-------->" + book.getCategory());
											System.out.println("Book Availability-------->"+book.isAvaliable());
											System.out.println("-----------------------------------------------------");
										}

									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}
									break;

								case 5:
									try {
										System.out.println("users in library are :");
										System.out.println("-------------------------------");

										List<LibraryUserBean> allBooks = adminService.showUsers();
										Iterator<LibraryUserBean> iterator = allBooks.iterator();
										while (iterator.hasNext()) {

											LibraryUserBean user = (LibraryUserBean) iterator.next();
											System.out.println("user id ---------->" + user.getId());
											System.out.println("Username --------> " + user.getUserName());
											System.out.println("Firstname------> " + user.getFirstName());
											System.out.println("Lastname------->" + user.getLastName());
											System.out.println("Email Id-------->" + user.getEmailId());
											System.out.println("Role---------->" + user.getRole());
											System.out.println("No of books borrowed---------->"+user.getNoOfBooksBorrowed());
											System.out.println("Fine------------->"+user.getFine());
											System.out.println("-----------------------------------------------------");
										}

									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}

									break;

								case 6:
									System.out.println("Enter the bookId to be removed:");
									String bookId1 = scanner.next();
									boolean validateBookId1 = lmsValidation.validateByBookId(bookId1);
									while (!validateBookId1) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											bookId1 = scanner.next();
											if (lmsValidation.validateByBookId(bookId1)) {
												break;
											}
										}
									}
									try {
										boolean isDeleted = adminService.removeBook(Integer.parseInt(bookId1));
										if (isDeleted) {
											System.out.println("Book removed from library");
										}
									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}
									break;

								case 7:
									try {

										List<RequestInfo> requestInfos = adminService.showRequests();
										for (RequestInfo info : requestInfos) {
											System.out.println("Requests for Books are :");
											System.out.println("-------------------------------");
											System.out.println("Request Id------------>" + info.getRequestId());
											System.out.println("User id----------- " + info.getUserId());
											System.out.println("Book id ---------- " + info.getBookId());
											System.out.println("Book IssuedDate-------->" + info.getIssueDate());
											System.out.println(
													"Expected Return Date------->" + info.getExpectedReturnDate());
											System.out.println("Returned Date----------->" + info.getReturnDate());
											System.out.println("-------------------------------");
										}
									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}
									break;
								case 8:
									System.out.println("Enter Request Id");
									int rid = checkRequestId();
									try {
										boolean issue = adminService.bookIssue(rid);
										if (issue) {
											System.out.println("Book Issued");
										}
									} catch (LMSException e) {
										System.err.println(e.getMessage());
									}
									break;
								case 9:
									System.out.println("Receive Returned Book");
									System.out.println("-----------------------");
									System.out.println("Enter Request Id");
									rid = checkRequestId();
									try {
										boolean result = adminService.isBookReceived(rid);
										if (result) {
											System.out.println("Book Received");
										}
									} catch (LMSException e) {
										System.err.println(e.getMessage());
									}
									break;
								case 0:
									break;
								default:
									System.out.println("Invalid Option");
									break;

								}

							} while (check != 0);
						}

					} catch (LMSException e) {
						System.err.println(e.getMessage());
						lmsController();
					}
					break;
				case 2:
					try {
						System.out.println("-----------------");
						System.out.println("Enter User Email id eg:xyz@gmail.com");
						String userEmailId = scanner.next();
						boolean validateByEmail = lmsValidation.validateByEmail(userEmailId);
						while (!validateByEmail) {
							try {
								throw new LMSException("Please enter valid Email Id");
							} catch (LMSException lmse) {
								System.err.println("Please valid Email");
								userEmailId = scanner.next();
								if (lmsValidation.validateByEmail(userEmailId)) {
									break;
								}
							}
						}
						System.out.println(
								"Enter User password it should contain (8-15)characters one uppercase,lowercase,special charcter");
						String userPassword = scanner.next();
						boolean validatePassword = lmsValidation.passwordValidation(userPassword);
						while (!validatePassword) {
							try {
								throw new LMSException("Please enter valid password");
							} catch (LMSException lmse) {
								System.err.println("Please valid password");
								userPassword = scanner.next();
								if (lmsValidation.passwordValidation(userPassword)) {
									break;
								}
							}
						}

						LibraryUserBean userInfo = userService.login(userEmailId, userPassword);
						if (userInfo != null) {
							System.out.println("User logged in");
							do {
								System.out.println("1.Change password");
								System.out.println("2. Books in library");
								System.out.println("3. Search a Book");
								System.out.println("4. Request a Book");
								System.out.println("5. Return Book");
								System.out.println("0. Exit");
								System.out.println("Enter your choice");
								userChoice = checkChoice();

								switch (userChoice) {
								case 1:
									System.out.println("Changing Password");
									System.out.println("-----------------");
									System.out.println("Enter User EmailId");
									String userEmail = scanner.next();
									boolean validateByUserEmail = lmsValidation.validateByEmail(userEmail);
									while (!validateByUserEmail) {
										try {
											throw new LMSException("Please enter valid Email Id");
										} catch (LMSException lmse) {
											System.err.println("Please valid Email");
											userEmail = scanner.next();
											if (lmsValidation.validateByEmail(userEmail)) {
												break;
											}
										}
									}
									System.out.println("Enter old password");
									String oldPassword = scanner.next();
									boolean validateByOldPassword = lmsValidation.passwordValidation(oldPassword);
									while (!validateByOldPassword) {
										try {
											throw new LMSException("Please enter valid password");
										} catch (LMSException lmse) {
											System.err.println("Please enter valid password");
											oldPassword = scanner.next();
											if (lmsValidation.passwordValidation(oldPassword)) {
												break;
											}
										}
									}
									System.out.println("Enter new password");
									String newPassword = scanner.next();
									boolean validateByNewPassword = lmsValidation.passwordValidation(newPassword);
									while (!validateByNewPassword) {
										try {
											throw new LMSException("Please enter valid password");
										} catch (LMSException lmse) {
											System.err.println("Please enter valid password");
											newPassword = scanner.next();
											if (lmsValidation.passwordValidation(newPassword)) {
												break;
											}
										}
									}
									try {
										boolean changedPassword = userService.changePassword(userEmail, oldPassword,
												newPassword);
										System.out.println("Password changed successfully");
									} catch (LMSException lmse) {
										System.out.println(lmse.getMessage());
									}
									break;

								case 2:
									try {

										List<BookBean> allBooks = adminService.showBooks();
										Iterator<BookBean> iterator = allBooks.iterator();
										while (iterator.hasNext()) {
											BookBean book = (BookBean) iterator.next();
											System.out.println("Books present in library are :");
											System.out.println("-------------------------------");
											System.out.println("Book id ---------->" + book.getBookId());
											System.out.println("Book Name --------> " + book.getBookName());
											System.out.println("Book Authour------> " + book.getAuthorName());
											System.out.println("Book publisher------->" + book.getPublisher());
											System.out.println("Book Category-------->" + book.getCategory());
											System.out.println("Book Availability-------->"+book.isAvaliable());
											System.out.println("-----------------------------------------------------");
										}

									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}
									break;
								case 3:
									System.out.println("Search a Book");
									System.out.println("Enter book Id");
									String searchBookId = scanner.next();
									boolean validateBySearchBookId = lmsValidation.validateByBookId(searchBookId);
									while (!validateBySearchBookId) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											searchBookId = scanner.next();
											if (lmsValidation.validateByBookId(searchBookId)) {
												break;
											}
										}
									}
									try {
										BookBean bookInfo = userService.searchById(Integer.parseInt(searchBookId));
										if (bookInfo != null) {
											System.out.println("Book is found");
											System.out.println("Book Id---------->" + bookInfo.getBookId());
											System.out.println("Book name is---------->" + bookInfo.getBookName());
											System.out.println("Author name is---------->" + bookInfo.getAuthorName());
										}
									} catch (LMSException e) {
										System.out.println(e.getMessage());
									}

									break;
								case 4:
									LibraryUserBean userBean = new LibraryUserBean();
									System.out.println("Enter user id");
									BookBean booksBean = new BookBean();
									String userId = scanner.next();
									boolean validatedByUserId = lmsValidation.ValidateByUserId(userId);
									while (!validatedByUserId) {
										try {
											throw new LMSException("please enter valid user id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid user Id");
											userId = scanner.next();
											if (lmsValidation.ValidateByUserId(userId)) {
												break;
											}
										}
									}
									userBean.setId(Integer.parseInt(userId));
									System.out.println("Enter book Id");
									String bookId = scanner.next();
									boolean validatedByBookId = lmsValidation.validateByBookId(bookId);
									while (!validatedByBookId) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											bookId = scanner.next();
											if (lmsValidation.validateByBookId(bookId)) {
												break;
											}
										}
									}

									booksBean.setBookId(Integer.parseInt(bookId));
									System.out.println("Enter Book Name");
									String bookName = scanner.next();
									booksBean.setBookName(bookName);

									System.out.println("Enter User Name");
									String userName = scanner.next();
									userBean.setUserName(userName);
									try {

										boolean request = userService.bookRequest(Integer.parseInt(userId),
												Integer.parseInt(bookId));
										if (request) {

											System.out.println("Request placed to admin");

										}

									} catch (LMSException e) {

										System.out.println(e.getMessage());
									}

									break;

								case 5:
									LibraryUserBean info = new LibraryUserBean();

									System.out.println("Returning a book:");
									System.out.println("------------------");
									System.out.println("Enter User Id");
									String id = scanner.next();
									boolean validateId = lmsValidation.ValidateByUserId(id);
									while (!validateId) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											id = scanner.next();
											if (lmsValidation.ValidateByUserId(id)) {
												break;
											}
										}
									}
									System.out.println("Enter Book Id");
									String bookId4 = scanner.next();
									boolean validatedBookId = lmsValidation.validateByBookId(bookId4);
									while (!validatedBookId) {
										try {
											throw new LMSException("please enter valid book id");
										} catch (LMSException lmse) {
											System.out.println("Please enter only valid book Id");
											bookId4 = scanner.next();
											if (lmsValidation.validateByBookId(bookId4)) {
												break;
											}
										}
									}

									try {
										boolean isReturn = userService.bookReturn(Integer.parseInt(id),
												Integer.parseInt(bookId4));
										if (isReturn) {
											System.out.println("Returning request placed to Admin");
										}
									} catch (LMSException e) {
										System.err.println(e.getMessage());
									}

									break;
								case 0:
									break;

								default:
									System.out.println("Invalid option");

									break;
								}// userchoice
							} while (userChoice != 0);
						}
					} catch (LMSException e) {
						System.err.println(e.getMessage());
						lmsController();
					}
					break;
				default:
					System.out.println("Invalid option");
					break;

				}// choice

			} while (true);
		} catch (LMSException e) {
			System.err.println(e.getMessage());
			lmsController();
		}
	}

}
