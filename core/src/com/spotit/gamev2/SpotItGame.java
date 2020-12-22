package com.spotit.gamev2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SpotItGame extends ApplicationAdapter {

	ShapeRenderer shapeRenderer;

	SpriteBatch batch;
	BitmapFont font;

	GameMaster gm;

	OrthographicCamera camera;
	SpotItInputProcessor inputProcessor;

	int symbolsPerCard;
	float symbolSize;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("segoeUIblack_128.fnt"));
		font.setColor(Color.WHITE);

		gm = new GameMaster("SpaceIcons.atlas", symbolsPerCard = 8, "Charlotte");
		gm.updateCardPair();

		camera = new OrthographicCamera(3000f, 2000f);
		inputProcessor = new SpotItInputProcessor(camera, gm);
		Gdx.input.setInputProcessor(inputProcessor);

		symbolSize = camera.viewportHeight / 4f * 2f / symbolsPerCard;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0x2b / 255f, 0x2b / 255f, 0x2b / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		runGameScreen();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		gm.getDeck().getTextureAtlas().dispose();
		font.dispose();
	}

	private void runGameScreen() {
		try {
			drawCards(gm.getCurrCardPair());

			String playerText = String.format("Player: %s", gm.getPlayer().getName());
			String scoreText = String.format("Score: %d", gm.getPlayer().getScore());
			batch.begin();
			font.draw(
					batch,
					playerText + "    " + scoreText,
					3f / 4f * camera.viewportWidth / -2f,
					3f / 4f * camera.viewportHeight / 2f
			);
			batch.end();
		}
		catch (NullPointerException npe) {
			batch.begin();
			font.draw(
					batch,
					"GAME OVER",
					3f / 4f * camera.viewportWidth / -2f,
					3f / 4f * camera.viewportHeight / 2f
			);
			batch.end();
		}
	}

	private void drawCards(Card[] cards) {
		cards[0].setCardPosition(new Vector2(camera.viewportWidth / -4f, 0f));
		cards[0].setCardRadius(camera.viewportHeight / 4f);
		cards[1].setCardPosition(new Vector2(camera.viewportWidth / 4f, 0f));
		cards[1].setCardRadius((camera.viewportHeight / 4f));

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (Card card : cards) {
			shapeRenderer.setColor(new Color(0xB1 / 255f, 0x5B / 255f, 0x2E / 255f, 1));
			shapeRenderer.circle(card.getCircle().x, card.getCircle().y, card.getCircle().radius);
			shapeRenderer.setColor(Color.DARK_GRAY);
			shapeRenderer.circle(card.getCircle().x, card.getCircle().y, card.getCircle().radius * 0.95f);
		}
		shapeRenderer.end();

		Symbol[][] symbols = {
				cards[0].getSymbols(),
				cards[1].getSymbols()
		};

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i = 0; i < cards.length; i++) {
			float angle = 0f;
			for (int j = 0; j < symbols[i].length; j++) {
				Sprite currSprite = symbols[i][j].getSprite();
				currSprite.setSize(symbolSize, symbolSize);
				currSprite.setCenter(
						cards[i].getCircle().x + (cards[i].getCircle().radius * MathUtils.cosDeg(angle)) / 2f,
						cards[i].getCircle().y + (cards[i].getCircle().radius * MathUtils.sinDeg(angle)) / 2f
				);
				currSprite.draw(batch);
				angle += 360f / (float) symbolsPerCard;
			}
		}
		batch.end();
	}

}
