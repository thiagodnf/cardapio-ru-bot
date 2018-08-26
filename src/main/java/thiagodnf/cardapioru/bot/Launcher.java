package thiagodnf.cardapioru.bot;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * The main Class of the application. 
 * You have to start the execution here.
 * 
 * @author Thiago Ferreira
 * @since 2018-08-25
 * @version 1.0.0
 */
@SpringBootApplication
@EnableScheduling
public class Launcher {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	public static void main(String[] args) {
		
		ApiContextInitializer.init();

		SpringApplication.run(Launcher.class, args);
	}
}
