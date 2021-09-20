package com.revature.dao;

import java.util.List;


import com.revature.model.Book;



public interface BookDAO {


	public String addBook(Book book);
	
	public String updateBook(Book book);
	public String deleteBook(Long bookId);
	public Book getBookById(Long bookId);
	public boolean isBookExists(Long bookId);
	
	public List<Book> getBookByName(String bookName);  
	public List<Book> getBookByAuthor(String authorName); 
	public List<Book>getBookByGenre(String genre); 
	public List<Book> getAllBooks();
	public List<Book> getBookByISBN(Long isbn);  

}
