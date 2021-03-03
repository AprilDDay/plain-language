package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This is an abstract class that is inherited by the Show Class. 
 * It has a defined method (but did not have to have a defined method)
 * that can be implemented by its child class with or without modification.
 * 
 * Any class that declares it extends this class must have a constructor and 
 * can call on its methods. If the method was not defined here and if
 * the class was a concrete class, it would inherit
 * the responsibility to define the method. 
 */
public abstract class Database {

	/**
	 * Private map. 
	 */
	private Map<String, String> map = new HashMap<>();
	
	/**
	 * Private input String to be defined by the concrete class that implements
	 * this method. 
	 */
	private String input;

	/**
	 * Puts words into the data structure by reading from a text using the BufferedReader 
	 * class provided by Java. It puts the words as a key/value pair.
	 * 
	 * @param input is the input to be added to the data structure
	 * 
	 * @throws Exception is the exception thrown from the Java IO API, if any
	 */
	public void putWords(String input) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));
		String word = new String();
		while ((word = br.readLine()) != null) {
			map.put(word, word);
		}
		br.close();//close BufferedReader
	}

}
