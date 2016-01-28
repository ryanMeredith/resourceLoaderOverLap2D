package uk.co.adeveloperabroad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.resources.ResourceManager;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class Loader extends ApplicationAdapter {

	private Viewport viewport;
	private SceneLoader sceneLoader;
    private GameResourceManager rm;
    private SpriteBatch spriteBatch;
    private MainSceneStage mainSceneStage;

    private boolean loaded = false;

	@Override
	public void create () {
		viewport = new FitViewport(160, 96);

        rm = new GameResourceManager();
        rm.initScene("MainScene");

        spriteBatch = new SpriteBatch();
		sceneLoader = new SceneLoader(rm);
        mainSceneStage = new MainSceneStage(sceneLoader, viewport);

//		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rm.update();

        if (!rm.isCurrentlyLoading && !loaded) {
            mainSceneStage.loadScene();
            loaded = true;

        } else if (!loaded) {
            spriteBatch.begin();
            spriteBatch.draw(rm.splashScreen, 100, 100);
            spriteBatch.end();
            System.out.println("batch draw");
        }
        mainSceneStage.act();
	}
}
