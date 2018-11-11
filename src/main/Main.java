package main;

/**
 * 
 * @author Auguste LEFEVRE & Marc WATINE
 *
 *	Where is Waldo Project 
 *	(Main class that works as a JUNIT Test, if you want to run the program to test it use the class program)
 *
 */
public final class Main {
	
    public static void main(String[] args) {
    
    testGetRed();
    	testGetGreen();
    	testGetBlue();
    	testValNeg();
    	testValSup();
    	testGray();
    //testToGray(); // function that test an assert
    	testGrayscale();
    	testCharlieGray();
    	testFindNBest();
    	testFindNBestMAX();
    	testFindBestMIN();
    	testFindBestMAX();
    testDistanceBasedSearch();
    testFindCharlie();
    testImage();
    testWrappingFood();
    testMirroringFood();
    testWrappingCharlie();
    testMirroringCharlie();
    testSimilarityBasedSearch();   
    findCharlie();
    testFindImage();
    	
    testNCCPatternEqualImage();
    testSimilarityPatternEqualImage();
    testSimilaritySimple();
    }
    
    /*
     * Tests for Class ImageProcessing
     */
    public static void testGetRed() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	if (red == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + red + " Expected value = " + ref);
    	}
    }

    public static void testGrayscale() {
    	System.out.println("Test Grayscale");
    int[][] image = Helper.read("images/food.png");
    	double[][] gray = ImageProcessing.toGray(image);
    	Helper.show(ImageProcessing.toRGB(gray), "test bw");
    }
    
    public static void testCharlieGray() {
    	System.out.println("Test l'image charlie en gris");
    	int [][] image = Helper.read("images/charlie_beach.png");
    	double[][] gray = ImageProcessing.toGray(image);
    	Helper.show(ImageProcessing.toRGB(gray), "test Waldo b&w");
    }
    
    
    public static void testGetGreen() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b00001111;
    	int green = ImageProcessing.getGreen(color);
    	if (green == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + green + " Expected value = " + ref);
    	}
    }
    
    public static void testGetBlue() { 
    	int color = 0b11110000_00001111_01010101;
    	int ref = 0b01010101;
    	int blue = ImageProcessing.getBlue(color);
    	if (blue == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + blue + " Expected value = " + ref);
    	}
    }
    
    public static void testValNeg(){
    		int red = -25;
    		int green = -255;
    		int blue = -1;
    		int rgb = ImageProcessing.getRGB(red, green, blue);
    		red = ImageProcessing.getRed(rgb);
    		green = ImageProcessing.getGreen(rgb);
    		blue = ImageProcessing.getBlue(rgb);
    		if ( red == blue && blue == green & green == 0) {
    			System.out.println("Test : negatives values passed");
    		} else {
    			System.out.println("Test : negatives value failed");
    		}
    }
    
    public static void testValSup() {
    		int red = 266;
		int green = 277;
		int blue = 3876;
		int rgb = ImageProcessing.getRGB(red, green, blue);
		red = ImageProcessing.getRed(rgb);
		green = ImageProcessing.getGreen(rgb);
		blue = ImageProcessing.getBlue(rgb);
		if ( red == blue && blue == green & green == 255) {
			System.out.println("Test : positive value passed");
		} else {
			System.out.println("Test : positive valiues failed");
		}
    	
    }
    
    public static void testGray() {
    	System.out.println("test gray (sup/inf)");
    	double x = -67.0;
    	double y = 2767.0;
    	int rgbinf = ImageProcessing.getRGB(x);
    	int rgbsup = ImageProcessing.getRGB(y);
    	int redinf, redsup, greeninf, greensup, blueinf, bluesup;
    	redinf = ImageProcessing.getRed(rgbinf);
    	redsup = ImageProcessing.getRed(rgbsup);
    	greeninf = ImageProcessing.getGreen(rgbinf);
    	greensup = ImageProcessing.getGreen(rgbsup);
    	blueinf = ImageProcessing.getBlue(rgbinf);
    	bluesup = ImageProcessing.getBlue(rgbsup);
    if ( redinf == blueinf && blueinf == greeninf && greeninf == 0) {
    		System.out.println("test gray inf success");
    } else {
    		System.out.println("test gray inf fail");
    }
    if ( redsup == bluesup && bluesup == greensup && greensup == 255) {
    		System.out.println("test gray sup success");
    } else {
    		System.out.println("test gray sup fail");
    }
  }
    // Don't call this function, it will stoped the program
    public static void testToGray() {
    		int[][]  tab = new int[0][0];
    		double [][] tab2;
    		System.out.println("We give an empty tab, the tets is passed if the programm stop.");
    		tab2 = ImageProcessing.toGray(tab);
    }
    
    /*
     * Tests for Class Collector
     */
    
    public static void testFindNBest() {
    	System.out.println("Test findNBest");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[][] coords = Collector.findNBest(10, t, true);    			
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c + " Val=" + t[r][c]);
    	}    
    }
    
    public static void testFindNBestMAX() {
    	System.out.println("Test findNBestMAX : return 10 elements in decreasing order, end their index on the table");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int[][] coords = Collector.findNBest(10, t, false);    			
    	for (int[] a : coords) {
    		int r = a[0];
    		int c = a[1];
    		System.out.println("Row=" + r + " Col=" + c + " Val=" + t[r][c]);
    	}    
    }
    
    public static void testFindBestMIN() {
    	System.out.println("test findBestMIN returns the minimum value of an array");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int [] coord = Collector.findBest(t, true);
    	int r = coord[0];
    	int c = coord[1];
    	System.out.println("Row : "+r+" Col : "+c+" Minimum = "+t[r][c]);
    	System.out.println("If values printed are 10 and row = 0 col = 2 then the test is valid");
    }
    
    public static void testFindBestMAX() {
    	System.out.println("test findBestMIN returns the minimum value of an array ");
    	double[][] t = new double[][] {{20, 30, 10, 50, 32}, {28, 39, 51, 78, 91}};
    	int [] coord = Collector.findBest(t, false);
    	int r = coord[0];
    	int c = coord[1];
    	System.out.println("Row : "+r+" Col : "+c+" Minimum = "+t[r][c]);
    	System.out.println("If value printed are 91 and row = 1 col = 4 then the test is valid");
    }

    /*
     * Tests for Class DistanceBasedSearch
     */
    
    public static void testDistanceBasedSearch() {
    	System.out.println("Test DistanceBasedSearch");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food);
    	Helper.show(ImageProcessing.matrixToRGBImage(distance, 0, 255), "Distance");
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
    }
    
    public static void testFindCharlie() {
    	System.out.println("Test Charlie avec distanceBasedSearch");
    	int[][] charlie_beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(charlie, charlie_beach);
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], charlie[0].length, charlie.length, charlie_beach);
    	System.out.println(p[0]+ ","+ p[1]);
    	Helper.show(charlie_beach, "Found!");
    }
    
    public static void testImage() {
    	System.out.println("We will trie to display the pattern on the image. When we use the DistanceSearch method we"
    			+ " can se that the pattern is not ALWAYS found. But when we use the SimiliritySearch method we can find the pattern"
    			+ " even if the image is in BnW or everything else."
    			 );
    	
    	System.out.println("Test avec imageDark");
    	int[][] imageDark = Helper.read("images/image-dark.png");
    	int[][] pattern = Helper.read("images/pattern.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(pattern, imageDark);
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], pattern[0].length, pattern.length, imageDark);
    	System.out.println(p[0]+ ","+ p[1]);
    	Helper.show(imageDark, "Dark Not Found!");
    	
    	System.out.println("Test avec imageOriginal");
    	int[][] image = Helper.read("images/image.png");
    	int[][] pattern2 = Helper.read("images/pattern.png");
    	double[][] distance2 = DistanceBasedSearch.distanceMatrix(pattern2, image);
    	int[] p2 = Collector.findBest(distance2, true);
    	Helper.drawBox(p2[0], p2[1], pattern2[0].length, pattern2.length, image);
    	System.out.println(p2[0]+ ","+ p2[1]);
    	Helper.show(image, " Original Found!");
    	
    	System.out.println("Test avec imageLight");
    	int[][] imageLight = Helper.read("images/image-light.png");
    	int[][] pattern3 = Helper.read("images/pattern.png");
    	double[][] distance3 = DistanceBasedSearch.distanceMatrix(pattern3, imageLight);
    	int[] p3 = Collector.findBest(distance3, true);
    	Helper.drawBox(p3[0], p3[1], pattern3[0].length, pattern3.length, imageLight);
    	System.out.println(p3[0]+ ","+ p3[1]);
    	Helper.show(imageLight, "Light Not Found!");
    	
    	System.out.println("The pattern is not find in an image Black our Light");
    }
    
    public static void testWrappingFood() {
    	System.out.println("test wrapping food");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrixStrategy(onions, food, "wrapping");
    	Helper.show(ImageProcessing.matrixToRGBImage(distance, 0, 255), "Distance by WRAPPING");
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found! (WITH WRAPPING)");
    }
    
    public static void testMirroringFood() {
    	System.out.println("test mirroring food");
    	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrixStrategy(onions, food, "mirroring");
    	Helper.show(ImageProcessing.matrixToRGBImage(distance, 0, 255), "Distance by MORRORING");
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found! (WITH MIRRORING)");
    }
    
    public static void testWrappingCharlie() {
    	System.out.println("test wrapping charlie");
    	int[][] charlieb = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrixStrategy(charlie, charlieb, "wrapping");
    	Helper.show(ImageProcessing.matrixToRGBImage(distance, 0, 255), "Distance by WRAPPING");
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], charlie[0].length, charlie.length, charlieb);
    	Helper.show(charlieb, "Found! (WITH WRAPPING)");
    }
    
    public static void testMirroringCharlie() {
    	System.out.println("test mirroring charlie");
    	int[][] charlieb = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrixStrategy(charlie, charlieb, "mirroring");
    	Helper.show(ImageProcessing.matrixToRGBImage(distance, 0, 255), "Distance by MIRRORING");
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], charlie[0].length, charlie.length, charlieb);
    	Helper.show(charlieb, "Found! (WITH MIRRORING)");
    }
    
    
    /*
     * Tests for Class SimilarityBasedSearch
     */

    public static void testSimilarityBasedSearch() {
    	System.out.println("Test SimilarityBasedSearch");
	int[][] food = Helper.read("images/food.png");
    	int[][] onions = Helper.read("images/onions.png");
    	double[][] foodGray = ImageProcessing.toGray(food);
    	double[][] onionsGray = ImageProcessing.toGray(onions);    	
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(onionsGray, foodGray);
    	int[][] best = Collector.findNBest(8, similarity, false);    			
    	for (int[] a : best) {
    		int r = a[0];
    		int c = a[1];
        	Helper.drawBox(r, c, onions[0].length, onions.length, food);
    	}
    	Helper.show(food, "Found again!");    	
    }
    
    public static void findCharlie() {
    	System.out.println("Find Charlie");
	int[][] beach = Helper.read("images/charlie_beach.png");
    	int[][] charlie = Helper.read("images/charlie.png");
    	double[][] beachGray = ImageProcessing.toGray(beach);
    	double[][] charlieGray = ImageProcessing.toGray(charlie);    	

    	System.out.println("Compute Similarity Matrix: expected time about 2 min");
    	double[][] similarity = SimilarityBasedSearch.similarityMatrix(charlieGray, beachGray);

    	System.out.println("Find N Best");
    	int[] best = Collector.findBest(similarity, false);   
    	double max = similarity[best[0]][best[1]];
    	
    	Helper.show(ImageProcessing.matrixToRGBImage(similarity, -1, max), "Similarity");
    	
    	Helper.drawBox(best[0], best[1], charlie[0].length, charlie.length, beach);
    	System.out.println("drawBox at (" + best[0] + "," + best[1] + ")");
    	Helper.show(beach, "Found again!");    	
    }
    
    public static void testFindImage () {
    		System.out.println("Test Find Image : here we will find the pattern using the SimiliratySearch method and se"
    				+ " that we can always find the pattern, independant of the images's seetings");
    	
    	//test the Dark image
    		System.out.println("Find on imageDark");
    		int[][] imageDark = Helper.read("images/image-dark.png");
    		int[][] pattern = Helper.read("images/pattern.png");
    		double[][] imageDarkGray = ImageProcessing.toGray(imageDark);
    		double[][] patternGray = ImageProcessing.toGray(pattern);    	

    		double[][] similarity = SimilarityBasedSearch.similarityMatrix(patternGray, imageDarkGray);

    		int[] best = Collector.findBest(similarity, false);   
    	
    	
    		Helper.drawBox(best[0], best[1], pattern[0].length, pattern.length, imageDark);
    		Helper.show(imageDark, "Dark Found");  
    	
    	//test the classical image
    		System.out.println("Find on imageOriginal");
    		int[][] image = Helper.read("images/image.png");
    		int[][] pattern1 = Helper.read("images/pattern.png");
    		double[][] imageGray = ImageProcessing.toGray(image);
    		double[][] pattern1Gray = ImageProcessing.toGray(pattern1);  
    	
    		double[][] similarity1 = SimilarityBasedSearch.similarityMatrix(pattern1Gray, imageGray);

    		int[] best1 = Collector.findBest(similarity1, false);   
        	
        	
    		Helper.drawBox(best1[0], best1[1], pattern1[0].length, pattern1.length, image);
    		Helper.show(image, "Original Found again!"); 
    	
    	//test the light image
    		System.out.println("Find on imageLight");
    		int[][] imageLight = Helper.read("images/image-light.png");
        	int[][] pattern2 = Helper.read("images/pattern.png");
        	double[][] imageLightGray = ImageProcessing.toGray(imageLight);
        	double[][] pattern2Gray = ImageProcessing.toGray(pattern2);    	

        	double[][] similarity2 = SimilarityBasedSearch.similarityMatrix(pattern2Gray, imageLightGray);

        	int[] best2 = Collector.findBest(similarity2, false);   
        	
        	
        	Helper.drawBox(best2[0], best2[1], pattern2[0].length, pattern2.length, imageLight);
        	Helper.show(imageLight, "Light Found!"); 
        	
        	//Conclusion
        	System.out.println("We observe that with the SimiliritySearch methode, we always find the pattern on the image");
        	
        	
    }
    
    public static void testNCCPatternEqualImage() {     
    	  double[][] pattern = {{ 0,   0, 0 },
    	                       { 0, 255, 0 },
    	                       { 0,   0, 0 }};
    	  double similarity = SimilarityBasedSearch.normalizedCrossCorrelation(0, 0, pattern, pattern);
    	  if (similarity == 1.0) {
    	    System.out.println("PASSED");      
    	  } else {
    	    System.out.println("ERROR: expected value 1.0 but was " + similarity);
    	  }
    	}
    
    public static void testSimilarityPatternEqualImage() {     
    	  double[][] pattern = {{ 0, 255}};
    	  double[][] similarity = SimilarityBasedSearch.similarityMatrix(pattern, pattern);
    	  if (similarity.length == 1) {
    	    if (similarity[0][0] == 1.0) {
    	      System.out.println("PASSED");
    	    } else {
    	      System.out.println("ERROR: expected value 1.0 but was " + similarity[0][0]);       
    	    }
    	  } else {
    	    System.out.println("ERROR: expected length 1 but was " + similarity.length);       
    	  }
    	}
    
    public static void testSimilaritySimple() {
    	  double[][] image = {{ 3, 2, 2, 2 },
    	                      { 0, 3, 0, 0 }};
    	  double[][] pattern = {{ 0, 3, 0}};
    	  double[][] similarity = SimilarityBasedSearch.similarityMatrix(pattern, image);
    	 
    	  if (similarity.length == 2 && similarity[0].length == 2) {
    	    if (similarity[0][0] == -0.5 && similarity[0][1] == -1.0 &&
    	        similarity[1][0] ==  1.0 && similarity[1][1] == -0.5) {
    	      System.out.println("PASSED");
    	    } else {
    	      System.out.println("ERROR: wrong values");  
    	    }
    	  } else {
    	    System.out.println("ERROR: incorrect size");       
    	  }
    	}

}
