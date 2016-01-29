package uk.co.adeveloperabroad;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;


public class Loader extends ApplicationAdapter {

	private Viewport viewport;
	private SceneLoader sceneLoader;
    private GameResourceManager rm;
    private Batch spriteBatch;

    private OtherSceneStage otherSceneStage;

    private boolean loaded = false;
    private LoadingBar loadingBar;

    private Stage currentStage;
    private Stage nextStage;
    private StageTransition stageTransition;


	@Override
	public void create () {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 160, 96);
		viewport = new FitViewport(160, 96, camera);
        rm = new GameResourceManager();
		sceneLoader = new SceneLoader(rm);
        spriteBatch = sceneLoader.getBatch();
        stageTransition = new StageTransition(spriteBatch, camera, 160, 96);
	}

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT
                | GL20.GL_DEPTH_BUFFER_BIT
                | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV
                : 0));


        rm.update();

      if (!loaded) {
            spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
            spriteBatch.begin();
            spriteBatch.draw(rm.splashScreen, 30, 30, 110, 60);
            spriteBatch.end();
        }

        displayStage();

        if (currentStage != null) {
            stageTransition.setStages(currentStage, null);
            stageTransition.updateTransition(Gdx.graphics.getDeltaTime());
           // currentStage.act(Gdx.graphics.getDeltaTime());
        }
	}

    protected void displayStage() {

        // if every thing has loaded start stage.
        if (!rm.isCurrentlyLoading && !loaded) {
            currentStage = new OtherSceneStage(sceneLoader, viewport, spriteBatch);
            loaded = true;
        }
    }
}
