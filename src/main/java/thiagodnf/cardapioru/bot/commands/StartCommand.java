package thiagodnf.cardapioru.bot.commands;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class StartCommand extends AbstractCommand {

	@Override
	public String getCommand() {
		return "start";
	}

	@Override
	public String getDescription() {
		return "Exibe a mensagem de boas vindas";
	}

	@Override
	public String getAction(CommandService commandService, User user, CommandArgs args) {
		return messages.getMessage("start.command");
	}
}
