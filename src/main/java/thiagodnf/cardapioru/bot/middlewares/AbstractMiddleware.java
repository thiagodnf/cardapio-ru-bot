package thiagodnf.cardapioru.bot.middlewares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thiagodnf.cardapioru.bot.services.MessageService;

@Component
public abstract class AbstractMiddleware {
	
	@Autowired
	protected MessageService messages;
	
	public abstract String execute(String chatId, String text);
}
