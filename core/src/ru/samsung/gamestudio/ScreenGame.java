package ru.samsung.gamestudio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    Bird bird;
    int tubeCount=3;
    Tube[] tubes;
    boolean isGameOver;

    public ScreenGame (MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
         bird = new Bird(50, 50, 5);
         initTubes();
    }

    @Override
    public void show() {
        isGameOver = false;
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            System.out.println("Just touched");
            bird.onClick();
        }

        bird.fly();
        if (!bird.isInField()) {
            System.out.println("not in field");
            isGameOver = true;
        }
        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                System.out.println("hit");
                isGameOver = true;
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }
    void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }


    @Override
    public void resize(int width, int height) {}


    @Override
    public void pause() {}


    @Override
    public void resume() {}


    @Override
    public void hide() {}


    @Override
    public void dispose() {}
}
