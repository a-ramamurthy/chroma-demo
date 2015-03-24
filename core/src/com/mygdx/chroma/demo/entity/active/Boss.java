/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.active;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.chroma.demo.Constants;
import com.mygdx.chroma.demo.screen.GameOverScreen;
import com.mygdx.chroma.demo.screen.ScreenManager;


/**
 * The boss of the region.
 */
public class Boss extends Enemy
{
    private Animation summAnim;
	private Animation attackAnim;
	private Texture defaultSprite;
	public Sprite curSprite;
	public Body body;
	public Fixture fixture;
	private float stateTime=0;
	public boolean isStationary;
	public boolean isAttacking;
	public int hp;
	public BodyDef bd;
	public FixtureDef fd;


	public Boss(float x, float y)
	{
		attackAnim=Constants.generateAnimation("boss-attack.png", 1, 4, Constants.ENTITY_ANIM_SPEED);
		summAnim=Constants.generateAnimation("boss-attack.png", 1, 2, Constants.ENTITY_ANIM_SPEED);
		defaultSprite=new Texture(Gdx.files.internal("boss-default.png"));
		curSprite = new Sprite(defaultSprite);
		curSprite.setPosition(x,y);

		hurt=false;
		hp = 1000;
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
	}
	public void update(Player player)
	{
		if(Math.random()<0.01)
			attack(player);
		if(hp<=0)
			{
		    		curSprite.setRegion(defaultSprite);
		    		hp+=500;
			}
		else if (isAttacking)
		{
			curSprite.setRegion(attackAnim.getKeyFrame(stateTime+=Gdx.graphics.getDeltaTime(), true));  
			if(attackAnim.getKeyFrameIndex(stateTime)==3)
			{
				stateTime=0;
				isAttacking=false;    
			}
		}
	}

	public void attack(Player player)
	{
		isAttacking=true;
		player.getAttacked(10);
		
	}

	public void getAttacked(int dmg)
	{
		if(hp > 0)
		{
			hp-=dmg;
			body.applyForceToCenter(new Vector2(1000000000f, 0), true);
			
		}
		body.applyForceToCenter(new Vector2(1000000000f, 0), true);
	}
}