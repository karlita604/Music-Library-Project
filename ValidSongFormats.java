package assignment1;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */

public enum ValidSongFormats {
	/*
	 * A list of the acceptable audio formats for Song
	 */
	MP3("mp3"), 
	M4A("m4a"), 
	WMA("wma"), 
	WAV("wav"), 
	FLAC("flac");
	
	private String aValidSongFormat;

	/*
	 * creates a valid format enum with its string representation
	 * @param pFormat
	 */
	ValidSongFormats(String pValidSongFormat)
	{
		aValidSongFormat = pValidSongFormat;
	}
	
	/*
	 * gets string type of a valid format enum
	 * @return the format in its string representation
	 */
	public String getValidSongFormat()
	{
		return aValidSongFormat;
	}
	
}

