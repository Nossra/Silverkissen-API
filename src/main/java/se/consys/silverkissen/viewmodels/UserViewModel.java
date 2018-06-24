package se.consys.silverkissen.viewmodels;

public class UserViewModel {
	private int id;
	private String email;
	
	public UserViewModel() {
		
	}
	
	public UserViewModel(int id, String email) {
		this.setId(id);
		this.setEmail(email);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
