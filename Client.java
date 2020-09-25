package assignment1;

import static assignment1.OptionalTagKeys.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Part of COMP303 Activity 1 - Music Library program.
 * 
 * @author William Tang, Marco Caniglia, Xintian Xu, Karla González, Alexander Mackay,
 * 		   Alexander Valentin, Charles Roux, Hantao Zeng, Nghi Huyuh
 */



public class Client {

	public static void main(String[] args) {
		Library library1 = new Library();
		
		// Create songs in the library --> test out our methods
		Path p1 = Paths.get("abs/path/to/song.mp3");
		Path p2 = Paths.get("abs/path/other/tune.wav");
		Path p3 = Paths.get("abs/path/to/song2.mp3");
		Path p4 = Paths.get("abs/path/other/tune2.wav");
		Path p5 = Paths.get("abs/path/to/song3.mp3");
		Path p6 = Paths.get("abs/path/other/tune3.wav");
		Path p7 = Paths.get("abs/path/to/song4.mp3");
		Path p8 = Paths.get("abs/path/other/tune4.wav");
		
		//add the songs to the library we created
		library1.addSong(p1, "Many Men", "50 Cent", 400);
		library1.addSong(p2, "Goosebumps", "Travis Scott", 300);
		library1.addSong(p3, "Gifted", "Cordae", 350);
		library1.addSong(p4, "Wrong", "That Kid LAROI", 289);
		library1.addSong(p5, "When it Rains it Pours", "50 Cent", 560);
		library1.addSong(p6, "We Paid", "Lil Baby", 366);
		library1.addSong(p7, "3 Headed Goat", "Lil Durk", 400);
		library1.addSong(p8, "Conversations", "Juice WRLD", 608);
	
		
		// Add some tags to the second song.
		Song s2 = library1.getSong(p2);
		s2.putOptionalTag(new OptionalTag(GENRE, "rap"));
		s2.putOptionalTag(new OptionalTag(BPM, "65"));
		s2.putOptionalTag(new OptionalTag(RATING, "8"));
		s2.putCustomTag(new CustomTag("favorite", "True"));
		
		// Create an album.
		String albumName1 = "Birds in The Trap Sing McKnight";
		library1.createAlbum(albumName1, "Travis Scott");
		
		// Add the songs to the album.
		library1.addSongToAlbum(p2, albumName1, 9);
		library1.addSongToAlbum(p1, albumName1, 1);
		
		// Create a playlist.
		String playlist1 = "Marco's Playlist";
		library1.createPlaylist(playlist1);
		
		//test out duplicate names
		library1.createAlbum(albumName1);
		library1.createPlaylist(playlist1);
		library1.getPlaylist(playlist1 + " (1)");
		library1.getAlbum(albumName1 + " (1)");
		System.out.println("These are the albums and playlists in the library:");
		for (Playlist p: library1.getPlaylists())
		{
			System.out.println(p.getName());
		}
		for (Album a: library1.getAlbums())
		{
			System.out.println(a.getTitle());
		}
		System.out.println();
		
		// Add the songs to the playlist.
		library1.addSongToPlaylist(p1, playlist1);
		library1.addSongToPlaylist(p1, playlist1);
		library1.addSongToPlaylist(p2, playlist1);
		library1.addSongToPlaylist(p3, playlist1);
		library1.addSongToPlaylist(p4, playlist1);
		library1.addSongToPlaylist(p5, playlist1);
		library1.addSongToPlaylist(p6, playlist1);
		library1.addSongToPlaylist(p7, playlist1);
		library1.addSongToPlaylist(p8, playlist1);
		
		
		// Retrieve the playlist and album as immutable copies to view their songs.
		Playlist pl = library1.getPlaylist(playlist1);
		Album al = library1.getAlbum(albumName1);
		
		//Print out contents of a Playlist
		System.out.println("Playlist Marco's Playlist:");
		for (Song s : pl.getSongs())
		{
			System.out.println(s.toString());
		}
		
		// Aggregated info from a playlist.
		System.out.println("\nMarco's Playlist contains " + pl.getNumberofSongs() + " songs");
		
		System.out.println("Marco's Playlist contains songs by: " + pl.getListofArtist());
		
		System.out.println("Marco's Playlist is: " + pl.getTotalPlayingTime() + " seconds long");
		
		System.out.println("Average bpm of all songs in Marco\'s Playlist is: " + pl.getAverageBPM());
		
		
		// Contents of an album (includes track number):
		System.out.println("\nSongs in album " + albumName1);
		for (Entry<Song, Integer> pair : al.getSongs().entrySet())
		{
			System.out.println("Track(" + pair.getValue() + ") " + pair.getKey().toString());
		}
		
		// Remove a song from the library
		library1.removeSong(p1);
		
		// We can attempt to remove it again, but nothing bad will happen.
		library1.removeSong(p1);
		
		// Trying to remove a song from a copy of the album will throw an error
		// Since the collection is unmodifiable.
		//al.removeSong(p2);
		
		// We can, however, change Song tags anywhere we can access a Song.
		pl.getSongs().get(0).putCustomTag(new CustomTag("Banger", "True"));
		
		System.out.println("\nThese are the custom tags for " + library1.getSong(p2).toString());
		Map<String, CustomTag> songTags = library1.getSong(p2).getCustomTags();
		for (Entry<String, CustomTag> pair : songTags.entrySet())
		{
			System.out.println("Tag: " + pair.getKey() + ", Value: " + pair.getValue().getValue());
		}
				
		// Removing invalid songs from the library
		library1.removeInvalid();
		
		// Trying to remove/add songs will throw java.lang.UnsupportedOperationException
		// Since the Tags copy is unmodifiable. It must be done through the actual Song instance.
		//songTags.remove("Banger");
		
		// Changing names.
		library1.changeAlbumName(albumName1 + " (1)", "The better album");
		library1.changeAlbumArtist(albumName1, "My Artist!!!");
		library1.changePlaylistName(playlist1, "Marco's Better Playlist");
		
		// Changing names into a name that already exists will also work (puts a (1) at the end).
		library1.changeAlbumName("The better album", albumName1);
	}

}
