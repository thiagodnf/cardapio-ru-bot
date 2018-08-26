package thiagodnf.cardapioru.bot.commands;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class RuSemAlertasCommand extends AbstractCommand {

	@Override
	public String getCommand() {
		return "rusemalertas";
	}

	@Override
	public String getDescription() {
		return "Remove todos os alertas";
	}

	@Override
	public String getAction(User user, CommandArgs args) {

		user.setAlerts(new ArrayList<>());
		
		userService.save(user);
		
		return messages.getMessage("alerts.clear.msg.success");
	}
}
