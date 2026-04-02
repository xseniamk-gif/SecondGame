package ru.samsung.gamestudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.PointCounter;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenColor implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonRed;
    TextButton buttonGreen;
    TextButton buttonYellow;
    TextButton buttonMenu;
    TextButton buttonBlue;

    int gamePoints;
    public String selectedColor = "blue";

    public ScreenColor(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        buttonRed = new TextButton(50, 200, "Red Bird", 3f);
        buttonGreen = new TextButton(500, 200, "Green Bird", 3f);
        buttonYellow = new TextButton(50, 400, "Yellow Bird", 3f);
        buttonBlue = new TextButton(500, 400, "Blue Bird", 3f);
        buttonMenu = new TextButton(860, 300, "Menu", 3f);
        background = new MovingBackground("pictures_for_game/background/restart_bg.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonRed.isHit((int) touch.x, (int) touch.y)) {
                selectedColor = "red";
                myGdxGame.screenGame.setBirdColor("red");  // Устанавливаем цвет
                myGdxGame.setScreen(myGdxGame.screenMenu);

            }
            if (buttonGreen.isHit((int) touch.x, (int) touch.y)) {
                selectedColor = "green";
                myGdxGame.screenGame.setBirdColor("green");
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
            if (buttonYellow.isHit((int) touch.x, (int) touch.y)) {
                selectedColor = "yellow";
                myGdxGame.screenGame.setBirdColor("yellow");
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
            if (buttonBlue.isHit((int) touch.x, (int) touch.y)) {
                selectedColor = "blue";
                myGdxGame.screenGame.setBirdColor("blue");
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
            if (buttonMenu.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        buttonBlue.draw(myGdxGame.batch);
        buttonRed.draw(myGdxGame.batch);
        buttonYellow.draw(myGdxGame.batch);
        buttonGreen.draw(myGdxGame.batch);
        buttonMenu.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();

    }
}