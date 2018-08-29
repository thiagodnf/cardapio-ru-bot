package thiagodnf.cardapioru.bot.commands;

import java.util.List;

import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.model.User;
import thiagodnf.cardapioru.bot.utils.Campuses;
import thiagodnf.cardapioru.bot.utils.CommandArgs;
import thiagodnf.cardapioru.bot.utils.Universities;

@Component
public class RuMonitoreCampusCommand extends AbstractCommand {

	public RuMonitoreCampusCommand() {
		super(1, 1);
	}
	
	@Override
	public String getCommand() {
		return "rumonitorecampus";
	}

	@Override
	public String getDescription() {
		return "Define o campus que você quer monitorar";
	}

	@Override
	public String getAction(User user, CommandArgs args) {

		List<String> campuses = Universities.getCampus(user.getUniversity());

		String university = user.getUniversity();
		String campus = args.getArgs().get(0);

		if (!campuses.contains(campus)) {
			return messages.getMessage("campus.msg.invalid", Universities.parse(university),String.join(", ", campuses));
		}
		
		user.setCampus(campus);
		
		userService.save(user);
		
		return messages.getMessage("campus.msg.success", Campuses.getCampusName(university, campus));
	}
}
