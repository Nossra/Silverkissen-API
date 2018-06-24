package se.consys.silverkissen.helpers;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {
	public static List<Integer> separateIds(String idString) {
		String[] separatedIds = idString.split("-");
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < separatedIds.length; i++) {
			ids.add(Integer.parseInt(separatedIds[i]));
		}
		return ids;		
	}
	
	public static List<String> separateStrings(String strings) {
		String[] separatedStrings = strings.split("-");
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < separatedStrings.length; i++) {
			stringList.add(separatedStrings[i]);
		}
		return stringList;		
	}
}
