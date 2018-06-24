package se.consys.silverkissen.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.consys.silverkissen.dataaccess.cats.CatHibernateImpl;
import se.consys.silverkissen.dataaccess.cats.iCatDao;
import se.consys.silverkissen.dataaccess.images.ImageHibernateImpl;
import se.consys.silverkissen.dataaccess.images.iImageDao;
import se.consys.silverkissen.entities.Cat;
import se.consys.silverkissen.entities.Image;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.helpers.LocalDateHelper;

@Path("cats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatController {

	private iCatDao dao = new CatHibernateImpl();
	private iImageDao imageDao = new ImageHibernateImpl();
	
	@GET
	public Response findAll() {
		try {
			List<Cat> cats = dao.findAll();
			return Response.status(200).entity(cats).build();
		} catch (NoResultException e) {
			System.out.println("No litters found.");
			return Response.status(204).build();
		}		
	}
	
	@GET
	@Path("/parents")
	public Response findParents() {
		try {
			List<Cat> cats = dao.findAll();
			List<Cat> parents = new ArrayList<Cat>();
			for (Cat cat : cats) {
				if (cat.isParent()) {
					parents.add(cat);
				}
			}
			return Response.status(200).entity(parents).build();
		} catch (NoResultException e) {
			System.out.println("No litters found.");
			return Response.status(204).build();
		}		
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@DefaultValue("0") @PathParam("id") int id) {
		try {
			Cat cat = dao.findById(id);
			return Response.status(200).entity(cat).build();
		} catch(NoResultException e) {
			Log log = new Log(Log.Messages.NORESULT, "Cat", String.valueOf(id));
			dao.saveLog(log);
			return Response.status(204).build();
		}
	}
	
	@POST
	public Response create(Cat cat) {
		if (cat.isParent()) {
			cat.setAge(Period.between(cat.getBorn(), LocalDate.now()).getYears());	
		}
		if (cat.getImages() != null) {
			for (Image i : cat.getImages()) {
				imageDao.create(i);
			}
		}		
		dao.create(cat);
		
		return Response.status(200).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@DefaultValue("0") @PathParam("id") int id) {
		Cat cat = dao.findById(id);
		dao.delete(cat);
		return Response.status(200).build();
	}
	
	@PATCH
	@Path("/{id}")
	public Response update(
			@DefaultValue("0") @PathParam("id") int id,
			@DefaultValue("") @QueryParam("born") LocalDateHelper born,
			@DefaultValue("") @QueryParam("notes") String notes,
			@DefaultValue("-1") @QueryParam("age") int age,
			@DefaultValue("") @QueryParam("breed") String breed,
			@DefaultValue("") @QueryParam("sex") String sex,
			@DefaultValue("") @QueryParam("color") String color,
			@DefaultValue("") @QueryParam("name") String name,
			@DefaultValue("-1") @QueryParam("chipped") int chipped,
			@DefaultValue("-1") @QueryParam("vaccinated") int vaccinated,
			@DefaultValue("-1") @QueryParam("pedigree") int pedigree) {
		System.out.println("patch called. " + name);
		try {
			Cat cat = dao.findById(id);
			
			if (born != null && !born.getLocalDate().equals(LocalDate.MIN)) cat.setBorn(born.getLocalDate());
			if (!notes.equals("")) cat.setNotes(notes);
			if (age != -1) cat.setAge(age);
			if (!breed.equals("")) cat.setBreed(breed);
			if (!color.equals("")) cat.setColor(color);
			if (!name.equals("")) {
				System.out.println("Setting name...");
				cat.setName(name);
			}
			if (!sex.equals("")) cat.setSex(sex);
			
			if (chipped != -1) {
				if (chipped == 0) {
					cat.setChipped(false);
				} else {
					cat.setChipped(true);
				}
			}
			if (vaccinated != -1) {
				if (vaccinated == 0) {
					cat.setVaccinated(false);
				} else {
					cat.setVaccinated(true);
				}
			}
			if (pedigree != -1) {
				if (pedigree == 0) {
					cat.setPedigree(false);
				} else {
					cat.setPedigree(true);
				}
			}
			
			dao.update(cat);
			return Response.status(200).build();
		} catch (NoResultException e) {
			Log log = new Log(Log.Messages.NORESULT, "Cat", String.valueOf(id));
			return Response.status(204).build();
		}
	}
}
