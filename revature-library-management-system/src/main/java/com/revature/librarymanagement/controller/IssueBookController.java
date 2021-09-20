package com.revature.librarymanagement.controller;

import java.time.LocalDateTime;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.IssueBookService;
import com.revature.librarymanagement.service.UserService;

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
	public ResponseEntity<String> issueBook(@RequestBody IssueBookDto issueBookDto) {
		Long issueId=issueBookDto.getIssueId();
		if (issueBookService.isIssuedDetailsExists(issueId)) {
			throw new DuplicateIdException("Issue with this Issue Id already exists!");

		}
		return new ResponseEntity<>(issueBookService.issueBook(issueBookDto), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<IssueBook>> getAllIssuedBooks() {
		List<IssueBook> issuedbooks =issueBookService.getAllIssuedBooks();
		return (CollectionUtils.isEmpty(issuedbooks)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(issuedbooks, HttpStatus.OK);
	}

	@GetMapping("/issue/{issueId}")
	public ResponseEntity<IssueBook> getDetailsByIssueId(@PathVariable("issueId") Long issueId) {


		if (issueBookService.isIssuedDetailsExists(issueId)) {
			IssueBook issuedData = issueBookService.getDetailsByIssueId(issueId);
			return new ResponseEntity<>(issuedData, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Issue details with:" + issueId + " Not Found!");

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

	@GetMapping(value="dueDate")
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
	public ResponseEntity<String> updateIssuedBook(@RequestBody IssueBookDto issueBookDto) {

		long issueId = issueBookDto.getIssueId();

		if (issueBookService.isIssuedDetailsExists(issueId))

			return new ResponseEntity<>(issueBookService.updateIssuedBook(issueBookDto), HttpStatus.OK);

		else
			throw new IdNotFoundException("Issued Book details with:" + issueId + " Not Found to Update!");

	
	}

	@DeleteMapping("issuedbook/{issueId}")
	public ResponseEntity<String> deleteIssuedBook(@PathVariable("issueId") Long issueId) {

		if (issueBookService.isIssuedDetailsExists(issueId))

			return new ResponseEntity<>(issueBookService.deleteIssuedBook(issueId), HttpStatus.OK);
		else
			throw new IdNotFoundException("Issued Book details with:" + issueId + " Not Found to delete!");


	
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
