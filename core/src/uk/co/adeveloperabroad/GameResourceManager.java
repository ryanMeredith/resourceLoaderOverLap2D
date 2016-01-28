package uk.co.adeveloperabroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import java.io.File;

/**
 * Created by snow on 28/01/16.
 */
public class GameResourceManager extends ResourceManager {

    public boolean isCurrentlyLoading = false;
    public AssetManager assetManager;
    public Texture splashScreen;

    GameResourceManager() {
        loadProjectVO();
        assetManager = new AssetManager();
        loadSplashScreen();
    }

    public void initAllResources() {

        loadProjectVO();
        for (int i = 0; i < projectVO.scenes.size(); i++) {
            loadSceneVO(projectVO.scenes.get(i).sceneName);
            scheduleScene(projectVO.scenes.get(i).sceneName);
        }
        loadAssets();
    }

    public void initScene(String sceneName) {

        for (int i = 0; i < projectVO.scenes.size(); i++) {
           if (projectVO.scenes.get(i).sceneName.equals(sceneName)){
               loadSceneVO(projectVO.scenes.get(i).sceneName);
               scheduleScene(projectVO.scenes.get(i).sceneName);
           }
        }
        loadAssets();

    }



    /**
     * Loads all the scheduled assets into memory including
     * main atlas pack, particle effects, sprite animations, spine animations and fonts
     */
    public void loadAssets() {
        loadAtlasPack();

    }

    @Override
    public void loadAtlasPack() {
        isCurrentlyLoading = true;
        assetManager.load(packResolutionName + File.separator + "pack.atlas", TextureAtlas.class);
    }

    public boolean update() {
        if (isCurrentlyLoading) {
            if (assetManager.update()) {
                if (isCurrentlyLoading) {
                    postLoad();
                }
                return true;
            }
        }
        return false;
    }

    protected void postLoad() {
        if (mainPack == null) {
            mainPack = assetManager.get(packResolutionName + File.separator + "pack.atlas", TextureAtlas.class);
        }

        isCurrentlyLoading = false;
    }

    public void loadSplashScreen() {
        splashScreen = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

}
