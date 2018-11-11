package main;

public class SimilarityBasedSearch {

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {
		
		assert image != null :"'image' == null";
		assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel";
		assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
		
		
		double n = image.length*image[0].length;
		double somme = 0.0;
		
		assert n != 0 : "n est egale a 0 donc division par 0... IMPOSSIBLE";
		
		for (int i = 0; i < image.length; ++i) {
			for (int j = 0; j < image[0].length; ++j) {
				somme += image[i][j];
			}
		}
		double moyenne = somme / n;
		
		return moyenne;
	}

	
	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		
		assert pattern != null :"Le model == null";
		assert pattern.length != 0 : "Le parametre 'pattern' ne contient pas de pixel";
		assert pattern[0].length !=0: "Le parametre 'pattern' ne contient pas de pixel";
		assert image != null : "L'image de fond == null";	
		assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel";
		assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
		
		assert row+pattern.length <= image.length : "Le model sort de l'image de fond en longueur";
		assert col+pattern[0].length <= image[0].length : "Le model sort de l'image de fond en largeur";
		
		assert row >= 0 : "le parametre row rentre en parametre est inferieur a 0";
		assert col >= 0 : "Le parametre col rentre en parametre est inferieur a 0";
		
		
		double numerateur = 0.0;
		double denom1 =0.0;
		double denom2 = 0.0;
		double denominateur = 0.0;
		double coeffImage = 0.0;
		double coeffPattern = 0.0;
		double moyennePattern = mean(pattern);
		double moyenneImage = windowMean(image, row, col, pattern[0].length, pattern.length );
		
		for (int i = 0; i < pattern.length; ++i) {
			for (int j = 0; j < pattern[0].length; ++j) {
				coeffImage = image[row + i][col + j];
				coeffPattern = pattern[i][j];
				numerateur += (coeffImage - moyenneImage) * (coeffPattern - moyennePattern);
				denom1 += (coeffImage - moyenneImage) * (coeffImage - moyenneImage);
				denom2 += (coeffPattern - moyennePattern) * (coeffPattern - moyennePattern);
			}
		}
		
		denominateur = Math.sqrt(denom1*denom2);
	
		if ( denominateur == 0.0) {
			return -1;
		}
		
		double resultat = numerateur/denominateur;
		
		return resultat;
		
	}

	 
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {
		
		assert pattern != null : "Le model == null";
		assert pattern.length != 0 : "Le parametre 'pattern' ne contient pas de pixel";
		assert pattern[0].length !=0: "Le parametre 'pattern' ne contient pas de pixel";
		assert image != null : "L'image de fond == null";
		assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel";
		assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
		
		int x = image.length - pattern.length + 1;
		int y = image[0].length - pattern[0].length + 1;
		double[][] coeffCorel = new double[x][y];
		
		for (int i = 0; i < x; ++i) {
			for (int j = 0; j < y; ++j) {
				coeffCorel[i][j] = normalizedCrossCorrelation(i, j, pattern, image);
			}
		}
	
		return coeffCorel;
	}
	
	/**
	 * Calcul la moyenne des niveaux de gris d'une portion d'une image en gris
	 * @param matrix : une matrice de type double qui correspond a l'image dont on va definir la portion a cacluler. Requirement : != null
	 * @param row : la ligne a partir de laquelle on calcul la moyenne. Requirement : >= 0
	 * @param col : la colonne a partir de laquelle on calcul la moyenne. Requirement : >= 0
	 * @param width : la largeur de la portion dont on va calcuer la moyenne. Requirement : > 0
	 * @param height : la hauteur de la portion dont on va caclculer la moyenne. Requirement : > 0
	 * @return moyenne : la moyenne des niveaux de gris de la portion de l'image
	 */
	public static double windowMean(double [][] matrix, int row, int col, int width, int height) {
		
		assert matrix.length != 0 : "Le parametre 'matrix' ne contient pas de pixel";
		assert matrix[0].length !=0: "Le parametre 'matrix' ne contient pas de pixel";
		assert matrix != null : "La matrice == null";
		
		assert row >= 0 : "le parametre row rentre en parametre est inferieur a 0";
		assert col >= 0 : "Le parametre col rentre en parametre est inferieur a 0";
		
		assert width > 0 : "la largeur du tableau de retour est inferieur ou egale a 0";
		assert height > 0 : "La hauteur du tabelau de retour est inferieur ou egale a 0";
		
		double[][] window = new double[height][width];
		
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				window[i][j] = matrix[i+row][j+col];
			}
		}
		double moyenne = mean(window);
		return moyenne;
		
	}

}
