package thiagodnf.cardapioru.bot.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.ApiService;
import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class RuOntemCommand extends AbstractCommand {

	@Autowired
	private ApiService apiService;
			
	@Override
	public String getCommand() {
		return "ruontem";
	}

	@Override
	public String getDescription() {
		return "Exibe o cardápio de ontem";
	}

	@Override
	public String getAction(CommandService commandService, User user, CommandArgs args) {
		try {
			return apiService.getYesterdaysMenuAsHTML(user.getUniversity(), user.getCampus());
		} catch (RestClientException ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
}
