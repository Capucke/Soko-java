package gameStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListeLevels {
	public final static String LEVEL_LISTE_FILE = "/levels/listeLevels.txt";
	public final static ArrayList<String> LISTE_LEVELS;
	public final static int NB_LEVELS;

	static {
		LISTE_LEVELS = ListeLevels.getLevels(LEVEL_LISTE_FILE);
		NB_LEVELS = LISTE_LEVELS.size();
	}

	// public ListeLevels(String listePath) {
	// this.levelNames = new ArrayList<String>();
	// this.initLevels(listePath);
	// System.out
	// .println("Liste des niveaux :\n" + this.levelNames.toString());
	// }

	private static ArrayList<String> getLevels(String listePath) {
		ArrayList<String> levelNames = new ArrayList<String>();

		InputStream fileStream = SokoGame.class.getResourceAsStream(listePath);
		BufferedReader buffLevel = new BufferedReader(new InputStreamReader(
				fileStream));
		String currLevelName;

		try {
			while ((currLevelName = buffLevel.readLine()) != null) {
				levelNames.add(currLevelName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return levelNames;

	}

}
