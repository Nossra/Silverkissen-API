package se.consys.silverkissen.dataaccess.cats;

import java.util.List;

import org.hibernate.Session;

import se.consys.silverkissen.entities.Cat;
import se.consys.silverkissen.entities.Litter;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.utilities.HibernateUtil;

public class CatHibernateImpl implements iCatDao {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	

	public void create(Cat cat) {
		session.beginTransaction();
		session.persist(cat);
		session.getTransaction().commit();
	}

	public void update(Cat cat) {
		session.beginTransaction();
		session.merge(cat);
		session.getTransaction().commit();
	}

	public void delete(Cat cat) {
		session.beginTransaction();
		session.delete(cat);
		session.getTransaction().commit();
	}

	public Cat findById(int id) {
		//might have to be "Animal" instead of "Cat"
		String HQL = "FROM Animal WHERE id = :id";
		
		@SuppressWarnings("unchecked")
		Cat result = (Cat) session.createQuery(HQL)
			.setParameter("id", id)
			.setMaxResults(1)
			.getSingleResult();		
		return  result;
	}

	public List<Cat> findAll() {
		String HQL_FIND_ALL = "FROM Cat";
		
		@SuppressWarnings("unchecked")
		List<Cat> result = (List<Cat>) session.createQuery(HQL_FIND_ALL)
			.getResultList();
		return  result;
	}
	
	public void saveLog(Log log) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.persist(log);
		session.getTransaction().commit();
	}
	
}
