package uk.co.adeveloperabroad;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by snow on 29/01/16.
 */
public class LoadingBar {

    private GameResourceManager gameResourceManager;
    private ShapeRenderer shape;
    private Camera camera;

    public LoadingBar(GameResourceManager resourceManager, Camera camera) {
        gameResourceManager = resourceManager;
        this.camera = camera;
        shape = new ShapeRenderer();
        ShaderProgram.pedantic = false;
    }

    public void renderProgressBar() {

        shape.setProjectionMatrix(camera.combined);

        // draw progress bar;
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(40, 10, 90 * gameResourceManager.percentageLoaded, 10);
        shape.end();

        // Draw the loading bar outline.
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.rect(40, 10, 90, 10);
        shape.end();


    }



}
