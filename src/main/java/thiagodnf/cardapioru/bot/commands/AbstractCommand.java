package thiagodnf.cardapioru.bot.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.services.MessageService;
import thiagodnf.cardapioru.bot.services.UserService;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public abstract class AbstractCommand {

	@Autowired
	protected MessageService messages;
	
	@Autowired
	protected UserService userService;
	
	protected int min;

	protected int max;

	public AbstractCommand(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public AbstractCommand() {
		this(0, 0);
	}
	
	public String execute(CommandService commandService, String chatId, CommandArgs args) {

		if (max == 0 && min == 0 & !args.getArgs().isEmpty()) {
			return messages.getMessage("command.args.requires.zero");
		}

		if (args.getArgs().size() < min) {
			return messages.getMessage("command.args.requires.min", min);
		}

		if (args.getArgs().size() > max) {
			return messages.getMessage("command.args.requires.max", max);
		}

		User user = userService.findByChatId(chatId);

		if (user == null) {
			user = new User(chatId);
		}

		return getAction(commandService, user, args);
	}
	
	public abstract String getCommand();

	public abstract String getDescription();

	public abstract String getAction(CommandService commandService, User user, CommandArgs args);
}
