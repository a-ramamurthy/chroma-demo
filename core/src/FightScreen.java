/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.chroma.demo.Constants;
import com.mygdx.chroma.demo.entity.active.Player;
import com.mygdx.chroma.demo.entity.inactive.Terrain;

/**
 * Represents the screen that appears during a game level.
 */
public class FightScreen extends Screen
{

	//world stuff
	public World world;
	public SpriteBatch batch;
	public Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	public ShapeRenderer shapeRenderer;
	public OrthographicCamera camera;

	private Player player;
	private Terrain[][] terrain=new Terrain[10][15];
	//private Terrain[][] terrain=new Terrain[10][1];

	private Texture backdrop;

	private float mw=Constants.WIDTH*Constants.METERS_PER_PIXEL;
	private float mh=Constants.HEIGHT*Constants.METERS_PER_PIXEL;

	@Override
	public void create()
	{
		batch=new SpriteBatch();
		world=new World(Constants.GRAVITY, true);
		backdrop=new Texture(Gdx.files.internal("temp-background.jpg"));
		camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		debugRenderer=new Box2DDebugRenderer();
		backdrop=new Texture(Gdx.files.internal("temp-background.jpg"));
		initTerrain();
		initPlayer();
		initEdges();

	}

	public void initPlayer()
	{
		player=new Player(terrain[0][0].image.getX()+50*100, terrain[0][0].image.getY()+400*100);
		//System.out.println(terrain[0][0].image.getY());
		player.body=world.createBody(player.bd);
		//player.body.setUserData(player.curSprite);
		player.body.createFixture(player.fd);
		MassData md=new MassData();
		md.mass=Constants.PLAYER_MASS;
		player.fixture=player.body.createFixture(player.fd);
	}

	public void initTerrain()
	{
		
		//terrain[0][0]=new Terrain(0,300,"grass-block-top.png");
		//terrain[1][0]=new Terrain(0,299,"grass-block-side.png");


		for(int c=0; c<terrain.length; c++)
		{
			//float stackHeight=(float)(Math.random()*Constants.TERRAIN_VARIANCE-Constants.TERRAIN_VARIANCE/2+Constants.HEIGHT/4);
			float stackHeight=200;
			System.out.println("Stack Height: "+stackHeight);
			terrain[c][0]=new Terrain(4+c*129, stackHeight, "grass-block-top.png");
			for(int r=1; r<terrain[0].length; r++)
				terrain[c][r]=new Terrain(4+c*129, stackHeight-20f*r, "grass-block-side.png");

		}
		for(Terrain[] stack : terrain)
		{
			for(Terrain block : stack)
			{
				block.body=world.createBody(block.bd);
				block.body.createFixture(block.fdTop);
				block.body.createFixture(block.fdLeft);
				block.body.createFixture(block.fdRight);
				block.body.setUserData(block.image);
			}
		}
	}//96 34

	public void initEdges()
	{
		BodyDef bdLeft=new BodyDef();
		bdLeft.type=BodyDef.BodyType.StaticBody;
		bdLeft.position.set(0,0);
		FixtureDef fdLeft=new FixtureDef();
		EdgeShape leftEdge=new EdgeShape();
		leftEdge.set(new Vector2(0, 0), new Vector2(0,Constants.HEIGHT));
		fdLeft.shape=leftEdge;
		Body left = world.createBody(bdLeft);
		left.createFixture(fdLeft);

	}
	public void checkControls()
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			System.exit(0);
		if(Gdx.input.isKeyPressed(Keys.NUM_0))
			System.exit(0);
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			player.move(Constants.LEFT);
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			player.move(Constants.RIGHT);
		if(Gdx.input.isKeyJustPressed(Keys.UP))
			player.jump();
		if(Gdx.input.isKeyPressed(Keys.DOWN))
			player.fallFast();
		if(Gdx.input.isKeyPressed(Keys.SPACE))
			player.attack();


	}
	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#update()
	 */
	@Override
	public void update()
	{
		checkControls();
		camera.update();
		player.update();
		for(Terrain[] stack : terrain)
			for(Terrain block : stack)
				block.update();
		//player.move(Constants.LEFT);
		world.step(Constants.WORLD_STEP, 6, 2);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.mygdx.chroma.demo.screen.Screen#render(com.badlogic.gdx.graphics.
	 * g2d.SpriteBatch)
	 */
	@Override
	public void render(SpriteBatch sb)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(0.007f*Constants.PIXELS_PER_METER, 0.007f*Constants.PIXELS_PER_METER, 0);
		sb.begin();
		sb.draw(backdrop,0,0,Constants.WIDTH,Constants.HEIGHT);
		System.out.println(player.curSprite.getX());
		System.out.println(player.curSprite.getY());
		if(player.dir==Constants.LEFT)
			sb.draw(player.curSprite,player.curSprite.getX()+100,player.curSprite.getY(),-100,100);
		else
			sb.draw(player.curSprite,player.curSprite.getX(),player.curSprite.getY(),100,100);

		//sb.draw(terrain[0][0].image,terrain[0][0].image.getX(),terrain[0][0].image.getY());
		//sb.draw(terrain[1][0].image,terrain[1][0].image.getX(),terrain[1][0].image.getY());

		for(Terrain[] stack : terrain)
			for(Terrain block : stack)
			{
				sb.setColor(new Color(0.5f, 0.5f, 0.5f, 1));
				sb.draw(block.image, block.image.getX(), block.image.getY());
				sb.setColor(Color.WHITE);
			}
		sb.end();
		debugRenderer.render(world, debugMatrix);

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#dispose()
	 */
	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#pause()
	 */
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#resume()
	 */
	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

}