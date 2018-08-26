package thiagodnf.cardapioru.bot.bots.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotWithPolling extends TelegramLongPollingBot{

	@Value("${telegram.username}")
	private String botUserName;
	
	@Value("${telegram.bot.token}")
	private String botToken;
	
	@Override
	public void onUpdateReceived(Update update) {
		
		// We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    

	    	// Set variables
	        String message_text = update.getMessage().getText();
	        long chat_id = update.getMessage().getChatId();

	        SendMessage message = new SendMessage() // Create a message object object
	                .setChatId(chat_id)
	                .setText(message_text);
	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException ex) {
	            ex.printStackTrace();
	        }
	        
	    }
	}

	@Override
	public String getBotUsername() {
		return this.botUserName;
	}

	@Override
	public String getBotToken() {
		return this.botToken;
	}

}
