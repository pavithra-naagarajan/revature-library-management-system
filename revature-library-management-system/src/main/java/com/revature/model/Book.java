package com.revature.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="books_table")


public class Book {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookId;
	
	@Column(nullable=false)
	private String bookName;
	
	@Column(nullable=false)
	private String authorName;
	
	@Column(nullable=false)
	private String genre;
	
	@Column(nullable=false)
	private int volume;
	
	@Column(nullable=false)
	private int edition;
	
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	
	public Book() {
		
	}

	public Book(Long bookId, String bookName, String authorName, String genre, int volume, int edition,
			LocalDateTime createdOn, LocalDateTime updatedOn) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.genre = genre;
		this.volume = volume;
		this.edition = edition;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", genre=" + genre
				+ ", volume=" + volume + ", edition=" + edition + ", createdOn=" + createdOn + ", updatedOn="
				+ updatedOn + "]";
	}

}
