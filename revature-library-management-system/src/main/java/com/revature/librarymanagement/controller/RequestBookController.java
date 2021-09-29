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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.model.RequestBook;
import com.revature.librarymanagement.service.BookService;
import com.revature.librarymanagement.service.RequestBookService;
import com.revature.librarymanagement.service.UserService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("requestbook")
public class RequestBookController {
	private static final Logger logger = LogManager.getLogger(RequestBookController.class);

	@Autowired
	RequestBookService requestBookService;
	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	// insert a requestBookDetails

	@PostMapping
	public ResponseEntity<String> addRequestBook(@RequestBody RequestBookDto requestBookDto) {
		logger.info("Entering add requested book Function");

		return new ResponseEntity<>(requestBookService.addRequestBook(requestBookDto), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<RequestBook>> getAllRequestedBooks() {
		logger.info("Entering get all requested book Function");

		return new ResponseEntity<>(requestBookService.getAllRequestedBooks(), HttpStatus.OK);
	}

	@DeleteMapping("/{requestId}")
	public ResponseEntity<String> deleteRequestedBook(@PathVariable("requestId") Long requestId) {
		logger.info("Entering delete requested book Function");

		return new ResponseEntity<>(requestBookService.deleteRequestedBook(requestId), HttpStatus.OK);

	}

	@GetMapping("/request/{requestId}")
	public ResponseEntity<RequestBook> getDetailsByRequestId(@PathVariable("requestId") Long requestId) {
		logger.info("Entering get requested book by Id Function");

		return new ResponseEntity<>(requestBookService.getDetailsByRequestId(requestId), HttpStatus.OK);

	}

}
