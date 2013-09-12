package com.ecs.kasbrik.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ecs.kasbrik.Kasbrik;

public abstract class AbstractScreen implements Screen {
	protected final Kasbrik kasbrik;	
	protected final Stage	stage;
	
	private SpriteBatch	batch;
	
	
	public AbstractScreen(Kasbrik kasbrik){
		this.kasbrik=kasbrik;
		this.stage = new Stage();
		this.batch=new SpriteBatch();
	}
/*****************************************************************************************************************************************
*****************************************************************************************************************************************/
	public SpriteBatch getBatch()
    {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }
	/*****************************************************************************************************************************************
	*****************************************************************************************************************************************/	
	@Override
	 public void show() {
	  Gdx.input.setInputProcessor(stage);
	 }

	 @Override
	 public void hide() {
	  Gdx.input.setInputProcessor(null);
	 }

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		if(batch!=null)batch.dispose();		
	}

}
