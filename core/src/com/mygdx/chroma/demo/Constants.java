package com.mygdx.chroma.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Stores all constant information for the game. *
 */
public class Constants 
{

	/** The number of squares in each column and row of the world map. */
	public static final int GRID_SIZE=5;
	/** The width of the game screen in pixels. */
	public static final int WIDTH = 1280;
	/** The height of the game screen in pixels. */
	public static final int HEIGHT=720;
	
	public static final float PIXELS_PER_METER = 100f;
	public static final float METERS_PER_PIXEL = 1/PIXELS_PER_METER;
	
	
	public static final boolean DEBUG_ON=true;
	
	public static final Vector2 GRAVITY=new Vector2(0,-100f);
	
	public static final float WORLD_STEP=1/60f;
	
	public static final float ENTITY_ANIM_SPEED=0.25f;
	
	public static final float PLAYER_MASS=800;
	
	public static final float TERRAIN_VARIANCE=50f;
	
	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;
	
	
	
	//TOOLS
	 public static Animation generateAnimation(String spriteSheetName, int rows, int cols, float animationSpeed)
	    {
		Texture tempTexture = new Texture(Gdx.files.internal(spriteSheetName));
		TextureRegion[][] tempArr2D = TextureRegion.split(tempTexture, tempTexture.getWidth()/cols, tempTexture.getHeight()/rows);
		TextureRegion[] tempArr1D = new TextureRegion[rows*cols];
		int index=0;
		for(int r=0; r<rows; r++)
		    for(int c=0; c<cols; c++)
			tempArr1D[index++] = tempArr2D[r][c];
		return new Animation(animationSpeed, tempArr1D);
	    }
}
