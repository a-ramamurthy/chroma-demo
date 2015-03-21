/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.chroma.demo.Constants;

/**
 * Represents the screen that appears during a game level.
 */
public class FightScreen extends Screen
{
	public World world;
	public Box2DDebugRenderer debugRenderer;
	public OrthographicCamera camera;
	private Sprite sprite;
	private Sprite groundSprite;
	private Body body;
	private Body ground;
	
	
	@Override
	public void create() {
		sprite=new Sprite(new Texture("image.png"));
		sprite.setPosition(100f, 100f);
		groundSprite=new Sprite(new Texture("Texture.png"));
		groundSprite.setPosition(0f, 0f);
				
		debugRenderer = new Box2DDebugRenderer();
		camera=new OrthographicCamera(30,  30 * (Constants.SCREEN_HEIGHT / Constants.SCREEN_WIDTH));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		world = new World(Constants.GRAVITY, true);
		debugRenderer.render(world,camera.combined);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX(), sprite.getY());
		bodyDef.fixedRotation = true;
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth(), sprite.getHeight());
		FixtureDef fd = new FixtureDef();
		fd.shape=shape;
		fd.density=0.0f;
		fd.friction=0.1f;
		fd.restitution=0.1f;
		body.createFixture(fd);
		body.setUserData(sprite);
        //Access the sprite
        	
		
		BodyDef groundDef=new BodyDef();
		groundDef.type = BodyType.StaticBody;
		groundDef.position.set(0, 0);
		ground = world.createBody(groundDef);
		FixtureDef fd2=new FixtureDef();
		EdgeShape edgeShape = new EdgeShape();
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight()-groundSprite.getHeight();
        edgeShape.set(-w/2,-h/2,w/2,-h/2);
        fd2.shape = edgeShape;
        fd2.isSensor = true;
		ground.createFixture(fd2);
		ground.setUserData(groundSprite);
		
		edgeShape.dispose();
		shape.dispose();
	}

	@Override
	public void update() {
		camera.update();
		Vector2 vel = body.getLinearVelocity();
		Vector2 pos = body.getPosition();

		

		// apply left impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.A) && vel.x > -500) {      
			System.out.println(vel);
		     body.applyLinearImpulse(-80f, -50f, pos.x, pos.y, true);
		     //body.setLinearVelocity(-100, -50);
		}

		// apply right impulse, but only if max velocity is not reached yet
		if (Gdx.input.isKeyPressed(Keys.D) && vel.x < 500) {
		     body.applyLinearImpulse(80f, -50f, pos.x, pos.y, true);
		    // body.setLinearVelocity(100, -50);
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		camera.update();
		
		batch.begin();
        batch.draw((Sprite)body.getUserData(), body.getPosition().x, body.getPosition().y);
        batch.draw((Sprite)ground.getUserData(), ground.getPosition().x, ground.getPosition().y, 500, groundSprite.getHeight());
        batch.end();
		
		
		world.step(1/60f, 6, 2);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
}