package thiagodnf.cardapioru.bot.commands;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class RuAlerteCommand extends AbstractCommand {

	public RuAlerteCommand() {
		super(1, 10);
	}
	
	@Override
	public String getCommand() {
		return "rualerte";
	}

	@Override
	public String getDescription() {
		return "Define alertas para uma comida";
	}

	@Override
	public String getAction(CommandService commandService, User user, CommandArgs args) {

		if (args.getArgs().isEmpty()) {
			return messages.getMessage("alerts.args.requires.list");
		}

		for (String food : args.getArgs()) {
			if (!user.getAlerts().contains(food)) {
				user.getAlerts().add(food);
			}
		}
		
		userService.save(user);
		
		String list = args.getArgs().stream().collect(Collectors.joining(", "));
		
		return messages.getMessage("alerts.add.msg.success", list);
	}
}
