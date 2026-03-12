package ru.samsung.gamestudio;

public interface Screen {

    public void show ();
    public void render (float delta);

    public void hide ();
    public void dispose ();
}