package thiagodnf.cardapioru.bot.utils;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import thiagodnf.cardapioru.bot.model.Menu;

public class Formatters {
	
	public static String menuToString(Menu menu) {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<b>")
			.append(dateToString(menu.getDate()))
			.append("</b> no <b>")
			.append(Campuses.parse(menu.getUniversity(), menu.getCampus()))
			.append("</b>")
			.append("\n\n")
			.append("<i>Café da Manhã</i>\n")
			.append(mealToString(menu.getBreakfast()))
			.append("\n")
			.append("<i>Almoço</i>\n")
			.append(mealToString(menu.getLunch()))
			.append("\n")
			.append("<i>Jantar</i>\n")
			.append(mealToString(menu.getDinner()));
		        
		return buffer.toString();
	}
	
	public static String mealToString(List<String> items) {

		String str = "";

		for (String item : items) {
			str += "    " + item + "\n";
		}

		str += "";

		return str;
	}
	
	public static String dateToString(Date date) {
		return dateTimeToString(new DateTime(date));
	}
	
	public static String dateTimeToString(DateTime dateTime) {
		
		DateTimeFormatter DATEFORMAT = DateTimeFormat.forPattern("dd/MM");

		String text = dateTime.toString(DATEFORMAT);

		switch (dateTime.getDayOfWeek()) {
			case 1:
				return text + " " + "Segunda-feira";
			case 2:
				return text + " " + "Terça-feira";
			case 3:
				return text + " " + "Quarta-feira";
			case 4:
				return text + " " + "Quinta-feira";
			case 5:
				return text + " " + "Sexta-feira";
			case 6:
				return text + " " + "Sábado";
			case 7:
				return text + " " + "Domingo";
		}

		return text;
	}
}
