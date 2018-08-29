package thiagodnf.cardapioru.bot.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import com.google.gson.Gson;

public class User {
	
	@Id
	private String id;
	
	@NotNull
	@NotEmpty
	private String university = "ufpr";
	
	@NotNull
	@NotEmpty
	private String chatId;
	
	@NotNull
	@NotEmpty
	private String campus = "politecnico";
	
	private List<String> alerts;
	
	public User() {
		
	}
	
	public User(String chatId) {
		this.chatId = chatId;
		this.alerts = new ArrayList<>();
	}

	public User(String university, String campus, String chatId) {
		this.university = university;
		this.campus = campus;
		this.chatId = chatId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public List<String> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<String> alerts) {
		this.alerts = alerts;
	}

	public String toString() {
		return new Gson().toJson(this);
	}
}
