package ru.samsung.gamestudio.characters;

import static ru.samsung.gamestudio.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import ru.samsung.gamestudio.screens.ScreenColor;

public class Bird {

    int x, y;
    int width, height;
    String birdColor;  // Изменено на String

    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 100;
    boolean jump = true;

    int frameCounter;
    public Texture[] framesArray;

    public Bird(int x, int y, int speed, int width, int height, String color) {  // Добавлен параметр color
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.birdColor = color;  // Сохраняем цвет
        frameCounter = 0;

        framesArray = new Texture[]{
                new Texture("pictures_for_game/bird/bird0_" + color + ".png"),
                new Texture("pictures_for_game/bird/bird1_" + color + ".png"),
                new Texture("pictures_for_game/bird/bird2_" + color + ".png"),
                new Texture("pictures_for_game/bird/bird1_" + color + ".png")
        };
    }


    public void setY(int y) {
        this.y = y;
    }

    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;
        return true;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }

}