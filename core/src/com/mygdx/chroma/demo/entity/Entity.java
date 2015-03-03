/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Represents an abstract object in the game world.
 */
public abstract class Entity 
{
	
	private float x, y;
	private boolean visibilty;
	
	/**
	 * Places an entity at the position (x, y).
	 * @param x the x-coordinate of the entity's place
	 * @param y the y-coordinate of the entity's place
	 */
	public void place(int x, int y)
	{
		
	}
	
	/**
	 * Deletes the entity.
	 */
	public void dispose()
	{
		
	}
	
	/**
	 * Sets the sprite of the entity.
	 * @param sprite the image of the entity.
	 */
	public void setSprite(Sprite sprite)
	{
		
	}
	
	/**
	 * Sets the visibility of the entity's sprite.
	 * @param visible true for show, false for hide
	 */
	public void setVisibility(boolean visible)
	{
		
	}
	
	
}

