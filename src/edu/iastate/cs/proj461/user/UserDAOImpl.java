package edu.iastate.cs.proj461.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAOImpl implements UserDAO{
	
	private SessionFactory sf;
	
	public UserDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		System.out.println("Fetching User");
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult();
		tx.commit();
		session.close();
		return user;
	}
	
	public Position getUserPosition(String username) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult();
		tx.commit();
		session.close();
		return user.getPos();
	}

}
