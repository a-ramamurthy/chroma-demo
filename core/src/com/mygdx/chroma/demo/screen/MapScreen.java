/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.chroma.demo.Constants;
import com.mygdx.chroma.demo.State;

/**
 * Represents the game map screen.
 */
public class MapScreen extends Screen
{
    public Box2DDebugRenderer debugRenderer;
    public OrthographicCamera camera;
    private Texture tile;
    private Texture foundtile;
    private Texture hiddentile;

    private int mapStartWidth=Constants.WIDTH/4;
    private int mapStartHeight=Constants.HEIGHT/4;
    private float tileWidth=Constants.HEIGHT/5;
    private float tileHeight=Constants.HEIGHT/5;

    private int currRow;
    private int currCol;

    private boolean encounter;
    private int cont;

    public Texture[][] map;
    public Texture[][] lightMap;
    public Texture[][] darkMap;

   
    private Sprite player;

    @Override
    public void create()
    {

	map=new Texture[Constants.GRID_SIZE][Constants.GRID_SIZE];
	darkMap=new Texture[Constants.GRID_SIZE][Constants.GRID_SIZE];
	lightMap=new Texture[Constants.GRID_SIZE][Constants.GRID_SIZE];

	

	tile=new Texture("tile.png");
	hiddentile=new Texture("hiddentile.png");
	foundtile=new Texture("foundtile.png");

	player=new Sprite(new Texture("map-sprite.png"));
	for(int row=0; row<Constants.GRID_SIZE; row++)
	    for(int col=0; col<Constants.GRID_SIZE; col++)
	    {
		map[row][col]=tile;
		darkMap[row][col]=hiddentile;
		lightMap[row][col]=foundtile;

		State.hiddenMap[row][col]=true;
		State.traveledMap[row][col]=false;
		State.foundMap[row][col]=false;

	    }
	currRow=0;
	currCol=0;
	playerMoved(0, 0);
	debugRenderer=new Box2DDebugRenderer();
	camera=new OrthographicCamera(30, 30*(Constants.HEIGHT/Constants.WIDTH));
	camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
	camera.update();

    }

    public void playerMoved(int row, int col)
    {
	State.hiddenMap[row][col]=false;
	//traveledMap[row][col] = true;

	if(row-1>=0) State.foundMap[row-1][col]=true;
	if(row+1<Constants.GRID_SIZE) State.foundMap[row+1][col]=true;
	if(col-1>=0) State.foundMap[row][col-1]=true;
	if(col+1<Constants.GRID_SIZE) State.foundMap[row][col+1]=true;

    }

    public void movePlayer()
    {
	if(Gdx.input.isKeyJustPressed(Keys.UP)&&!encounter)
	{
	    if(currCol+1<Constants.GRID_SIZE)

	    currCol+=1;
	}
	if(Gdx.input.isKeyJustPressed(Keys.DOWN)&&!encounter)
	{
	    if(currCol-1>=0)

	    currCol-=1;
	}
	if(Gdx.input.isKeyJustPressed(Keys.LEFT)&&!encounter)
	{
	    if(currRow-1>=0)

	    currRow-=1;
	}
	if(Gdx.input.isKeyJustPressed(Keys.RIGHT)&&!encounter)
	{
	    if(currRow+1<Constants.GRID_SIZE)

	    currRow+=1;
	}
	if(State.traveledMap[currRow][currCol]==false)
	    encounter=true;
	else
	    encounter=false;
	playerMoved(currRow, currCol);
	cont=0;
	if(Gdx.input.isKeyPressed(Keys.ENTER))
	{
	    
	    
	    State.traveledMap[currRow][currCol]=true;
	}
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(SpriteBatch batch)
    {
	camera.update();
	//Color c = new Color(batch.getColor());
	batch.begin();
	movePlayer();
	for(int row=0; row<Constants.GRID_SIZE; row++)
	    for(int col=0; col<Constants.GRID_SIZE; col++)
	    {
		if(State.traveledMap[row][col]==true)
		    batch.draw(map[row][col], mapStartWidth+row*tileWidth, mapStartHeight+col*tileHeight, tileWidth, tileHeight); //sprite, xloc,yloc,xsize,ysize
		else if(State.foundMap[row][col]==true)
		    batch.draw(lightMap[row][col], mapStartWidth+row*tileWidth, mapStartHeight+col*tileHeight, tileWidth, tileHeight); //sprite, xloc,yloc,xsize,ysize
		else
		    batch.draw(darkMap[row][col], mapStartWidth+row*tileWidth, mapStartHeight+col*tileHeight, tileWidth, tileHeight); //sprite, xloc,yloc,xsize,ysize
	    }
	batch.draw(player, currRow*tileWidth+mapStartWidth+tileWidth/4, currCol*tileHeight+mapStartHeight+tileWidth/4);
	batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
	// TODO Auto-generated method stub
    }

    @Override
    public void dispose()
    {
	// TODO Auto-generated method stub
    }

    @Override
    public void pause()
    {
	// TODO Auto-generated method stub
    }

    @Override
    public void resume()
    {
	// TODO Auto-generated method stub
    }
}