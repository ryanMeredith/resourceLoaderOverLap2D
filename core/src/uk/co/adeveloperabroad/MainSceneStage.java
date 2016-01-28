package uk.co.adeveloperabroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * Created by snow on 28/01/16.
 */
public class MainSceneStage extends Stage {

    SceneLoader sceneLoader;
    Viewport viewport;

    MainSceneStage(SceneLoader sceneLoader, Viewport viewport){
        this.sceneLoader = sceneLoader;
        this.viewport = viewport;


    }

    public void loadScene() {
        sceneLoader.loadScene("MainScene", viewport);
        System.out.println("here");
    }


    @Override
    public void act() {
        super.act();
        System.out.println("act");
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
