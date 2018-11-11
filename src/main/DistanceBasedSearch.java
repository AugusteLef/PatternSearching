package main;

public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {

		int imageRed = ImageProcessing.getRed(imagePixel);
		int imageGreen = ImageProcessing.getGreen(imagePixel);
		int imageBlue = ImageProcessing.getBlue(imagePixel);
		
		
		int patternRed = ImageProcessing.getRed(patternPixel);
		int patternGreen = ImageProcessing.getGreen(patternPixel);
		int patternBlue = ImageProcessing.getBlue(patternPixel);
		
		double absoluteError = 0.0;
		absoluteError = (Math.abs(patternRed-imageRed)+Math.abs(patternGreen-imageGreen) + Math.abs(patternBlue-imageBlue)) / 3.0; 
		return absoluteError;
	}

	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned
	 * at the provided row, column-coordinates in a RGB image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {

		assert pattern.length != 0 : "Le parametre 'pattern' ne contient pas de pixel";
		assert pattern[0].length !=0: "Le parametre 'pattern' ne contient pas de pixel";
		assert pattern != null : "Le model = null";
		assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel"; 
		assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
		assert image != null : "L'image de fond = null";
		
		assert row+pattern.length <= image.length : "Le modele depasse de l'image de fond en hauteur";
		assert col+pattern[0].length <= image[0].length : "Le model est plus large que l'image de fond";
		
		int H = pattern.length;
		int W = pattern[0].length;
		
		
		int d = pattern.length*pattern[0].length;
		
		assert d != 0 : "'d' egale 0 donc divison par zero... IMPOSSIBLE";
		
		double absoluteError = 0.0;
		
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j) {
				absoluteError += pixelAbsoluteError(pattern[i][j], image[row+i][col+j]);
			}
		}
		
		double result = absoluteError / d;
		return result;
		
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {

			assert pattern.length != 0 : "Le parametre 'pattern' ne contient pas de pixel";
    			assert pattern[0].length !=0: "Le parametre 'pattern' ne contient pas de pixel";
			assert pattern != null : "Le model == null";
			assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel"; 
			assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
			assert image != null : "L'image de fond == null";
						
			assert (pattern.length) <= image.length : "La hauteur du modele est plus grande que celui de l'image de fond";
			assert (pattern[0].length) <= image[0].length : "La largeur du modele est plus grande que celui de l'image de fond";

			
			int x = (image.length - ( pattern.length - 1 ) );
			int y = (image[0].length - ( pattern[0].length - 1 ) );
				
			double[][] distanceMatrix = new double[x][y];
			
			for (int i = 0; i < x; ++i) {
				for (int j = 0; j < y ; ++j) {
					distanceMatrix [i][j] = meanAbsoluteError (i, j, pattern, image);
				}
			}
					
			return distanceMatrix; 
				
	}
	
	public static double[][] distanceMatrixStrategy(int pattern[][], int image[][], String strategy) {
		assert pattern.length != 0 : "Le parametre 'pattern' ne contient pas de pixel";
		assert pattern[0].length !=0: "Le parametre 'pattern' ne contient pas de pixel";
		assert pattern != null : "Le model == null";
		assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel"; 
		assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
		assert image != null : "L'image de fond == null";
					
		assert (pattern.length) <= image.length : "La hauteur du modele est plus grande que celui de l'image de fond";
		assert (pattern[0].length) <= image[0].length : "La largeur du modele est plus grande que celui de l'image de fond";
		
		assert (strategy == "wrapping" || strategy == "mirroring") : "La strategie definie n'existe pas";
		
		
		int x = image.length;
		int y = image[0].length;
		double[][] distanceMatrixStrategy = new double[x][y];
		
		
		if (strategy == "wrapping") {
			for (int i = 0; i < x ; ++i) {
				for (int j = 0; j < y ; ++j ) {
					int indexx = (i % ( x - pattern.length) ) ;
					int indexy = (j % (y - pattern[0].length) ) ;
					
					distanceMatrixStrategy[i][j] = meanAbsoluteError(indexx,indexy,pattern, image);
				}
			}
		} else {
			int indexI;
			int indexJ;
			for (int i = 0; i < x ; ++i) {
				for (int j = 0; j < y ; ++j) {
					if ( i >= image.length - pattern.length) {
						indexI = (image.length - pattern.length) - 2 - (i % (image.length - pattern.length));
					} else {
						indexI = i;
					}
					if ( j >= image[0].length - pattern[0].length) {
						indexJ = (image[0].length - pattern[0].length) - 2 - (j % (image[0].length - pattern[0].length));
					} else {
						indexJ = j;
					}
					
					distanceMatrixStrategy[i][j] = meanAbsoluteError(indexI, indexJ, pattern, image);
				}
			}
			
		}
		
		return distanceMatrixStrategy;
		
	}
	
	
}