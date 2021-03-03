package ie.gmit.dip;

import java.io.*;
import java.util.*;

/**
 * The Show Class is a concrete class. It has specialisation inheritance from the abstract class 
 * Database. The Show Class also has specification inheritance from the 
 * interface Files. The Show Class "IS-A" database. As it is a concrete
 * class, it must implement and inherit all methods from the parent classes
 * and interfaces.
*/
public class Show extends Database implements Files {

	/**
	 * This is a constructor for the parent/super class. All 
	 * child classes must have as the first constructor in the class
	 * a constructor inherited from the parent class.
	 * 
	 *  See Database
	 */
	public Show() {
		
	}

	/**
	 * This is a scanner to allow the user to input information. 
	 */
	private static Scanner scanner;
	
	/**
	 * This is a string. 
	 */
	private static String userInput = new String();

	/**
	 * 
	 */
	private static boolean fill = false;

	private boolean keepRunning = true;

	/**
	 * This is a simple String array. It is set to private to prevent others 
	 * from know how the data structure works.
	 */
	private String[] words;
	private String googleWord;

	/**
	 * The data structure is set to private to prevent others 
	 * from knowing how the data structure works. 
	 * This is a HashMap. A hash map organises key/value pairs with a
	 * hash code. 
	 */
	private Map<String, String> map = new HashMap<>();
	private Set<String> set = new HashSet<>();

	/**
	 * Starts the program. 
	 */
	public void start() {
		
		/**
		 * The scanner allows the user to input the words. It is part
		 * of the <b>Scanner</b> class provided by Java.
		 */
		scanner = new Scanner(System.in);
		
		/**
		 * This instantiates a class called UserInterface that has a 
		 * dependency relationship with Show. If the UserInterface Class 
		 * changes, for example, any changes to the load() method,
		 * that may force a change in the Show Class.  
		 */
		UserInterface userInterface = new UserInterface();
		
		/**
		 * Loads a user interface for the user to know that the program has
		 * loaded and what the program is.
		 */
		userInterface.load();
		
		/**
		 * The while-loop is set at always true (i.e. as an <b>infinite loop</b>
		 * to allow the user to continue
		 * to input information.
		 */

		while (keepRunning) {
			/**
			 * Instantiates this class.
			 */
			Show run = new Show();
			
			/**
			 * This is a try/catch block surrounds these
			 * methods as these methods throw exceptions from the input/output
			 * stream. 
			 * These methods are handled.
			 */
			try {
				/**
				 * Creates the set that is used to determine what Google 
				 * words, if any, from the word list will be matched as values
				 * to the words/synonyms from the Moby Thesaurus.
				 */
				run.createSet();
				
				/**
				 * Puts the words from the Moby Thesaurus as keys to the Google
				 * Words set as values into the HashMap data structure.
				 */
				run.initialise();
				/**
				 * Puts words from the Google list into the map. 
				 * 
				 * @param Files from the <i>interface</i> Files to designate 
				 * and to make concrete the String input from the abstract class
				 * Database.
				 * 
				 * The putWords() method is inherited from the parent class.
				 * It is called on here without any @Override method.
				 */				
				run.putWords(Files.googleWordFile);

				/**
				 * Catch block for the exceptions, if any, thrown by the input/output
				 * from the Buffered readers.
				 */
			} catch (Exception e) {
				/**
				 * Prints the errors, if any.
				 */
				e.printStackTrace();
			}

			/**
			 * Prints to let the user know to enter text.
			 */
			System.out.print("Enter Text>");

			/**
			 * Gets user's input from the next line and reads it in 
			 * as a String.
			 */
			userInput = scanner.nextLine().toString();

			/**
			 * Swaps the user's input with the paired Google word. If there 
			 * are no paired Google words, it returns the word. The Google 
			 * word itself is an identify reference, i.e. it refers to itself.
			 * 
			 * @param user input to swap with matched Google words on the list, if any
			 * 
			 */
			run.getGoogleEquivalent(userInput);

			/**
			 * Colours from enum to print out results of the swap
			 */
			System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
			System.out.println(ConsoleColour.RESET);
		}
	}

	/**
	 * Creates the set of words (Google words) that will be used to 
	 * determine whether there is a synonym that matches to words in the 
	 * Moby Thesaurus. It reads in the line from the Google word list gotten from
	 * the Files interface using a BufferedReader and then creates strings for  
	 * the line as long as the line is not null.
	 * 
	 * @throws Exception from the BufferedReader and Input stream reader thta are part 
	 * of the Java IO API.
	 */	
	public void createSet() throws Exception {

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(Files.googleWordFile))));
		String line = new String();

		while ((line = br.readLine()) != null) {
			set.add(line);
		}
		br.close();//close BufferedReader
	}

	/**
	 * Puts the words from the Moby Thesaurus into the data structure if
	 * the words are matched to words on the Google words list. It does this
	 * for each word in the list of synonyms for the word if one word matches
	 * to the Google word. 
	 * 
	 * @throws Exception for the BufferedReader while reading in the file from 
	 * the Files interface
	 */
	public void initialise() throws Exception {

		String line = new String();
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(Files.mobyThesaurus2File))));

		String word = new String();
		googleWord = null;

		while ((line = br2.readLine()) != null) {
			words = line.split(",");
			for (String w : words) {
				if (set.contains(w)) {
					fill = true;
					googleWord = w;
					addAll(words, googleWord);
					if (set.contains(w)) {
						googleWord = w;
						map.put(word, googleWord);
					}
					break;
				}
			}
		}
	}

	/**
	 * Puts words in the data structure for all the words that are synonyms
	 * for a word in the Moby Thesaurus. 
	 * 
	 * @param words (String array) to add as the key in the key/value pair, iterating 
	 * for each word in the String array
	 * @param googleWord to add as the value in the key/value pair that will be returned 
	 * if the user enters the paired word/key 
	 */
	private void addAll(String[] words, String googleWord) {
		for (String word : words) {
			map.put(word, googleWord);
		}
	}

	/**
	 * Searches for the word entered by the user for the matching Google word, if any,
	 * and swaps the word if there is a value or returns the word, such as
	 * Galway, if the word that was entered does not have a Google word value. 
	 * Takes in the String, creates a string array, searches for each string in the string array
	 * in the data structure and determines whether there is a value to swap. 
	 * Puts the string array back into one long string with spaces and returns the output.
	 *  
	 * @param userInput taken from user to be determined whether there is a swap possible
	 * @return String whether or not a match was found or a swap made
	 */
	public String getGoogleEquivalent(String userInput) {

		String[] temps = userInput.split(" ");// this works
		String output = new String();
		for (int i = 0; i < temps.length; i++) {
			if (map.containsKey(temps[i])) {
				temps[i] = map.get(temps[i]);
			} else {
				temps[i] = temps[i];
			}
		}
		StringBuffer outNow = new StringBuffer();
		for (int i = 0; i < temps.length; i++) {
			outNow.append(temps[i] + " ");
		}
		System.out.print(outNow);
		return output;
	}

}
