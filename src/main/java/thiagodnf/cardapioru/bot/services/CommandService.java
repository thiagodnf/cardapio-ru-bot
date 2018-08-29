package thiagodnf.cardapioru.bot.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import thiagodnf.cardapioru.bot.commands.AbstractCommand;
import thiagodnf.cardapioru.bot.commands.HelpCommand;
import thiagodnf.cardapioru.bot.commands.RuAlertasCommand;
import thiagodnf.cardapioru.bot.commands.RuAlerteCommand;
import thiagodnf.cardapioru.bot.commands.RuAmanhaCommand;
import thiagodnf.cardapioru.bot.commands.RuCampusCommand;
import thiagodnf.cardapioru.bot.commands.RuHojeCommand;
import thiagodnf.cardapioru.bot.commands.RuMonitoreCampusCommand;
import thiagodnf.cardapioru.bot.commands.RuNaoAlerteCommand;
import thiagodnf.cardapioru.bot.commands.RuOntemCommand;
import thiagodnf.cardapioru.bot.commands.RuSemAlertasCommand;
import thiagodnf.cardapioru.bot.commands.StartCommand;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Service
public class CommandService {
	
	private static final Logger LOGGER = Logger.getLogger(CommandService.class);
	
	@Value("${bot.username}")
	private String botUserName;
	
	public interface AnswerCallback{
		void process(String chatId, String answer);
	}
	
	@Autowired
	private MessageService messages;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private MiddlewareService middlewares;
	
	private HashMap<String, AbstractCommand> commands = new HashMap<>();
	
	@PostConstruct
	public void initIt() {
		this.add(context.getBean(StartCommand.class));
		this.add(context.getBean(HelpCommand.class));
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
	
	public List<AbstractCommand> getListOfCommands(){
		return this.commands.values().stream().collect(Collectors.toList());
	}

	public void execute(String chatId, String text, AnswerCallback callback) {
		
		try {
			// Execute all middleware
			text = middlewares.execute(chatId, text);
			
			// Parse the commands
			CommandArgs commandArgs = CommandArgs.parse(text);
			
			// Sometimes, the command is sent from chat or group. If the user has more the one bot
			// inside that, the @Username is sent too. The next command removes the username from command
			commandArgs.clearUsernameFromCommand(botUserName);

			// If the user typed a different one, we execute the action
			AbstractCommand command = this.commands.get(commandArgs.getCommand());

			String response = "";
			
			// No command was found
			if (command == null) {
				response = messages.getMessage("unrecognized.command");
			} else {
				response = command.execute(this, chatId, commandArgs);
			}

			callback.process(chatId, response);
		}catch(Exception ex) {
			LOGGER.error(ex);
			callback.process(chatId, ex.getMessage());
		}
	}
}
