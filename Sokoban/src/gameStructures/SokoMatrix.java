package gameStructures ;

class SokoMatrix extends GameMatrix{
	
	private final int EMPTY = 0 ;
	private final int SOKO = 1 ;
	private final int BAG = 2 ;
	private final int GOAL = 3 ;
	private final int WALL = 4 ;
	
	public GameMatrix(int width, int height, int nbObj){
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(
					"Il doit y avoir au moins une ligne et une colonne");
		}
		if (nbObj <= 0) {
			throw new IllegalArgumentException(
					"Il doit y avoir au moins un type d'objet");
		}
		this.matrix = new int[height][width];
		this.nbObj = nbObj ;
	}
	
}