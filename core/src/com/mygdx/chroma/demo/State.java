/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo;

import java.io.File;

/**
 * Contains all the memorized information necessary to represent a single playthrough at a single moment.
 * Is initialized once per playthrough, and is updated continuously throughout. It can also be loaded from a save file.
 */
public class State 
{
	/** The default constructor. Creates a randomized game in its start state. */
	public State()
	{
		
	}//end new state constructor
	
	/**
	 * The constructor that creates a game based on a save file.
	 * @param saveFile a .csav file that contains all information about a game at a single moment.
	 */
	public State(File saveFile)
	{
		
	}//end saved state constructor
	
	/**
	 * Updates a certain field in the current game state to fit a specified value.
	 * @param field the integer code of the field to update
	 * @param newValue the new value to set the field to
	 */
	public void update(int field, Object newValue)
	{
		
	}//end update
	
	/**
	 * Gets the value of a certain field.
	 * @param field the integer code of the field to access
	 * @return the value of that field
	 */
	public Object get(int field)
	{
		return null;
	}//end get
	
	/**
	 * Writes or overwrites a new save file with a particular name.
	 * @param fileName the name of the file to create or update
	 */
	public void writeSaveFile(String fileName)
	{
		
	}//end writeSaveFile

}//end State.java
