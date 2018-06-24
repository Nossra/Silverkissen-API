package se.consys.silverkissen.main;

import java.util.List;
import java.time.LocalDate;
import java.time.Period;

import org.hibernate.Session;

import se.consys.silverkissen.dataaccess.cats.CatHibernateImpl;
import se.consys.silverkissen.dataaccess.cats.iCatDao;
import se.consys.silverkissen.dataaccess.litters.LitterHibernateImpl;
import se.consys.silverkissen.dataaccess.litters.iLitterDao;
import se.consys.silverkissen.entities.Cat;
import se.consys.silverkissen.entities.Litter;
import se.consys.silverkissen.helpers.LocalDateHelper;
import se.consys.silverkissen.utilities.HibernateUtil;

public class app {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		iCatDao catDao = new CatHibernateImpl();
////		
//		iLitterDao litterDao = new LitterHibernateImpl();
//		
//		Litter l = new Litter();
//		
//		l.setVaccinated(true);
//		l.setChipped(true);
//		l.setPedigree(true);
//		l.setDisplayPicture("assets/images/stef.jpg");
//		
//		litterDao.create(l);
		System.out.println("finished..");
		
	}
}
