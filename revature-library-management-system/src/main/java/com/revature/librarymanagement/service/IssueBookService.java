package com.revature.librarymanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.librarymanagement.dto.IssueBookDto;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;

public interface IssueBookService {
	
	
	/**
	 * function to insert a issuedbook and details
	 * @param issueBookDto--object of IssueBookDto
	  * @return --it will return a message as issued successfully
	 */
	public String issueBook(IssueBookDto issueBookDto);

	
	/**
 * 
 * @return--it will return all the issued books with user id and book id
 */
	public List<IssueBook> getAllIssuedBooks();

	/**
	 * 
	 * @param issueId--based on that we can fetch the issued details
	 * @return--it will return the issued details respective to the issueId
	 */
	public IssueBook  getDetailsByIssueId(Long issueId);

	
	/**
 * function to update a issuedbook and details
	 * @param issueBookDto--object of IssueBookDto
	  * @return --it will return a message as update successfully
 */
	public String updateIssuedBook( IssueBookDto issueBookDto);
	
	
	/**
	 * function to delete a issuedbook and details
		 * @param issueId--based on issued Id it will perform delete
		  * @return --it will return a message as delete successfully
	 */
	public String deleteIssuedBook(long issueId);
	
	
	/**
	 * 
	 * @param issueId--based on issueId it will check existence of issued details
	 * @return--return boolean value true or false 
	 */
	public boolean isIssuedDetailsExists(Long issueId);

	/**
	 * 
	 * @param user--based on user we can find the issued details
	 * @return--it will return issued book details respective to the user
	 */
	public List<IssueBook> getDetailsByUserId(User user);

	/**
	 * 
	 * @param book--based on book we can find the issued details
	 * @return--it will return issued book details respective to the book
	 */
	public IssueBook getDetailsByBookId(Book book);

	/**
	 * 
	 * @param issueDate--based on issueDate we can find the issued details
	 * @return--it will return issued book details respective to the issueDate
	 */
	 
	
	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate);

	/**
	 * 
	 * @param dueDate--based on dueDate we can find the issued details
	 * @return--it will return issued book details respective to the dueDate
	 */
	 
	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate);
}
