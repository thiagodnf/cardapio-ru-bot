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
	
	public Menu getYesterdaysMenu(String campus) {
		return restTemplate.getForObject(url + campus + "/yesterday", Menu.class);
	}
	
	public Menu getTodaysMenu(String campus) {
		return restTemplate.getForObject(url + campus + "/today", Menu.class);
	}
	
	public Menu getTomorrowsMenu(String campus) {
		return restTemplate.getForObject(url + campus + "/tomorrow", Menu.class);
	}

	public String getYesterdaysMenuAsHTML(String campus) {
		
		Menu menu = getYesterdaysMenu(campus);

		if (menu == null) {
			return messages.getMessage("unavailable.menu");
		}

		return Formatters.menuToString(menu);
	}
	
	public String getTodaysMenuAsHTML(String campus) {

		Menu menu = getTodaysMenu(campus);

		if (menu == null) {
			return messages.getMessage("unavailable.menu");
		}

		return Formatters.menuToString(menu);
	}
	
	public String getTomorrowsMenuAsHTML(String campus) {
		
		Menu menu = getTomorrowsMenu(campus);

		if (menu == null) {
			return messages.getMessage("unavailable.menu");
		}

		return Formatters.menuToString(menu);
	}
}
