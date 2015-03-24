/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.active;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.chroma.demo.Constants;
import com.mygdx.chroma.demo.State;

/**
 * Represents the player avatar.
 */
public class Player extends ActiveEntity
{
    private Animation runningAnim;
    public Sprite curSprite;
    public Body body;
    public Fixture fixture;
    private float stateTime=0;
    private boolean isJumping;
    public boolean isRunning;
    public boolean isStationary;
    public boolean dir;
    public BodyDef bd;
    public FixtureDef fd;
    FixtureDef box;
    final short PHYSICS_ENTITY = 0x1;    // 0001
	final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    
    public Player(float x, float y)
    {
	//Entity Graphics
	runningAnim=Constants.generateAnimation("player-running.png", 1, 4, Constants.ENTITY_ANIM_SPEED);
	curSprite=new Sprite(runningAnim.getKeyFrame(0));
	curSprite.setPosition(x, y);
	//Entity Mechanics
	bd = new BodyDef();
	bd.type = BodyDef.BodyType.DynamicBody;
	bd.position.set((curSprite.getX() + curSprite.getWidth()/2)/Constants.PIXELS_PER_METER,(curSprite.getY())/Constants.PIXELS_PER_METER);
	bd.fixedRotation=true;
	PolygonShape hitbox=new PolygonShape();
	hitbox.setAsBox(curSprite.getWidth()/2/Constants.PIXELS_PER_METER, curSprite.getHeight()/2/Constants.PIXELS_PER_METER);
	fd = new FixtureDef();
	fd.shape=hitbox;
	fd.density=0.5f;
	fd.restitution=0;
	fd.friction=100f;
	stateTime=0f;
	fd.filter.categoryBits = PHYSICS_ENTITY;
	fd.filter.maskBits = PHYSICS_ENTITY|WORLD_ENTITY;
	//bd=fd;
	
	
	dir=Constants.RIGHT;
	isJumping=false;
	isRunning=false;
	isStationary=true;
	
    }
    
    public void jump()
	{
		if (!isJumping)
		{
			body.setLinearVelocity(body.getLinearVelocity().x, 500000f+body.getLinearVelocity().y);
			isJumping=true;
		}
	}

	public void fallFast()
	{
		body.setLinearVelocity(body.getLinearVelocity().x, -500000f+body.getLinearVelocity().y);
	}
	
	public void move(boolean direction)
	{
	    
	    this.dir=direction;
	    isRunning=true;
	    
	    if(dir==Constants.RIGHT)	
		body.setLinearVelocity(new Vector2(500000, Constants.GRAVITY.y*100));
	    else
		body.setLinearVelocity(new Vector2(-500000, Constants.GRAVITY.y*100));
	}

	public void update()
	{
	   if(body.getLinearVelocity().y==0)
	       isJumping=false;
	   if(body.getLinearVelocity().x==0)
	       isRunning=false;
	    if(isJumping)
		curSprite.setRegion(runningAnim.getKeyFrame(0));
	    else if (isRunning)
	    	curSprite.setRegion(runningAnim.getKeyFrame(stateTime+=Gdx.graphics.getDeltaTime(), true));
	    else if(isStationary)
		curSprite.setRegion(runningAnim.getKeyFrame(0));
	    body.setUserData(curSprite);
	    curSprite.setPosition(body.getPosition().x, body.getPosition().y);
	  
	}

	/**
	 * 
	 */
	public void attack()
	{
	    // TODO Auto-generated method stub
	    
	}
}