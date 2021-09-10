package com.revature.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.BookDAO;
import com.revature.model.Book;
import com.revature.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	
	@Autowired
	private BookDAO bookDAO;

	

	@Override
	public String addBook(Book book) {
		return bookDAO.addBook(book);
	}

	@Override
	public String updateBook(Book book) {
		return bookDAO.updateBook(book);
	}

	@Override
	public String deleteBook(Long bookId) {
		return bookDAO.deleteBook(bookId);

	}

	@Override
	public Book getBookById(Long bookId) {
		return bookDAO.getBookById(bookId);

	}

	@Override
	public boolean isBookExists(Long bookId) {
		Book book = bookDAO.getBookById(bookId);
		return (book != null);

	}

	@Override
	public List<Book> getBookByName(String bookName) {
		return bookDAO.getBookByName(bookName);

	}

	@Override
	public List<Book> getBookByAuthor(String authorName) {
		return bookDAO.getBookByAuthor(authorName);
	}

	@Override
	public List<Book> getBookByGenre(String genre) {
		return bookDAO.getBookByGenre(genre);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();

	}
}
