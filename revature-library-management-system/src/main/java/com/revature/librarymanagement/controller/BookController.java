package com.revature.librarymanagement.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.BookDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.service.BookService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	BookService bookService;

	// get book by id
	@GetMapping("{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {

		
		if (bookService.isBookExists(bookId)) {
			Book book = bookService.getBookById(bookId);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Book with:" + bookId + " Not Found!");

	}

	// insert a book
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
		Long bookId = bookDto.getBookId();
		Long isbn = bookDto.getIsbn();

		if (bookService.isBookExists(bookId)) {
			throw new DuplicateIdException("Book with Id already exists!");

		}

		if (bookService.getBookByISBN(isbn) != null) {

			throw new DuplicateIdException("Book already exists with same ISBN!");

		} else

			return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.OK);

	}
		

	// update a book
	@PutMapping
	public ResponseEntity<String> updateBook(@RequestBody BookDto bookDto) {

		long bookId = bookDto.getBookId();

		if (bookService.isBookExists(bookId))

			return new ResponseEntity<>(bookService.updateBook(bookDto), HttpStatus.OK);

		else 
			throw new IdNotFoundException("Book:" + bookId + " Not Found to Update!");


	
	}

	// delete a book
	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId) {

		if (bookService.isBookExists(bookId))

			return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
		else
			throw new IdNotFoundException("Book Id:" + bookId + " Not Found to delete!");

		
	}

	@GetMapping("/bookName/{bookName}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable String bookName) {
		List<Book> books =bookService.getBookByName(bookName);
		return (CollectionUtils.isEmpty(books)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/author/{authorName}")
	public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String authorName) {
		List<Book> books =bookService.getBookByAuthor(authorName);
		return (CollectionUtils.isEmpty(books)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(books, HttpStatus.OK);
		
	}

	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Book>> getBookByGenre(@PathVariable String genre) {
		List<Book> books =bookService.getBookByGenre(genre);
		return (CollectionUtils.isEmpty(books)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(books, HttpStatus.OK);
	}

	//get book by isbn
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<Book>getBookByISBN(@PathVariable Long isbn) {
		Book book = bookService.getBookByISBN(isbn);
		return (book != null) ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> books =bookService.getAllBooks();
		return (CollectionUtils.isEmpty(books)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(books, HttpStatus.OK);
	}

	// exception for Duplicate Id insertion..
		@ExceptionHandler(DuplicateIdException.class)

		public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		// exception for Id not Found ..
		@ExceptionHandler(IdNotFoundException.class)

		public ResponseEntity<String> userNotFound(IdNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}
