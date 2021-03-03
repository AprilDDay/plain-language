package ie.gmit.dip;

/**
 * Class that has a dependency relationship with the Show Class. 
 * Contains the method that has the user interface, telling the user
 * information about the programme.
 *
 */
public class UserInterface {

	/**
	 * Loads the user interface so that the user knows that the program is loaded
	 * and can see information about the programme.
	 */
	public void load() {

		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*             Text Simplifier V0.1                *");
		System.out.println("*       (AKA Orwellian Language Compliance)       *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
	}

}
