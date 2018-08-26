package thiagodnf.cardapioru.bot.commands;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class RuAlertasCommand extends AbstractCommand {
	
	@Override
	public String getCommand() {
		return "rualertas";
	}

	@Override
	public String getDescription() {
		return "Exibe os alertas cadastrados";
	}

	@Override
	public String getAction(User user, CommandArgs args) {
		
		if (user.getAlerts().isEmpty()) {
			return messages.getMessage("alerts.empty");
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<b>")
			.append(messages.getMessage("alerts.title"))
			.append("</b>\n");
			
		for (int i = 0; i < user.getAlerts().size(); i++) {
			buffer.append(i + 1)
				.append(". ")
				.append(user.getAlerts().get(i))
				.append("\n");
		}
		
		return buffer.toString();
	}

}
