package main;

import java.util.ArrayList;

public class Collector {

	/**
	 * Find the row, column coordinates of the best element (biggest or smallest) for the given matrix
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicates if the smallest element is the best or not (biggest is then the best)
	 * @return an array of two integer coordinates, row first and then column
	 */
	public static int[] findBest(double[][] matrix, boolean smallestFirst) {
		
		assert matrix != null : "matrix == null! ";
		assert matrix.length != 0 : "La matrice recu est vide";
		
		int x = matrix.length;
		int y = matrix[0].length;
		
		int row = 0;
		int col = 0;
		
		if (smallestFirst == true) {
			
			double min = Double.POSITIVE_INFINITY;
			
			for (int i = 0; i < x ; ++i) {
				for (int j = 0; j < y; ++j) {
					if (matrix[i][j] < min) {
						min = matrix[i][j];
						col = i;
						row = j;
					}
				}
			}
		} else {
			double max = Double.NEGATIVE_INFINITY;
			
			for (int i = 0; i < x ; ++i) {
				for (int j = 0; j < y; ++j) {
					if (matrix[i][j] > max) {
						max = matrix[i][j];
						col = i;
						row = j;
					}
				}
			}
		}
		
		int[] coordonate = new int[2];
		coordonate[0] = col;
		coordonate[1] = row;

		return coordonate ;
	}

	
	/**
	 * Find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean,  indicates if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBest(int n, double[][] matrix, boolean smallestFirst) {

		assert matrix != null : "matrix == null! ";
		assert matrix.length != 0 : "La matrice recu est vide";
		assert n > 0 : "n est inferieur a 0";	
		assert n <= matrix.length * matrix[0].length : "Vous cherchez plus d'elements qu'il n'y en a... IMPOSSIBLE";
		
		double[][] copy = new double[matrix.length][matrix[0].length];
		
		for (int i = 0; i < copy.length; ++i) {
			for (int j = 0; j < copy[0].length; ++j) {
				copy[i][j] = matrix[i][j];
			}	
		}
		
		int[][] coordonnee = new int[n][2];
		
		for (int i = 0; i < n; ++i) {
			if (smallestFirst) {
				int[] resp = findBest(copy, smallestFirst);
				copy[resp[0]][resp[1]] = Double.POSITIVE_INFINITY;
				coordonnee[i][0] = resp[0];
				coordonnee[i][1] = resp[1];
			} else {
				int[] resp = findBest(copy, smallestFirst);
				copy[resp[0]][resp[1]] = Double.NEGATIVE_INFINITY;
				coordonnee[i][0] = resp[0];
				coordonnee[i][1] = resp[1];
			}
		}
			

		return coordonnee;
	}	
	
	/** Not implemented method... Have fun if you want to :)
	 */
	public static ArrayList<int[]> quicksortPixelCoordinates(double[][] matrix) {
		return new ArrayList<int[]>();
	}

	
	/** Not implemented method... Have fun if you want to :)
	 */
	public static int[][] findNBestQuickSort(int n, double[][] matrix, boolean smallestFirst) {
		
    		return new int[][]{};
	}	
}

