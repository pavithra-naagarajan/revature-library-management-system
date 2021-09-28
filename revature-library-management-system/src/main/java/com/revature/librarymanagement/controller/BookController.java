package com.revature.librarymanagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.revature.librarymanagement.dto.BookDto;

import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.service.BookService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("book")
public class BookController {

	private static final Logger logger = LogManager.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	// get book by id
	@GetMapping("{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
		logger.info("Entering Get Book By Id Function");

		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);

	}

	// insert a book
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
		logger.info("Entering Add Book Function");

		return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.OK);

	}

	// update a book
	@PutMapping
	public ResponseEntity<String> updateBook(@RequestBody BookDto bookDto) {

		logger.info("Entering Update Book Function");

		return new ResponseEntity<>(bookService.updateBook(bookDto), HttpStatus.OK);

	}

	// delete a book
	@DeleteMapping("/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId) {
		logger.info("Entering Delete Book Function");

		return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.OK);

	}

	// get book by name
	@GetMapping("/bookname/{bookName}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable String bookName) {
		logger.info("Entering Get Book By Name Function");

		return new ResponseEntity<>(bookService.getBookByName(bookName), HttpStatus.OK);
	}

	// get book by author
	@GetMapping("/author/{authorName}")
	public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String authorName) {
		logger.info("Entering Get Book By Author Name Function");

		return new ResponseEntity<>(bookService.getBookByAuthor(authorName), HttpStatus.OK);

	}

	// get book by genre
	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Book>> getBookByGenre(@PathVariable String genre) {
		logger.info("Entering Get Book By genre Name Function");

		return new ResponseEntity<>(bookService.getBookByGenre(genre), HttpStatus.OK);
	}

	// get book by publisher
	@GetMapping("/publisher/{publisher}")
	public ResponseEntity<List<Book>> getBookByPublisher(@PathVariable String publisher) {
		logger.info("Entering Get Book By publisher Name Function");

		return new ResponseEntity<>(bookService.getBookByPublisher(publisher), HttpStatus.OK);
	}

	// get book by isbn
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<Book> getBookByISBN(@PathVariable Long isbn) {
		logger.info("Entering Get Book By ISBN Function");

		return new ResponseEntity<>(bookService.getBookByISBN(isbn), HttpStatus.OK);
	}

	// get all books
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		logger.info("Entering Get All Books Function");

		return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
	}

}
