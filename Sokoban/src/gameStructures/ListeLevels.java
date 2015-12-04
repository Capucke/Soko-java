package gameStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListeLevels {
	private ArrayList<String> levelNames;

	public ListeLevels(String listePath) {
		this.levelNames = new ArrayList<String>();
		this.initLevels(listePath);
		System.out
				.println("Liste des niveaux :\n" + this.levelNames.toString());
	}

	private void initLevels(String listePath) {
		InputStream fileStream = SokoGame.class
				.getResourceAsStream(listePath);
		BufferedReader buffLevel = new BufferedReader(new InputStreamReader(
				fileStream));
		String currLevelName;

		try {
			while ((currLevelName = buffLevel.readLine()) != null) {
				this.levelNames.add(currLevelName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getLevels() {
		return this.levelNames;
	}

}
