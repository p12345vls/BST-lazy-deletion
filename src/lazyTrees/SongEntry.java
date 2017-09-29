package lazyTrees;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The Class Songs reads and inserts songs read from a jason file to the
 * LazySearchTree.It stores also sample songs read from a txt file.
 * 
 */
public class SongEntry extends Item {
	/**
	 * LazySearchTree with SongEntry objects
	 */
	private static LazySearchTree<SongEntry> tree;

	/**
	 * Instantiates a new song.
	 * 
	 * @param song the song
	 */
	public SongEntry(String song) {
		super(song);
		
	}

	/** The song. */
	private String song;

	/** The tree. */

	/**
	 * reads the jason file and inserts the data into the BST tree
	 * 
	 * @param file
	 *            the jasonfile
	 *@return tree the tree with all the songs inserted            
	 */
	public static LazySearchTree<SongEntry> tunes(String file) {

		tree = new LazySearchTree<SongEntry>();

		try {
			FileReader fileReader = new FileReader(file);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

			JSONArray allSongs = (JSONArray) jsonObject.get("songs");

			Iterator<?> iterator = allSongs.iterator();

			while (iterator.hasNext()) {
				JSONObject currentJson = (JSONObject) iterator.next();

				tree.insert(new SongEntry(currentJson.get("title").toString()));

			}
		} // attempt to parse the input file
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tree;
	}

	/**
	 * Finds song.
	 * 
	 * @param song
	 *            the song
	 * @return the song
	 */
	public static Item findSong(Item song) {
		try {
			return tree.find(song);
		} catch (NoSuchElementException e) {
			// System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * reads the Sample songs from a txt file.
	 * 
	 * @param file
	 *            the file
	 * @return the array list with sample songs
	 */
	public static ArrayList<String> samples(String file) {
		ArrayList<String> sampleSongs = new ArrayList<>();

		BufferedReader fileReader = null;
		try {

			String line = "";

			fileReader = new BufferedReader(new FileReader(file));

			while ((line = fileReader.readLine()) != null) {

				sampleSongs.add(line);

			}
			fileReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error reading from file");
		}
		return sampleSongs;

	}

}