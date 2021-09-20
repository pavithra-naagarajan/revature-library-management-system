package com.revature.librarymanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.dto.IssueBookDto;

import com.revature.librarymanagement.mapper.IssueBookMapper;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.IssueBookService;


@Service
public class IssueBookServiceImpl implements IssueBookService {
	@Autowired
	private IssueBookDAO issueBookDAO;

	@Override
	public String issueBook(IssueBookDto issueBookDto) {
		IssueBook issueBook=IssueBookMapper.dtoToEntity(issueBookDto);

			return issueBookDAO.issueBook(issueBook);
	}

	@Override
	public List<IssueBook> getAllIssuedBooks() {
		
		return issueBookDAO.getIssuedBooks();
	}

	
	

	@Override
	public IssueBook getDetailsByIssueId(Long issueId) {
		return issueBookDAO.getDetailsByIssueId(issueId);
	}

	@Override
	public String updateIssuedBook(IssueBookDto issueBookDto) {
		IssueBook issueBook=IssueBookMapper.dtoToEntity(issueBookDto);
			return issueBookDAO.updateIssuedBook(issueBook);
	}

	@Override
	public String deleteIssuedBook(long issueId) {
		return issueBookDAO.deleteIssuedBook(issueId);
	}

	@Override
	public boolean isIssuedDetailsExists(Long issueId) {
		return issueBookDAO.isIssuedDetailsExists(issueId);
	}

	@Override
	public List<IssueBook>  getDetailsByUserId(User user) {
		return issueBookDAO.getDetailsByUserId(user);
	}

	@Override
	public IssueBook getDetailsByBookId(Book book) {
		return issueBookDAO.getDetailsByBookId(book);
	}

	@Override
	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate) {
		return issueBookDAO.getDetailsByIssueDate(issueDate);
	}

	@Override
	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate) {
		return issueBookDAO.getDetailsByDueDate(dueDate);
	}

}
