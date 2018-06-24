package se.consys.silverkissen.entities;

public class TokenObject {
	private String token;

	
	public TokenObject() {}
	
	public TokenObject(String token) {
		this.setToken(token);
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
