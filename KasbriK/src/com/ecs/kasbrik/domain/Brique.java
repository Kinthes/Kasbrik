package com.ecs.kasbrik.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ecs.kasbrik.res.Data;

public class Brique extends Elmt{
	
	public static enum TypeBrique{
		OneHit, TwoHit, ThreeHit, Explosive, Indestructible;
	}
	
	private TypeBrique 	typeBrique;
	private Color		couleur;
		
	public Brique(float x, float y, Color couleur, TypeBrique typeBrique){
		this.position=new Vector2(x,y);
		this.couleur=couleur;
		this.typeBrique=typeBrique;
		this.vitesse=new Vector2(0,0);
		this.setHeight(Data.BRIQUE_SIZE_Y);
		this.setWidth(Data.BRIQUE_SIZE_X);
	}
	
	 @Override
	 public void draw(SpriteBatch batch, float parentAlpha) {
		// Color color = getColor();		
        batch.setColor(couleur.r, couleur.g, couleur.b, couleur.a * parentAlpha);
        batch.draw(Data.brique,this.position.x, this.position.y);
	 }
	

	
	public TypeBrique getTypeBrique() {
		return typeBrique;
	}

	public void setTypeBrique(TypeBrique typeBrique) {
		this.typeBrique = typeBrique;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}	
}
