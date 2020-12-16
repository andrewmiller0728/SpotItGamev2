package com.spotit.gamev2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

public class SpotItGame extends ApplicationAdapter implements InputProcessor {
	OrthographicCamera camera;

	ShapeRenderer shapeRenderer;

	SpriteBatch batch;
	TextureAtlas spaceAtlas;
	SymbolSet spaceSet;
	Deck gameDeck;
	Card cardL, cardR;
	Sprite[] spritesL, spritesR;
	Symbol[] symbolsL, symbolsR;

	int symbolsPerCard;
	float cardRadius, symbolSize;

	boolean isCorrect;

	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);

		camera = new OrthographicCamera(300f, 200f);

		shapeRenderer = new ShapeRenderer();

		batch = new SpriteBatch();
		spaceAtlas = new TextureAtlas("SpaceIcons.atlas");
		int atlasLength = spaceAtlas.getRegions().size;

		String[] names = new String[atlasLength];
		Color[] colors = new Color[atlasLength];
		for (int i = 0; i < atlasLength; i++) {
			names[i] = String.format("(%d)", i + 1);
			colors[i] = Color.CLEAR;
		}
		spaceSet = new SymbolSet("Science Pack", names, colors);
		gameDeck = new Deck(spaceSet, symbolsPerCard = 6);

		cardRadius = camera.viewportHeight / 4f;
		symbolSize = cardRadius * 2f / symbolsPerCard;

		isCorrect = false;

		System.out.println(gameDeck.toFormattedString());
		cardL = gameDeck.pickCard();
		cardR = gameDeck.pickCard();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0x2b / 255f, 0x2b / 255f, 0x2b / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Circle circleL = new Circle(camera.viewportWidth / -4f, 0f, cardRadius);
		Circle circleR = new Circle(camera.viewportWidth / 4f, 0f, cardRadius);

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(new Color(0xB1 / 255f, 0x5B / 255f, 0x2E / 255f, 1));
		shapeRenderer.circle(circleL.x, circleL.y, circleL.radius);
		shapeRenderer.circle(circleR.x, circleR.y, circleR.radius);
		shapeRenderer.setColor(Color.DARK_GRAY);
		shapeRenderer.circle(circleL.x, circleL.y, circleL.radius - 2f);
		shapeRenderer.circle(circleR.x, circleR.y, circleR.radius - 2f);
		shapeRenderer.end();

		symbolsL = cardL.getSymbols();
		spritesL = new Sprite[symbolsL.length];
		float angleL = 0f;
		for (int i = 0; i < symbolsL.length; i++) {
			spritesL[i] = spaceAtlas.createSprite(symbolsL[i].getName());
			spritesL[i].setSize(symbolSize, symbolSize);
			spritesL[i].setCenter(
					circleL.x + circleL.radius / 2f * MathUtils.cosDeg(angleL),
					circleL.y + circleL.radius / 2f * MathUtils.sinDeg(angleL)
			);
			angleL += 360f / (float) symbolsL.length;
		}
		symbolsR = cardR.getSymbols();
		spritesR = new Sprite[symbolsR.length];
		float angleR = 0f;
		for (int i = 0; i < symbolsR.length; i++) {
			spritesR[i] = spaceAtlas.createSprite(symbolsR[i].getName());
			spritesR[i].setSize(symbolSize, symbolSize);
			spritesR[i].setCenter(
					circleR.x + circleR.radius / 2f * MathUtils.cosDeg(angleR),
					circleR.y + circleR.radius / 2f * MathUtils.sinDeg(angleR)
			);
			angleR += 360f / (float) symbolsR.length;
		}
		if (spritesL.length != spritesR.length) {
			throw new RuntimeException("Card symbols counts do not match");
		}

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i = 0; i < spritesL.length; i++) {
			spritesL[i].draw(batch);
			spritesR[i].draw(batch);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		spaceAtlas.dispose();
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
			for (int i = 0; i < symbolsL.length; i++) {
				for (int j = 0; j < symbolsR.length; j++) {
					if (symbolsL[i].equals(symbolsR[j])) {
						if (spritesL[i].getBoundingRectangle().contains(cursorX, cursorY)
								|| spritesR[j].getBoundingRectangle().contains(cursorX, cursorY)) {
							isCorrect = true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (isCorrect) {
			cardL = gameDeck.pickCard();
			cardR = gameDeck.pickCard();
			isCorrect = false;
		}
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
