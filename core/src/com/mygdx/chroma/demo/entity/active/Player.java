/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.active;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
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
    private Animation runningAnim, attackAnim;
    public Sprite curSprite;
    public Body body;
    public Fixture fixture;
    private float stateTime=0;
    private boolean isJumping;
    public boolean isRunning;
    public boolean isStationary;
    public boolean isAttacking;
    public boolean dir;
    public int hp;
    public BodyDef bd;
    public FixtureDef fd;
    FixtureDef box;
    final short PHYSICS_ENTITY = 0x1;    // 0001
	final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
	
	public Sprite sword;
	public Fixture swordF;
	public FixtureDef sfd;
    
    public Player(float x, float y)
    {
	//Entity Graphics
	runningAnim=Constants.generateAnimation("player-running.png", 1, 4, Constants.ENTITY_ANIM_SPEED);
	attackAnim=Constants.generateAnimation("player-full.png", 1, 4, Constants.ENTITY_ANIM_SPEED);
	curSprite=new Sprite(runningAnim.getKeyFrame(0));
	curSprite.setPosition(x, y);
	sword=new Sprite(new Texture(Gdx.files.internal("sword.png")));
	//Entity Mechanics
	hp=100;
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


	sfd=new FixtureDef();
	PolygonShape swordHitbox=new PolygonShape();
	swordHitbox.setAsBox(curSprite.getWidth()/2/Constants.PIXELS_PER_METER, curSprite.getHeight()/2/Constants.PIXELS_PER_METER, new Vector2(0,0), 0f);
	sfd.shape=swordHitbox;
	
	
	dir=Constants.RIGHT;
	isJumping=false;
	isRunning=false;
	isStationary=true;
	
    }
    
    

	public void move(boolean direction)
	{
	    
	    this.dir=direction;
	    isRunning=true;
	    
	    if(dir==Constants.RIGHT)	
		body.setLinearVelocity(new Vector2(5000000, 0f));
	    else
		body.setLinearVelocity(new Vector2(-5000000, 0f));
	}

	public void update()
	{
	   if(body.getLinearVelocity().y==0)
	       isJumping=false;
	   if(body.getLinearVelocity().x==0)
	       isRunning=false;
	    if(isJumping)
		curSprite.setRegion(runningAnim.getKeyFrame(0));
	     else if (isAttacking)
	    {
		curSprite.setRegion(attackAnim.getKeyFrame(stateTime+=Gdx.graphics.getDeltaTime(), true));
		if(dir==Constants.RIGHT)
		    sword.rotate(-10f);
		else
		    sword.rotate(10f);
		if(attackAnim.getKeyFrameIndex(stateTime)==3)
		{
		    stateTime=0;
		    isAttacking=false;
		    
		}
	    }
	     else if (isRunning)
	    	curSprite.setRegion(runningAnim.getKeyFrame(stateTime+=Gdx.graphics.getDeltaTime(), true));
	   
	    else if(isStationary)
		curSprite.setRegion(runningAnim.getKeyFrame(0));

	    curSprite.setPosition(body.getPosition().x, body.getPosition().y);
	  
	}

	public void attack(Boss boss)
	{
	    isAttacking=true;
	    stateTime=0;
	    if(dir==Constants.RIGHT)
		sword.setRotation(90);
	    else
		sword.setRotation(-90);
	    boss.getAttacked(100);
	   	  
	}
	
	public void getAttacked(int dmg)
	{
	    if(dir==Constants.LEFT)
		body.applyForceToCenter(new Vector2(1000000000f, 0), true);
	    else
		body.applyForceToCenter(new Vector2(-1000000000f, 0), true);
	    hp-=dmg;
	    
	}
}