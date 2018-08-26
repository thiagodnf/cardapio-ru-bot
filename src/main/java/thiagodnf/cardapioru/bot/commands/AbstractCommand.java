package thiagodnf.cardapioru.bot.commands;

public abstract class AbstractCommand {

	public abstract String getCommand();
	
	public abstract String getDescription();
	
	public abstract String execute();
}
