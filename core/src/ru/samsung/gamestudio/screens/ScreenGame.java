package ru.samsung.gamestudio.screens;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samsung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Point;

import ru.samsung.gamestudio.characters.Bird;
import ru.samsung.gamestudio.components.MovingBackground;
import ru.samsung.gamestudio.MyGdxGame;
import ru.samsung.gamestudio.components.PointCounter;
import ru.samsung.gamestudio.characters.Tube;
import ru.samsung.gamestudio.components.TextButton;

public class ScreenGame implements Screen {

    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    MyGdxGame myGdxGame;

    Bird bird;
    PointCounter pointCounter;
    MovingBackground background;
    TextButton buttonPause;

    int tubeCount = 3;
    Tube[] tubes;

    int gamePoints;
    boolean isGameOver;
    boolean ifPause=false;
    int c=1;
    String birdColor = "blue";  // Добавьте поле для цвета

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        initTubes();
        background = new MovingBackground("pictures_for_game/background/game_bg.png");
        // Передаем цвет птицы
        bird = new Bird(20, SCR_HEIGHT / 2, 7, 180, 150, birdColor);
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        buttonPause = new TextButton(10, 600, "Pause", 2f);
    }

    // Добавьте метод для обновления цвета
    public void setBirdColor(String color) {
        this.birdColor = color;
        if (bird != null) {
            bird.dispose();
            bird = new Bird(20, SCR_HEIGHT / 2, 7, 180, 150, birdColor);
        }
    }


    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
        bird.setY(SCR_HEIGHT / 2);
        initTubes();

    }

    @Override
    public void render(float delta) {
        if (isGameOver) {
            myGdxGame.screenRestart.gamePoints = gamePoints;
            myGdxGame.setScreen(myGdxGame.screenRestart);
        }
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonPause.isHit((int) touch.x, (int) touch.y)) {
                if (c == 1) {
                    ifPause = true;
                    c = 2;
                } else {
                    ifPause = false;
                    c = 1;
                }
            }
        }
        if (ifPause== false) {

            if (Gdx.input.justTouched()) {
                bird.onClick();
            }

            background.move();
            bird.fly();
            if (!bird.isInField()) {
                System.out.println("not in field");
                isGameOver = true;
            }
            for (Tube tube : tubes) {
                tube.move();
                if (tube.isHit(bird)) {
                    isGameOver = true;
                    System.out.println("hit");
                } else if (tube.needAddPoint(bird)) {
                    gamePoints += 1;
                    tube.setPointReceived();
                    System.out.println(gamePoints);
                }
            }
        }

            ScreenUtils.clear(1, 0, 0, 1);
            myGdxGame.camera.update();
            myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
            myGdxGame.batch.begin();

            background.draw(myGdxGame.batch);
            bird.draw(myGdxGame.batch);
            buttonPause.draw(myGdxGame.batch);
            for (Tube tube : tubes) tube.draw(myGdxGame.batch);
            pointCounter.draw(myGdxGame.batch, gamePoints);

            myGdxGame.batch.end();

    }

    @Override
    public void resize ( int width, int height){

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose () {

        bird.dispose();
        background.dispose();
        pointCounter.dispose();
        for (int i = 0; i < tubeCount; i++) {
            tubes[i].dispose();
        }
    }


    void initTubes () {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }
}


