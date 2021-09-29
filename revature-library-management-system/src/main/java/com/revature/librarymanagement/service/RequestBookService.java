package com.revature.librarymanagement.service;

import java.util.List;

import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.model.RequestBook;

public interface RequestBookService {

	
	public String addRequestBook(RequestBookDto requestBookDto);
	public List<RequestBook> getAllRequestedBooks();
	public String deleteRequestedBook(Long requestId);
	public RequestBook getDetailsByRequestId(Long requestId);
}
