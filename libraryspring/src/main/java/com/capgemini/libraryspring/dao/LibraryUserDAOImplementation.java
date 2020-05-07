package com.capgemini.libraryspring.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.libraryspring.dto.BookBean;
import com.capgemini.libraryspring.dto.LibraryUserBean;
import com.capgemini.libraryspring.dto.RequestBean;
import com.capgemini.libraryspring.exception.LibraryManagementSystemException;


@Repository
public class LibraryUserDAOImplementation implements LibraryUserDAO {
	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public LibraryUserBean login(String emailId, String password) {
		EntityManager manager = null;
		try {
		factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
		manager = factory.createEntityManager();
		String query = "select a from LibraryUserBean a where a.emailId = :emailId and a.password =:password";
		TypedQuery<LibraryUserBean> adminInfo = manager.createQuery(query, LibraryUserBean.class);
		adminInfo.setParameter("emailId", emailId);
		adminInfo.setParameter("password", password);
		LibraryUserBean bean=adminInfo.getSingleResult();
			return bean;
		} catch (Exception e) {
			throw new LibraryManagementSystemException("Invalid Login Credentials");
		} finally {
			manager.close();

		}
	}

	@Override
	public boolean addUser(LibraryUserBean info) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		String jpql = null;
		boolean flag = false;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			jpql = "select r from LibraryUserBean r ";
			TypedQuery<LibraryUserBean> query = manager.createQuery(jpql, LibraryUserBean.class);
			List<LibraryUserBean> users = query.getResultList();

