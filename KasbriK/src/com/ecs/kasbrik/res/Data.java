package com.ecs.kasbrik.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Data {
	public static final int BALLE_SIZE_X = 16;
	public static final int BALLE_SIZE_Y = 16;
	public static final int BRIQUE_SIZE_X = 64;
	public static final int BRIQUE_SIZE_Y = 32;
	public static final int RAQUETTE_SIZE_X = 64;
	public static final int RAQUETTE_SIZE_Y = 32;
	
	public static TextureAtlas atlas;
	 public static TextureRegion balle;
	 public static TextureRegion raquette;
	 public static TextureRegion brique;
	 
	 
	 public Data() {
	  atlas = new TextureAtlas(Gdx.files.internal("data/images.atlas"));
	  balle = atlas.findRegion("balle");
	  raquette = atlas.findRegion("raquette");
	  brique = atlas.findRegion("brique");
	 }

	 public void dispose() {
	  atlas.dispose();
	 }
}
