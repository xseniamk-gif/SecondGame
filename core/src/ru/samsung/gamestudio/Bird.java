package ru.samsung.gamestudio;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 200;
    boolean jump;

    Texture[] framesArray;
    int frameCounter;
    int width=200;
    int height=200;

    boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;
        return true;
    }

    public Bird(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        framesArray = new Texture[] {
                new Texture("pictures_for_game/bird/bird0.png"),
                new Texture("pictures_for_game/bird/bird1.png"),
                new Texture("pictures_for_game/bird/bird2.png"),
                new Texture("pictures_for_game/bird/bird1.png"),
        };
    }
    void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {

            y += speed;
        } else {
            y -= 1.5 * speed;
        }
    }

    void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }
}