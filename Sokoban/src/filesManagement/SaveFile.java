package filesManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class SaveFile {

	private static File saveDir;
	private static String saveDirName = "SokobanJudith";

	private static File saveFile;
	private static String saveFileName = "save.txt";

	public static String getFilePath() {
		return saveFile.getAbsolutePath();
	}

	public static File getFile() {
		return saveFile;
	}

	public static boolean createFileIfNotExist() {
		createFolder();
		saveFile = new File(saveDir, saveFileName);
		// if the file does not exist, create it
		if (!saveFile.exists()) {
//			System.out.println("creating file: " + saveFileName);

			try {
				saveFile.createNewFile();
			} catch (SecurityException se) {
				se.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return saveFile.isFile();
	}

	public static boolean createFolder() {
		saveDir = new File(System.getenv("APPDATA"), saveDirName);
		// if the directory does not exist, create it
		if (!saveDir.exists()) {
//			System.out.println("creating directory: " + saveDirName);
//			System.out.println("\nChemin : " + saveDir.getAbsolutePath());
			boolean result = false;

			try {
				result = saveDir.mkdir();
			} catch (SecurityException se) {
				System.err.println(se.getStackTrace());
			}
//			if (result) {
//				System.out.println("directory : " + saveDirName + " created");
//			}
			return result;
		}
		return true;
	}

	public static boolean clearFile() {
		if (saveFile.isFile()) {
			FileWriter saveFileWriter;
			PrintWriter saveFilePw;
			try {
				saveFileWriter = new FileWriter(saveFile, false);
				saveFilePw = new PrintWriter(saveFileWriter, false);
				saveFilePw.flush();
				saveFilePw.close();
				saveFileWriter.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}

	public static boolean addNumber(int num) {
		boolean fileExist = createFileIfNotExist();
		if (!fileExist) {
			System.err.println("Le fichier n'existe pas");
			return false;
		}
		FileWriter saveFileWriter;
		Writer saveBuffWriter;
		try {
			saveFileWriter = new FileWriter(saveFile, true);
			saveBuffWriter = new BufferedWriter(saveFileWriter);
			saveBuffWriter.append(Integer.toString(num) + "\n");
			saveBuffWriter.flush();
			saveBuffWriter.close();
			saveFileWriter.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
