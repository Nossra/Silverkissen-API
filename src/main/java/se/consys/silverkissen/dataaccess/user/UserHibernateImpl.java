package se.consys.silverkissen.dataaccess.user;

import java.time.LocalDate;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import se.consys.silverkissen.entities.Litter;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.entities.User;
import se.consys.silverkissen.utilities.HibernateUtil;

public class UserHibernateImpl implements iUserDao {

	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	@Override
	public boolean find(String email, String password) {
		try {
			String HQL = "From User WHERE email=:email AND password=:password";
			
			session.createQuery(HQL)
				.setParameter("email", email)
				.setParameter("password", password)
				.setMaxResults(1)
				.getSingleResult();		
			
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public void saveLog(Log log) {
		session.beginTransaction();
		session.persist(log);
		session.getTransaction().commit();
	}
	
	
}
