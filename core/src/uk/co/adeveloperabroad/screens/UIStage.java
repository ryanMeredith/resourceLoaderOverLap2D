package uk.co.adeveloperabroad.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.scene2d.CompositeActor;

/**
 * Created by snow on 28/01/16.
 */
public class UIStage extends Stage implements Telegraph {

    SceneLoader sceneLoader;
    Viewport viewport;

    public UIStage(SceneLoader sceneLoader, Viewport viewport, Batch batch){
        super(viewport, batch);
        this.sceneLoader = sceneLoader;
        this.viewport = viewport;
        Gdx.input.setInputProcessor(this);

        ProjectInfoVO projectInfoVO = sceneLoader.getRm().getProjectVO();
        CompositeItemVO footButtonItem = projectInfoVO.libraryItems.get("footButton");
        CompositeActor footButton = new CompositeActor(footButtonItem, sceneLoader.getRm());
        addActor(footButton);

        footButton.setX(10);
        footButton.setY(10);
        footButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("I'm a library actor Button");
                MessageManager.getInstance().dispatchMessage(1);
            }
        });

    }



    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return false;
    }
}
