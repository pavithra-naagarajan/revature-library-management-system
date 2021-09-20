package com.revature.librarymanagement.dto;

import java.util.Date;

import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.User;

public class IssueBookDto {
	private Long issueId;
	private Date issueDate;
	private Date dueDate;
	private User user;
	private Book book;

	public IssueBookDto() {

	}

	public IssueBookDto(Long issueId, Date issueDate, Date dueDate, User user, Book book) {
		super();
		this.issueId = issueId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.user = user;
		this.book = book;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "IssueBook [issueId=" + issueId + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", user=" + user
				+ ", book=" + book + "]";
	}

}
