package com.spotit.gamev2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SpotItGame extends ApplicationAdapter {

	ShapeRenderer shapeRenderer;

	SpriteBatch batch;
	BitmapFont font;

	Deck deck;
	CardPair currCardPair;
	CommandQueue commandQueue;

	OrthographicCamera camera;
	SpotItInputProcessor inputProcessor;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("segoeUIblack_128.fnt"));
		font.setColor(Color.WHITE);

		deck = new Deck("SpaceIcons.atlas", 6);
		currCardPair = null;
		commandQueue = new CommandQueue();

		camera = new OrthographicCamera(900f, 600f);
		inputProcessor = new SpotItInputProcessor(camera, commandQueue);
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0x2b / 255f, 0x2b / 255f, 0x2b / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Symbol symbol : currCardPair.getSymbols()) {
			symbol.getSprite().draw(batch);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
	}

	private void update() {
		if (currCardPair == null || currCardPair.solved) {
			currCardPair = deck.pickCardPair();

			float angle = 0f; // Degrees

			Card cardL = currCardPair.cardL;
			cardL.setCardPosition(new Vector2(camera.viewportWidth / -4f, 0f));
			cardL.setCardRadius(100f);
			for (Symbol symbolL : cardL.getSymbols()) {
				symbolL.getSprite().setBounds(
						cardL.getCircle().x + cardL.getCircle().radius * MathUtils.cosDeg(angle),
						cardL.getCircle().y + cardL.getCircle().radius * MathUtils.sinDeg(angle),
						cardL.getCircle().radius * 0.75f,
						cardL.getCircle().radius * 0.75f
				);
				angle += 360f / cardL.getSymbols().length;
			}

			Card cardR = currCardPair.cardR;
			cardR.setCardPosition(new Vector2(camera.viewportWidth / 4f, 0f));
			cardR.setCardRadius(100f);
			for (Symbol symbolR : cardR.getSymbols()) {
				symbolR.getSprite().setBounds(
						cardR.getCircle().x + cardR.getCircle().radius * MathUtils.cosDeg(angle),
						cardR.getCircle().y + cardR.getCircle().radius * MathUtils.sinDeg(angle),
						cardR.getCircle().radius * 0.75f,
						cardR.getCircle().radius * 0.75f
				);
				angle += 360f / cardR.getSymbols().length;
			}
		}

		inputProcessor.setCurrCardPair(currCardPair);
		commandQueue.tick();
	}

}
