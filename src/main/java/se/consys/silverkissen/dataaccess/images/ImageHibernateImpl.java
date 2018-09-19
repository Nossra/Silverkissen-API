package se.consys.silverkissen.dataaccess.images;

import java.util.List;

import org.hibernate.Session;

import se.consys.silverkissen.entities.Cat;
import se.consys.silverkissen.entities.Image;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.utilities.HibernateUtil;

public class ImageHibernateImpl implements iImageDao {

	private Session session = HibernateUtil.getSessionFactory().openSession();
	
	
	public void create(Image image) {
		session.beginTransaction();
		session.persist(image);
		session.getTransaction().commit();
	}

	
	public void update(Image image) {
		session.beginTransaction();
		session.merge(image);
		session.getTransaction().commit();
	}

	
	public void delete(Image image) {
		session.beginTransaction();
		session.delete(image);
		session.getTransaction().commit();
	}

	
	public List<Image> findAll() {
		String HQL_FIND_ALL = "FROM Image";
		
		@SuppressWarnings("unchecked")
		List<Image> result = (List<Image>) session.createQuery(HQL_FIND_ALL)
			.getResultList();
		return  result;
	}

	
	public Image findById(int id) {
		String HQL = "FROM Image WHERE id = :id";
		
		@SuppressWarnings("unchecked")
		Image result = (Image) session.createQuery(HQL)
			.setParameter("id", id)
			.setMaxResults(1)
			.getSingleResult();		
		return  result;
	}

	
	public void saveLog(Log log) {
		session.beginTransaction();
		session.persist(log);
		session.getTransaction().commit();
	}

}
