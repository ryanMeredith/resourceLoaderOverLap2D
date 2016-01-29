package uk.co.adeveloperabroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by snow on 29/01/16.
 */
public class StageTransition implements Disposable{

    private Stage currentStage;
    private Stage nextStage;
    private Batch batch;
    private Camera camera;
    private Integer worldWidth;
    private Integer worldHeight;

    Texture splashScreen = new Texture(Gdx.files.internal("badlogic.jpg"));
    private FrameBuffer currentFrameBuffer;
    private FrameBuffer nextFrameBuffer;

    private float transitionTime = 2.0f;
    private float time = 0.0f;

    public StageTransition(Batch batch, Camera camera, Integer worldWidth, Integer worldHeight) {
        this.batch = batch;
        this.camera = camera;
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;

        currentFrameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888,
                worldWidth, worldHeight, false);
        nextFrameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888,
                worldWidth, worldHeight, false);

    }

    public void setStages(Stage currentStage, Stage nextStage) {
        this.currentStage = currentStage;
        this.nextStage = nextStage;
    }

    public void updateTransition(float delta){
        time += delta;

//        currentFrameBuffer.bind();
        batch.setProjectionMatrix(camera.combined);
        camera.update();
//
//
        batch.begin();
//        drawTexture(splashScreen);
        batch.end();
        currentStage.getViewport().apply();
        currentStage.draw();

        FrameBuffer.unbind();

        batch.begin();
        batch.setColor(1.0f, 0f, 0f, 0.5f);
        drawTexture(currentFrameBuffer.getColorBufferTexture());
        batch.end();


//        nextFrameBuffer.bind();
//        nextStage.act();
//        nextFrameBuffer.unbind();



    }

    private void drawTexture(Texture texture) {
        int width = texture.getWidth();
        int height = texture.getHeight();

        batch.draw(texture,
                0.0f, 0.0f,
                0.0f, 0.0f,
                width, height,
                1/5f, 1/5f,
                0.0f,
                0, 0,
                width, height,
                false, false);
    }


    @Override
    public void dispose() {
        currentFrameBuffer.dispose();
        nextFrameBuffer.dispose();
        currentStage.dispose();
        nextStage.dispose();
        batch.dispose();
    }
}