			for (LibraryUserBean libraryUserBean : users) {
				if (libraryUserBean.getEmailId().equalsIgnoreCase(info.getEmailId())) {
					throw new LibraryManagementSystemException("This EmailId is already existing ");
				}

			}
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(info);
			flag = true;
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (flag) {
				transaction.rollback();
			}
			throw new LibraryManagementSystemException(e.getMessage());

		} finally {
			manager.close();

		}
	}

	@Override
	public List<LibraryUserBean> viewUsers() {

		EntityManager manager = null;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			String jpql = "select m from LibraryUserBean m";
			TypedQuery<LibraryUserBean> query = manager.createQuery(jpql, LibraryUserBean.class);
			List<LibraryUserBean> recordlist = query.getResultList();
			if (recordlist.isEmpty()) {
				throw new LibraryManagementSystemException("No users present in Library");
			} else {
				return recordlist;
			}
		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}
	}

	@Override
	public boolean addBook(BookBean bookDetails) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(bookDetails);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException("Book is already added");
		} finally {
			manager.close();

		}
	}

	@Override
	public BookBean search(int bookId) {

		EntityManager manager = null;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			BookBean search = manager.find(BookBean.class, bookId);
			if (search == null) {
				throw new LibraryManagementSystemException("No book Found");
			} else {
				return search;
			}
		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}
	}

	@Override
	public List<BookBean> viewBooks() {
		EntityManager manager = null;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			String jpql = "select b from BookBean b";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			List<BookBean> bookList = query.getResultList();
			if (bookList.isEmpty()) {
				throw new LibraryManagementSystemException("No books present in libaray");
			} else {
				return bookList;
			}
		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}
	}

	@Override
	public List<RequestBean> viewRequests() {
		EntityManager manager = null;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			String jpql = "select m from RequestBean m";
			TypedQuery<RequestBean> query = manager.createQuery(jpql, RequestBean.class);
			List<RequestBean> recordlist = query.getResultList();
			if (recordlist.isEmpty()) {
				throw new LibraryManagementSystemException("No Requests found");
			} else {
				return recordlist;
			}
		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}

	}

	@Override
	public boolean issueBook(int rid) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		RequestBean info = new RequestBean();
		BookBean bookInfo = new BookBean();
		LibraryUserBean user = new LibraryUserBean();

		int noOfBooks = 0;
		int reqBookId = 0;
		int reqUserId = 0;

		Date date = new Date();
		Date expectedReturnDate = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 15);
		expectedReturnDate = calendar.getTime();

		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			info = manager.find(RequestBean.class, rid);

			if (info != null) {
				Date issueDate = info.getIssueDate();
				if (issueDate == null) {
					reqUserId = info.getId();
					reqBookId = info.getBookId();

					info.setIssueDate(date);
					info.setExpectedReturnDate(expectedReturnDate);
					transaction.commit();

					transaction.begin();
					user = manager.find(LibraryUserBean.class, reqUserId);
					noOfBooks = user.getNoOfBooksBorrowed();
					++noOfBooks;
					System.out.println("No Of Books Borrowed" + noOfBooks);

					user.setNoOfBooksBorrowed(noOfBooks);
					transaction.commit();

					transaction.begin();
					bookInfo = manager.find(BookBean.class, reqBookId);
					bookInfo.setAvaliable(false);
					transaction.commit();
				} else {
					throw new LibraryManagementSystemException("This Book Is Already Issued");
				}

			} else {
				throw new LibraryManagementSystemException("Invalid Request Id");
			}

		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}

		return true;
	}

	@Override
	public boolean removeBook(int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean record = manager.find(BookBean.class, bookId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new LibraryManagementSystemException("Book can't be removed");
		} finally {
			manager.close();

		}

	}

	@Override
	public boolean receivedBook(int requestId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		RequestBean info = new RequestBean();
		BookBean bookInfo = new BookBean();
		LibraryUserBean user = new LibraryUserBean();

		int noOfBooks = 0;
		int reqBookId = 0;
		int reqUserId = 0;
		double fine = 0;

		Date expectedReturnDate = null;
		Date returnedDate = null;

		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			info = manager.find(RequestBean.class, requestId);

			if (info != null) {
				reqBookId = info.getBookId();
				reqUserId = info.getId();
				returnedDate = info.getReturnedDate();
				expectedReturnDate = info.getExpectedReturnDate();
				transaction.commit();

				if ((returnedDate != null) && (expectedReturnDate != null)) {
					long expReturnDateInMilliSecs = expectedReturnDate.getTime();
					long returnedDateInMilliSecs = returnedDate.getTime();
					long diffInMilliSecs = returnedDateInMilliSecs - expReturnDateInMilliSecs;
					int NoOfDaysDelayed = (int) (diffInMilliSecs / (24 * 60 * 60 * 1000));

					transaction.begin();
					user = manager.find(LibraryUserBean.class, reqUserId);
					noOfBooks = user.getNoOfBooksBorrowed();
					--noOfBooks;
					user.setNoOfBooksBorrowed(noOfBooks);
					if (NoOfDaysDelayed > 0) {
						fine = user.getFine();
						fine = fine + (NoOfDaysDelayed * 5);
						user.setFine(fine);
					}
					transaction.commit();

					transaction.begin();
					bookInfo = manager.find(BookBean.class, reqBookId);
					bookInfo.setAvaliable(true);
					transaction.commit();

					transaction.begin();
					info = manager.find(RequestBean.class, requestId);
					manager.remove(info);
					transaction.commit();

				} else {
					throw new LibraryManagementSystemException("User Not Yet Returned The Book");
				}

			} else {
				throw new LibraryManagementSystemException("Invalid Request Id");
			}
		} catch (Exception e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}
		return true;
	}

	@Override
	public boolean bookRequest(int id, int bookId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;

		RequestBean info = new RequestBean();
		BookBean bookInfo = new BookBean();

		String jpql = null;
		int noOfReq = 0;
//		boolean flag = false;

		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			jpql = "select count(*) from RequestBean r where r.id=:id";
			Query query = manager.createQuery(jpql);
			query.setParameter("id", id);
			noOfReq = ((Number) query.getSingleResult()).intValue();
			System.out.println("no of req placed" + noOfReq);

			if (noOfReq < 3) {
				bookInfo = manager.find(BookBean.class, bookId);

				if (bookInfo != null) {
					jpql = "select r from RequestBean r ";
					TypedQuery<RequestBean> query2 = manager.createQuery(jpql, RequestBean.class);
					List<RequestBean> list = query2.getResultList();

					for (RequestBean requestInfo : list) {
						if (requestInfo.getBookId() == bookId) {
							throw new LibraryManagementSystemException(
									"This Book Request is Already Placed By SomeOne ");
						}
					}

					if (bookInfo.isAvaliable()) {
						transaction.begin();
						info.setId(id);
						info.setBookId(bookId);
						manager.persist(info);
						// flag=true;
						transaction.commit();

					} else {
						throw new LibraryManagementSystemException("This Book Is Not Available For Borrowing");
					}

				} else {
					throw new LibraryManagementSystemException("Invalid Book Id");
				}

			} else {
				throw new LibraryManagementSystemException("You have Already Placed Maximum No Of Requests");
			}

		} catch (

		LibraryManagementSystemException e) {
			transaction.rollback();

			// if(flag) {
			// transaction.rollback();
			// }
			throw new LibraryManagementSystemException(e.getMessage());
		}
		return true;
	}

	@Override
	public boolean bookReturn(int userId, int bookId) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		RequestBean info = new RequestBean();

		String jpql = null;
		int reqId = 0;

		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, 20);
		Date returnedDate = calendar2.getTime();

		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			jpql = "select ri from RequestBean ri ";
			TypedQuery<RequestBean> query2 = manager.createQuery(jpql, RequestBean.class);
			List<RequestBean> list = query2.getResultList();

			for (RequestBean requestInfo : list) {
				if ((requestInfo.getBookId() == bookId) && (requestInfo.getId() == userId)) {
					if (requestInfo.getReturnedDate() != null) {
						throw new LibraryManagementSystemException("Already returned this book");
					} else {
						reqId = requestInfo.getrId();
					}
				}
			}

			if (reqId != 0) {
				transaction.begin();
				info = manager.find(RequestBean.class, reqId);
				info.setReturnedDate(returnedDate);
				transaction.commit();

			} else {
				throw new LibraryManagementSystemException(
						"Invalid Book Id,This Is Not The Book You Have Taken / Book Not Yet Issued");
			}

		} catch (LibraryManagementSystemException e) {
			transaction.rollback();
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}

		return true;

	}

	@Override
	public boolean changePassword(int id, String oldPassword, String newPassword) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		LibraryUserBean user = new LibraryUserBean();
		String password = null;

		try {
			factory = Persistence.createEntityManagerFactory("lmsPersistenceUnit");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			user = manager.find(LibraryUserBean.class, id);
			password = user.getPassword();
			if (password.equals(oldPassword)) {
				user.setPassword(newPassword);
				transaction.commit();
			} else {
				throw new LibraryManagementSystemException("Invalid Password");
			}
		} catch (LibraryManagementSystemException e) {
			throw new LibraryManagementSystemException(e.getMessage());
		} finally {
			manager.close();

		}
		return true;
	}

}
