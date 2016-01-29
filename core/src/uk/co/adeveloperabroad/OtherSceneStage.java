package uk.co.adeveloperabroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * Created by snow on 28/01/16.
 */
public class OtherSceneStage extends Stage {

    SceneLoader sceneLoader;
    Viewport viewport;

    OtherSceneStage(SceneLoader sceneLoader, Viewport viewport, Batch batch){
        super(viewport, batch);
        this.sceneLoader = sceneLoader;
        this.viewport = viewport;

        sceneLoader.loadScene("OtherScene", viewport);

    }



    @Override
    public void act(float delta) {
        super.act(delta);

        sceneLoader.getEngine().update(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
