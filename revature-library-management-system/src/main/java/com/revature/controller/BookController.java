package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Book;

import com.revature.service.BookService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	BookService bookService;

	// get book by id
	@GetMapping("{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {

		Book book = new Book();
		if (bookService.isBookExists(bookId)) {
			book = bookService.getBookById(bookId);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else
			return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);

	}

	// insert a book
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);

	}

	// update a book
	@PutMapping
	public ResponseEntity<String> updateBook(@RequestBody Book book) {

		long bookId = book.getBookId();

		if (bookService.isBookExists(bookId))

			return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);

		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// delete a book
	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId) {

		if (bookService.isBookExists(bookId))

			return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/bookName/{bookName}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable String bookName) {

		return new ResponseEntity<>(bookService.getBookByName(bookName), HttpStatus.OK);
	}

	@GetMapping("/author/{authorName}")
	public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String authorName) {

		return new ResponseEntity<>(bookService.getBookByAuthor(authorName), HttpStatus.OK);
	}

	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Book>> getBookByGenre(@PathVariable String genre) {

		return new ResponseEntity<>(bookService.getBookByGenre(genre), HttpStatus.OK);
	}

	//get book by isbn
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<List<Book>> getBookByISBN(@PathVariable Long isbn) {

		return new ResponseEntity<>(bookService.getBookByISBN(isbn), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {

		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}

}
