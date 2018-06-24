package se.consys.silverkissen.dataaccess.litters;

import java.util.List;

import org.hibernate.Session;

import se.consys.silverkissen.entities.Litter;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.utilities.HibernateUtil;

public class LitterHibernateImpl implements iLitterDao {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	@Override
	public void create(Litter litter) {
		session.beginTransaction();
		session.persist(litter);
		session.getTransaction().commit();
	}

	@Override
	public void update(Litter litter) {
		session.beginTransaction();
		session.merge(litter);
		session.getTransaction().commit();		
	}

	@Override
	public void delete(Litter litter) {
		session.beginTransaction();
		session.remove(litter);
		session.getTransaction().commit();	
	}

	@Override
	public Litter findById(int id) {
		String HQL = "FROM Litter WHERE id = :id";
		
		@SuppressWarnings("unchecked")
		Litter result = (Litter) session.createQuery(HQL)
			.setParameter("id", id)
			.setMaxResults(1)
			.getSingleResult();		
		return  result;
	}

	@Override
	public List<Litter> findAll() {
		String HQL_FIND_ALL = "FROM Litter";
		
		@SuppressWarnings("unchecked")
		List<Litter> result = (List<Litter>) session.createQuery(HQL_FIND_ALL)
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
