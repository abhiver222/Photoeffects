
/* Manipulates RGB values
 * @author averma11
 */

public class RGBUtilities {

/**
 * @param rgb the encoded color int
 * @return the red component (0..255)
 */
	public static int toRed(int rgb) {
		int i = ((rgb>>16) & 0xff);
		return (i);
	}

	public static int toGreen(int rgb) {
		int i = ((rgb>>8) & 0xff);
		return i;
	}

	public static int toBlue(int rgb) {
		int i = ((rgb) & 0xff);
		return i; 
	}

	/**
	 * 
	 * @param r the red component (0..255)
	 * @param g the green component (0..255)
	 * @param b the blue component (0..255)
	 * @return a single integer representation the rgb color (8 bits per component) rrggbb
	 */
	static int toRGB(int r, int g, int b) {
		return ((r<<16) | (g<<8) | b); // FIX THIS
	}

}
