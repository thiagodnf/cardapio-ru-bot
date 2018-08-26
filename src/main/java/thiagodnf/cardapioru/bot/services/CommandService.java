package thiagodnf.cardapioru.bot.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import thiagodnf.cardapioru.bot.commands.AbstractCommand;
import thiagodnf.cardapioru.bot.commands.RuAlertasCommand;
import thiagodnf.cardapioru.bot.commands.RuAlerteCommand;
import thiagodnf.cardapioru.bot.commands.RuAmanhaCommand;
import thiagodnf.cardapioru.bot.commands.RuCampusCommand;
import thiagodnf.cardapioru.bot.commands.RuHojeCommand;
import thiagodnf.cardapioru.bot.commands.RuMonitoreCampusCommand;
import thiagodnf.cardapioru.bot.commands.RuNaoAlerteCommand;
import thiagodnf.cardapioru.bot.commands.RuOntemCommand;
import thiagodnf.cardapioru.bot.commands.RuSemAlertasCommand;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Service
public class CommandService {
	
	@Autowired
	private MessageService messages;
	
	@Autowired
	private ApplicationContext context;
	
	private HashMap<String, AbstractCommand> commands = new HashMap<>();
	
	@PostConstruct
	public void initIt() {
		this.add(context.getBean(RuHojeCommand.class));
		this.add(context.getBean(RuOntemCommand.class));
		this.add(context.getBean(RuAmanhaCommand.class));
		this.add(context.getBean(RuAlertasCommand.class));
		this.add(context.getBean(RuAlerteCommand.class));
		this.add(context.getBean(RuNaoAlerteCommand.class));
		this.add(context.getBean(RuSemAlertasCommand.class));
		this.add(context.getBean(RuCampusCommand.class));
		this.add(context.getBean(RuMonitoreCampusCommand.class));
	}
	
	public void add(AbstractCommand command) {
		this.commands.put(command.getCommand(), command);
	}

	public String execute(long chatId, CommandArgs commandArgs) {

		String commandAsString = commandArgs.getCommand();

		// Verify the default commands 'help' and 'start'
		if (commandAsString.equalsIgnoreCase("help")) {
			return getHelpCommand();
		}
		if (commandAsString.equalsIgnoreCase("start")) {
			return messages.getMessage("start.command");
		}
		
		// If the user typed a different one, we execute the action
		AbstractCommand command = this.commands.get(commandAsString);

		// No command was found
		if (command == null) {
			return messages.getMessage("unrecognized.command");
		}

		// Command was found. Execute it
		return command.execute(String.valueOf(chatId), commandArgs);
	}
	
	public String getHelpCommand() {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<b>")
			.append(messages.getMessage("available.command"))
			.append("</b>")
			.append("\n");

		for (String key : commands.keySet()) {
			buffer.append("/")
				.append(key)
				.append(" - ")
				.append(commands.get(key).getDescription())
				.append("\n");
		}

		return buffer.toString();
	}
}
