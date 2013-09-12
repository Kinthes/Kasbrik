package com.ecs.kasbrik;

import com.badlogic.gdx.Game;
import com.ecs.kasbrik.screens.GameScreen;



public class Kasbrik extends Game {
	public static final String LOG = Kasbrik.class.getSimpleName();
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	@Override
	public void create() {
		this.setScreen(new GameScreen(this));
		
	}
	
	@Override
	public void render(){
		super.render();
	}
}