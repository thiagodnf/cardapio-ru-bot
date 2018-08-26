package thiagodnf.cardapioru.bot.services;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String key, Object... args) {

		String text = messageSource.getMessage(key, null, new Locale("pt_br"));

		if (StringUtils.isBlank(text)) {
			return "Não foi possível encontrar a key";
		}

		return String.format(text, args);
	}
}
