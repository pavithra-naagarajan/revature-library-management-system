package com.revature.librarymanagement.service.impl;

import java.time.LocalDateTime;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.controller.MailSend;
import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.IssueBookMapper;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.IssueBookService;

@Service
public class IssueBookServiceImpl implements IssueBookService {
	private static final Logger logger = LogManager.getLogger(IssueBookServiceImpl.class);

	@Autowired
	private IssueBookDAO issueBookDAO;

	@Override
	public String issueBook(IssueBookDto issueBookDto) {
		logger.info("Entering issue book Function");

		IssueBook issueBook = IssueBookMapper.dtoToEntity(issueBookDto);
		Long issueId = issueBook.getIssueId();
		User user = issueBook.getUser();
		Book book = issueBook.getBook();
		if (issueBookDAO.isIssuedDetailsExists(issueId))
			throw new DuplicateIdException("Issued book already exists with same Id!");

		MailSend.sendMail(user.getMailId(), "Regarding Book Issue :",
				"Hi, " + user.getFirstName() + "\nThe book which you have requested is issued successfully!"
						+ "\nBook Name :" + book.getBookName() + "\nAuthor Name :" + book.getAuthorName()
						+ "\nIssue Date :" + issueBook.getIssueDate() + "\nDue Date :" + issueBook.getDueDate()
						+ "\n\nThank You.");
		return issueBookDAO.issueBook(issueBook);

	}

	@Override
	public List<IssueBook> getAllIssuedBooks() {
		logger.info("Entering get issued books Function");

		List<IssueBook> issuedBooks = issueBookDAO.getIssuedBooks();
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException("No records found!");
		return issuedBooks;

	}

	@Override
	public IssueBook getDetailsByIssueId(Long issueId) {
		logger.info("Entering get Details By IssueId Function");

		if (issueBookDAO.isIssuedDetailsExists(issueId))
			return issueBookDAO.getDetailsByIssueId(issueId);

		throw new IdNotFoundException("IssuedBook details with:" + issueId + " Not Found!");
	}

	@Override
	public String updateIssuedBook(IssueBookDto issueBookDto) {
		logger.info("Entering update issued book Function");

		IssueBook issueBook = IssueBookMapper.dtoToEntity(issueBookDto);
		Long issueId = issueBook.getIssueId();
		if (issueBookDAO.isIssuedDetailsExists(issueId))
			return issueBookDAO.updateIssuedBook(issueBook);

		throw new IdNotFoundException("Issued Book details with:" + issueId + " Not Found to update!");
	}

	@Override
	public String deleteIssuedBook(long issueId) {
		logger.info("Entering delete Details By IssueId Function");

		if (issueBookDAO.isIssuedDetailsExists(issueId))
			return issueBookDAO.deleteIssuedBook(issueId);

		throw new IdNotFoundException("Issued Book details with:" + issueId + " Not Found to delete!");
	}

	@Override
	public boolean isIssuedDetailsExists(Long issueId) {
		logger.info("Entering isIssuedDetailsExists Function");

		return issueBookDAO.isIssuedDetailsExists(issueId);
	}

	@Override
	public List<IssueBook> getDetailsByUserId(User user) {
		logger.info("Entering get Details By UserId function");

		List<IssueBook> issuedBooks = issueBookDAO.getDetailsByUserId(user);
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException("No records found!");
		return issuedBooks;
	}

	@Override
	public IssueBook getDetailsByBookId(Book book) {
		logger.info("Entering get Details By bookId function");

		IssueBook issuedBook = issueBookDAO.getDetailsByBookId(book);
		if (issuedBook == null)
			throw new NullValueException("No record found!");
		return issuedBook;
	}

	@Override
	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate) {
		logger.info("Entering get Details By issueDate function");

		List<IssueBook> issuedBooks = issueBookDAO.getDetailsByIssueDate(issueDate);
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException("No records found!");
		return issuedBooks;
	}

	@Override
	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate) {
		logger.info("Entering get Details By dueDate function");

		List<IssueBook> issuedBooks = issueBookDAO.getDetailsByDueDate(dueDate);
		if (CollectionUtils.isEmpty(issuedBooks))
			throw new NullValueException("No records found!");
		return issuedBooks;
	}

	@Override
	public String updateFineAmount(Long issueId) {
		logger.info("Entering updateFineAmount function");

		IssueBook issuedDetails = issueBookDAO.getDetailsByIssueId(issueId);
		if (issuedDetails != null)
			return issueBookDAO.updateFineAmount(issueId);
		throw new IdNotFoundException("Issue Id Not Found!");

	}

	@Override
	public String updateDueDate(Long issueId) {
		logger.info("Entering updateDueDate function");

		IssueBook issuedDetails = issueBookDAO.getDetailsByIssueId(issueId);
		if (issuedDetails != null)
			return issueBookDAO.updateDueDate(issueId);
		throw new IdNotFoundException("Issue Id Not Found!");
	}

}
