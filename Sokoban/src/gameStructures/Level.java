package gameStructures;

public class Level {

	private String fileName;
	private String apparentName;
	private boolean completed;
	
	
	public Level(String file){
		this(file, file, false);
	}
	
	public Level(String file, String name){
		this(file, name, false);
	}
	
	public Level(String file, boolean wasCompleted){
		this(file, file, wasCompleted);
	}
	
	public Level(String file, String name, boolean wasCompleted){
		this.fileName = file;
		this.apparentName = name;
		this.completed = wasCompleted;
	}
	
	public void setFile(String file){
		this.fileName = file;
	}
	
	public String getFile(){
		return new String(this.fileName);
	}
	
	public void setName(String name){
		this.apparentName = name;
	}
	
	public String getName(){
		String name = new String(this.apparentName);
		if (this.completed){
			name += new String(" (ok)");
		}
		return name;
	}
	
	public boolean isCompleted(){
		return this.completed;
	}
	
	public void setCompleted(boolean compl){
		this.completed = compl;
	}

}
