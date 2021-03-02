package com.survivorbirdgame.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {

	//sprite is for game objects like bird, background, bee etc.
	//sprite drawer, bacher
	SpriteBatch batch;

	//object that will be added , image vs. main object
	//textures
	Texture back;
	Texture bird;
	Texture bee1; Texture bee2; Texture bee3;

	float birdX, birdY;
	float[] enemyX = new float[2];
	float[] enemyOffSet1 = new float[2];
	float[] enemyOffSet2 = new float[2];
	float[] enemyOffSet3 = new float[2];

	int gameOn = 0;
	float velocity = 0f;
	float gravity = 0.2f;
	float distance = 0;
	@Override
	public void create () {
		batch = new SpriteBatch();

		//images
		back = new Texture("back.png");
		bird = new Texture("bird.png");
		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");
		//first x axis for bird
		birdX = Gdx.graphics.getWidth() / 4; birdY = Gdx.graphics.getHeight() /2;

		Random rnd = new Random();


		//creates enemies(bees)
		for (int i = 0; i < enemyX.length; i++){
			enemyX[i] = Gdx.graphics.getWidth() + distance;
			distance = Gdx.graphics.getWidth() / 2;

			enemyOffSet1[i] = (Gdx.graphics.getHeight() - 200) * (rnd.nextFloat() - 0.5f);
			enemyOffSet2[i] = (Gdx.graphics.getHeight() - 200) * (rnd.nextFloat() - 0.5f);
			enemyOffSet3[i] = (Gdx.graphics.getHeight() - 200) * (rnd.nextFloat() - 0.5f);
		}

	}

	@Override
	public void render () {


		batch.begin();			//batch starts

		//draws image, draw(texture, x axis, y axis, width, height),, Gdx.graphics = for dynamic screen
		batch.draw(back, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(bird, birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

		// if game on
		if(gameOn == 1){

			//draws enemies, bees at random places
			for (int i = 0; i < enemyX.length; i++){

				if(enemyX[i] < 0){
					Random rnd = new Random();
					enemyX[i] = Gdx.graphics.getWidth();

					//defines offsets
					enemyOffSet1[i] = (Gdx.graphics.getHeight() - 200) * (rnd.nextFloat() - 0.5f);
					enemyOffSet2[i] = (Gdx.graphics.getHeight() - 200) * (rnd.nextFloat() - 0.5f);
					enemyOffSet3[i] = (Gdx.graphics.getHeight() - 200) * (rnd.nextFloat() - 0.5f);

				}

				//draws bees at random places
				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet1[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
				batch.draw(bee1, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);


				enemyX[i] -= 3;
			}



			//if just touched then velecity -7, for y axis
			if(Gdx.input.justTouched()){
				velocity = -7;
			}

			if(birdY > 0 || velocity < 0){
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}
		}
		//first touch on the screen
		else{
			if(Gdx.input.justTouched())
				gameOn = 1;
		}



		batch.end();			//batch finishes
	}
	
	@Override
	public void dispose () {

	}
}
