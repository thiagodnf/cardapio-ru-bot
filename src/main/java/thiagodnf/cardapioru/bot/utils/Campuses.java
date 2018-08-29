package thiagodnf.cardapioru.bot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Campuses {
	
	private static Map<String, String> campuses = new HashMap<>();
	
	static {
		campuses.put("ufpr_politecnico", "Centro Politécnico");
		campuses.put("ufpr_central", "Central");
		campuses.put("ufpr_botanico", "Jardim Botânico");
		campuses.put("ufpr_agrarias", "Agrárias");
		campuses.put("ufpr_mirassol", "Mirassol");
		campuses.put("ufpr_litoral", "UFPR Litoral");
		campuses.put("ufpr_cem", "UFPR CEM");
		campuses.put("ufpr_palotina", "Palotina");
		campuses.put("ufpr_jandaia", "Jandaia do Sul");
	}
	
	private Campuses(){
		throw new UnsupportedOperationException();
	}
	
	public static boolean isValid(String university, String campus) {
		return campuses.containsKey(university + "_" + campus);
	}

	public static String getCampusName(String university, String campus) {

		if (isValid(university, campus)) {
			return campuses.get(university + "_" + campus);
		}

		return "Desconhecido";
	}

	public static List<String> getCampuses(String university) {

		List<String> campuses = new ArrayList<>();

		for (String key : Campuses.campuses.keySet()) {
			if (key.startsWith(university)) {
				campuses.add(key.split("_")[1]);
			}
		}

		return campuses;
	}
	
}
