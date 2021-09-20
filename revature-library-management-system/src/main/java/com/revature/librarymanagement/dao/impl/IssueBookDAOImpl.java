package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.dao.IssueBookDAO;
import com.revature.librarymanagement.model.Book;
import com.revature.librarymanagement.model.IssueBook;
import com.revature.librarymanagement.model.User;

@Repository
public class IssueBookDAOImpl implements IssueBookDAO {

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public String issueBook(IssueBook issueBook) {

		Session session = sessionFactory.getCurrentSession();
		Long issueId = null;
		try {

			session.save(issueBook);
			issueId = issueBook.getIssueId();
		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "Book is issued successfully with : " + issueId + " at " + localTime;
	}

	@Override
	public List<IssueBook> getIssuedBooks() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<IssueBook> query = session.createQuery("select i from IssueBook i");
		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}

	@Override
	public IssueBook getDetailsByIssueId(Long issueId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(IssueBook.class, issueId);
	}

	@Transactional
	@Override
	public String updateIssuedBook(IssueBook issueBook) {
		Session session = sessionFactory.getCurrentSession();

		try {

			session.merge(issueBook);

		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "Issued book details updated successfully!";
	}

	@Transactional
	@Override
	public String deleteIssuedBook(long issueId) {
		Session session = sessionFactory.getCurrentSession();
		IssueBook issuedDetails = getDetailsByIssueId(issueId);
		try {

			session.delete(issuedDetails);
		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "Book issued details deleted with : " + issueId + " at " + localTime;
	}

	@Override
	public boolean isIssuedDetailsExists(Long issueId) {
		Session session = sessionFactory.getCurrentSession();
		IssueBook issuedDetails = session.get(IssueBook.class, issueId);
		return (issuedDetails != null);
	}

	@Override
	public List<IssueBook> getDetailsByUserId(User user) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<IssueBook> resultList = session.createQuery("select i from IssueBook i where i.user=?1")
				.setParameter(1, user).getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public IssueBook getDetailsByBookId(Book book) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<IssueBook> resultList = session.createQuery("select i from IssueBook i where i.book=?1")
				.setParameter(1, book).getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

	@Override
	public List<IssueBook> getDetailsByIssueDate(LocalDateTime issueDate) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<IssueBook> resultList = session.createQuery("select i from IssueBook i where i.issueDate=?1")
				.setParameter(1, issueDate).getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public List<IssueBook> getDetailsByDueDate(LocalDateTime dueDate) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<IssueBook> resultList = session.createQuery("select i from IssueBook i where i.dueDate=?1")
				.setParameter(1, dueDate).getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}
}
