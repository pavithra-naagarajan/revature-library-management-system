package com.revature.librarymanagement.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books_table")

public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(nullable = false,unique=true)
	private Long isbn;

	@Column(nullable = false)
	private String bookName;

	@Column(nullable = false)
	private String authorName;

	@Column(nullable = false)
	private String genre;

	@Column(nullable = false)
	private int volume;

	@Column(nullable = false)
	private int edition;

	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@Temporal(TemporalType.DATE)
	private Date updatedOn;

	@OneToOne(mappedBy = "book",fetch=FetchType.LAZY)
	@JsonIgnore
	private IssueBook issueBook;

	public Book() {

	}

	
	public Book(Long bookId, Long isbn, String bookName, String authorName, String genre, int volume, int edition,
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


	public IssueBook getIssueBook() {
		return issueBook;
	}


	public void setIssueBook(IssueBook issueBook) {
		this.issueBook = issueBook;
	}


	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", isbn=" + isbn + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", genre=" + genre + ", volume=" + volume + ", edition=" + edition + ", createdOn=" + createdOn
				+ ", updatedOn=" + updatedOn + ", issueBook=" + issueBook + "]";
	}

}


































