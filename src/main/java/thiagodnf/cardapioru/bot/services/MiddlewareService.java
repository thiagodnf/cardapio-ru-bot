package thiagodnf.cardapioru.bot.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import thiagodnf.cardapioru.bot.middlewares.AbstractMiddleware;
import thiagodnf.cardapioru.bot.middlewares.FloodMiddleware;

@Service
public class MiddlewareService {

	@Autowired
	private ApplicationContext context;
	
	private List<AbstractMiddleware> middlewares = new ArrayList<>();
	
	@PostConstruct
	public void initIt() {
		this.middlewares.add(context.getBean(FloodMiddleware.class));
	}
	
	public String execute(String chatId, String text) {
		
		for(AbstractMiddleware middleware : this.middlewares) {
			text = middleware.execute(chatId, text);
		}
		
		return text;
	}	
}
