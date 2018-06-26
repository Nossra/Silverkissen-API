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
import se.consys.silverkissen.dataaccess.litters.LitterHibernateImpl;
import se.consys.silverkissen.dataaccess.litters.iLitterDao;
import se.consys.silverkissen.entities.Cat;
import se.consys.silverkissen.entities.Image;
import se.consys.silverkissen.entities.Litter;
import se.consys.silverkissen.entities.LitterStatus;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.helpers.ListHelper;
import se.consys.silverkissen.helpers.LocalDateHelper;

@Path("litters")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LitterController {

	private iLitterDao dao = new LitterHibernateImpl();
	private iCatDao catDao = new CatHibernateImpl();
	private iImageDao imageDao = new ImageHibernateImpl();
	private final int WEEKS_TO_GET_READY = 13;
	
	@GET
	public Response findAll() {
		try {
			List<Litter> litters = dao.findAll();
			return Response.status(200).entity(litters).build();
		} catch (NoResultException e) {
			Log log = new Log(Log.Messages.NORESULT, "Litter", "findAll");
			dao.saveLog(log);
			return Response.status(204).build();
		}		
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@DefaultValue("0") @PathParam("id") int id) {
		try {
			Litter litter = dao.findById(id);
			return Response.status(200).entity(litter).build();
		} catch(NoResultException e) {
			Log log = new Log(Log.Messages.NORESULT, "Litter", "findById: " + String.valueOf(id));
			dao.saveLog(log);
			return Response.status(204).build();
		}
	}
	
	@POST
	public Response create(Litter litter) {
		List<Cat> kittens = new ArrayList<Cat>();
	    for (int i = 0; i < litter.getNumberOfKittens(); i++) {
			Cat kitten = new Cat();
			kitten.setBorn(litter.getBorn());
			kitten.setAge(Period.between(litter.getBorn(), LocalDate.now()).getYears());
			if (litter.getParents().get(0).getBreed().equals(litter.getParents().get(1).getBreed().trim())) {
				kitten.setBreed(litter.getParents().get(0).getBreed());
			} else {
				kitten.setBreed("Bondkatt");
			}			
			kittens.add(kitten);
		}
	    litter.setKittens(kittens);
		litter.setReadyAt(litter.getBorn().plus(Period.ofWeeks(WEEKS_TO_GET_READY)));
		if (litter.getImageUrls() != null) {
			for (Image i : litter.getImageUrls()) {
				imageDao.create(i);
			}
		}
		if (litter.getKittens() != null) {
			for (Cat c : litter.getKittens()) {
				catDao.create(c);
			}
		}
		dao.create(litter);

		return Response.ok().build();
	}
	
	@PATCH
	@Path("/{id}")
	public Response update(
			@DefaultValue("0") @PathParam("id") int id,
			@DefaultValue("") @QueryParam("notes") String notes,
			@DefaultValue("") @QueryParam("born") LocalDateHelper born,
			@DefaultValue("") @QueryParam("readyAt") LocalDateHelper readyAt,
			@DefaultValue("") @QueryParam("kittens") String kittensString,
			@DefaultValue("") @QueryParam("parents") String parentsString,
			@DefaultValue("-1") @QueryParam("males") int numberOfMales,
			@DefaultValue("-1") @QueryParam("females") int numberOfFemales,
			@DefaultValue("") @QueryParam("images") String imageIds,
			@DefaultValue("-1") @QueryParam("chipped") int chipped,
			@DefaultValue("-1") @QueryParam("vaccinated") int vaccinated,
			@DefaultValue("-1") @QueryParam("pedigree") int pedigree,
			@DefaultValue("") @QueryParam("displayimage") String displayPicture,
			@DefaultValue("") @QueryParam("litterstatus") String status) {
		try {
			Litter litter = dao.findById(id);
			if (!status.equals("")) {
				litter.setStatus(status);
			}
			if (!notes.equals("")) litter.setNotes(notes);
			if (born != null && !born.getLocalDate().equals(LocalDate.MIN)) {
				litter.setBorn(born.getLocalDate());
				for (Cat kitten : litter.getKittens()) {
					kitten.setAge(Period.between(litter.getBorn(), LocalDate.now()).getYears());
				}
			}
			if (readyAt != null && !readyAt.getLocalDate().equals(LocalDate.MIN)) litter.setReadyAt(readyAt.getLocalDate());
			if (numberOfMales != -1) litter.setNumberOfMales(numberOfMales);
			if (numberOfFemales != -1) litter.setNumberOfFemales(numberOfFemales);
			if (!displayPicture.equals("")) litter.setDisplayPicture(displayPicture);

			if (chipped != -1) {
				if (chipped == 0) {
					litter.setChipped(false);
					for (Cat kitten : litter.getKittens()) {
						kitten.setChipped(false);
					}
				} else {
					litter.setChipped(true);
					for (Cat kitten : litter.getKittens()) {
						kitten.setChipped(true);
					}
				}
			}
			if (vaccinated != -1) {
				if (vaccinated == 0) {
					litter.setVaccinated(false);
					for (Cat kitten : litter.getKittens()) {
						kitten.setVaccinated(false);
					}
				} else {
					litter.setVaccinated(true);
					for (Cat kitten : litter.getKittens()) {
						kitten.setVaccinated(true);
					}
				}
			}
			if (pedigree != -1) {
				if (pedigree == 0) {
					litter.setPedigree(false);
					for (Cat kitten : litter.getKittens()) {
						kitten.setPedigree(false);
					}
				} else {
					litter.setPedigree(true);
					for (Cat kitten : litter.getKittens()) {
						kitten.setPedigree(true);
					}
				}
			}
			
			if (!kittensString.equals("")) {
				try {
					List<Integer> ids = ListHelper.separateIds(kittensString);
					List<Cat> kittens = new ArrayList<Cat>();
					for (Integer kittenId : ids) {
						Cat cat = catDao.findById(kittenId);
						kittens.add(cat);
					}
					litter.setKittens(kittens);
				} catch(NoResultException e) {
					Log log = new Log(Log.Messages.NORESULT, "Cat", "kittensString: " + kittensString);
					dao.saveLog(log);
					return Response.status(204).build();
				}
			}

			if (!parentsString.equals("")) {
				try {
					List<Integer> ids = ListHelper.separateIds(parentsString);
					List<Cat> parents = new ArrayList<Cat>();
					for (Integer parentId : ids) {
						Cat cat = catDao.findById(parentId);
						parents.add(cat);
					}
					litter.setParents(parents);
				} catch(NoResultException e) {
					Log log = new Log(Log.Messages.NORESULT, "Cat", "parentsString: " + parentsString);
					dao.saveLog(log);
					return Response.status(204).build();
				}
			}
			
			if (!imageIds.equals("")) {
				try {
					List<Integer> imagelist = ListHelper.separateIds(imageIds);
					List<Image> images = new ArrayList<Image>();
					for (Integer imageId : imagelist) {
						Image i = imageDao.findById(imageId);
						images.add(i);
					}
					litter.setImageUrls(images);
				} catch (NoResultException e) {
					Log log = new Log(Log.Messages.NORESULT, "Image", "Image ids: " + imageIds);
					dao.saveLog(log);
					return Response.status(204).build();
				}
			}
			
			dao.update(litter);
			return Response.ok().build();
		} catch (NoResultException e) {
			Log log = new Log(Log.Messages.NORESULT, "Litter", String.valueOf(id));
			dao.saveLog(log);
			return Response.status(204).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@DefaultValue("0") @PathParam("id") int id) {
		Litter litter = dao.findById(id);
//		List<Cat> kittensInLitter = new ArrayList<Cat>();
//		for (Cat c : litter.getKittens()) {
//			kittensInLitter.add(c);
//		}
		
		dao.delete(litter);
//		for (Cat c : kittensInLitter) {
//			if (c.isParent() == false) {
//				catDao.delete(c);
//			}
//		}
		return Response.status(200).build();
	}
}