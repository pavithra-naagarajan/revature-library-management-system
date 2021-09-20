package com.revature.service;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.model.Book;
import com.revature.model.IssueBook;
import com.revature.model.User;

public interface IssueBookService {
	public String issueBook(IssueBook issueBook);

	public List<IssueBook> getAllIssuedBooks();

	public IssueBook  getDetailsByIssueId(Long issueId);

	public String updateIssuedBook( IssueBook issueBook);
	public String deleteIssuedBook(long issueId);
	
	public boolean isIssuedDetailsExists(Long issueId);

	public List<IssueBook> getDetailsByUserId(User user);

	public IssueBook getDetailsByBookId(Book book);

	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate);

	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate);
}
