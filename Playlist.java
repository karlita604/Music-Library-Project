package assignment1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */


public class Playlist {
	
	private String aName;
	private List<Song> aSongs;
	
	public Playlist(String pName)
	{
		this.aSongs = new ArrayList<Song>();
		this.aName = pName;
	}
	
	public Playlist()
	{
		this.aSongs = new ArrayList<Song>();
		this.aName = "Untitled Playlist";
	}
	
	public Playlist(Playlist pPlaylist)
	{
		this.aName = pPlaylist.aName;
		this.aSongs = Collections.unmodifiableList(pPlaylist.aSongs);
	}
	
	/*
	 * @return a copy of the Playlist class with its Songs List made unmodifiable
	 */
	public Playlist getImmutableCopy()
	{
		return new Playlist(this);
	}
	
	/*
	 * @return String Returns the name of the playlist
	 */
	public String getName()
	{
		return this.aName;
	}
	
	/* Changes the name of the playlist
	 * 
	 * @param pName The name to change to
	 */
	public void setName(String pName)
	{
		this.aName = pName;
	}
	
	
	/* Adds a song to the playlist
	 * 
	 * @param pSong The song to add
	 */
	public void addSong(Song pSong)
	{
		this.aSongs.add(pSong);
	}
	
	
	/* Removes a song from the playlist
	 * 
	 * @param Path pPath: the path of the song to remove
	 */
	public void removeSong(Path pPath)
	{
		List<Song> songsCopy = new ArrayList<Song>(this.aSongs);
		int i = 0;
		for (Song s: songsCopy)
		{
			if (s.getPath().equals(pPath))
			{
				this.aSongs.remove(i);
			}
			i++;
		}
	}
	
	/*
	 * @return List<Song> a copy of the songs in this playlist
	 */
	public List<Song> getSongs()
	{
		return Collections.unmodifiableList(this.aSongs);
	}
	
	
	/*
	 * @return int The number of songs in the playlist.
	 */
	public int getNumberofSongs()
	{
		return this.aSongs.size();
	}
	
	

	/*
	 * @return int The total playing time of the playlist.
	 */
	public int getTotalPlayingTime()
	{
		int i = 0;
		for (Song pSong : this.aSongs)
		{
			i += pSong.getTime();
		}
		return i;
	}
	
	/*
	 * @return int the average BPM of the playlist.
	 */
	public int getAverageBPM()
	{
		int totalBPM = 0;
		int count = 0;
		for(Song pSong : this.aSongs) {
			Map<OptionalTagKeys, OptionalTag> optionalTag = pSong.getOptionalTags();
			OptionalTag bpm = optionalTag.get(OptionalTagKeys.BPM);
			if (bpm != null)
			{
				totalBPM += Integer.parseInt(bpm.getValue());
				count++;
			}
		}
		
		return (totalBPM/count);
	}
	
	/* Checks if an artist is in a playlist
	 * @param String pArtist the name of an artist
	 * @return boolean
	 */
	public boolean isArtistInPlaylist(String pArtist) 
	{
		for(Song pSong : this.aSongs) {
			if(pSong.getArtist().equals(pArtist)) return true;
		}
		return false;
	}
	
	/*
	 * @return List<String> A list of all artists in the playlist as Strings.
	 */
	public List<String> getListofArtist()
	{
		List<String> allArtist = new ArrayList<String>();
		
		for(Song pSong : this.aSongs) {
			String pArtist = pSong.getArtist();
			
			boolean exists = false;
			for(int i=0; i<allArtist.size(); i++) {
				if(pArtist.equals(allArtist.get(i))) {
					exists = true;
				}
			}
			if(exists==false) allArtist.add(pArtist);
		}
		return allArtist;
	}
	
	/*
	 * @return int The average rating in of Songs in the playlist.
	 */
	public int getAverageRating()
	{
		int rating = 0;
		int count = 0;
		for(Song pSong : this.aSongs) {
			Map<OptionalTagKeys, OptionalTag> optionalTag = pSong.getOptionalTags();
			OptionalTag t = optionalTag.get(OptionalTagKeys.RATING);
			if (t != null)
			{
				rating += Integer.parseInt(t.getValue());
				count++;
			}
		}
		
		return (rating/count);
	}
	
	/*
	 * @return String the most frequent genre found in the songs of the playlist
	 */
	public String getMostFrequentGenre()
	{
		Map<String, Integer> g = new HashMap<String, Integer>();
		String res = "";
		int max = -1;
		Integer prev = 0;
		
		for(Song pSong : this.aSongs) {
			Map<OptionalTagKeys, OptionalTag> optionalTag = pSong.getOptionalTags();
			OptionalTag t = optionalTag.get(OptionalTagKeys.GENRE);
			if (t != null)
			{
				prev = g.get(t.getValue());
				if (prev != null) g.put(t.getValue(), 1 + prev);
				else g.put(t.getValue(),  1);
			}
		}
		
		for (Entry<String, Integer> pair: g.entrySet())
		{
			if (pair.getValue() > max)
			{
				max = pair.getValue();
				res = pair.getKey();
			}
		}
		
		return res;
		
	}
	
	
		
}

