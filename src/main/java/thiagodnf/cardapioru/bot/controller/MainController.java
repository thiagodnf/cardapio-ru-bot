package thiagodnf.cardapioru.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import thiagodnf.cardapioru.bot.bots.TelegramBot;

@RestController
public class MainController {

	@Value("${bot.username}")
	private String botUserName;
	
	@Value("${telegram.bot.token}")
	private String botToken;
	
	@Autowired
	private TelegramBot telegramBot;
	
	@RequestMapping(value = "/callback/bot${telegram.bot.token}", method = RequestMethod.POST)
	@ResponseBody
	public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
		return telegramBot.onWebhookUpdateReceived(update);
	}
}
