/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.active;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.chroma.demo.entity.Entity;


/**
 * Represents a moving abstract object in the game world.
 */
public abstract class ActiveEntity extends Entity
{
	
    public boolean hurt;
    public float invincibility;

}