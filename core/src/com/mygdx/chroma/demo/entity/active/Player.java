/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.demo.entity.active;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.chroma.demo.Constants;
import com.mygdx.chroma.demo.State;


/**
 * Represents the player avatar.
 */
public class Player extends ActiveEntity
{
	Vector2 pos = new Vector2();
	Vector2 accel = new Vector2();
	Vector2 vel = new Vector2();

	int playerState;
	float stateTime;

	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
		playerState = Constants.PLAYER_STANDING;
		stateTime = 0;
	}
	public void update() {

	}
	private void processKeys(int state)
	{
		//if fight screen

		//Attack
		if(Gdx.input.isKeyPressed(Keys.Z))
			if(playerState == Constants.JUMP) playerState = Constants.ATTACKJUMP;
			else playerState = Constants.JUMP;
		//Jump
		if((Gdx.input.isKeyPressed(Keys.UP)) && state != Constants.JUMP) { 
			playerState = Constants.JUMP;
			vel.y = Constants.JUMP_VELOCITY;
		}

		//Move
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			if(playerState != Constants.JUMP) playerState = Constants.RUN;
			dir = Constants.LEFT;
			accel.x = Constants.ACCELERATION * dir;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(playerState != Constants.JUMP) playerState = Constants.RUN;
			dir = Constants.RIGHT;
			accel.x = Constants.ACCELERATION * dir;
		}
		else {
			if(playerState != Constants.JUMP) playerState = Constants.IDLE;
			accel.x = 0;
		}
	}
}