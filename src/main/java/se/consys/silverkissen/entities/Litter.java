package se.consys.silverkissen.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Litter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToMany
	@JoinTable(
			name = "litter_parent",
			joinColumns = @JoinColumn(name="litter_id", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="parent_animal_id", referencedColumnName="id"))
	private List<Cat> parents;
	@OneToMany
	@JoinTable(
			name = "litter_kitten",
			joinColumns = @JoinColumn(name="litter_id", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="kitten_animal_id", referencedColumnName="id"))
	private List<Cat> kittens;
	private LocalDate readyAt;
	private LocalDate born;
	@OneToMany
	private List<Image> imageUrls;
	private String displayPicture;
	private String notes;
	private int numberOfKittens;
	private int numberOfFemales;
	private int numberOfMales;
	private boolean vaccinated;
	private boolean pedigree;
	private boolean chipped;
	private String status;
	
	public Litter() {
		
	}
	
//	public Litter(List<Cat> parents, List<Cat> kittens, LocalDate readyAt, LocalDate born, 
//			List<String> imageUrls, String displayPicture, String notes, int numberOfKittens, 
//			int numberOfFemales, int numberOfMales, boolean vaccinated, boolean pedigree, boolean chipped) {
//		this.setBorn(born);
//		this.setChipped(chipped);
//		this.setDisplayPicture(displayPicture);
//		this.setImageUrls(imageUrls);
//		this.setKittens(kittens);
//		this.setNotes(notes);
//		this.setNumberOfFemales(numberOfFemales);
//		this.setNumberOfKittens(numberOfKittens);
//		this.setNumberOfMales(numberOfMales);
//		this.setParents(parents);
//		this.setPedigree(pedigree);
//		this.setVaccinated(vaccinated);
//		this.setReadyAt(born.plus(Period.ofWeeks(13)));
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Cat> getParents() {
		return parents;
	}
	public void setParents(List<Cat> parents) {
		this.parents = parents;
	}
	public List<Cat> getKittens() {
		return kittens;
	}
	public void setKittens(List<Cat> kittens) {
		this.kittens = kittens;
	}
	public LocalDate getReadyAt() {
		return readyAt;
	}
	public void setReadyAt(LocalDate readyAt) {
		this.readyAt = readyAt;
	}
	public LocalDate getBorn() {
		return born;
	}
	public void setBorn(LocalDate born) {
		this.born = born;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getNumberOfKittens() {
		return numberOfKittens;
	}
	public void setNumberOfKittens(int numberOfKittens) {
		this.numberOfKittens = numberOfKittens;
	}
	public int getNumberOfFemales() {
		return numberOfFemales;
	}
	public void setNumberOfFemales(int numberOfFemales) {
		this.numberOfFemales = numberOfFemales;
	}
	public int getNumberOfMales() {
		return numberOfMales;
	}
	public void setNumberOfMales(int numberOfMales) {
		this.numberOfMales = numberOfMales;
	}

	public boolean isChipped() {
		return chipped;
	}

	public void setChipped(boolean chipped) {
		this.chipped = chipped;
	}

	public boolean isPedigree() {
		return pedigree;
	}

	public void setPedigree(boolean pedigree) {
		this.pedigree = pedigree;
	}

	public boolean isVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	public String getDisplayPicture() {
		return displayPicture;
	}

	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}

	public List<Image> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<Image> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
