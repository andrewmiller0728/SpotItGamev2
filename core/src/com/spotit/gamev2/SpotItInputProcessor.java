package com.spotit.gamev2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SpotItInputProcessor implements InputProcessor {

    OrthographicCamera camera;
    public SpotItInputProcessor(OrthographicCamera camera) {
        super();
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT && pointer == 0) {
            Vector2 cursor = new Vector2(
                    MathUtils.map(
                            0,
                            Gdx.graphics.getWidth(),
                            camera.viewportWidth / -2f,
                            camera.viewportWidth / 2f,
                            screenX
                    ),
                    MathUtils.map(
                            0,
                            Gdx.graphics.getHeight(),
                            camera.viewportHeight / 2f,
                            camera.viewportHeight / -2f,
                            screenY
                    )
            );
            // TODO: Implement touchDown()
            System.out.printf("touch down at (x:%.2f, y:%.2f)\n", cursor.x, cursor.y);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO: Implement touchUp()
        System.out.print("touch up\n");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
