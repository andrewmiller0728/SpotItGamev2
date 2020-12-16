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

public class SpotItGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpotItInputProcessor inputProcessor;

	ShapeRenderer shapeRenderer;

	SpriteBatch batch;
	TextureAtlas spaceAtlas;
	SymbolSet spaceSet;
	GameMaster gm;
	Sprite[] sprites;

	int symbolsPerCard;
	float cardRadius, symbolSize;

	@Override
	public void create () {
		camera = new OrthographicCamera(3000f, 2000f);
		inputProcessor = new SpotItInputProcessor(camera);
		Gdx.input.setInputProcessor(inputProcessor);

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
		sprites = new Sprite[symbolsPerCard * 2];

		cardRadius = camera.viewportHeight / 4f;
		symbolSize = cardRadius * 2f / symbolsPerCard;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0x2b / 255f, 0x2b / 255f, 0x2b / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		drawCards(gm.getCurrCardPair());
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		spaceAtlas.dispose();
	}

	private Sprite[] drawCards(Card[] cards) {
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

		sprites = new Sprite[symbolsPerCard * 2];
		int spritesIndex = 0;
		for (int i = 0; i < cardCircles.length; i++) {
			float angle = 0f;
			Circle cardCircle = cardCircles[i];
			for (Symbol symbol : symbols[i]) {
				Sprite currSprite = spaceAtlas.createSprite(symbol.getName());
				currSprite.setSize(symbolSize, symbolSize);
				currSprite.setCenter(
						cardCircle.x + (cardCircle.radius * MathUtils.cosDeg(angle)) / 2f,
						cardCircle.y + (cardCircle.radius * MathUtils.sinDeg(angle)) / 2f
				);
				sprites[spritesIndex] = currSprite;
				spritesIndex++;
				angle += 360f / (float) symbolsPerCard;
			}
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Sprite sprite : sprites) {
			sprite.draw(batch);
		}
		batch.end();
		return sprites;
	}

}
