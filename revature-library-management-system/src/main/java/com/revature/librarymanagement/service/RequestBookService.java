package com.revature.librarymanagement.service;

import java.util.List;

import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.model.RequestBook;

public interface RequestBookService {

	/**
	 * it will add request for book
	 * 
	 * @param requestBookDto
	 * @return-added request message
	 */
	public String addRequestBook(RequestBookDto requestBookDto);

	/**
	 * 
	 * @return-all the requested books
	 */
	public List<RequestBook> getAllRequestedBooks();

	/**
	 * delete requested book based on request id
	 * 
	 * @param requestId
	 * @return-deleted message
	 */
	public String deleteRequestedBook(Long requestId);

	/**
	 * 
	 * @param requestId
	 * @return-issue details based on request id
	 */
	public RequestBook getDetailsByRequestId(Long requestId);
}
