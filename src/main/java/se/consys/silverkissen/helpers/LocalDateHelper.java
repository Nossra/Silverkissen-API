package se.consys.silverkissen.helpers;

import java.time.LocalDate;

public class LocalDateHelper {
	private LocalDate value;
	private LocalDate defaultValue = LocalDate.MIN;

	public LocalDateHelper() {
		this.value = this.defaultValue;
	}
	
	public LocalDateHelper(String val) {
		this.value = LocalDate.parse(val); 
	}
	
	private void setValue(String val) {
		if(val == null || val.equals(""))
			this.value = this.defaultValue;
		else
			this.value = LocalDate.parse(val); 
	}
	
	public LocalDate getLocalDate() {
		return this.value;
	}
	
}
