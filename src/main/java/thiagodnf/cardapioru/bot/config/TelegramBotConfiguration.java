package thiagodnf.cardapioru.bot.config;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import org.telegram.telegrambots.starter.TelegramBotInitializer;

import thiagodnf.cardapioru.bot.bots.TelegramBot;

/**
 * #TelegramBotsApi added to spring context as well
 */
@Configuration
@ConditionalOnProperty(prefix="telegrambots",name = "enabled", havingValue = "true", matchIfMissing = true)
public class TelegramBotConfiguration {

	@Value("${telegram.bot.url.external}")
	private String externalURL;
	
	@Value("${telegram.bot.url.internal}")
	private String internalURL;
	
	@Bean
	@ConditionalOnMissingBean(TelegramBotsApi.class)
	public TelegramBotsApi telegramBotsApi() throws TelegramApiRequestException {
		
		System.out.println(externalURL);
		
//		TelegramBotsApi bot = new TelegramBotsApi(externalURL, internalURL);
//		
		TelegramBotsApi bot = new TelegramBotsApi();
		
		//bot.registerBot(new TelegramBot());
		
		return bot;
	}

	@Bean
	@ConditionalOnMissingBean
	public TelegramBotInitializer telegramBotInitializer(
			TelegramBotsApi telegramBotsApi,
			Optional<List<LongPollingBot>> longPollingBots,
			Optional<List<WebhookBot>> webHookBots) {
		
		return new TelegramBotInitializer(
				telegramBotsApi, 
				longPollingBots.orElseGet(Collections::emptyList),
				webHookBots.orElseGet(Collections::emptyList));
	}
}
