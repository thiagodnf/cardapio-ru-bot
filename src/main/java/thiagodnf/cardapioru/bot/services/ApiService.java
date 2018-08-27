package thiagodnf.cardapioru.bot.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import thiagodnf.cardapioru.bot.model.Menu;
import thiagodnf.cardapioru.bot.utils.Formatters;

@Service
public class ApiService {
	
	@Value("${api.url}")
	private String url;
	
	@Autowired
	protected MessageService messages;
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void initIt() {
		this.restTemplate = new RestTemplate();
	}
	
	public Menu getYesterdaysMenu(String university, String campus) {
		return restTemplate.getForObject(url + "/" + university + "/" + campus + "/yesterday", Menu.class);
	}
	
	public Menu getTodaysMenu(String university, String campus) {
		return restTemplate.getForObject(url + "/" + university + "/" + campus +  "/today", Menu.class);
	}
	
	public Menu getTomorrowsMenu(String university, String campus) {
		return restTemplate.getForObject(url + "/" + university + "/" + campus +  "/tomorrow", Menu.class);
	}

	public String getYesterdaysMenuAsHTML(String university, String campus) {
		
		Menu menu = getYesterdaysMenu(university, campus);

		if (menu == null) {
			return messages.getMessage("unavailable.menu");
		}

		return Formatters.menuToString(menu);
	}
	
	public String getTodaysMenuAsHTML(String university, String campus) {

		Menu menu = getTodaysMenu(university, campus);

		if (menu == null) {
			return messages.getMessage("unavailable.menu");
		}

		return Formatters.menuToString(menu);
	}
	
	public String getTomorrowsMenuAsHTML(String university, String campus) {
		
		Menu menu = getTomorrowsMenu(university, campus);

		if (menu == null) {
			return messages.getMessage("unavailable.menu");
		}

		return Formatters.menuToString(menu);
	}
}
