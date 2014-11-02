
// @author averma11
 
public class Stenography {

	/**
	 * Hides one image inside another using bit operations. Be sure to save
	 * hidden images as 'something.png' - otherwise the jpg compression will
	 * tend to destroy the lowest bit information.
	 * 
	 * @param mainImage
	 *            - the decoy image
	 * @param secretImage
	 *            - the secret image
	 * @return a new image with the secret image resized and embedded inside the
	 *         other image
	 * 
	 */
	public static int[][] hide(int[][] mainImage, int[][] secretImage) {
		int width = mainImage.length, height = mainImage[0].length;
		int[][] resizedSecret = PixelEffects.resize(secretImage, mainImage);
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int secretRGB = resizedSecret[i][j];
				int sRed = RGBUtilities.toRed(secretRGB);
				int sGreen = RGBUtilities.toGreen(secretRGB);
				int sBlue = RGBUtilities.toBlue(secretRGB);

				// main decoy image should be something unremarkable
				int rgb = mainImage[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);

				red = (red & 0xf0) | (sRed >> 4);
				blue = (blue & 0xf0) | (sBlue >> 4);
				green = (green & 0xf0) | (sGreen >> 4);
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}
		return result;
	}

	public static int[][] extract(int[][] source) {
		int width = source.length, height = source[0].length;

		int[][] result = new int[width][height];

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);

				red = (((red & 0x0f) << 4) | 0x08);
				blue = (((blue & 0x0f) << 4) | 0x08);
				green = (((green & 0x0f) << 4) | 0x08);
				
				// I get the secret value from the image, then I take the average value
				// if I have a hexadecimal value between 50(decimal 80) and 60(decimal 96), I will take
				// the average value which is 58(decimal 88) so that i get the average between the two
				// hence "or'ing" with 0x08

				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}

		
	}

}
