package assignment1;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */


public class ExpectedTags {
	private String aTitle;
	private String aArtist;
	private int aTime;
	
	public ExpectedTags(String pTitle, String pArtist, int pTime)
	{
		this.aTitle = pTitle;
		this.aArtist = pArtist;
		this.aTime = pTime;
	}
	
	/*
	 * @return String the title
	 */
	public String getTitle()
	{
		return this.aTitle;
	}
	
	/*
	 * @return String the artist
	 */
	public String getArtist()
	{
		return this.aArtist;
	}
	
	/*
	 * @return int the time in seconds
	 */
	public int getTime()
	{
		return this.aTime;
	}
	
	/*
	 * Sets the title.
	 * @param String title.
	 */
	public void setTitle(String pTitle)
	{
		this.aTitle = pTitle;
	}
	
	/*
	 * Sets the artist.
	 * @param String artist.
	 */
	public void setArtist(String pArtist)
	{
		this.aTitle = pArtist;
	}
	
	/*
	 * Sets the time
	 * @param int the time in seconds.
	 */
	public void setTime(int pTime)
	{
		this.aTime = pTime;
	}
	
}
