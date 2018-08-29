package thiagodnf.cardapioru.bot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * This class saves information about the campuses for every university
 * these bots support
 * 
 * @author Thiago Ferreira
 * @Version 1.0.1
 * @since 2018-09-28
 *
 */
public class Campuses {
	
	/**
	 * Map the campus' key and campus' name
	 */
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
	
	/**
	 * Constructor
	 * 
	 * @throws UnsupportedOperationException if you instatiate this class
	 */
	private Campuses(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Verify if a given university has a campus. It returns
	 * <code>true</code> if the university has this campus, otherwise, <code>false</code>
	 * 
	 * @param university the given university
	 * @param campus the given campus
	 * @return
	 */
	public static boolean isValid(String university, String campus) {
		
		Preconditions.checkArgument(StringUtils.isNotBlank(university), "the university name should be not null or empty");
		Preconditions.checkArgument(StringUtils.isNotBlank(campus), "the campus name should be not null or empty");
		
		return campuses.containsKey(university + "_" + campus);
	}

	/**
	 * It returns the campus' name. If university or the campus is
	 * invalid, the 'Desconhecido' text is returned
	 * 
	 * @param university the given university
	 * @param campus the given campus
	 * @return
	 */
	public static String getCampusName(String university, String campus) {
		
		Preconditions.checkArgument(StringUtils.isNotBlank(university), "the university name should be not null or empty");
		Preconditions.checkArgument(StringUtils.isNotBlank(campus), "the campus name should be not null or empty");

		if (isValid(university, campus)) {
			return campuses.get(university + "_" + campus);
		}

		return "Desconhecido";
	}

	/**
	 * It returns all campus given an university name
	 * 
	 * @param university the given university
	 * @return list of all campuses
	 */
	public static List<String> getCampuses(String university) {
		
		Preconditions.checkArgument(StringUtils.isNotBlank(university), "the university name should be not null or empty");

		List<String> campuses = new ArrayList<>();

		for (String key : Campuses.campuses.keySet()) {
			if (key.startsWith(university)) {
				campuses.add(key.split("_")[1]);
			}
		}

		return campuses;
	}
	
}
