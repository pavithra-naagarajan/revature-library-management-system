package com.revature.controller;

import java.time.LocalDateTime;

import java.util.List;

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

import com.revature.model.Book;
import com.revature.model.IssueBook;
import com.revature.model.User;
import com.revature.service.BookService;
import com.revature.service.IssueBookService;
import com.revature.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("issuebook")
public class IssueBookController {

	@Autowired
	IssueBookService issueBookService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	// insert a issuedBookDetails
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody IssueBook issueBook) {

		return new ResponseEntity<>(issueBookService.issueBook(issueBook), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<IssueBook>> getAllIssuedBooks() {

		return new ResponseEntity<>(issueBookService.getAllIssuedBooks(), HttpStatus.OK);
	}

	@GetMapping("/issue/{issueId}")
	public ResponseEntity<IssueBook> getDetailsByIssueId(@PathVariable("issueId") Long issueId) {

		return new ResponseEntity<>(issueBookService.getDetailsByIssueId(issueId), HttpStatus.OK);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<IssueBook>> getDetailsByUserId(@PathVariable("userId") Long userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<>(issueBookService.getDetailsByUserId(user), HttpStatus.OK);

	}

	@GetMapping("/book/{bookId}")
	public ResponseEntity<IssueBook> getDetailsByBookId(@PathVariable("bookId") Long bookId) {
		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<>(issueBookService.getDetailsByBookId(book), HttpStatus.OK);

	}

	@GetMapping(value = "dueDate")
	public ResponseEntity<List<IssueBook>> getDetailsByDueDate(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dueDate) {
		return new ResponseEntity<>(issueBookService.getDetailsByDueDate(dueDate), HttpStatus.OK);

	}

	@GetMapping(value = "issueDate")
	public ResponseEntity<List<IssueBook>> getDetailsByIssueDate(
			@RequestParam("issueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime issueDate) {
		return new ResponseEntity<>(issueBookService.getDetailsByIssueDate(issueDate), HttpStatus.OK);

	}

	@PutMapping
	public ResponseEntity<String> updateIssuedBook(@RequestBody IssueBook issueBook) {

		long issueId = issueBook.getIssueId();

		if (issueBookService.isIssuedDetailsExists(issueId))

			return new ResponseEntity<>(issueBookService.updateIssuedBook(issueBook), HttpStatus.OK);

		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("issuedbook/{issueId}")
	public ResponseEntity<String> deleteIssuedBook(@PathVariable("issueId") Long issueId) {

		if (issueBookService.isIssuedDetailsExists(issueId))

			return new ResponseEntity<>(issueBookService.deleteIssuedBook(issueId), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
