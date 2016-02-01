package uk.co.adeveloperabroad;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

import uk.co.adeveloperabroad.screens.UIStage;
import uk.co.adeveloperabroad.screens.SplashScreen;
import uk.co.adeveloperabroad.screens.ThisScreen;


public class MainClass extends Game implements Telegraph {

	private Viewport viewport;
	private SceneLoader sceneLoader;
    private GameResourceManager rm;
    private Batch spriteBatch;


    private boolean loaded = false;
    private Stage uiStage;
    private SplashScreen splashScreen;


	@Override
	public void create () {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 160, 96);
		viewport = new FitViewport(160, 96, camera);
        rm = new GameResourceManager();
		sceneLoader = new SceneLoader(rm);
        spriteBatch = sceneLoader.getBatch();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);


        splashScreen = new SplashScreen(rm.splashScreen, spriteBatch, new LoadingBar(rm, camera));
        setScreen(splashScreen);

        MessageManager.getInstance().addListener(this, 1);

	}


    @Override
    public void render() {
        super.render();

        rm.update();

        // only show splash at start of game;
        if (splashScreen != null) {
            showSplashScreen();
        }

        if (uiStage != null) {
            uiStage.act();
            uiStage.draw();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

	}

    private void showSplashScreen() {

        // if loaded and faded in
        if (!rm.isCurrentlyLoading  & splashScreen.alpha == 1) {
            splashScreen.fadeOut();
        }

        // when fully faded out start the game
        if (splashScreen.fadeOut && splashScreen.alpha == 0 && !loaded) {
          uiStage = new UIStage(sceneLoader,viewport,spriteBatch);
            uiStage.addAction(Actions.alpha(0));
            uiStage.addAction(Actions.fadeIn(10));
            splashScreen.dispose();
            loaded = true;
        }
    }




    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override // only one message keeping it simple
    public boolean handleMessage(Telegram msg) {
        uiStage.addAction(Actions.fadeOut(4));
        uiStage.addAction( Actions.sequence(Actions.fadeOut(5f),
                        new Action() {
                            public boolean act(float delta) {
                                setScreen(new ThisScreen(sceneLoader, viewport));
                                uiStage.dispose();
                                return true; // returning true consumes the event
                            }
                        })
        );


        return true;
    }
}
