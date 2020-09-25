package assignment1;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static assignment1.OptionalTagKeys.*;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
*/


public class Song {
	
	private Path aSong;
	private String aFormat;
	private Tags aTags;
	
	/*
	 * Constructs Song given a path to the song file
	 * 
	 * @param pStringPath The string representing the path to the song file, will be converted to a Path
	 * @pre new File(pSong.toString()).isFile(); 
	 * @pre pTags != null
	 * @pre isValidFormat(getExtension(pSong))
	 */
	public Song(Path pSong, String pTitle, String pArtist, int pTime) 
	{
		assert new File(pSong.toString()).isFile();
		assert isValidFormat(getExtension(pSong));
		assert pTitle != null;
		assert pArtist != null;
		assert pTime >= 0;
		aSong = pSong;
		aFormat = getExtension(pSong);
		
		ExpectedTags expected = new ExpectedTags(pTitle, pArtist, pTime);
		aTags = new Tags(expected);
	}
	
	/*
	 * get the string of the extension of the song file
	 * @param Path pSong 
	 * @return string with extension of the song file
	 */
	private String getExtension(Path pSong)
	{
		String stringPath = pSong.toString();
		if ( stringPath.lastIndexOf(".") != -1 && stringPath.lastIndexOf(".") != 0)
		{
			return stringPath.substring(stringPath.lastIndexOf(".") + 1);
		}
		else
		{
			return "";
		}
	}
	
	/*
	 * check if extension of song file is a valid format (one of the enums in ValidSongFormat)
	 * @param String pExtension
	 * @pre pExtension != null
	 * @return boolean whether it is a valid format
	 */
	private boolean isValidFormat(String pExtension)
	{
		assert pExtension != null;
		for (ValidSongFormats format: ValidSongFormats.values())
		{
			if (pExtension == format.getValidSongFormat())
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Check if a song is invalid (does not exist or not a file)
	 *  
	 * @param pSong the Path to the song file that will be checked
	 * @pre pSong != null
	 */
	public boolean isValid()
	{
		assert this.aSong != null;
		return new File(this.aSong.toString()).isFile();
	}
	
	/*
	 * @return String the format of the song
	 */
	public String getFormat()
	{
		return this.aFormat;
	}
	
	/*
	 * @return Path the path of the song
	 */
	public Path getPath()
	{
		return this.aSong;
	}
	
	/*
	 * @return String The song object as a string.
	 */
	public String toString()
	{
		return ("[Song] " + this.aTags.getTitle() + " by " + this.aTags.getArtist());
	}
	
	/*
	 * @return Map<OptionalTagKeys, OptionalTag> The optional tags
	 */
	public Map<OptionalTagKeys, OptionalTag> getOptionalTags()
	{
		return this.aTags.getOptionalTags();
	}
	
	/*
	 * @return Map<String, CustomTag> The custom tags
	 */
	public Map<String, CustomTag> getCustomTags()
	{
		return this.aTags.getCustomTags();
	}
	
	/*
	 * @return String the Artist of the song
	 */
	public String getArtist()
	{
		return this.aTags.getArtist();
	}
	
	/*
	 * @return String the Title of the song
	 */
	public String getTitle()
	{
		return this.aTags.getTitle();
	}
	
	/*
	 * @return int the Time of the song
	 */
	public int getTime()
	{
		return this.aTags.getTime();
	}
	
	/*
	 * Inserts an optional tag for the song. Previous tags may be
	 * overwritten.
	 * @param OptionalTag the tag to put in.
	 */
	public void putOptionalTag(OptionalTag pOt)
	{
		this.aTags.putOptional(pOt);
	}
	
	/*
	 * Inserts a custom tag for the song. Previous tags may be
	 * overwritten.
	 * @param CustomTag the tag to put in.
	 */
	public void putCustomTag(CustomTag pCt)
	{
		this.aTags.putCustom(pCt);
	}
	
	/*
	 * Removes the tag specified by the key.
	 * @param OptionalTagkeys pOt
	 */
	public void removeOptionalTag(OptionalTagKeys pOt)
	{
		this.aTags.removeOptional(pOt);
	}
	
	/*
	 * Removes the tag specified by the key.
	 * @param String pCt
	 */
	public void removeCustomTag(String pCt)
	{
		this.aTags.removeCustom(pCt);
	}

	
}