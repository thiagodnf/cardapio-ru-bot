package thiagodnf.cardapioru.bot.model;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class Menu {

	private String id;
	
	private String university = "ufpr";
	
	private String campus;

	private Date date;

	private List<String> breakfast;

	private List<String> lunch;

	private List<String> dinner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(List<String> breakfast) {
		this.breakfast = breakfast;
	}

	public List<String> getLunch() {
		return lunch;
	}

	public void setLunch(List<String> lunch) {
		this.lunch = lunch;
	}

	public List<String> getDinner() {
		return dinner;
	}

	public void setDinner(List<String> dinner) {
		this.dinner = dinner;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
}
