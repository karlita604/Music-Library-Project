package assignment1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */


public class Album{
	private Map<Song, Integer> aAlbum;
	private String aTitle;
	private String aArtist;

	/* Constructors
	 * 
	 * @pre albumTitle != null
	 * The artistName can be null, in which case we set aArtist = "Various Artists"
	 * The constructor can be called with only one arg of type String, in which case
	 * aTitle = arg, and aArtist = "Unknown Artist"
	 */
	public Album(String albumTitle, String artistName) {
	    
		assert albumTitle != null;
		this.aTitle = albumTitle;
		if (artistName == null) {
			this.aArtist = "Various Artists";
		} else {
			this.aArtist = artistName;
		}
		this.aAlbum = new HashMap<Song, Integer>();
	}
	
	public Album(String albumTitle)
	{
		assert albumTitle != null;
		this.aTitle = albumTitle;
		this.aArtist = "Unknown Artist";
		this.aAlbum = new HashMap<Song, Integer>();
	}
	
	public Album(Album pAlbum)
	{
		this.aAlbum = Collections.unmodifiableMap(pAlbum.aAlbum);
		this.aTitle = pAlbum.aTitle;
		this.aArtist = pAlbum.aArtist;
	}
	
	/*
	 * @return Album a copy of the album but with its Song map made unmodifiable.
	 */
	public Album getImmutableCopy()
	{
		return new Album(this);
	}
	
	/*
	 * @return String the title of the album
	 */
	public String getTitle()
	{
		return this.aTitle;
	}
	
	/*
	 * @return String the artist of the album
	 */
	public String getArtist()
	{
		return this.aArtist;
	}
	
	/*
	 * @return Map<Song, Integer> a unmodifiable copy of the Songs.
	 */
	public Map<Song, Integer> getSongs()
	{
		return Collections.unmodifiableMap(this.aAlbum);
	}
	
	/* Changes the album title
	 * @param String albumTitle: the new title to change
	 */
	public void changeTitle(String albumTitle)
	{
	    this.aTitle = albumTitle;
	}
	
	/* Changes the album artist
	 * @param String artist: the new artist to change
	 */
	public void changeArtist(String artist) {
		this.aArtist = artist;
	}

	/* Add a song to the Album
	 * @param Song pSong: the song to add
	 * @param int index: the track number
	 * @pre index >= 0
	 */
	public void addSong(Song pSong, int index) {
		assert index >= 0;
		this.aAlbum.put(pSong, index);
	}
	
	/*
	 * @return int the number of songs in the album
	 */
	public int numberOfSongs() {
		return this.aAlbum.size();
	}

	/*
	 * @return int the total length of the album in seconds
	 */
	public int timeLengthOfAlbum() {
		int length = 0;
		for (Song cur : this.aAlbum.keySet()) {
			length += cur.getTime();
		}
		return length;
	}

	/* Removes a song from the Album
	 * @param Path pPath: the path of the song
	 */
	public void removeSong(Path pPath) {
		List<Song> songsCopy = new ArrayList<Song>(this.aAlbum.keySet());
		for (Song s: songsCopy)
		{
			if (s.getPath().equals(pPath))
			{
				this.aAlbum.remove(s);
			}

		}
		
	}


}
