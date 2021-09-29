package com.revature.librarymanagement.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;

public interface IssueBookDAO {
	public String issueBook(IssueBook issueBook);

	public List<IssueBook> getIssuedBooks();
	public IssueBook getDetailsByIssueId(Long issueId);

	public String updateIssuedBook(IssueBook issueBook);

	public String deleteIssuedBook(long issueId);
	public boolean isIssuedDetailsExists(Long issueId);
	
	public List<IssueBook> getDetailsByUserId(User user);

	public IssueBook getDetailsByBookId(Book book);
	
	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate);

	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate);

	public String updateFineAmount(Long issueId);

	public String updateDueDate(Long issueId);

}
