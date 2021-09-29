package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.controller.MailSend;
import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.BookService;

@Repository
public class IssueBookDAOImpl implements IssueBookDAO {

	private static final Logger logger = LogManager.getLogger(IssueBookDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	BookService bookService;

	private static final String GET_ALL_ISSUEDBOOKS = "select i from IssueBook i";
	private static final String GET_BY_USERID = "select i from IssueBook i where i.user=?1";
	private static final String GET_BY_BOOKID = "select i from IssueBook i where i.book=?1";
	private static final String GET_BY_ISSUEDATE = "select i from IssueBook i where i.issueDate=?1";
	private static final String GET_BY_DUEDATE = "select i from IssueBook i where i.dueDate=?1";

	@Transactional
	@Override
	public String issueBook(IssueBook issueBook) {
		logger.info("Entering issue book Function");

		Long issueId = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Long bookId = issueBook.getBook().getBookId();
			bookService.updateBookStatus(bookId, "Issued");
			session.save(issueBook);
			issueId = issueBook.getIssueId();
			return "Book is issued successfully with : " + issueId + " at " + localTime;

		} catch (Exception e) {
			throw new DatabaseException("Error in adding data in DataBase");
		}

	}

	@Override
	public List<IssueBook> getIssuedBooks() {
		logger.info("Entering get issued books Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			Query<IssueBook> query = session.createQuery(GET_ALL_ISSUEDBOOKS, IssueBook.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public IssueBook getDetailsByIssueId(Long issueId) {
		logger.info("Entering get Details By IssueId Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(IssueBook.class, issueId);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Transactional
	@Override
	public String updateIssuedBook(IssueBook issueBook) {
		logger.info("Entering update issued book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(issueBook);
			return "Issued book details updated successfully!";

		} catch (Exception e) {
			throw new DatabaseException("Error in updating data in DataBase");
		}

	}

	@Transactional
	@Override
	public String deleteIssuedBook(long issueId) {
		logger.info("Entering delete Details By IssueId Function");

		IssueBook issuedDetails = getDetailsByIssueId(issueId);
		try {
			Session session = sessionFactory.getCurrentSession();
			Long bookId = issuedDetails.getBook().getBookId();
			bookService.updateBookStatus(bookId, "Available");
			session.save(issuedDetails);
			issueId = issuedDetails.getIssueId();

			session.delete(issuedDetails);
			return "Book issued details deleted with : " + issueId + " at " + localTime;

		} catch (Exception e) {
			throw new DatabaseException("Error in deleting data from DataBase");
		}
	}

	@Override
	public boolean isIssuedDetailsExists(Long issueId) {
		logger.info("Entering isIssuedDetailsExists Function");

		Session session = sessionFactory.getCurrentSession();
		IssueBook issuedDetails = session.get(IssueBook.class, issueId);
		return (issuedDetails != null);
	}

	@Override
	public List<IssueBook> getDetailsByUserId(User user) {
		logger.info("Entering get Details By UserId function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_USERID, IssueBook.class).setParameter(1, user)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public IssueBook getDetailsByBookId(Book book) {
		logger.info("Entering get Details By bookId function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_BOOKID, IssueBook.class).setParameter(1, book)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate) {
		logger.info("Entering get Details By issueDate function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_ISSUEDATE, IssueBook.class)
					.setParameter(1, issueDate).getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate) {
		logger.info("Entering get Details By dueDate function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<IssueBook> resultList = session.createQuery(GET_BY_DUEDATE, IssueBook.class).setParameter(1, dueDate)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Transactional
	@Override
	public String updateFineAmount(Long issueId) {
		logger.info("Entering updateFineAmount function");

		try {

			Session session = sessionFactory.getCurrentSession();

			IssueBook issueDetails = getDetailsByIssueId(issueId);
			int fine=issueDetails.getFineAmount();
			issueDetails.setFineAmount(fine+50);
			session.update(issueDetails);
			MailSend.sendMail(issueDetails.getUser().getMailId(), "Fine Payment :",
					"Hi, " + issueDetails.getUser().getFirstName() + "\nYour due date is over for the issued book."
							+ "\n Book Name :" + issueDetails.getBook().getBookName()
							+ "\nPay the fine and return the book!" + "\n\nThank You.");
			return "Fine amount updated succesfully!";
		} catch (Exception e) {
			throw new DatabaseException("Error in updating data in DataBase");
		}
	}

	@Transactional
	@Override
	public String updateDueDate(Long issueId) {
		logger.info("Entering updateDueDate function");
		try {

			Session session = sessionFactory.getCurrentSession();

			IssueBook issueDetails = getDetailsByIssueId(issueId);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			// manipulate date
			cal.add(Calendar.DATE, 15);
			Date dueDate = cal.getTime();

			issueDetails.setDueDate(dueDate);
			session.update(issueDetails);
			MailSend.sendMail(issueDetails.getUser().getMailId(), "Renewal success :",
					"Hi, " + issueDetails.getUser().getFirstName() + "\nYour due date is changed for the issued book."
							+ "\n Book Name :" + issueDetails.getBook().getBookName()
							+ "\ncheck it once again in your user dashboard!" + "\n\nThank You.");
			return "Due Date updated succesfully!";
		} catch (Exception e) {
			throw new DatabaseException("Error in updating data in DataBase");
		}
	}
}
