package edu.iastate.cs.proj461.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.StrongPasswordEncryptor;

import edu.iastate.cs.proj461.room.Room;

public class UserDAOImpl implements UserDAO{
	
	private SessionFactory sf;
	
	public UserDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public User getUserByUsername(String username) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//Criteria crit = session.createCriteria(User.class);
		//crit.add(Restrictions.eq("userName", username));
		//User user = (User) crit.uniqueResult();
		Query query = session.createQuery("from User u where u.userName =:name");
		query.setParameter("name", username);
		User user = (User) query.uniqueResult();
				 
		tx.commit();
		session.close();
		return user;
	}
	
	public Position getUserPosition(String username) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("userName", username));
		User user = (User) crit.uniqueResult();
		tx.commit();
		session.close();
		return user.getPos();
	}
	
	public List<User> getAllUsers() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		
		List<User> users = (List<User>) crit.list();
		tx.commit();
		session.close();
		return users;
	}

	@Override
	public void addUser(User user) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
		session.persist(user);
		tx.commit();
		session.close();
	}
	
	public void updateUserInfo(User user, boolean passwordChanged) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		if(passwordChanged) {
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
		}
		session.update(user);
		tx.commit();
		session.close();
	}
	
	public boolean userNameExists(String username) {

		Session session = null;
		Transaction tx = null;

		User user = null;
		try {
			session  = sf.openSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("userName", username));
			user = (User) crit.uniqueResult();
			tx.commit();
			return (user != null);
		}
		catch (Exception ex) {
			if(tx != null) tx.rollback();
			throw ex;
		}
		finally {
			session.close();
		}
	
	}

}
