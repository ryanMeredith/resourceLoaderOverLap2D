package uk.co.adeveloperabroad.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

import uk.co.adeveloperabroad.LoadingBar;


/**
 * Created by snow on 01/02/16.
 */
public class SplashScreen implements Screen {


    private Batch batch;

    private Float time = 0f;

    private LoadingBar loadingBar;
    private Float showLoadingBarAfter = 4.0f;

    private Texture splash;
    private Float fadeSpeed = 0.025f;
    private Float fadeTime = 0.0f;
    public Float alpha = 0.0f;


    public Boolean fadeIn = true;
    public Boolean fadeOut = false;


    public SplashScreen(Texture splash, Batch batch, LoadingBar loadingBar) {
        this.splash = splash;
        this.batch = batch;
        this.loadingBar = loadingBar;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
                | GL20.GL_DEPTH_BUFFER_BIT
                | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV
                : 0));
        time += delta;
        fadeTime += delta;

        batch.setColor(1, 1, 1, alpha);
        batch.begin();
        batch.draw(splash, 30, 30, 110, 60);
        batch.end();

        if( time > showLoadingBarAfter && !fadeOut) {
            loadingBar.renderProgressBar();
        }

        if (fadeIn) {
            if (fadeTime >= fadeSpeed && alpha != 1) {
                alpha = MathUtils.clamp(alpha + 0.025f, 0, 1);
                fadeTime = 0.0f;
            }
        }


        if (fadeOut) {
            if (fadeTime >= fadeSpeed && alpha != 0) {
                alpha = MathUtils.clamp(alpha - 0.025f, 0, 1);
                fadeTime = 0.0f;
            }
        }

    }

    public void fadeOut(){
        fadeIn = false;
        fadeOut = true;
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
        splash.dispose();
    }
}
