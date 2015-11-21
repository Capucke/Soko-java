package gameStructures ;

class GameMatrix{
	
	private int[][] matrix ;
	private int nbObj ;
	
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
	
	public int[][] getMatrix() {
		return this.matrix;
	}
	
	public int getNbLines() {
		return this.matrix.length;
	}
	
	public int getNbCol() {
		return this.matrix[0].length;
	}
	
	public int getObj(int i, int j) {
		if (i < 0 || i >= this.matrix.length) {
			throw new IllegalArgumentException(
					"L'indice i correspond au numéro de la ligne où se trouve la case à laquelle vous souhaitez accéder. "
							+ "Il doit donc être un entier compris entre 0 et "
							+ this.matrix.length);
		} else if (j < 0 || j >= this.matrix[0].length) {
			throw new IllegalArgumentException(
					"L'indice i correspond au numéro de la colonne où se trouve la case à laquelle vous souhaitez accéder. "
							+ "Il doit donc être un entier compris entre 0 et "
							+ this.matrix[0].length);
		} else {
			return this.matrix[i][j];
		}
	}
	
	public void setObj(int i, int j, int objType) {
		if (objType < 0 || objType >= this.nbObj) {
			throw new IllegalArgumentException(
					"L'état passé en paramètre doit être un entier compris entre 0 et "
							+ this.nbObj);
		} else if (i < 0 || i >= this.getNbLines()) {
			throw new IllegalArgumentException(
					"L'indice i correspond au numéro de la ligne où se trouve la case à laquelle vous souhaitez accéder. "
							+ "Il doit donc être un entier compris entre 0 et "
							+ (this.getNbLines() - 1));
		} else if (j < 0 || j >= this.getNbCol()) {
			throw new IllegalArgumentException(
					"L'indice i correspond au numéro de la colonne où se trouve la case à laquelle vous souhaitez accéder. "
							+ "Il doit donc être un entier compris entre 0 et "
							+ (this.getNbCol() - 1));
		} else {
			this.matrix[i][j] = objType;
		}
	}
	
}