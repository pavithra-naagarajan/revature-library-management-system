package com.revature.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.dao.BookDAO;
import com.revature.model.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public String addBook(Book book) {
		Session session = sessionFactory.getCurrentSession();

		Long bookId = null;
		try {

			session.save(book);

			bookId = book.getBookId();
		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "Book is added successfully : " + bookId + " at " + localTime;

	}

	@Transactional
	@Override
	public String updateBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		try {

			session.merge(book);

		} catch (HibernateException e1) {

			e1.printStackTrace();
		}
		return "updated successfully!";

	}

	@Transactional
	@Override
	public String deleteBook(Long bookId) {
		Session session = sessionFactory.getCurrentSession();
		Book book = getBookById(bookId);
		try {

			session.delete(book);
		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "Book deleted with : " + bookId + " at " + localTime;

	}

	@Override
	public Book getBookById(Long bookId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(Book.class, bookId);

	}

	@Override
	public boolean isBookExists(Long bookId) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, bookId);
		return (book != null);
	}

	@Override
	public List<Book> getBookByName(String bookName) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Book> resultList = session.createQuery("select b from Book b where b.bookName=?1")
				.setParameter(1, bookName).getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Book> resultList = session.createQuery("select b from Book b where b.authorName=?1")
				.setParameter(1, authorName).getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public List<Book> getBookByGenre(String genre) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Book> resultList = session.createQuery("select b from Book b where b.genre=?1").setParameter(1, genre)
				.getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public List<Book> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Book> query = session.createQuery("select b from Book b");
		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}

	@Override
	public List<Book> getBookByISBN(Long isbn) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Book> resultList = session.createQuery("select b from Book b where b.isbn=?1").setParameter(1, isbn)
				.getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

}
