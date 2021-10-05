package com.revature.librarymanagement.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.RequestBookDAO;
import com.revature.librarymanagement.dto.RequestBookDto;
import com.revature.librarymanagement.mapper.RequestBookMapper;
import com.revature.librarymanagement.model.RequestBook;
import com.revature.librarymanagement.service.RequestBookService;

@Service
public class RequestBookServiceImpl implements RequestBookService {
	private static final Logger logger = LogManager.getLogger(RequestBookServiceImpl.class);

	@Autowired
	private RequestBookDAO requestBookDAO;

	@Override
	public String addRequestBook(RequestBookDto requestBookDto) {
		logger.info("Entering add request book Function");

		RequestBook requestBook = RequestBookMapper.dtoToEntity(requestBookDto);

		return requestBookDAO.addRequestBook(requestBook);
	}

	@Override
	public List<RequestBook> getAllRequestedBooks() {
		logger.info("Entering getAllRequestedBooks Function");

		return requestBookDAO.getAllRequestedBooks();

	}

	@Override
	public String deleteRequestedBook(Long requestId) {
		logger.info("Entering delete Requested Book Function");

		return requestBookDAO.deleteRequestedBook(requestId);
	}

	@Override
	public RequestBook getDetailsByRequestId(Long requestId) {
		logger.info("Entering get Details By RequestId Function");

		return requestBookDAO.getDetailsByRequestId(requestId);
	}

}
