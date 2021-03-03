package ie.gmit.dip;

import java.util.*;
import java.io.*;

/**
 * @author April Day
 * @version V0.1
 * @since 2020
 * 
 * The class Runner contains the <i>main</i> method. 
 * It is a dependency on the class Show. A change in the Show Class will
 * force a change in the Runner Class. The Runner Class 
 * starts the program but exists independently of the other classes.
*/
public class Runner {
	
	/**
	 * Main method for the packaged programme. 
	 * 
	 * @param args that are the code to make up the programme
	 * @throws Exception as there are methods that use the Java IO API
	 */
	public static void main(String[] args) throws Exception {

		/**
		 * Instantiates the class Show that has a dependency relationship 
		 * with the Runner Class.
		 */
		Show s = new Show();
		
		/**
		 * Calls the method that starts the programme and calls other methods.
		 */
		s.start();

	}

}