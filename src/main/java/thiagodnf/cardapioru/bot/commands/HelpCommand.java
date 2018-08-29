package thiagodnf.cardapioru.bot.commands;

import java.util.List;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class HelpCommand extends AbstractCommand {

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "Apresenta o menu de ajuda com todos os comandos";
	}

	@Override
	public String getAction(CommandService commandService, User user, CommandArgs args) {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<b>")
			.append(messages.getMessage("available.command"))
			.append("</b>")
			.append("\n");
		
		List<AbstractCommand> commands = commandService.getListOfCommands();

		for (AbstractCommand command : commands) {
			buffer.append("/")
				.append(command.getCommand())
				.append(" - ")
				.append(command.getDescription())
				.append("\n");
		}

		return buffer.toString();
	}
}
