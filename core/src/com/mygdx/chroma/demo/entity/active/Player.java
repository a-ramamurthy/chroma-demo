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
    
    public Player(float x, float y)
    {
	//Entity Graphics
	runningAnim=Constants.generateAnimation("player-running.png", 1, 4, Constants.ENTITY_ANIM_SPEED);
	curSprite=new Sprite(runningAnim.getKeyFrame(0));
	curSprite.setPosition(x, y);
		
	//Entity Mechanics
	bd = new BodyDef();
	bd.type = BodyDef.BodyType.DynamicBody;
	bd.position.set(new Vector2(x, y));
	bd.fixedRotation=true;
	PolygonShape hitbox=new PolygonShape();
	hitbox.setAsBox(curSprite.getWidth()/2, curSprite.getHeight()/2);
	fd = new FixtureDef();
	fd.shape=hitbox;
	fd.density=0.5f;
	fd.restitution=0;
	fd.friction=100f;
	
	
	
	dir=Constants.RIGHT;
	isJumping=false;
	isRunning=false;
	isStationary=true;
	
    }
    
    public void jump()
	{
		if (!isJumping)
		{
			body.applyLinearImpulse(new Vector2(0, 500000), new Vector2(body.getPosition().x, body.getPosition().y), true);
			isJumping=true;
		}
	}

	public void fallFast()
	{
	    body.applyLinearImpulse(new Vector2(0, -500000), new Vector2(body.getPosition().x, body.getPosition().y), true);
	}
	
	public void move(boolean direction)
	{
	    
	    this.dir=direction;
	    isRunning=true;
	    
	    if(dir==Constants.RIGHT)	
		body.setLinearVelocity(new Vector2(500000, 0));
	    else
		body.setLinearVelocity(new Vector2(-500000, 0));
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
	  
	}

	/**
	 * 
	 */
	public void attack()
	{
	    // TODO Auto-generated method stub
	    
	}
}