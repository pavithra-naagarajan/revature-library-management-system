package com.revature.librarymanagement.dto;

import java.util.Date;


import com.revature.librarymanagement.model.IssueBook;

public class BookDto {
	private Long bookId;
	private Long isbn;
	private String bookName;
	private String authorName;
	private String genre;
	private int volume;
	private int edition;
	private Date createdOn;
	private Date updatedOn;
	private IssueBook issueBook;
public BookDto() {
	
}

public BookDto(Long bookId, Long isbn, String bookName, String authorName, String genre, int volume, int edition,
		Date createdOn, Date updatedOn, IssueBook issueBook) {
	super();
	this.bookId = bookId;
	this.isbn = isbn;
	this.bookName = bookName;
	this.authorName = authorName;
	this.genre = genre;
	this.volume = volume;
	this.edition = edition;
	this.createdOn = createdOn;
	this.updatedOn = updatedOn;
	this.issueBook = issueBook;
}

public Long getBookId() {
	return bookId;
}
public void setBookId(Long bookId) {
	this.bookId = bookId;
}

public Long getIsbn() {
	return isbn;
}

public void setIsbn(Long isbn) {
	this.isbn = isbn;
}

public String getBookName() {
	return bookName;
}
public void setBookName(String bookName) {
	this.bookName = bookName;
}
public String getAuthorName() {
	return authorName;
}
public void setAuthorName(String authorName) {
	this.authorName = authorName;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public int getVolume() {
	return volume;
}
public void setVolume(int volume) {
	this.volume = volume;
}
public int getEdition() {
	return edition;
}
public void setEdition(int edition) {
	this.edition = edition;
}
public Date getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}
public Date getUpdatedOn() {
	return updatedOn;
}
public void setUpdatedOn(Date updatedOn) {
	this.updatedOn = updatedOn;
}
public IssueBook getIssueBook() {
	return issueBook;
}
public void setIssueBook(IssueBook issueBook) {
	this.issueBook = issueBook;
}

@Override
public String toString() {
	return "BookDto [bookId=" + bookId + ", isbn=" + isbn + ", bookName=" + bookName + ", authorName=" + authorName
			+ ", genre=" + genre + ", volume=" + volume + ", edition=" + edition + ", createdOn=" + createdOn
			+ ", updatedOn=" + updatedOn + ", issueBook=" + issueBook + "]";
}



}

