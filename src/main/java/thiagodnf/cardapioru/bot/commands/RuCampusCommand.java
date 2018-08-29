package thiagodnf.cardapioru.bot.commands;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.utils.Campuses;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class RuCampusCommand extends AbstractCommand {

	@Override
	public String getCommand() {
		return "rucampus";
	}

	@Override
	public String getDescription() {
		return "Exibe o campus que você monitora";
	}

	@Override
	public String getAction(CommandService commandService, User user, CommandArgs args) {
		return messages.getMessage("campus.monitoring", Campuses.getCampusName(user.getUniversity(), user.getCampus()));
	}
}
