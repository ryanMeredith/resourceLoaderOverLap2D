package uk.co.adeveloperabroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * Created by snow on 28/01/16.
 */
public class MainSceneStage extends Stage {

    SceneLoader sceneLoader;
    Viewport viewport;

    MainSceneStage(SceneLoader sceneLoader, Viewport viewport, Batch spriteBatch){
        super(viewport, spriteBatch);
        this.sceneLoader = sceneLoader;
        this.viewport = viewport;
        sceneLoader.loadScene("MainScene", viewport);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
