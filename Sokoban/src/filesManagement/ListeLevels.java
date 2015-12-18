package filesManagement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import gameStructures.Level;
import gameStructures.SokoGame;

public class ListeLevels {

	public final static String LEVEL_INFO_DIR = "/levels/infos/";
	public final static String LEVEL_LISTE_FILE_NAME = "listeLevels.txt";

	public final static ArrayList<Level> LISTE_LEVELS;
	public final static ArrayList<String> LISTE_LEVEL_FILES;
	public final static int NB_LEVELS;
	private static ArrayList<Integer> indexCompletedLevels;

	static {
		String fullPathListeLevels = new String(ListeLevels.LEVEL_INFO_DIR
				+ ListeLevels.LEVEL_LISTE_FILE_NAME);

		LISTE_LEVEL_FILES = ListeLevels.getLevelFiles(fullPathListeLevels);

		NB_LEVELS = LISTE_LEVEL_FILES.size();
		ListeLevels.indexCompletedLevels = ListeLevels.scanSaveFile();

		LISTE_LEVELS = new ArrayList<Level>();
		int i = 0;
		ListeLevels.LISTE_LEVELS.add(new Level(LISTE_LEVEL_FILES.get(i),
				new String("Tutoriel")));
		for (i = 1; i < NB_LEVELS; i++) {
			ListeLevels.LISTE_LEVELS.add(new Level(LISTE_LEVEL_FILES.get(i),
					new String("Niveau " + i)));
		}

		int indice;
		for (int j = 0; j < ListeLevels.indexCompletedLevels.size(); j++) {
			indice = ListeLevels.indexCompletedLevels.get(j);
			ListeLevels.LISTE_LEVELS.get(indice).setCompleted(true);
		}
	}

	private static ArrayList<String> getLevelFiles(String listePath) {
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

	private static ArrayList<Integer> scanSaveFile() {
		ArrayList<Integer> listeCompletedLevels = new ArrayList<Integer>();

		InputStream fileStream;
		BufferedReader buffLevel;
		try {
			SaveFile.createFileIfNotExist();
			fileStream = new FileInputStream(SaveFile.getFile());
			System.out.println("\n\n\n" + SaveFile.getFilePath() + "\n\n\n");
			buffLevel = new BufferedReader(new InputStreamReader(fileStream));

			String completedLevel;
			Integer completedLevelInd;
			while ((completedLevel = buffLevel.readLine()) != null) {
				completedLevelInd = Integer.parseInt(completedLevel);
				listeCompletedLevels.add(completedLevelInd);
			}
			buffLevel.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listeCompletedLevels;
	}

	public static void reInitStat() {
		for (int i = 0; i < NB_LEVELS; i++) {
			ListeLevels.LISTE_LEVELS.get(i).setCompleted(false);
		}
		SaveFile.createFileIfNotExist();
		SaveFile.clearFile();
	}

}
