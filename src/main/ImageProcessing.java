package main;
public final class ImageProcessing {
	
    /**
     * Returns red component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer,  between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb) {
    		int x = rgb >> 16; 	       			
    		int y = x & 0b11111111;    			
    		y = testBornes(y);				    
    		return y; 
    }

    /**
     * Returns green component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb) {
    		int x = rgb >> 8; 	       			
    		int y = x & 0b11111111;    			
    		y = testBornes(y); 					
    		return y; 
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb : a 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
    	public static int getBlue(int rgb) {
    		int x = rgb & 0b11111111;    			
    		x = testBornes(x);						
    		return x;
    }

   
    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb : 32-bits RGB color
     * @return a double between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static double getGray(int rgb) {
    		
    		int red = getRed(rgb);
    		int green = getGreen(rgb);
    		int blue = getBlue(rgb);
    		
    		double moyenne = (red + green + blue) / 3.0;
    		
    		return moyenne;
    }

    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red : an integer
     * @param green : an integer 
     * @param blue : an integer
     * @return a 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue) {
    	
    		red = testBornes(red);
    		green = testBornes(green);
    		blue = testBornes(blue);
    		
    		int rgb;
    		
    		rgb = (((red << 16) | green << 8) | blue);
    		
    		return rgb; 
    }

    /**
     * Returns packed RGB components from given gray-scale value.
     * @param gray : an integer 
     * @return a 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(double gray) {
    		
    		gray = Math.round(gray);
    		
    		int x = (int)gray; 
    		x = testBornes(x);
    		
    		int rgb;
    		
    		rgb = (((x << 16) | x << 8) | x);
    		
    		return rgb;
    }

    /**
     * Converts packed RGB image to gray-scale image.
     * @param image : a HxW integer array
     * @return a HxW double array
     * @see #encode
     * @see #getGray
     */
    public static double[][] toGray(int[][] image) {
    	
    		assert image != null : "image == null";
    		assert image.length != 0 : "Le parametre 'image' ne contient pas de pixel";
    		assert image[0].length !=0: "Le parametre 'image' ne contient pas de pixel";
    		
    		int H = image.length;							        
		int W = image[0].length;	
		
		double[][] imageGray = new double[H][W];	
		
		for (int i = 0; i < H; ++i) {
			for (int j = 0; j < W; ++j) {
				imageGray[i][j] = getGray(image[i][j]);
				}
		}
	return imageGray;
    }

    /**
     * Converts gray-scale image to packed RGB image.
     * @param channels : a HxW double array
     * @return a HxW integer array
     * @see #decode
     * @see #getRGB(double)
     */
    public static int[][] toRGB(double[][] gray) {
    	
		assert gray != null : "gray == null";
		assert gray.length != 0 : "Le parametre 'gray' ne contient pas de pixel";
		assert gray[0].length !=0: "Le parametre 'gray' ne contient pas de pixel";
		
    		int H = gray.length;							    
		int W =  gray[0].length;	
		
		int[][] imageRGB = new int[H][W]; 
		
    		for (int i = 0; i < H; ++i) {
    			for (int j = 0; j < W; ++j) {
    				imageRGB[i][j] = getRGB(gray[i][j]);    
    			}
    			
    		}
    		return imageRGB;
    		

    }

    
    /**
     * Convert an arbitrary 2D double matrix into a 2D integer matrix 
     * which can be used as RGB image
     * @param matrix : the arbitrary 2D double array to convert into integer
     * @param min : a double, the minimum value the matrix could theoretically contains
     * @param max : a double, the maximum value the matrix could theoretically contains
     * @return an 2D integer array, containing a RGB mapping of the matrix 
     */
    public static int[][] matrixToRGBImage(double[][] matrix, double min, double max) {
 
		assert matrix != null : "matrix == null";
		assert matrix.length != 0 : "Le parametre 'matrix' ne contient pas de pixel";
		assert matrix[0].length !=0: "Le parametre 'matrix' ne contient pas de pixel";
    		assert min != max : "Les valeurs 'min' et 'max' sont egales donc on obtient une division par 0 IMPOSSIBLE";
    		
    		if ( min > max) {
    			double tmp = min;
    			min = max;
    			max = tmp;
    		}
    		
    		int[][] RGBmatrix = new int[matrix.length][matrix[0].length];
    		
    		for (int i = 0; i < matrix.length; ++i) {
    			for(int j = 0; j < matrix[0].length; ++j) {
    				double x = matrix[i][j];
    				double g = ((x - min)/(max - min))*255;
    				int rgb = getRGB(g);
    				RGBmatrix[i][j] = rgb;
    				}	
    		}
    	return RGBmatrix;
    }
    
    /**
     * Check that the int receive is a RGB color
     * @param color : int, level of the color to test
     * @return colorverif : the param if the color is correctn, else 255
     */
    
    private static int testBornes(int color) {
    		int colorverif = color;
    			if ( colorverif < 0) {
    				colorverif = 0;
    			}
    			if ( colorverif > 255) {
    				colorverif= 255;
    			}
    			return colorverif;
    }
}
