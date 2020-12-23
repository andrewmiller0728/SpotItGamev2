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

	OrthographicCamera camera;
	SpotItInputProcessor inputProcessor;

	Deck deck;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("segoeUIblack_128.fnt"));
		font.setColor(Color.WHITE);

		camera = new OrthographicCamera(3000f, 2000f);
		inputProcessor = new SpotItInputProcessor(camera);
		Gdx.input.setInputProcessor(inputProcessor);

		deck = new Deck("SpaceIcons.atlas", 6);
		System.out.println(deck.toFormattedString());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0x2b / 255f, 0x2b / 255f, 0x2b / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		font.draw(batch, "Hello World!", -400f, 30f);
		batch.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		font.dispose();
	}

}
