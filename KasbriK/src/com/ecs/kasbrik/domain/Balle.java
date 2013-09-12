package com.ecs.kasbrik.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ecs.kasbrik.res.Data;

public class Balle extends Elmt{
	
		private Vector2 orientation; 
		private float   multiplicateurVitesse;

	public Balle(Vector2 position){
		this.position = position;
		this.orientation=new Vector2(1,1);
		this.vitesse=new Vector2(2f,2f);
		this.setHeight(Data.BALLE_SIZE_Y);
		this.setWidth(Data.BALLE_SIZE_X);
		this.multiplicateurVitesse=1;
	}
	
	 public float getMultiplicateurVitesse() {
		return multiplicateurVitesse;
	}

	public void setMultiplicateurVitesse(float multiplicateurVitesse) {
		this.multiplicateurVitesse = multiplicateurVitesse;
	}

	public Vector2 getOrientation() {
		return orientation;
	}

	public void setOrientation(Vector2 orientation) {
		this.orientation = orientation;
	}

	@Override
	 public void draw(SpriteBatch batch, float parentAlpha) {
		 batch.setColor(Color.WHITE);		
         batch.draw(Data.balle, this.position.x, this.position.y);
	 }



}
