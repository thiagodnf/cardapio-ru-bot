package thiagodnf.cardapioru.bot.tasks;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.bots.TelegramBot;
import thiagodnf.cardapioru.bot.model.Menu;
import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.ApiService;
import thiagodnf.cardapioru.bot.services.MessageService;
import thiagodnf.cardapioru.bot.services.UserService;
import thiagodnf.cardapioru.bot.utils.Campuses;
import thiagodnf.cardapioru.bot.utils.Universities;

@Component
public class SendAlertsTask {

	private static final Logger LOGGER = Logger.getLogger(SendAlertsTask.class);
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TelegramBot telegramBot;
	
	@Scheduled(cron = "0 00 11 ? * *")
	public void sendLunch() {
		execute("lunch");
	}
	
	@Scheduled(cron = "0 30 17 ? * *")
	public void sendDinner() {
		execute("dinner");
	}
	
	public void execute(String meal) {
		
		LOGGER.info("Sending alerts for " + meal);
		
		for (String university : Universities.getAllUniversities()) {

			LOGGER.info("Processing university " + university);
			
			for (String campus : Campuses.getCampuses(university)) {
				
				LOGGER.info("Processing campus " + campus);
				
				Menu menu = apiService.getTodaysMenu(university, campus);

				if (menu == null) {
					continue;
				}
				
				String menuWithouAccents = removeAccents(menu.getMealAsJson(meal));
				
				List<User> users = userService.findByUniversityAndCampus(university, campus);

				for (User user : users) {

					if (user.getAlerts().isEmpty()) {
						continue;
					}
					
					List<String> foundFoods = new ArrayList<>();

		            for(String food : user.getAlerts()) {
		                
	            		String foodWithoutAccents = removeAccents(food);

	            		if (menuWithouAccents.contains(foodWithoutAccents)) {
		                    foundFoods.add(food);
		                }
		            }

					if (!foundFoods.isEmpty()) {

						String campusName = Campuses.getCampusName(university, campus);
						String found = String.join(", ", foundFoods);
						String mealName = meal.equalsIgnoreCase("dinner") ? "jantar" : "almoço";

						String text = messageService.getMessage("alerts.send", campusName, found, mealName);

						telegramBot.sendMessageAsHTML(user.getChatId(), text);
					}
				}
			}
		}
		
		LOGGER.info("Done");
	}
	
	public String removeAccents(String text) {
		return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
