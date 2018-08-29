package thiagodnf.cardapioru.bot.middlewares;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.stereotype.Component;

@Component
public class FloodMiddleware extends AbstractMiddleware {

	private int limit = 2;
	
	private Map<String, DateTime> hits = new HashMap<>();
	
	public String execute(String chatId, String text) {
		
		DateTime now = DateTime.now();
		DateTime last = DateTime.now();

		if (this.hits.containsKey(chatId)) {
			
			last = this.hits.get(chatId);

			Seconds seconds = Seconds.secondsBetween(last, now);

			if (seconds.getSeconds() <= limit) {
				throw new RuntimeException(messages.getMessage("flood.msg"));
			}			
		}
		
		this.hits.put(chatId, now);

		return text;
	}
}
