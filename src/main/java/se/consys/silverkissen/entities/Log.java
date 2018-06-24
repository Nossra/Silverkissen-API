package se.consys.silverkissen.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime timestamp;
	private String message;
	private String entity;
	private String parameter;
	
	public Log() {
		
	}
	public Log(Messages message) {
		this.setTimestamp(LocalDateTime.now());
		this.setMessage(enumHelper(message));
	}
	
	public Log(Messages message, String onEntity, String parameter) {
		this.setTimestamp(LocalDateTime.now());
		this.setMessage(enumHelper(message));
		this.setEntity(onEntity);
		this.setParameter(parameter);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static enum Messages {
		UNAUTHORIZED,
		NORESULT
	}
	
	private String enumHelper(Messages value) {
		String message = "";
		switch (value) {
			case UNAUTHORIZED :
				message = "Failed login attempt";
				break;
			case NORESULT :
				message = "No result found on requested id.";
				break;
		}
		return message;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
