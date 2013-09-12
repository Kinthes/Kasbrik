package com.ecs.kasbrik.domain;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Elmt extends Actor{
	Vector2 position;//=new Vector2();
	Vector2 vitesse;//=new Vector2();
	

	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getVitesse() {
		return vitesse;
	}
	public void setVitesse(Vector2 vitesse) {
		this.vitesse = vitesse;
	}
}
