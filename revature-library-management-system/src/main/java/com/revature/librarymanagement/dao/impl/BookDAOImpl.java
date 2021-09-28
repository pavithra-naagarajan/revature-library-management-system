package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;

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

import com.revature.librarymanagement.dao.BookDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.Book;

@Repository
public class BookDAOImpl implements BookDAO {
	private static final Logger logger = LogManager.getLogger(BookDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_BOOK_BY_NAME = "select b from Book b where b.bookName LIKE ?1";
	private static final String GET_BOOK_BY_AUTHORNAME = "select b from Book b where b.authorName LIKE?1";
	private static final String GET_BOOK_BY_GENRE = "select b from Book b where b.genre=?1";
	private static final String GET_BOOK_BY_PUBLISHER = "select b from Book b where b.publisher LIKE?1";
	private static final String GET_ALL_BOOKS = "select b from Book b";
	private static final String GET_BOOK_BY_ISBN = "select b from Book b where b.isbn=?1";

	@Transactional
	@Override
	public String addBook(Book book) {
		logger.info("Entering add book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			book.setStatus("Available");
			book.setCreatedOn(new Date());
			session.save(book);

			Long bookId = book.getBookId();
			return "Book is added successfully : " + bookId + " at " + localTime;

		} catch (Exception e) {
			throw new DatabaseException("Error in adding data to DataBase");
		}

	}

	@Transactional
	@Override
	public String updateBook(Book book) {
		logger.info("Entering update book Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			book.setUpdatedOn(new Date());

			session.merge(book);
			return "Book updated successfully!";

		} catch (Exception e) {
			throw new DatabaseException("Error in updating data in DataBase");
		}

	}

	@Transactional
	@Override
	public String deleteBook(Long bookId) {
		logger.info("Entering delete book By Id Function");

		Book book = getBookById(bookId);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(book);
			return "Book deleted with : " + bookId + " at " + localTime;

		} catch (Exception e) {
			throw new DatabaseException("Error in deleting data from DataBase");
		}

	}

	@Override
	public Book getBookById(Long bookId) {
		logger.info("Entering get book By Id Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(Book.class, bookId);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}

	}

	@Override
	public boolean isBookExists(Long bookId) {
		logger.info("Entering is book exists Function");

		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, bookId);
		return (book != null);
	}

	@Override
	public List<Book> getBookByName(String bookName) {
		logger.info("Entering get book By name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_NAME, Book.class)
					.setParameter(1, "%" + bookName + "%").getResultList();
			return ((resultList).isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		logger.info("Entering get book By author name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_AUTHORNAME, Book.class)
					.setParameter(1, "%" + authorName + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public List<Book> getBookByGenre(String genre) {
		logger.info("Entering get book By genre name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_GENRE, Book.class).setParameter(1, genre)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public List<Book> getBookByPublisher(String publisher) {
		logger.info("Entering get book By publisher name Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_PUBLISHER, Book.class)
					.setParameter(1, "%" + publisher + "%").getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public List<Book> getAllBooks() {
		logger.info("Entering get all books Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			Query<Book> query = session.createQuery(GET_ALL_BOOKS, Book.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public Book getBookByISBN(Long isbn) {
		logger.info("Entering get book By ISBN Function");

		try {
			Session session = sessionFactory.getCurrentSession();

			List<Book> resultList = session.createQuery(GET_BOOK_BY_ISBN, Book.class).setParameter(1, isbn)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Transactional
	@Override
	public String updateBookStatus(Long bookId, String status) {
		logger.info("Entering update book status Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			Book book = getBookById(bookId);
			book.setStatus(status);
			session.merge(book);
			return "Book Status updated successfully!";

		} catch (Exception e) {
			throw new DatabaseException("Error in updating data in DataBase");
		}

	}

}
