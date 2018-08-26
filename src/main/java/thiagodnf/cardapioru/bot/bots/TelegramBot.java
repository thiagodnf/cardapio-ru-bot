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
import thiagodnf.cardapioru.bot.services.MessageService;
import thiagodnf.cardapioru.bot.utils.CommandArgs;

@Component
public class TelegramBot extends TelegramLongPollingBot{

	private static final Logger LOGGER = Logger.getLogger(TelegramBot.class);
	
	@Value("${telegram.username}")
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
	    	
	    	String text = m.getText();
	    	long chatId = m.getChatId();
	    	
	    	LOGGER.info("Message Received: " + text);
	    	
	    	// For now this bot supports just commands
			if (m.isCommand()) {
				
				// Parse the commands
				CommandArgs commandArgs = CommandArgs.parse(text);
				
				// Execute the command and get the response
				String response = commands.execute(chatId, commandArgs);
				
				// Send the message
				sendMessageAsHTML(String.valueOf(chatId), response);
			} else {
				// The message is not a command.  However we have to communicate
				// to user s(he) should send just commands. We avoid channel or groups
				if (m.isUserMessage()) {
					sendMessageAsHTML(String.valueOf(chatId), messages.getMessage("only.commands"));
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
