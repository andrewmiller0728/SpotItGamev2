package com.spotit.gamev2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SpotItInputProcessor implements InputProcessor {

    private final OrthographicCamera camera;
    private final GameMaster gm;
    private SymbolSprite[][] currSymbolSprites;
    private boolean isGuessCorrect;

    public SpotItInputProcessor(OrthographicCamera currCam, GameMaster gm) {
        super();
        camera = currCam;
        this.gm = gm;
        currSymbolSprites = gm.getCurrSymbolSprites();
        isGuessCorrect = false;
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
            currSymbolSprites = gm.getCurrSymbolSprites();
            for (SymbolSprite[] currSymbolSpriteRow : currSymbolSprites) {
                for (SymbolSprite symbolSprite : currSymbolSpriteRow) {
                    if (symbolSprite.getSprite().getBoundingRectangle().contains(cursor)) {
                        isGuessCorrect = gm.makeGuess(symbolSprite.getSymbol());
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isGuessCorrect) {
            gm.updateCardPair();
        }
        isGuessCorrect = false;

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
