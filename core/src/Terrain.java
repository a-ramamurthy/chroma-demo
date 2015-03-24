/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.inactive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.chroma.demo.Constants;

/**
 * The treasure chests found in the game levels.
 */
public class Terrain extends InactiveEntity
{
	float x, y;
	public Body body;
	public BodyDef bd;
	public Fixture fixture;
	public FixtureDef fdTop, fdLeft, fdRight;
	public Sprite image;

	public Terrain(float x, float y, String imageName)
	{
		image=new Sprite(new Texture(Gdx.files.internal(imageName)));
		bd=new BodyDef();
		bd.type=BodyDef.BodyType.StaticBody;
		bd.position.set(new Vector2(((x-Constants.WIDTH/Constants.PIXELS_PER_METER)/2), (y-Constants.HEIGHT/Constants.PIXELS_PER_METER/4)));
		fdTop=new FixtureDef();
		EdgeShape top=new EdgeShape();
		top.set(new Vector2(x, y), new Vector2((x+128)*2, y));
		fdTop.shape=top;
		fdLeft=new FixtureDef();
		EdgeShape left=new EdgeShape();
		left.set(new Vector2(x, y), new Vector2(x, y-image.getHeight()));
		fdLeft.shape=left;
		fdRight=new FixtureDef();
		EdgeShape right=new EdgeShape();
		right.set(new Vector2(x+image.getWidth(), y), new Vector2(x+image.getWidth(), y-image.getHeight()));
		fdRight.shape=right;
	}
	
	public void update()
	{
		image.setPosition(body.getPosition().x*2, body.getPosition().y*1.6f);
	}

}