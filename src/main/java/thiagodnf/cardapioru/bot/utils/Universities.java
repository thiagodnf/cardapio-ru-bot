package thiagodnf.cardapioru.bot.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Universities {

	private static Map<String, String> universities = new HashMap<>();

	static {
		universities.put("ufpr", "UFPR");
	}

	public static boolean isValid(String university) {
		return universities.containsKey(university);
	}

	public static String parse(String university) {
		
		if (isValid(university)) {
			return universities.get(university);
		}
		
		return "Desconhecida";
	}
	
	public static List<String> getAllUniversities(){
		return universities.keySet().stream().collect(Collectors.toList());
	}
	
	public static List<String> getCampus(String university){
		return Campuses.getCampuses(university);
	}
}
