package com.spotit.gamev2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

public class SpotItGame extends ApplicationAdapter {

	ShapeRenderer shapeRenderer;

	SpriteBatch batch;
	TextureAtlas spaceAtlas;
	SymbolSet spaceSet;
	GameMaster gm;

	OrthographicCamera camera;
	SpotItInputProcessor inputProcessor;

	int symbolsPerCard;
	float cardRadius, symbolSize;

	@Override
	public void create () {
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
		spaceSet = new SymbolSet("Space Pack", names, colors);
		gm = new GameMaster(spaceSet, symbolsPerCard = 6, "Andrew");
		gm.updateCardPair();

		camera = new OrthographicCamera(3000f, 2000f);
		inputProcessor = new SpotItInputProcessor(camera, gm);
		Gdx.input.setInputProcessor(inputProcessor);

		cardRadius = camera.viewportHeight / 4f;
		symbolSize = cardRadius * 2f / symbolsPerCard;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0x2b / 255f, 0x2b / 255f, 0x2b / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gm.setCurrSymbolSprites(drawCards(gm.getCurrCardPair()));
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		spaceAtlas.dispose();
	}

	private SymbolSprite[][] drawCards(Card[] cards) {
		Symbol[][] symbols = {
				cards[0].getSymbols(),
				cards[1].getSymbols()
		};
		Circle[] cardCircles = {
				new Circle(camera.viewportWidth / -4f, 0f, cardRadius),
				new Circle(camera.viewportWidth / 4f, 0f, cardRadius)
		};

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (int i = 0; i < cardCircles.length; i++) {
			shapeRenderer.setColor(new Color(0xB1 / 255f, 0x5B / 255f, 0x2E / 255f, 1));
			shapeRenderer.circle(cardCircles[i].x, cardCircles[i].y, cardCircles[i].radius);
			shapeRenderer.setColor(Color.DARK_GRAY);
			shapeRenderer.circle(cardCircles[i].x, cardCircles[i].y, cardCircles[i].radius * 0.95f);
		}
		shapeRenderer.end();

		SymbolSprite[][] symbolSprites = new SymbolSprite[gm.getCurrCardPair().length][symbolsPerCard];
		for (int i = 0; i < gm.getCurrCardPair().length; i++) {
			float angle = 0f;
			Circle cardCircle = cardCircles[i];
			for (int j = 0; j < symbols[i].length; j++) {
				Symbol currSymbol = symbols[i][j];
				Sprite currSprite = spaceAtlas.createSprite(currSymbol.getName());
				currSprite.setSize(symbolSize, symbolSize);
				currSprite.setCenter(
						cardCircle.x + (cardCircle.radius * MathUtils.cosDeg(angle)) / 2f,
						cardCircle.y + (cardCircle.radius * MathUtils.sinDeg(angle)) / 2f
				);
				symbolSprites[i][j] = new SymbolSprite(currSymbol, currSprite);
					angle += 360f / (float) symbolsPerCard;
			}
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (SymbolSprite[] symbolSpritesRow : symbolSprites) {
			for (SymbolSprite symbolSprite : symbolSpritesRow) {
				symbolSprite.getSprite().draw(batch);
			}
		}
		batch.end();
		return symbolSprites;
	}

}
