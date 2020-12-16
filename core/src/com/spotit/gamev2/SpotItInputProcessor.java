package com.spotit.gamev2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public class SpotItInputProcessor implements InputProcessor {

    private OrthographicCamera camera;

    public SpotItInputProcessor(OrthographicCamera currCam) {
        super();
        camera = currCam;
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
            float cursorX = MathUtils.map(
                    0,
                    Gdx.graphics.getWidth(),
                    camera.viewportWidth / -2f,
                    camera.viewportWidth / 2f,
                    screenX
            );
            float cursorY = MathUtils.map(
                    0,
                    Gdx.graphics.getHeight(),
                    camera.viewportHeight / 2f,
                    camera.viewportHeight / -2f,
                    screenY
            );
            // TODO: Handle clicking a symbol
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO: if the clicked symbol is the matching symbol, give a point
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
