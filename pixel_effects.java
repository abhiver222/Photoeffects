

/* A class to implement the various pixel effects.
 * @author averma11
 */
public class PixelEffects {

	/** Copies the source image to a new 2D integer image */
	public static int[][] copy(int[][] source) {
		// Create a NEW 2D integer array and copy the colors across
		// See redeye code below
		int a = source.length;
		int b= source[0].length;
		int k;
		int[][] nev = new int[a][b];
		for(int i=0;i<a;i++)
			for(int j=0;j<b;j++){
				k=source[i][j];
				nev[i][j]=k;
			}
		return nev;
	}
	/*
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
		
		int[][] resized=new int[newWidth][newHeight];
		int reswid = source.length;
		int reshig = source[0].length;
		int newwid = newWidth;
		int newhig = newHeight;
		for(int i=0;i<newwid;i++)
			for(int j=0;j<newhig;j++){

				int srcx=(int)((i/(double)newwid)*reswid);
				
				int srcy=(int)((j/(double)newhig)*reshig);

				resized[i][j]=source[srcx][srcy];
			}
		
		return resized;
		
	}

	/**
	 * Half the size of the image.
	 */
	public static int[][] half(int[][] source) {
		int[][] halved = resize(source,source.length/2,source[0].length/2);
		return halved; // Fix Me
	}
	
	/*
	 * 
	 * @param source
	 * the source image
	 * @param reference
	 * @return the resized image
	 */
	public static int[][] resize(int[][] source, int[][] reference) {
		int reswid=reference.length;
		int reshig=reference[0].length;
		int[][] resized = resize(source,reswid,reshig);
		
		return resized; // Fix Me
	}

	/** Flip the image vertically */
	public static int[][] flip(int[][] source) {
		int reswid = source.length;
		int reshig = source[0].length;
		int[][] flipped = new int[reswid][reshig];
		
		for(int i=0;i<reswid;i++){
			for(int j=0;j<reshig;j++){
				flipped[i][j]=source[i][reshig-j-1];
			}
			
		}
		
		return flipped;
	}

	/** Reverse the image horizontally */
	public static int[][] mirror(int[][] source) { 
		int reswid = source.length;
		int reshig = source[0].length;
		int[][] flipped = new int[reswid][reshig];
		
		for(int i=0;i<reswid;i++){
			for(int j =0;j<reshig;j++)
				flipped[i][j]=source[reswid-i-1][j];
		}
		
		
		return flipped;
	}

	/** Rotate the image */
	public static int[][] rotateLeft(int[][] source) {
		int reswid = source[0].length;
		int reshig = source.length;
		int[][] rotated = new int[reswid][reshig];
		
		for(int i=0;i<reswid;i++)
			for(int j=0;j<reshig;j++){
				rotated[i][j]=source[reshig-j-1][i];
			}
		
		return rotated;
	}

	/** Merge the red,blue,green components from two images */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		int[][] merged  = new int[sourceA.length][sourceA[0].length]; 
		for(int i=0;i<sourceA.length;i++)
			for(int j=0;j<sourceA[0].length;j++){
				int red1 = ((sourceA[i][j]>>16)& 0xff);
				int red2 = ((sourceB[i][j]>>16)& 0xff);
				int green1 = ((sourceA[i][j]>>8)& 0xff);
				int green2 = ((sourceB[i][j]>>8)& 0xff);
				int blue1 = ((sourceA[i][j])& 0xff);
				int blue2 = ((sourceB[i][j])& 0xff);
				
				red1/=2;red2/=2;green1/=2;green2/=2;blue1/=2;blue2/=2;
				
				int red = ((red1+red2));
				int green = ((green1+green2));
				int blue = ((blue1+blue2));
				
				merged[i][j]=((red<<16)|(green<<8)|blue);
				
			}
		return merged;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		// If the image has a different size than the background use the
		// resize() method
		// create an image the same as the background size.
		int[][] nmg;
		int reswid = backImage.length;
		int reshig = backImage[0].length;
		int[][] chroma = new int[reswid][reshig];
		
		nmg = resize(foreImage, backImage); 
		for(int i=0;i<reswid;i++)
			for(int j=0;j<reshig;j++){
				if(((foreImage[i][j]>>8) & 0xff) == 0)
					chroma[i][j] = foreImage[i][j];
				else
					chroma[i][j] = backImage[i][j];
					
			}
		
		return chroma;
	}

	/** Removes "redeye" caused by a camera flash */
	public static int[][] redeye(int[][] source, int[][] sourceB) {

		int width = source.length, height = source[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				if (red > 4 * Math.max(green, blue) && red > 64)
					red = green = blue = 0;
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}

		return result;
	}

	
	public static int[][] funky(int[][] source, int[][] sourceB) {
		
		// Does not ask for any user input and returns a new 2D array

		int reswid = source.length;
		int reshig = source[0].length;
		int[][] work = sourceB;
		work = resize(work, source);
		int[][] funky = new int[reswid][reshig];		
		for(int i=0;i<reswid;i++)
			for(int j=0;j<reshig;j++){
				if((source[i][j]>50))
					funky[i][j]=work[i][j];
				else
					funky[i][j] = source[i][j];
				if(source[i][j]<10)
					funky[i][j]=work[i][j];
				else
					funky[i][j] = source[i][j];
			}
		
		return funky;
	}
}
