package com.ecs.kasbrik.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ecs.kasbrik.res.Data;

public class Raquette extends Elmt {	
		
	public Raquette(float x, float y){
		this.position=new Vector2(x,y);
		this.vitesse=new Vector2(5.5f,0);
		this.setHeight(Data.RAQUETTE_SIZE_Y);
		this.setWidth(Data.RAQUETTE_SIZE_X);
	}
	
	 @Override
	 public void draw(SpriteBatch batch, float parentAlpha) {		
		 batch.setColor(Color.WHITE);		 
         batch.draw(Data.raquette, this.position.x, this.position.y);
	 }
	

	
}