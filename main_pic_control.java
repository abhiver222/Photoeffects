
/*A class to delegate each effect request.
 * The process method is called whenever the user selects a menu item.
 * 
 * @author averma11
 */
public class Effects {
	/**
	 * Returns a new image (2D array) based on the command and the source parameters.
	 * This method delegates all of the work to PixelEffects class
	 * @param cmd - the command to execute
	 * @param source - the primary image source (not changed)
	 * @param background - the secondary (background) image (not changed)
	 * @return the new image or null if the command failed.
	 */
	public static int[][] process(String cmd, int[][] source, int[][] background) {
		// The basic form of this method is one long if-else if else if....
		// To turn a string into a particular method call
		
		//if(cmd is ___ call some PixelEffects.____ method )
		//else if ... (repeat for all strings
		
		// See the end of Photoscoop.java for a list strings
		// For example the Stenography ones should be ...
		//e.g.  if(cmd.equals("hide")) return Stenography.hide(source,background);
		//e.g. else if(cmd.equals("extract")) return Stenography.extract(source);
		// And add the others for PixelEffects
		
		// hint: for some commands the string is slightly different than the method name
		// hint: remember to use string.equals not == to compare strings
		// This message and the exception should not occur if you match all possible command strings
		//"half", "rotate", "flip", "mirror","redeye","funky" , "resize", "merge", "key"
		if(cmd.equals("half"))
			return (PixelEffects.half(source));
		else if(cmd.equals("rotate"))
			return PixelEffects.rotateLeft(source);
		else if(cmd.equals("flip"))
			return PixelEffects.flip(source);
		else if(cmd.equals("mirror"))
			return PixelEffects.mirror(source);
		else if(cmd.equals("redeye"))
			return PixelEffects.redeye(source, background);
		else if(cmd.equals("funky"))
			return PixelEffects.funky(source, background);
		else if(cmd.equals("resize"))
			return PixelEffects.resize(source, background);
		else if(cmd.equals("merge"))
			return PixelEffects.merge(source, background);
		else if(cmd.equals("key"))
			return PixelEffects.chromaKey(source, background);
		else if(cmd.equals("hide"))
			return Stenography.hide(background, source);
		else if(cmd.equals("extract"))
				return Stenography.extract(source);
		
		
		System.out.println("Todo: Implement Effects.process("+cmd+")");
		throw new RuntimeException("Unknown command:"+cmd);
	}

} // end class
