package com.capgemini.librarymanagementsystemhibernate.controller;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.LibraryUserBean;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestBean;
import com.capgemini.librarymanagementsystemhibernate.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystemhibernate.fatory.LibraryManagementSystemFactory;
import com.capgemini.librarymanagementsystemhibernate.service.LibraryUserService;
import com.capgemini.librarymanagementsystemhibernate.validation.LibraryManagementSystemValidation;

public class LibraryManagementSystem {
	public static Scanner scanner = new Scanner(System.in);

	public static int checkChoice() {
		boolean flag = false;
		int choice = 0;
		do {
			try {
				choice = scanner.nextInt();
				flag = true;
			} catch (InputMismatchException e) {
				flag = false;
				System.err.println("Admin or User Choice Should Contain Only Digits");
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
		LibraryUserService userService = LibraryManagementSystemFactory.getLibraryUserService();

		LibraryManagementSystemValidation lmsValidation = LibraryManagementSystemFactory
				.getLibraryManagementSystemValidation();
		Random rand = new Random();
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
								throw new LibraryManagementSystemException("Please enter valid Email Id");
							} catch (LibraryManagementSystemException lmse) {
								System.err.println(
										"Please valid EmailId eg:abc@gmail.com and it must have @gmail or @yahoo,.com or org");
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
								throw new LibraryManagementSystemException("Please enter valid password");
							} catch (LibraryManagementSystemException lmse) {
								System.err.println(
										"Please valid password,It should contain atleast (8-15)characters i.e., one uppercase,lowercase and special charcter");
								password = scanner.next();
								if (lmsValidation.passwordValidation(password)) {
									break;
								}
							}
						}

						LibraryUserBean bean = userService.login(emailId, password);
						if (bean != null && (bean.getRole().equalsIgnoreCase("Admin"))) {
							System.out.println("Admin Login Successfull");

							do {
								System.out.println("press 1 to Register");
								System.out.println("press 2 to Search");
								System.out.println("press 3 to Add Book");
								System.out.println("press 4 to Display All Books");
								System.out.println("press 5 to Display Users");
								System.out.println("press 6 to Remove Book");
								System.out.println("press 7 to Dispaly Requests");
								System.out.println("press 8 to Book Issue");
								System.out.println("press 9 to Receive Returned Book");
								System.out.println("press 0 to LogOut");

								System.out.println("Enter your choice");
								check = checkChoice();
								switch (check) {
								case 1:
									int userId = (int) (Math.random() * 1000);
									if (userId <= 100) {
										userId = userId + 100;
									}
									System.out.println(userId);
									scanner.nextLine();
									System.out.println("Enter username");
									String userName = scanner.nextLine();
									boolean validateByName = lmsValidation.validateByName(userName);
									while (!validateByName) {
										try {
											throw new LibraryManagementSystemException("Please enter only alphabets");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println("Please enter only alphabets");
											userName = scanner.next();
											if (lmsValidation.validateByName(userName)) {

												break;
											}
										}
									}
									System.out.println("Enter email id");
									String email = scanner.next();
									boolean validateByEmailId1 = lmsValidation.validateByEmail(email);
									while (!validateByEmailId1) {
										try {
											throw new LibraryManagementSystemException("Please enter valid email id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid emailid eg:abc@gmail.com and it must have @gmail or @yahoo,.com or org");
											email = scanner.next();
											if (lmsValidation.validateByEmail(email)) {

												break;
											}
										}
									}
									System.out.println("Enter password");
									String userPassword = scanner.next();
									boolean validatePassword = lmsValidation.passwordValidation(userPassword);
									while (!validatePassword) {
										try {
											throw new LibraryManagementSystemException("Please valid password");
										} catch (LibraryManagementSystemException lmse) {
											System.out.println(
													"Please enter valid password,It should contain atleast (8-15)characters i.e., one uppercase,lowercase and special charcter");
											userPassword = scanner.next();
											if (lmsValidation.passwordValidation(userPassword)) {

												break;
											}
										}
									}
									System.out.println("Enter role");
									String role = checkRole();
									LibraryUserBean user1 = new LibraryUserBean();
									user1.setId(userId);
									user1.setName(userName);
									user1.setEmailId(email);
									user1.setPassword(userPassword);
									user1.setRole(role);
									try {

										boolean res = userService.addUser(user1);

										if (res) {
											System.out.println("user registered succesfully");
										}
									} catch (LibraryManagementSystemException lmse) {
										System.err.println(lmse.getMessage());
									}

									break;

								case 2:
									System.out.println("Search a Book");
									System.out.println("Enter book Id");
									String searchBookId = scanner.next();
									boolean validateByBookId = lmsValidation.validateByBookId(searchBookId);
									while (!validateByBookId) {
										try {
											throw new LibraryManagementSystemException("please enter valid book id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid book Id,It should contain exactly 3 digits");
											searchBookId = scanner.next();
											if (lmsValidation.validateByBookId(searchBookId)) {
												break;
											}
										}
									}

									try {
										BookBean bookInfo = userService.search(Integer.parseInt(searchBookId));
										if (bookInfo != null) {
											System.out.println("Book is found");
											System.out.println("Book Id----->" + bookInfo.getBookId());
											System.out.println("Book Name---->" + bookInfo.getBookName());
											System.out.println("Book Author---->" + bookInfo.getAuthor());
											System.out.println("Book Avaliability------>" + bookInfo.isAvaliable());

										}
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}

									break;

								case 3:
									int bookId = (int) (Math.random() * 1000);
									if (bookId <= 100) {
										bookId = bookId + 100;
									}
									System.out.println(bookId);
									scanner.nextLine();
									System.out.println("Enter Book name");
									String bookname = scanner.next();
									boolean validateByBookName = lmsValidation.validateByName(bookname);
									while (!validateByBookName) {
										try {
											throw new LibraryManagementSystemException("Please enter only alphabets");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println("Please enter only alphabets");
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
											throw new LibraryManagementSystemException("Please enter only alphabets");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println("Please enter only alphabets");
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
											throw new LibraryManagementSystemException("Please enter only alphabets");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println("Please enter only alphabets");
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
											throw new LibraryManagementSystemException("Please enter only alphabets");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println("Please enter only alphabets");
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

									bookBean1.setBookId(bookId);
									bookBean1.setAuthor(authorname);
									bookBean1.setBookName(bookname);
									bookBean1.setPublisher(bookPublisher);
									bookBean1.setCategory(category);
									bookBean1.setAvaliable(isAvaliable);
									try {
										boolean bookAdded = userService.addBook(bookBean1);
										System.out.println(bookAdded);

										if (bookAdded) {
											System.out.println("book is added");
										}
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}

									break;
								case 4:
									try {

										List<BookBean> allBooks = userService.viewBooks();
										System.out.println("Books present in library are :");
										System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
												"BOOKID", "BOOKNAME", "AUTHOR", "PUBLISHER", "CATEGORY", "AVALIABLE"));
										for (BookBean book : allBooks) {

											System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
													book.getBookId(), book.getBookName(), book.getAuthor(),
													book.getPublisher(), book.getCategory(), book.isAvaliable()));
										}

									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}
									break;

								case 5:
									try {
										System.out.println("users in library are :");
										System.out.println("-------------------------------");
										List<LibraryUserBean> allUsers = userService.viewUsers();
										System.out.println("Users of Library are :");
										System.out.println(String.format("%-5s %-20s %-30s %-30s %-10s", "USERId",
												"USERNAME", "EMAILID", "NoOfBooksBorrowed", "FINE"));
										for (LibraryUserBean info : allUsers) {

											System.out.println(String.format("%-5s %-20s %-30s %-30s %-5s",
													info.getId(), info.getName(), info.getEmailId(),
													info.getNoOfBooksBorrowed(), info.getFine()));
										}
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}

									break;

								case 6:
									System.out.println("Enter the bookId to be removed:");
									String bookId1 = scanner.next();
									boolean validateBookId1 = lmsValidation.validateByBookId(bookId1);
									while (!validateBookId1) {
										try {
											throw new LibraryManagementSystemException("please enter valid book id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid book Id,It should contain exactly 3 digits");
											bookId1 = scanner.next();
											if (lmsValidation.validateByBookId(bookId1)) {
												break;
											}
										}
									}
									try {
										boolean isDeleted = userService.removeBook(Integer.parseInt(bookId1));
										if (isDeleted) {
											System.out.println("Book removed from library");
										}
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}
									break;

								case 7:
									try {
										List<RequestBean> requestInfos = userService.viewRequests();
										System.out.println("Requests for Books are :");
										System.out.println(String.format("%-10s %-10s %-10s %-40s %-45s %-35s",
												"REQUESTID", "USER ID", "BOOK ID", "ISSUE DATE", "EXPECTED RETURN DATE",
												"RETURN DATE"));

										for (RequestBean requestInfo : requestInfos) {
											System.out.println(String.format("%-10s %-10s %-10s %-40s %-45s %-35s",
													requestInfo.getrId(), requestInfo.getId(), requestInfo.getBookId(),
													requestInfo.getIssueDate(), requestInfo.getExpectedReturnDate(),
													requestInfo.getReturnedDate()));
										}

									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}
									break;
								case 8:
									System.out.println("Enter Request Id");
									int rid = checkRequestId();
									try {
										boolean issue = userService.issueBook(rid);
										System.out.println("Book Issued");
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}
									break;
								case 9:
									System.out.println("Receive Returned Book");
									System.out.println("-----------------------");
									System.out.println("Enter Request Id");
									rid = checkRequestId();
									try {
										boolean result = userService.receivedBook(rid);
										if (result) {
											System.out.println("Book Received to libraian successfully");
										}
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}
									break;
								case 0:
									break;
								default:
									System.err.println("Invalid Option,Choice should in between 0 to 9");
									break;

								}

							} while (check != 0);
						} else {
							System.err.println("Invalid login Credentails");
						}

					} catch (LibraryManagementSystemException e) {
						System.err.println(e.getMessage());
						lmsController();
					}
					break;
				case 2:
					try {
						System.out.println("-----------------");
						System.out.println("Enter User EmailId eg:xyz@gmail.com");
						String userEmailId = scanner.next();
						boolean validateByEmail = lmsValidation.validateByEmail(userEmailId);
						while (!validateByEmail) {
							try {
								throw new LibraryManagementSystemException("Please enter valid Email Id");
							} catch (LibraryManagementSystemException lmse) {
								System.err.println(
										"Please valid EmailId eg:abc@gmail.com and it must have @gmail or @yahoo,.com or org");
								userEmailId = scanner.next();
								if (lmsValidation.validateByEmail(userEmailId)) {
									break;
								}
							}
						}
						System.out.println("Enter User password");
						String userPassword = scanner.next();
						boolean validatePassword = lmsValidation.passwordValidation(userPassword);
						while (!validatePassword) {
							try {
								throw new LibraryManagementSystemException("Please enter valid password");
							} catch (LibraryManagementSystemException lmse) {
								System.err.println(
										"Please valid password,It should contain atleast (8-15)characters i.e., one uppercase,lowercase and special charcter");
								userPassword = scanner.next();
								if (lmsValidation.passwordValidation(userPassword)) {
									break;
								}
							}
						}

						LibraryUserBean userInfo = userService.login(userEmailId, userPassword);
						if (userInfo != null && (userInfo.getRole().equalsIgnoreCase("user"))) {
							System.out.println("User logged in");
							do {
								System.out.println("press 1 to Change password");
								System.out.println("press 2 to Books in library");
								System.out.println("press 3 to Search a Book");
								System.out.println("press 4 to Request a Book");
								System.out.println("press 5 to Return Book");
								System.out.println("press 0 to LogOut");
								System.out.println("Enter your choice");
								userChoice = checkChoice();

								switch (userChoice) {
								case 1:
									LibraryUserBean user = new LibraryUserBean();
									System.out.println("Changing Password");
									System.out.println("-----------------");
									System.out.println("Enter UserId");
									String validUserId = scanner.next();
									boolean validatedById = lmsValidation.ValidateByUserId(validUserId);
									while (!validatedById) {
										try {
											throw new LibraryManagementSystemException("please enter valid user id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid user Id,It should contain exactly 3 digits");
											validUserId = scanner.next();
											if (lmsValidation.ValidateByUserId(validUserId)) {
												break;
											}
										}
									}
									System.out.println("Enter old password");
									String oldPassword = scanner.next();
									boolean validateByOldPassword = lmsValidation.passwordValidation(oldPassword);
									while (!validateByOldPassword) {
										try {
											throw new LibraryManagementSystemException("Please enter valid password");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter valid password,It should contain atleast (8-15)characters i.e., one uppercase,lowercase and special charcter");
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
											throw new LibraryManagementSystemException("Please enter valid password");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter valid password,It should contain atleast (8-15)characters i.e., one uppercase,lowercase and special charcter");
											newPassword = scanner.next();
											if (lmsValidation.passwordValidation(newPassword)) {
												break;
											}
										}
									}

									try {
										boolean changedPassword = userService.changePassword(
												Integer.parseInt(validUserId), oldPassword, newPassword);
										System.out.println("Password changed successfully");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println(lmse.getMessage());
									}
									break;

								case 2:
									try {

										List<BookBean> allBooks = userService.viewBooks();
										System.out.println("Books present in library are :");
										System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
												"BOOKID", "BOOKNAME", "AUTHOR", "PUBLISHER", "CATEGORY", "AVALIABLE"));
										for (BookBean book : allBooks) {

											System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
													book.getBookId(), book.getBookName(), book.getAuthor(),
													book.getPublisher(), book.getCategory(), book.isAvaliable()));
										}

									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}
									break;
								case 3:
									System.out.println("Search a Book");
									System.out.println("Enter book Id");
									String searchBookId = scanner.next();
									boolean validateBySearchBookId = lmsValidation.validateByBookId(searchBookId);
									while (!validateBySearchBookId) {
										try {
											throw new LibraryManagementSystemException("please enter valid book id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid book Id,It should contain exactly 3 digits");
											searchBookId = scanner.next();
											if (lmsValidation.validateByBookId(searchBookId)) {
												break;
											}
										}
									}
									try {
										BookBean bookInfo = userService.search(Integer.parseInt(searchBookId));
										if (bookInfo != null) {
											System.out.println("Book is found");
											System.out.println("Book Id---------->" + bookInfo.getBookId());
											System.out.println("Book name is---------->" + bookInfo.getBookName());
											System.out.println("Author name is---------->" + bookInfo.getAuthor());
											System.out
													.println("Book Avaliability----------->" + bookInfo.isAvaliable());
										}
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
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
											throw new LibraryManagementSystemException("please enter valid user id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid user Id,It should contain exactly 3 digits");
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
											throw new LibraryManagementSystemException("please enter valid book id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid book Id,It should contain exactly 3 digits");
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
									userBean.setName(userName);
									try {

										boolean request = userService.bookRequest(Integer.parseInt(userId),
												Integer.parseInt(bookId));
										if (request) {

											System.out.println("Request placed to admin");

										}

									} catch (LibraryManagementSystemException e) {

										System.err.println(e.getMessage());
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
											throw new LibraryManagementSystemException("please enter valid book id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid book Id,It should contain exactly 3 digits");
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
											throw new LibraryManagementSystemException("please enter valid book id");
										} catch (LibraryManagementSystemException lmse) {
											System.err.println(
													"Please enter only valid book Id,It should contain exactly 3 digits");
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
									} catch (LibraryManagementSystemException e) {
										System.err.println(e.getMessage());
									}

									break;
								case 0:
									break;

								default:
									System.err.println("Invalid option,Choice should in between 0 to 5");

									break;
								}// userchoice
							} while (userChoice != 0);
						} else {
							System.err.println("Invalid Login Credentails");
						}
					} catch (LibraryManagementSystemException e) {
						System.err.println(e.getMessage());
						lmsController();
					}
					break;
				default:
					System.err.println("Invalid option,Choice should be either 1 or 2");
					break;

				}// choice

			} while (true);
		} catch (LibraryManagementSystemException e) {
			System.err.println(e.getMessage());
			lmsController();
		}
	}

}
