package se.consys.silverkissen.entities;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal {
	private boolean pedigree;
	private boolean vaccinated;
	private boolean chipped;
	public Cat() {
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
	public boolean isChipped() {
		return chipped;
	}
	public void setChipped(boolean chipped) {
		this.chipped = chipped;
	}
}
