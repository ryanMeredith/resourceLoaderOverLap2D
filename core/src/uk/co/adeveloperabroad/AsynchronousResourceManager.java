package uk.co.adeveloperabroad;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.uwsoft.editor.renderer.resources.ResourceManager;

import java.io.File;

/**
 * Created by snow on 29/01/16.
 */
public class AsynchronousResourceManager extends ResourceManager {

    public boolean isCurrentlyLoading = false;
    public AssetManager assetManager;

    public float loadedAtStart = 0.0f;
    public float percentageLoaded = 0.0f;

    AsynchronousResourceManager() {
        assetManager = new AssetManager();
    }

    // fast method to make sure we have all project data as these should be small
    public void initAllSceneData() {

        loadProjectVO();
        for (int i = 0; i < projectVO.scenes.size(); i++) {
            loadSceneVO(projectVO.scenes.get(i).sceneName);
            scheduleScene(projectVO.scenes.get(i).sceneName);
        }
    }

    // Load individual scene data.
    public void initSceneData(String sceneName) {

        for (int i = 0; i < projectVO.scenes.size(); i++) {
            if (projectVO.scenes.get(i).sceneName.equals(sceneName)){
                loadSceneVO(projectVO.scenes.get(i).sceneName);
                scheduleScene(projectVO.scenes.get(i).sceneName);
            }
        }
    }

    @Override
    public void loadAtlasPack() {
        if (mainPack == null) {
            setLoadingFlag();
            assetManager.load(packResolutionName + File.separator + "pack.atlas", TextureAtlas.class);
        }
    }

    public void setLoadingFlag() {
        isCurrentlyLoading = true;
        loadedAtStart = assetManager.getLoadedAssets();
    }


    public boolean update() {

        if (isCurrentlyLoading) {
            boolean finishedLoading = assetManager.update();
            updatePercentageLoaded();
            if (finishedLoading) {
                if (isCurrentlyLoading) {
                    postLoad();
                }
                return true;
            }
        }
        return false;
    }

    // gives resources to overlap2D
    protected void postLoad() {

        if (mainPack == null) {
            mainPack = assetManager.get(packResolutionName + File.separator + "pack.atlas", TextureAtlas.class);
        }

        isCurrentlyLoading = false;
    }

    // calculates percentage loaded
    protected void updatePercentageLoaded() {
        percentageLoaded = Math.min(1,
                (assetManager.getLoadedAssets() - loadedAtStart) /
                        (assetManager.getLoadedAssets() + assetManager.getQueuedAssets() - loadedAtStart)
        );
    }


}
