package assignment1;

import java.util.Map;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */

public class Library {
	
	private Map<Path, Song> aSongs;
	private List<Playlist> aPlaylists;
	private List<Album> aAlbums;
	
	public Library()
	{
		this.aSongs = new HashMap<Path, Song>();
		this.aPlaylists = new ArrayList<Playlist>();
		this.aAlbums = new ArrayList<Album>();
	}
	
	//Internal methods
	
	/* Finds the playlist in the library
	 * @param String pName: the playlist to find
	 * @return int The index of the playlist. If it is not found,
	 * 		   -1 is returned.
	 */
	private int findPlaylist(String pName)
	{
		int i = 0;
		for (Playlist pl: this.aPlaylists)
		{
			if (pl.getName().equals(pName))
			{
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	/* Finds the album in the library
	 * @param String pName: the album to find
	 * @return int The index of the album. If it is not found,
	 * 		   -1 is returned.
	 */
	private int findAlbum(String pName)
	{
		int i = 0;
		for (Album pl: this.aAlbums)
		{
			if (pl.getTitle().equals(pName))
			{
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	/* Checks for dupes in the playlists with the same name.
	 * @param String pName the name of the playlist to check.
	 * @return int The number of copies found.
	 */
	private int checkPlaylistName(String pName)
	{
		int i = 0;
		for (Playlist p : this.aPlaylists)
		{
			String s = p.getName();
			boolean isSame = true;
			for (int j = 0; j < s.length() && j < pName.length(); j++)
			{
				if (s.charAt(j) != pName.charAt(j))
				{
					isSame = false;
					break;
				}
			}
			
			if (isSame)
			{
				if (s.length() == pName.length())
				{
					i++;
				} 
				else if (s.length() > pName.length())
					{
						if (s.length() - pName.length() == 4 && s.charAt(pName.length()) == ' ' &&
								s.charAt(pName.length() +1) == '(' && s.charAt(pName.length() + 3) == ')')
						{
							i++;
						}
					}
			} 
			else {
				continue;
			}
			
		}
		return i;
	}
	
	/* Checks for dupes in the albums with the same name.
	 * @param String pName the name of the album to check.
	 * @return int The number of copies found.
	 */
	private int checkAlbumName(String pTitle)
	{
		int i = 0;
		for (Album a : this.aAlbums)
		{
			String s = a.getTitle();
			boolean isSame = true;
			for (int j = 0; j < s.length() && j < pTitle.length(); j++)
			{
				if (s.charAt(j) != pTitle.charAt(j))
				{
					isSame = false;
					break;
				}
			}
			if (isSame)
			{
				if (s.length() == pTitle.length())
				{
					i++;
				} else if (s.length() > pTitle.length())
					{
						if (s.length() - pTitle.length() == 4 && s.charAt(pTitle.length()) == ' ' &&
								s.charAt(pTitle.length() +1) == '(' && s.charAt(pTitle.length() + 3) == ')')
						{
							i++;
						}
					}
			} else {
				continue;
			}
			
		}
		return i;
	}
	
	/*
	 * Adds the playlist to the library and checks for duplicates.
	 * @param Playlist aPlaylist the playlist to add.
	 */
	private void addPlaylist(Playlist aPlaylist)
	{
		String pName = aPlaylist.getName();
		int i = checkPlaylistName(pName);
		if (i == 0) {
			this.aPlaylists.add(aPlaylist);
		} else {
			aPlaylist.setName(pName + " (" + String.valueOf(i) + ")");
			this.aPlaylists.add(aPlaylist);
		}
	}
	
	/*
	 * Adds the album to the library and checks for duplicates.
	 * @param Album aAlbum the album to add.
	 */
	private void addAlbum(Album pAlbum)
	{
		String pTitle = pAlbum.getTitle();
		int i = checkAlbumName(pTitle);
		if (i == 0) {
			this.aAlbums.add(pAlbum);
		} else {
			pAlbum.changeTitle(pTitle + " (" + String.valueOf(i) + ")");
			this.aAlbums.add(pAlbum);
		};
	}
	
	/*
	 * Creates and adds a new Song to the library
	 * @param Path pPath: path of the song,
	 * @param String pTitle: the title
	 * @param String pArtist: the artist
	 * @param int pTime: the time of the song in seconds.
	 */
	public void addSong(Path pPath, String pTitle, String pArtist, int pTime)
	{
		for (Path p: this.aSongs.keySet())
		{
			if (p.toString().equals(pPath.toString()))
			{
				//handle duplicates case.
				System.out.println("Cannot add song with same path");
				return;
			}
		}
		this.aSongs.put(pPath, new Song(pPath, pTitle, pArtist, pTime));
	}
	
	/*
	 * Creates and adds a new Playlist to the library
	 * @param String pName the name the of the playlist
	 */
	public void createPlaylist(String pName) {
		Playlist newPlaylist = new Playlist(pName);
		addPlaylist(newPlaylist);
	}
	
	/*
	 * Creates and adds a new Album to the library
	 * @param String pName the name of the album
	 */
	public void createAlbum(String aTitle) {
		Album newAlbum = new Album(aTitle);
		addAlbum(newAlbum);
	}
	
	public void createAlbum(String aTitle, String aArtist) {
		Album newAlbum = new Album(aTitle, aArtist);
		addAlbum(newAlbum);
	}
	
	/*
	 * Removes a song from the library.
	 * @param String pPath the path of the song
	 */
	public void removeSong(Path pPath)
	{
		this.aSongs.remove(pPath);
		for (Playlist p : this.aPlaylists)
		{
			p.removeSong(pPath);
		}
		for (Album a: this.aAlbums)
		{
			a.removeSong(pPath);
		}
	}
	
	/*
	 * Removes all invalid songs from the library,
	 * including from the albums and playlists.
	 */
	public void removeInvalid()
	{
		List<Song> songsCopy = new ArrayList<Song>(this.aSongs.values());
		for (Song s: songsCopy)
		{
			if (!s.isValid())
			{
				removeSong(s.getPath());
			}
		}
	}
	
	/*
	 * Removes a playlist from the library.
	 * @param String pName the name of the playlist
	 * @pre a valid playlist name
	 */
	public void removePlaylist(String pName)
	{
		int res = findPlaylist(pName);
		if (res == -1) this.aPlaylists.remove(res);
	}
	
	/*
	 * Removes an album from the library.
	 * @param String pName the name of the album
	 * @pre a valid album name
	 */
	public void removeAlbum(String pName)
	{
		int res = findAlbum(pName);
		if (res == -1) this.aAlbums.remove(res);
	}
	
		
	/*
	 * Get the song from its path.
	 * @param Path pPath the path to song.
	 * @return Song
	 */
	public Song getSong(Path pPath)
	{
		return this.aSongs.get(pPath);
	}
	
	/*
	 * Get all of the songs in the library
	 * @return Map<Path, Song>
	 */
	public Map<Path, Song> getSongs()
	{
		return Collections.unmodifiableMap(this.aSongs);
	}
	
	/*
	 * Get a playlist from the library.
	 * @param String pName the name of the playlist
	 * @return Playlist
	 * @pre a valid playlist name.
	 */
	public Playlist getPlaylist(String pName)
	{
		return this.aPlaylists.get(findPlaylist(pName)).getImmutableCopy();
	}
	
	/*
	 * Get all of the playlists in the library
	 * @return List<Playlist>
	 */
	public List<Playlist> getPlaylists()
	{
		List<Playlist> res = new ArrayList<Playlist>();
		for (Playlist p: this.aPlaylists)
		{
			res.add(p.getImmutableCopy());
		}
		return res;
	}
	
	/*
	 * Get a copy of the albums
	 * @param String pName: the name of the album
	 * @return List<Album>
	 * @pre a valid album name
	 */
	public Album getAlbum(String pName)
	{
		return this.aAlbums.get(findAlbum(pName)).getImmutableCopy();
	}
	
	/*
	 * Get all of the albums in the library
	 * @return List<Album>
	 */
	public List<Album> getAlbums()
	{
		List<Album> res = new ArrayList<Album>();
		for (Album a: this.aAlbums)
		{
			res.add(a.getImmutableCopy());
		}
		return res;
	}
	
	/*
	 * Changes a playlist's name
	 * @param String pName: the playlist's name
	 * @param String pChangeName: the playlist's new name
	 * @pre a valid playlist name.
	 * 
	 * If the playlist could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void changePlaylistName(String pName, String pChangeName)
	{
		int res = findPlaylist(pName);
		if (res == -1) System.out.println("Could not find playlist to change name.");
		else this.aPlaylists.get(res).setName(pChangeName);
	}
	
	/*
	 * Changes an album's name
	 * @param String pName: the albums's name
	 * @param String pChangeName: the albums's new name
	 * @pre a valid album name
	 * 
	 * If the album could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void changeAlbumName(String pName, String pChangeName)
	{
		int res = findAlbum(pName);
		if (res == -1) System.out.println("Could not find album to change name.");
		else this.aAlbums.get(res).changeTitle(pChangeName);

	}
	
	/*
	 * Changes an album's artist
	 * @param String pName: the album's name
	 * @param String pChangeName: the album's new artist
	 * @pre a valid album name
	 * 
	 * If the album could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void changeAlbumArtist(String pName, String pChangeArtistName)
	{
		int res = findAlbum(pName);
		if (res == -1) System.out.println("Could not find album to change artist.");
		else this.aAlbums.get(res).changeArtist(pChangeArtistName);
	}

	/*
	 * Adds a song to a playlist
	 * @param Path pPath: path of the song to add
	 * @param String pName: the name of the playlist to add the song to
	 * @pre a valid playlist name
	 * @pre a valid song path
	 * 
	 * If the playlist could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void addSongToPlaylist(Path pPath, String pName)
	{
		int res = findPlaylist(pName);
		if (res == -1) System.out.println("Could not find playlist to add song.");
		else this.aPlaylists.get(res).addSong(this.aSongs.get(pPath));
	}
	
	/*
	 * Adds a song to an album
	 * @param Path pPath: path of the song to add
	 * @param String pName: the name of the album to add the song to
	 * @param int pTrack: the track number to give to the song
	 * @pre a valid album name
	 * @pre a valid song name
	 * 
	 * If the album could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void addSongToAlbum(Path pPath, String pName, int pTrack)
	{
		int res = findAlbum(pName);
		if (res == -1) System.out.println("Could not find album to add song.");
		this.aAlbums.get(res).addSong(this.aSongs.get(pPath), pTrack);
	}
	
	/*
	 * Removes a song from a playlist
	 * @param Path pPath: path of the song to remove
	 * @param String pName: the name of the playlist to remove the song to
	 * @pre a valid album name
	 * @pre a valid song name
	 * 
	 * If the album could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void removeSongFromPlaylist(Path pPath, String pName)
	{
		int res = findPlaylist(pName);
		if (res == -1) System.out.println("Could not find playlist to remove song.");
		this.aPlaylists.get(res).removeSong(pPath);
	}
	
	/*
	 * Removes a song from an album
	 * @param Path pPath: path of the song to remove
	 * @param String pName: the name of the album to remove the song to
	 * @pre a valid album name
	 * @pre a valid song name
	 * 
	 * If the album could not be found, an error message is printed and 
	 * no action is performed.
	 */
	public void removeSongFromAlbum(Path pPath, String pName)
	{
		int res = findAlbum(pName);
		if (res == -1) System.out.println("Could not find album to remove song.");
		this.aAlbums.get(res).removeSong(pPath);
	}
	
}

