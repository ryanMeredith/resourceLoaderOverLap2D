package uk.co.adeveloperabroad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * Created by snow on 28/01/16.
 * class for particular game (to be modified)
 */
public class GameResourceManager extends AsynchronousResourceManager {

    public Texture splashScreen = new Texture(Gdx.files.internal("badlogic.jpg"));

    GameResourceManager() {
        // get all VO data
        initAllSceneData();
        // get image pack
        loadAtlasPack();
    }





}
