package com.revature.librarymanagement.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.IssueBookDto;

import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.IssueBookService;
import com.revature.librarymanagement.service.UserService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("issuebook")
public class IssueBookController {

	private static final Logger logger = LogManager.getLogger(IssueBookController.class);

	@Autowired
	IssueBookService issueBookService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	// insert a issuedBookDetails
	@PostMapping
	public ResponseEntity<String> issueBook(@RequestBody IssueBookDto issueBookDto) {
		logger.info("Entering issue book Function");

		return new ResponseEntity<>(issueBookService.issueBook(issueBookDto), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<IssueBook>> getAllIssuedBooks() {
		logger.info("Entering Get all issued books Function");

		return new ResponseEntity<>(issueBookService.getAllIssuedBooks(), HttpStatus.OK);
	}

	@GetMapping("/issue/{issueId}")
	public ResponseEntity<IssueBook> getDetailsByIssueId(@PathVariable("issueId") Long issueId) {
		logger.info("Entering Get issued book details By issue Id Function");

		return new ResponseEntity<>(issueBookService.getDetailsByIssueId(issueId), HttpStatus.OK);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<IssueBook>> getDetailsByUserId(@PathVariable("userId") Long userId) {
		logger.info("Entering Get issued book details By user Id Function");

		User user = userService.getUserById(userId);
		return new ResponseEntity<>(issueBookService.getDetailsByUserId(user), HttpStatus.OK);

	}

	@GetMapping("/book/{bookId}")
	public ResponseEntity<IssueBook> getDetailsByBookId(@PathVariable("bookId") Long bookId) {
		logger.info("Entering Get issued book details By book Id Function");

		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<>(issueBookService.getDetailsByBookId(book), HttpStatus.OK);

	}

	@GetMapping(value = "dueDate")
	public ResponseEntity<List<IssueBook>> getDetailsByDueDate(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
		logger.info("Entering Get issued book details By duedate Function");

		return new ResponseEntity<>(issueBookService.getDetailsByDueDate(dueDate), HttpStatus.OK);

	}

	@GetMapping(value = "issueDate")
	public ResponseEntity<List<IssueBook>> getDetailsByIssueDate(
			@RequestParam("issueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime issueDate) {
		logger.info("Entering Get issued book details By issuedate Function");

		return new ResponseEntity<>(issueBookService.getDetailsByIssueDate(issueDate), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<String> updateIssuedBook(@RequestBody IssueBookDto issueBookDto) {

		logger.info("Entering update issued book details Function");

		return new ResponseEntity<>(issueBookService.updateIssuedBook(issueBookDto), HttpStatus.OK);

	}

	@DeleteMapping("/{issueId}")
	public ResponseEntity<String> deleteIssuedBook(@PathVariable("issueId") Long issueId) {

		logger.info("Entering delete issued book details By issue Id Function");

		return new ResponseEntity<>(issueBookService.deleteIssuedBook(issueId), HttpStatus.OK);

	}

	@PutMapping("/updatefine/{issueId}")
	public ResponseEntity<String> updateFineAmount(@PathVariable Long issueId) {
		logger.info("Entering update fine amount of issued book details By issue Id Function");

		return new ResponseEntity<>(issueBookService.updateFineAmount(issueId), HttpStatus.OK);

	}

	@PutMapping("/updateduedate/{issueId}")
	public ResponseEntity<String> updateDueDate(@PathVariable Long issueId) {
		logger.info("Entering update due date of issued book details By issue Id Function");

		return new ResponseEntity<>(issueBookService.updateDueDate(issueId), HttpStatus.OK);

	}

}
