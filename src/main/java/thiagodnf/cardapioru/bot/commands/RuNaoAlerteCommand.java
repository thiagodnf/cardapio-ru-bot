package thiagodnf.cardapioru.bot.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class RuNaoAlerteCommand extends AbstractCommand {

	public RuNaoAlerteCommand() {
		super(1, 10);
	}
	
	@Override
	public String getCommand() {
		return "runaoalerte";
	}

	@Override
	public String getDescription() {
		return "Remove uma comida da lista de alertas";
	}

	@Override
	public String getAction(User user, CommandArgs args) {
	
		List<String> foundFood = new ArrayList<>();
		List<String> notFoundFood = new ArrayList<>();

		for (String food : args.getArgs()) {
			
			if (user.getAlerts().contains(food)) {
				foundFood.add(food);
			} else {
				notFoundFood.add(food);
			}
		}
		
		if (foundFood.isEmpty()) {
			return messages.getMessage("alerts.remove.empty.found");
		}

		for (String food : foundFood) {
			
			if (user.getAlerts().contains(food)) {
				user.getAlerts().remove(food);
			}
		}
		
		userService.save(user);
		
		String answer = messages.getMessage("alerts.remove.msg.success", String.join(", ", foundFood));

		if (!notFoundFood.isEmpty()) {
			answer = answer + ". " + messages.getMessage("alerts.remove.not.found", String.join(", ", notFoundFood));
		}
				
		return answer;	
	}
}
