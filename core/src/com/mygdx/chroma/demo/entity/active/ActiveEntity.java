/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.active;

import com.mygdx.chroma.demo.entity.Entity;


/**
 * Represents a moving abstract object in the game world.
 */
public abstract class ActiveEntity extends Entity
{
	private float dirX, dirY;
	
	/**
	 * Moves the entity as it's meant to move by default.
	 */
	public void move()
	{
		
	}
	
	/**
	 * Moves the entity in the <dirX, dirY> direction by delta amount.
	 * @param dirX
	 * @param dirY
	 * @param delta
	 */
	public void move(float delta)
	{
		
	}
	
	/**
	 * Sets the direction of the entity.
	 * @param dirX
	 * @param dirY
	 */
	public void setDirection(float dirX, float dirY)
	{
		
	}
	
	
}