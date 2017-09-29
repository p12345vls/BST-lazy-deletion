
package lazyTrees;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * The Class FoothillTunesStore has user menu for add, remove, print hard and soft,
 * show minimum and maximum values of SongEntry.It contains main()
 */
public class FoothillTunesStore {
	
	/** The Constant JASONFILE. */
	static final String JASONFILE = "resources/music_genre_subset.json";
	
	/** The Constant SAMPLES_FILE. */
	static final String SAMPLES_FILE = "resources/sample_songs.txt";
	
	/** The scanner. */
	private Scanner scann;
	
	/** The title. */
	private String title;

	/** The print object. */
	PrintObject<Item> printObject = new PrintObject<Item>();
	
	/** The tree. */
	private LazySearchTree<Item> tree;

	/**
	 * Instantiates a new foothill tunes store.
	 */
	public FoothillTunesStore() {
		tree = new LazySearchTree<Item>();
		this.title = "";
		scann = new Scanner(System.in);
		SongEntry.tunes(JASONFILE);
	}

	/**
	 * Menu choice.
	 *
	 * @param choice the choice
	 */
	public void menuChoice(int choice) {

		switch (choice) {
		case 1:
			printHard();
			break;
		case 2:
			printSoft();
			break;
		case 3:
			addSong();
			break;
		case 4:
			deleteSong();
			break;
		case 5:
			showMin();
			break;
		case 6:
			showMax();
			break;
		case 7:
			showSamples();
			break;
		case 8:
			System.out.println("System Exit.");
			System.exit(0);
			break;
		default:
			break;
		}

	}

	/**
	 * Shows sample SongEntry.
	 */
	public void showSamples() {

		ArrayList<String> mostPlayed = SongEntry.samples(SAMPLES_FILE);
		for (String s : mostPlayed) {
			System.out.println(s);
		}

	}

	/**
	 * Menu.
	 *
	 * @return the choice of the user
	 */
	public int menu() {

		System.out.println("\n___________________________");
		System.out.println("\nHere is what you can do :\n");
		String[] menu = { "Show hard Play list", "Show soft Play list",
				"Add a song", "Remove a Song", "Show first in the list",
				"Show last in the list", "Show Sample SongEntry", "  -EXIT-" };

		for (int i = 0; i < menu.length; i++) {
			System.out.println((i + 1) + ". " + menu[i]);
		}

		return getUserInput();
	}

	/**
	 * Gets the user input.
	 *
	 * @return the user input
	 */
	public int getUserInput() {
		System.out.print("\nEnter your choice: ");
		int i = 0;
		boolean done = false;
		while (!done) {
			try {
				i = scann.nextInt();
				done = true;
			} catch (InputMismatchException e) {
				scann.nextLine();
				System.out.print("Please enter a number from 1 to 8 :");
			}

		}
		return i;
	}

	/**
	 * Adds the song.
	 */
	public void addSong() {

		System.out.println("Please enter the title:");
		scann = new Scanner(System.in);
		title = scann.nextLine();

		Item ttl = new Item(title);

		ttl = SongEntry.findSong(ttl);
		if (ttl != null) {
			tree.insert(ttl);
			System.out.println(ttl + " succesfully inserted ");
		} else {
			System.err.println("(" + title + ") "
					+ " Does not exist, please enter another song");
		}
	}

	/**
	 * Deletes song.
	 */
	public void deleteSong() {
		System.out.print("Please enter the name of the song:");
		scann = new Scanner(System.in);
		title = scann.nextLine();
		Item ttl = new Item(title);
		ttl = SongEntry.findSong(ttl);
		if (ttl != null) {
			tree.remove(ttl);
			System.out.println(ttl + " succesfully removed ");
		} else {
			System.err.println("(" + title + ") "
					+ " Does not exist, please enter another song to remove");
		}

	}

	/**
	 * Prints hard.
	 */
	public void printHard() {
		System.out.print("\nShowing hard: ");
		tree.traverseHard(printObject);

	}

	/**
	 * Prints soft.
	 */
	public void printSoft() {
		System.out.print("\nShowing soft: ");
		tree.traverseSoft(printObject);
	}

	/**
	 * Shows min.
	 */
	public void showMin() {
		try{
			System.out.println("\nFirst in the list:" + tree.findMin());
			}catch(NoSuchElementException e){
				System.err.println("All soft deleted, Nothing to show..");
			}
	}

	/**
	 * Shows max.
	 */
	public void showMax() {
		try{
		System.out.println("\nLast in the list:" + tree.findMax());
		}catch(NoSuchElementException e){
			System.err.println("All soft deleted, Nothing to show..");
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out
				.println("Welcome! We have over 50000 Songs in FoothillTunes store!");
		int choice = 0;
		FoothillTunesStore store = new FoothillTunesStore();
		do {
			choice = store.menu();
			store.menuChoice(choice);
		} while (choice > 0 && choice <= 8);

	}

}
