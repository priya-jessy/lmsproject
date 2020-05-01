package com.capgemini.librarymanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;

import com.capgemini.librarymanagementsystem.dto.BookBean;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserBean;
import com.capgemini.librarymanagementsystem.exception.LibraryManagementSystemException;
import com.capgemini.librarymanagementsystem.factory.LibraryManagementSystemFactory;
import com.capgemini.librarymanagementsystem.service.AdminServiceDAO;
import com.capgemini.librarymanagementsystem.service.UserServiceDAO;
import com.capgemini.librarymanagementsystem.validation.LibraryManagementSystemValidation;

public class LibraryManagementSystemController {

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
				System.err.println("Admin Choice or User Choice Should Contain Only Digits");
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

	public static void main(String[] args) {
		lmsController();

	}

	public static void lmsController() {

		AdminServiceDAO service = LibraryManagementSystemFactory.getAdminServiceDAO();
		UserServiceDAO userService = LibraryManagementSystemFactory.getUserService();
		UserBean userBean = new UserBean();
		BookBean bookBean = new BookBean();
		LibraryManagementSystemValidation validation = LibraryManagementSystemFactory.getValidation();

		int choice, check, userChoice;

		do {
			System.out.println("--------WELCOME TO LIBRARY MANAGEMENT SYSTEM--------");
			System.out.println("Press 1 to Amin Login");
			System.out.println("Press 2 to User Login");
			System.out.println("Enter your choice");
			choice = checkChoice();

			switch (choice) {

			case 1:
                System.out.println("Admin Login Page");
				System.out.println("-----------------");
				System.out.println("Enter Admin Email id eg:xyz@gmail.com");
				String adminEmailId = scanner.next();
				boolean validateByEmailId = validation.validateByEmail(adminEmailId);
				while (!validateByEmailId) {
					try {
						throw new LibraryManagementSystemException("Please enter valid Email Id");
					} catch (LibraryManagementSystemException lmse) {
						System.err.println("Please Enter  valid Admin EmailId");
						adminEmailId = scanner.next();
						if (validation.validateByEmail(adminEmailId)) {
							break;
						}
					}
				}
				System.out.println(
						"Enter Password i.e.,Password should contain (8-15)characters i.e.., atleast one uppercase,lower case,special character");
				String adminPassword = scanner.next();
				boolean validatePassword = validation.passwordValidation(adminPassword);
				while (!validatePassword) {
					try {
						throw new LibraryManagementSystemException("Please enter valid password");
					} catch (LibraryManagementSystemException lmse) {
						System.err.println("Please enter valid password");
						adminPassword = scanner.next();
						if (validation.passwordValidation(adminPassword)) {
							break;
						}
					}
				}
				try {

					boolean res = service.adminLogin(adminEmailId, adminPassword);
					if (res) {
						System.out.println("Admin logged in");
						do {
							System.out.println("Press 1 to  Register Student");
							System.out.println("Press 2 to Search");
							System.out.println("Press 3 to Add Book");
							System.out.println("Press 4 to Remove Book");
							System.out.println("Press 5 to View all Books");
							System.out.println("Press 6 to Book Issue");
							System.out.println("Press 7 to View all Users");
							System.out.println("Press 8 to View all Requests");
							System.out.println("Press 9 to Receive Returned Book");
							System.out.println("Press 0 to LogOut");

							System.out.println("Enter your choice");
							check = checkChoice();

							switch (check) {
							case 1:
								System.out.println("Enter Registration Details");
								System.out.println("----------------------------");
								System.out.println("Enter user Id");
								String regId = scanner.next();
								boolean validateUserId = validation.ValidateByUserId(regId);
								while (!validateUserId) {
									try {
										throw new LibraryManagementSystemException("please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter valid user id,It contains exactly 3 digits only");
										regId = scanner.next();
										if (validation.ValidateByUserId(regId)) {
											break;
										}
									}
								}
								System.out.println("Enter user name");
								String regName = scanner.next();
								boolean ValidateByName = validation.validateByName(regName);
								while (!ValidateByName) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper user name,It should contain only alphabets");
										regName = scanner.next();
										if (validation.validateByName(regName)) {
											break;
										}
									}
								}
								System.out.println("Enter Email Id eg:abc@gmail.com");
								String regEmailId = scanner.next();
								boolean validateByEmail = validation.validateByEmail(regEmailId);
								while (!validateByEmail) {
									try {
										throw new LibraryManagementSystemException("Please enter valid Email Id");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please Enter valid Email eg:abc@gmail.com");
										regEmailId = scanner.next();
										if (validation.validateByEmail(regEmailId)) {
											break;
										}
									}
								}
								System.out.println("Enter password");
								System.out.println(
										"Password shoud contain 8-15 characters,atleast one uppercase,lowercase and atleast one special charater");
								String regPassword = scanner.next();
								boolean validateByPassword = validation.passwordValidation(regPassword);
								while (!validateByPassword) {
									try {
										throw new LibraryManagementSystemException("Please enter valid password");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter valid password,It should contain 8-15 characters,atleast one uppercase,lowercase and atleast one special charater");
										regPassword = scanner.next();
										if (validation.passwordValidation(regPassword)) {
											break;
										}
									}
								}

								UserBean userBean1 = new UserBean();
								userBean1.setUserid(Integer.parseInt(regId));
								userBean1.setUsername(regName);
								userBean1.setEmail(regEmailId);
								userBean1.setPassword(regPassword);
								try {
									boolean result = service.addUser(userBean1);

									if (result) {
										System.out.println("User Registered successfully");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}
								break;

							case 2:
								System.out.println("Search a Book");
                                System.out.println("-------------------------");
								System.out.println("Enter book Id");
								String searchBookId = scanner.next();
								boolean validateByBookId = validation.idValidation(searchBookId);
								while (!validateByBookId) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper book id,It should contain exactly 6 digits");
										searchBookId = scanner.next();
										if (validation.idValidation(searchBookId)) {
											break;
										}
									}
								}
								try {
									BookBean bookSearch = service.searchBook(Integer.parseInt(searchBookId));
									System.out.println("Book found");
									System.out.println("Book Id ------------->" + bookSearch.getBookId());
									System.out.println("Book name-------------->" + bookSearch.getBookName());
									System.out.println("Author name---------->" + bookSearch.getAuthorName());
									System.out.println("Book Availability------>" + bookSearch.isAvaliable());

								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());

								}
								break;

							case 3:
								System.out.println("Add Book Details");
								System.out.println("Enter Book id");
								String bookId = scanner.next();
								boolean validateBookId = validation.idValidation(bookId);
								while (!validateBookId) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper book id,It should contain exactly 6 digits");
										bookId = scanner.next();
										if (validation.idValidation(bookId)) {
											break;
										}
									}
								}
								scanner.nextLine();
								System.out.println("Enter Author name");
								String authourName = scanner.next();
								boolean ValidateByAuthorName = validation.validateByName(authourName);
								while (!ValidateByAuthorName) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper name,It should contain only alphabets");
										authourName = scanner.nextLine();
										if (validation.validateByName(authourName)) {
											break;
										}
									}
								}
								scanner.nextLine();
								System.out.println("Enter Book Title");
								String bookTitle = scanner.next();
								boolean ValidateByBookName = validation.validateByName(bookTitle);
								while (!ValidateByBookName) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper name,It should contain only alphabets");
										bookTitle = scanner.next();
										if (validation.validateByName(bookTitle)) {
											break;
										}
									}
								}
								BookBean bookBean1 = new BookBean();

								bookBean1.setBookId(Integer.parseInt(bookId));
								bookBean1.setAuthorName(authourName);
								bookBean1.setBookName(bookTitle);
								scanner.nextLine();
								System.out.println("Enter Avalaiblity");
								boolean isAvaliable = checkAvailability();
								bookBean1.setAvaliable(isAvaliable);

								try {

									boolean bookAdded = service.addBook(bookBean1);
									System.out.println(bookAdded);
									if (bookAdded) {
										System.out.println("Book added to library Successfully");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}

								break;
							case 4:
								System.out.println("Enter book id to be remove ");
								String removeBookId = scanner.next();
								boolean validateByBookId1 = validation.idValidation(removeBookId);
								while (!validateByBookId1) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper book id,It should contain 6 digits exactly");
										removeBookId = scanner.next();
										if (validation.idValidation(removeBookId)) {
											break;
										}
									}
								}
								bookBean.setBookId(Integer.parseInt(removeBookId));
								try {
									boolean bookRemoved = service.removeBook(Integer.parseInt(removeBookId));
									if (bookRemoved) {
										System.out.println("Book Removed from library successfully");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}
								break;

							case 5:
								try {

									List<BookBean> allBooks = service.showBooks();
									for (BookBean book : allBooks) {
										System.out.println("Books present in library are :");
										System.out.println("-------------------------------");
										System.out.println("Book id ----------> " + book.getBookId());
										System.out.println("Book Name -------- >" + book.getBookName());
										System.out.println("Book Authour------ >" + book.getAuthorName());
										System.out.println("Book Avaliability----->" + book.isAvaliable());
										System.out
												.println("-----------------------------------------------------------");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}

								break;

							case 6:
								UserBean userBean2 = new UserBean();
								BookBean bookBean2 = new BookBean();
								System.out.println("enter Book Id");
								String bId = scanner.next();
								boolean validateByBid1 = validation.idValidation(bId);
								while (!validateByBid1) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper book id,It should contain 6 digits exactly");
										bId = scanner.next();
										if (validation.idValidation(bId)) {
											break;
										}
									}
								}
								System.out.println("enter User Id");
								String uId = scanner.next();
								boolean validateByUserId = validation.ValidateByUserId(uId);
								while (!validateByUserId) {
									try {
										throw new LibraryManagementSystemException("pleas enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter valid id number,it should contain 3 digits exactly");
										uId = scanner.next();
										if (validation.ValidateByUserId(uId)) {
											break;
										}
									}
								}

								bookBean2.setBookId(Integer.parseInt(bId));
								userBean2.setUserid(Integer.parseInt(uId));
								try {
									boolean isIssued = service.bookIssue(Integer.parseInt(uId), Integer.parseInt(bId));
									if (isIssued) {
										System.out.println("Book Issued to user successfully");
									}

								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}
								break;

							case 7:
								try {
									List<UserBean> userInfos = service.showUsers();
									for (UserBean info : userInfos) {
										System.out.println("Users of Library are :");
										System.out.println("-------------------------------");

										System.out.println("User id ---------- " + info.getUserid());
										System.out.println("User Name -------- " + info.getUsername());
										System.out.println("User Email------ " + info.getEmail());
										System.out.println(
												"User No Of Books Borrowed ------- " + info.getNumberOfBooks());
										System.out.println("Fine--------->" + info.getFine());
										System.out.println("-------------------------------");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}
								break;
							case 8:
								try {
									List<RequestInfo> requestInfos = service.showRequests();
									for (RequestInfo info : requestInfos) {
										System.out.println("Requests for Books are :");
										System.out.println("-------------------------------");
										System.out.println("Book id ---------- " + info.getBookId());
										System.out.println("User id -------- " + info.getUserId());
										System.out.println("Book Issued ------" + info.isIssued());
										System.out.println("Book Returned------" + info.isReturned());
										System.out.println("Book IssueDate------------" + info.getIssuedDate());
										System.out.println(
												"Book Excepted return date---->" + info.getExpectedReturnedDate());
										System.out.println(
												"book should be returned on----------->" + info.getReturnedDate());
										System.out.println("-------------------------------");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.out.println(lmse.getMessage());
								}
								break;
							case 9:
								System.out.println("Receive Returned Book");
								System.out.println("-----------------------");
								System.out.println("Enter User Id");
								String user1 = scanner.next();
								boolean validateByUserId1 = validation.ValidateByUserId(user1);
								while (!validateByUserId1) {
									try {
										throw new LibraryManagementSystemException("pleas enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter valid id number,It should contain 3 digits exactly");
										user1 = scanner.next();
										if (validation.ValidateByUserId(user1)) {
											break;
										}
									}
								}
								System.out.println("Enter Book Id");
								String book1 = scanner.next();
								boolean validateByBid = validation.idValidation(book1);
								while (!validateByBid) {
									try {
										throw new LibraryManagementSystemException("Please enter valid data");
									} catch (LibraryManagementSystemException lmse) {
										System.err.println("Please enter proper book id,It should contain 6 digits");
										book1 = scanner.next();
										if (validation.idValidation(book1)) {
											break;
										}
									}
								}

								bookBean.setBookId(Integer.parseInt(book1));
								userBean.setUserid(Integer.parseInt(user1));
								try {
									boolean isReceived = service.isBookReceived(Integer.parseInt(user1),
											Integer.parseInt(book1));
									if (isReceived) {
										System.out.println("Book received from user successfully");
									}
								} catch (LibraryManagementSystemException lmse) {
									System.err.println(lmse.getMessage());
								}
								break;
							case 0:
								break;
							default:
								System.out.println("Invalid option,Please enter from above options only");
								break;

							}

						} while (check != 0);
					}
				} catch (LibraryManagementSystemException lmse) {
					System.out.println(lmse.getMessage());
				}

				break;
			case 2:
                System.out.println("User Login Page");
				System.out.println("-----------------");
				System.out.println("Enter User Email id ex:xyz@gmail.com");
				String userEmailId1 = scanner.next();
				boolean validateByEmail = validation.validateByEmail(userEmailId1);
				while (!validateByEmail) {
					try {
						throw new LibraryManagementSystemException("Please enter valid Email Id ex:xyz@gmail.com");
					} catch (LibraryManagementSystemException lmse) {
						System.err.println("Please valid Email");
						userEmailId1 = scanner.next();
						if (validation.validateByEmail(userEmailId1)) {
							break;
						}
					}
				}
				System.out.println("Enter User password");
				String userPassword1 = scanner.next();
				boolean validateByPassword = validation.passwordValidation(userPassword1);
				while (!validateByPassword) {
					try {
						throw new LibraryManagementSystemException("Please enter valid password");
					} catch (LibraryManagementSystemException lmse) {
						System.err.println("Please enter valid password");
						userPassword1 = scanner.next();
						if (validation.passwordValidation(userPassword1)) {
							break;
						}
					}
				}
				try {

					UserBean userInfo = userService.login(userEmailId1, userPassword1);
					System.out.println("User logged in");
					do {
						System.out.println("1. Changepassword");
						System.out.println("2. Books in Library");
						System.out.println("3. Search a Book");
						System.out.println("4. Request a Book");
						System.out.println("5. Return Book");
						System.out.println("0. LogOut");
						System.out.println("Enter your choice");
						userChoice = checkChoice();
						switch (userChoice) {
						case 1:
							System.out.println("Changing Password");
							System.out.println("-----------------");
							System.out.println("Enter User EmailId");
							String userEmailId = scanner.next();
							boolean validateByUserEmail = validation.validateByEmail(userEmailId);
							while (!validateByUserEmail) {
								try {
									throw new LibraryManagementSystemException("Please enter valid Email Id");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please valid Email eg:abc@gmail.com");
									userEmailId = scanner.next();
									if (validation.validateByEmail(userEmailId)) {
										break;
									}
								}
							}
							System.out.println("Enter old password");
							String oldPassword = scanner.next();
							boolean validateByOldPassword = validation.passwordValidation(oldPassword);
							while (!validateByOldPassword) {
								try {
									throw new LibraryManagementSystemException("Please enter valid password");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter valid password,It should contain 8-15 characters,atleast one uppercase,lowercase and atleast one special charater");
									oldPassword = scanner.next();
									if (validation.passwordValidation(oldPassword)) {
										break;
									}
								}
							}
							System.out.println("Enter new password");
							String newPassword = scanner.next();
							boolean validateByNewPassword = validation.passwordValidation(newPassword);
							while (!validateByNewPassword) {
								try {
									throw new LibraryManagementSystemException("Please enter valid password");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter valid password,It should contain 8-15 characters,atleast one uppercase,lowercase and atleast one special charater");
									newPassword = scanner.next();
									if (validation.passwordValidation(newPassword)) {
										break;
									}
								}
							}
							try {
								boolean changedPassword = userService.changePassword(userEmailId, oldPassword,
										newPassword);
								System.out.println("Password changed successfully");
							} catch (LibraryManagementSystemException lmse) {
								System.out.println(lmse.getMessage());
							}
							break;

						case 2:
							List<BookBean> allBooks = service.showBooks();
							for (BookBean book : allBooks) {
								System.out.println("Books present in library are :");
								System.out.println("-------------------------------");
								System.out.println("Book Id----------->" + book.getBookId());
								System.out.println("Book Name-------------->" + book.getBookName());
								System.out.println("Author name-------->" + book.getAuthorName());
								System.out.println("Book Avaliability---->" + book.isAvaliable());
								System.out.println("---------------------------");
							}
							System.out.println("No books present in library");

							break;
						case 3:
							System.out.println("Search a Book");
							System.out.println("Enter book Id it should contain 6 digits");
							String searchBookId = scanner.next();
							boolean validateByBookId = validation.idValidation(searchBookId);
							while (!validateByBookId) {
								try {
									throw new LibraryManagementSystemException("Please enter valid data");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter proper book id,It should contain exactly 6 digits");
									searchBookId = scanner.next();
									if (validation.idValidation(searchBookId)) {
										break;
									}
								}
							}
							try {
								BookBean bookSearch = service.searchBook(Integer.parseInt(searchBookId));
								System.out.println("Book found");
								System.out.println("Book Id---------->" + bookSearch.getBookId());
								System.out.println("Book Name-------->" + bookSearch.getBookName());
								System.out.println("Author name--------->" + bookSearch.getAuthorName());
								System.out.println("Book Availability----->" + bookSearch.isAvaliable());

							} catch (LibraryManagementSystemException e) {
								System.out.println(e.getMessage());

							}
							break;

						case 4:
							System.out.println("Enter book id");
							String bookId = scanner.next();
							boolean validateByBookId1 = validation.idValidation(bookId);
							while (!validateByBookId1) {
								try {
									throw new LibraryManagementSystemException("Please enter valid data");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter proper book id,It should contain exactly 6 digits");
									bookId = scanner.next();
									if (validation.idValidation(bookId)) {
										break;
									}
								}
							}
							bookBean.setBookId(Integer.parseInt(bookId));

							System.out.println("Enter user id ");
							String userId = scanner.next();
							boolean validateByUserId1 = validation.ValidateByUserId(userId);
							while (!validateByUserId1) {
								try {
									throw new LibraryManagementSystemException("pleas enter valid data");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter valid id number,It should contain exactly 3 digits");
									userId = scanner.next();
									if (validation.ValidateByUserId(userId)) {
										break;
									}
								}
							}
							userBean.setUserid(Integer.parseInt(userId));
							RequestInfo request = new RequestInfo();
							try {
								boolean request1 = userService.bookRequest(Integer.parseInt(userId),
										Integer.parseInt(bookId));
								if (request1) {
									System.out.println("Request placed to admin");
								}
							} catch (LibraryManagementSystemException lmse) {
								System.out.println(lmse.getMessage());
							}
							break;
						case 5:
							System.out.println("Returning a book:");
							System.out.println("------------------");
							System.out.println("Enter User Id");
							String user = scanner.next();
							boolean validateByUser = validation.ValidateByUserId(user);
							while (!validateByUser) {
								try {
									throw new LibraryManagementSystemException("pleas enter valid data");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter valid id number,It should contain exactly 3 digits");
									user = scanner.next();
									if (validation.ValidateByUserId(user)) {
										break;
									}
								}
							}
							System.out.println("Enter Book Id");
							String book = scanner.next();
							boolean validatedByBookId = validation.idValidation(book);
							while (!validatedByBookId) {
								try {
									throw new LibraryManagementSystemException("Please enter valid data");
								} catch (LibraryManagementSystemException lmse) {
									System.err.println("Please enter proper book id,It should contain exactly 3 digits");
									book = scanner.next();
									if (validation.idValidation(book)) {
										break;
									}
								}
							}
							userBean.setUserid(Integer.parseInt(user));
							bookBean.setBookId(Integer.parseInt(book));
							RequestInfo requestInfo = new RequestInfo();

							try {
								boolean requestInfo1 = userService.bookReturn(Integer.parseInt(user),
										Integer.parseInt(book));
								if (requestInfo1) {
									System.out.println("Book is Returning to Admin");
								}

							} catch (LibraryManagementSystemException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 0:
							break;

						default:
							System.out.println("Invalid option,Please enter from above options only");

							break;
						}// userchoice
					} while (userChoice != 0);

				} catch (LibraryManagementSystemException lmse) {
					System.out.println(lmse.getMessage());
				}
				break;
			default:
				System.out.println("Invalid option,Please enter from above options only");
				break;

			}

		} while (true);

	}
}
