package assignment1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */


public class Tags {
	
	private ExpectedTags aExpected;
	private Map<OptionalTagKeys, OptionalTag> aOptional;
	private Map<String, CustomTag> aCustom;
	
	
	public Tags(ExpectedTags pExpected)
	{
		this.aExpected = pExpected;
		this.aOptional = new HashMap<OptionalTagKeys, OptionalTag>();
		this.aCustom = new HashMap<String, CustomTag>();
	}
	
	/*
	 * @return Map<OptionalTagKeys, OptionalTag> The optional tags
	 */
	public Map<OptionalTagKeys, OptionalTag> getOptionalTags()
	{
		return Collections.unmodifiableMap(this.aOptional);
	}
	
	/*
	 * @return Map<String, CustomTag> The custom tags
	 */
	public Map<String, CustomTag> getCustomTags()
	{
		return Collections.unmodifiableMap(this.aCustom);
	}
	
	/*
	 * @return String the title of expected tags
	 */
	public String getTitle()
	{
		return this.aExpected.getTitle();
	}
	
	/*
	 * @return String the artist of expected tags
	 */
	public String getArtist()
	{
		return this.aExpected.getArtist();
	}
	
	/*
	 * @return int the time of expected tags in seconds.
	 */
	public int getTime()
	{
		return this.aExpected.getTime();
	}
	
	/*
	 * Gets an optional tag from the key.
	 * @param OptionalTagkeys
	 */
	public OptionalTag getTag(OptionalTagKeys pKey)
	{
		return this.aOptional.get(pKey);
	}
	
	/*
	 * Gets a custom tag from the key.
	 * @param String the key
	 */
	public CustomTag getTag(String pKey)
	{
		return this.aCustom.get(pKey);
	}
	
	/*
	 * Sets the expectedtag title.
	 * @param String title
	 */
	public void setTitle(String pTitle)
	{
		aExpected.setTitle(pTitle);
	}
	
	/*
	 * Sets the expectedtag artist
	 * @param String artist
	 */
	public void setArtist(String pArtist)
	{
		aExpected.setTitle(pArtist);
	}
	
	/*
	 * Sets the expectedtag time
	 * @param int time in seconds.
	 */
	public void setTime(int pTime)
	{
		aExpected.setTime(pTime);
	}
	
	/*
	 * Inserts an optional tag for the song. Previous tags may be
	 * overwritten.
	 * @param OptionalTag the tag to put in.
	 */
	public void putOptional(OptionalTag pOptionalTag)
	{
		this.aOptional.put(pOptionalTag.getKey(), pOptionalTag);
	}
	
	/*
	 * Inserts a custom tag for the song. Previous tags may be
	 * overwritten.
	 * @param CustomTag the tag to put in.
	 */
	public void putCustom(CustomTag pExpectedTag)
	{
		this.aCustom.put(pExpectedTag.getKey(), pExpectedTag);
	}
	
	/*
	 * Removes the tag specified by the key.
	 * @param OptionalTagkeys pOt
	 */
	public void removeOptional(OptionalTagKeys pKey)
	{
		this.aOptional.remove(pKey);
	}
	
	/*
	 * Removes the tag specified by the key.
	 * @param String pCt
	 */
	public void removeCustom(String pKey)
	{
		this.aCustom.remove(pKey);
	}
	
}
