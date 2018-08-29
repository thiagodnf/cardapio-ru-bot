package thiagodnf.cardapioru.bot.bots;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import thiagodnf.cardapioru.bot.services.CommandService;
import thiagodnf.cardapioru.bot.services.CommandService.AnswerCallback;
import thiagodnf.cardapioru.bot.services.MessageService;

@Component
public class TelegramBot extends TelegramLongPollingBot{

	private static final Logger LOGGER = Logger.getLogger(TelegramBot.class);
	
	@Value("${bot.username}")
	private String botUserName;
	
	@Value("${telegram.bot.token}")
	private String botToken;
	
	@Autowired
	private MessageService messages;
	
	@Autowired
	private CommandService commands;
	
	@Override
	public void onUpdateReceived(Update update) {
		
		// We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    
	    	Message m = update.getMessage();
	    	
	    	String chatId = String.valueOf(m.getChatId());
	    	String text = m.getText();
	    	
	    	LOGGER.info("Message Received: " + text);
	    	
	    	// For now this bot supports just commands
			if (m.isCommand()) {
				commands.execute(chatId, text, new AnswerCallback() {
					
					@Override
					public void process(String chatId, String answer) {
						sendMessageAsHTML(chatId, answer);
					}
				});
			} else {
				// The message is not a command.  However we have to communicate
				// to user s(he) should send just commands. We avoid channel or groups
				if (m.isUserMessage()) {
					sendMessageAsHTML(chatId, messages.getMessage("only.commands"));
				}
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
	
	public void sendMessageAsHTML(String chatId, String text) {
		
		// Create a message object object
        SendMessage message = new SendMessage() 
        		.enableHtml(true)
                .setChatId(chatId)
                .setText(text);
        
        // Send message to user
		try {
        	execute(message); 
        } catch (TelegramApiException ex) {
            LOGGER.error(ex);
        }
	}
}
