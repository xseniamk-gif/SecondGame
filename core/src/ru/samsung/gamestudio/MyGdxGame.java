package ru.samsung.gamestudio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends Game {
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public OrthographicCamera camera;
	SpriteBatch batch;
	Texture birdTexture;
	int birdX = 0;
	int birdY = 0;
	int birdSpeed = 5;

	@Override
	public void create() {
		batch = new SpriteBatch();
		birdTexture = new Texture("assets/pictures_for_game/bird/bird0.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
	}

	@Override
	public void render() {
		birdX += birdSpeed;
		birdY += birdSpeed;

		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(birdTexture, birdX, birdY);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}