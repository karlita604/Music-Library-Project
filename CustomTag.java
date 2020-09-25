package assignment1;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */


public class CustomTag {
	private String aKey;
	private String aValue;
	
	public CustomTag(String pKey, String pValue)
	{
		this.aKey = pKey;
		this.aValue = pValue;
	}
	
	/*
	 * @return ExpectedTagKeys the key associated with the tag
	 */
	public String getKey()
	{
		return this.aKey;
	}

	
	/*
	 * @return String the tag value
	 */
	public String getValue()
	{
		return this.aValue;
	}
}

