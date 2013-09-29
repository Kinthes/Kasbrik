package com.ecs.kasbrik.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.ecs.kasbrik.Kasbrik;
import com.ecs.kasbrik.domain.Balle;
import com.ecs.kasbrik.domain.Brique;
import com.ecs.kasbrik.domain.Level;
import com.ecs.kasbrik.domain.Raquette;
import com.ecs.kasbrik.res.Data;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class GameScreen extends AbstractScreen {
	private Data	data;
	
	private Balle 	balle;	
	private Raquette raquette;
	
	//faire une liste
	private Level level1=new Level();
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>(){
		@Override
		protected Rectangle newObject(){
			return new Rectangle();
		}
	};
	
	public GameScreen(Kasbrik kasbrik) {
		super(kasbrik);
		
		data=new Data();
		
		
		
		raquette=new Raquette(Kasbrik.WIDTH/2 - Data.raquette.getRegionWidth()/2 ,3f);
		stage.addActor(raquette);	
		
		initLevel();
		
		balle=new Balle(new Vector2(50f,50f));
		stage.addActor(balle);		
	}
	
	private void initLevel(){
		for(int j=0;j<Level.VertBriques;j++)
			for(int i=0;i<Level.HorBriques;i++){
			float inc_y=Kasbrik.HEIGHT-(data.BRIQUE_SIZE_Y*Level.VertBriques);	
			float inc_x=(Kasbrik.WIDTH-(data.BRIQUE_SIZE_X*Level.HorBriques))/2;
			level1.getBriqueTab()[j][i]=new Brique( i*64+inc_x,  j*32+inc_y,  Color.ORANGE, Brique.TypeBrique.Indestructible);
			stage.addActor(level1.getBriqueTab()[j][i]);
			
			level1.getBriqueTab()[0][0].setCouleur(Color.BLUE);
			
			//Manière de procéder pour retirer une brique détruite
		//	stage.getRoot().removeActor(level1.getBriqueTab()[3][5]);
		//	level1.getBriqueTab()[3][5]=null;
			
		//	if(level1.getBriqueTab()[3][5]!=null)
		//		raquette.getPosition().y+=0.2f;
		
			}		
	}

	
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		  Gdx.gl.glClearColor(0, 0, 0, 1);
		  Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		  stage.act(delta);
		  stage.draw();
		  
		  raquette.getPosition().x+= Gdx.input.getAccelerometerY() * raquette.getVitesse().x; 
		  if(raquette.getPosition().x < 0) 
			  raquette.getPosition().x=0;
		  else if(raquette.getPosition().x > Kasbrik.WIDTH - Data.RAQUETTE_SIZE_X )
			  raquette.getPosition().x = Kasbrik.WIDTH - Data.RAQUETTE_SIZE_X;
		  moveBalle(delta, balle);
	}
	
	@Override
	public void dispose(){
		super.dispose();
		data.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.setViewport(Kasbrik.WIDTH, Kasbrik.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(),0);
	}

	
	public void moveBalle(float delta, Balle balle){
		boolean rebond_x=false, rebond_y=false;
		Vector2 pos = balle.getPosition();
		float inc_y=Kasbrik.HEIGHT-(data.BRIQUE_SIZE_Y*Level.VertBriques);	
		float inc_x=(Kasbrik.WIDTH-(data.BRIQUE_SIZE_X*Level.HorBriques))/2;
		int tiers=(int)(data.BALLE_SIZE_X / 3);
		
		//Gdx.app.log(Kasbrik.LOG, "balle speed : "+balle.getVitesse().x*balle.getOrientation().x*balle.getMultiplicateurVitesse()+","+balle.getVitesse().y*balle.getOrientation().y*balle.getMultiplicateurVitesse());		
		//Gdx.app.log(Kasbrik.LOG, "raquette location : "+raquette.getPosition());	
		
		// premier hitpoint = supérieur gauche ****************************************************
		int ind_x=(int)((balle.getPosition().x + tiers - inc_x)/Data.BRIQUE_SIZE_X);
		int ind_y=(int)((balle.getPosition().y + Data.BALLE_SIZE_Y - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y - Data.BALLE_SIZE_Y && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_y=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.y = ind_y*Data.BRIQUE_SIZE_Y+inc_y-Data.BALLE_SIZE_Y;
			}
		
		// 2eme hitpoint = supérieur droit ****************************************************
		ind_x=(int)((balle.getPosition().x + tiers + tiers - inc_x)/Data.BRIQUE_SIZE_X);
				
		if(balle.getPosition().y > inc_y - Data.BALLE_SIZE_Y && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0 )//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_y=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.y = ind_y*Data.BRIQUE_SIZE_Y+inc_y-Data.BALLE_SIZE_Y;
			}
		
		// 3eme hitpoint = droit haut ****************************************************
		ind_x=(int)((balle.getPosition().x + Data.BALLE_SIZE_X - inc_x)/Data.BRIQUE_SIZE_X);
		ind_y=(int)((balle.getPosition().y + tiers + tiers - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y - tiers && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_x=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.x = ind_x*Data.BRIQUE_SIZE_X+inc_x-Data.BALLE_SIZE_X;
			}
		
		// 4eme hitpoint = droit bas ****************************************************
		
		ind_y=(int)((balle.getPosition().y + tiers - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y - tiers - tiers && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_x=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.x = ind_x*Data.BRIQUE_SIZE_X+inc_x-Data.BALLE_SIZE_X;
			}
		
		// 5eme hitpoint = inférieur droit ****************************************************
		ind_x=(int)((balle.getPosition().x + tiers + tiers - inc_x)/Data.BRIQUE_SIZE_X);
		ind_y=(int)((balle.getPosition().y - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_y=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.y = ind_y*Data.BRIQUE_SIZE_Y+inc_y+Data.BRIQUE_SIZE_Y;
			}
		
		// 6eme hitpoint = inférieur gauche ****************************************************
		ind_x=(int)((balle.getPosition().x + tiers - inc_x)/Data.BRIQUE_SIZE_X);
		ind_y=(int)((balle.getPosition().y - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_y=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.y = ind_y*Data.BRIQUE_SIZE_Y+inc_y+Data.BRIQUE_SIZE_Y;
			}
		
		// 7eme hitpoint = gauche bas ****************************************************
		ind_x=(int)((balle.getPosition().x - inc_x)/Data.BRIQUE_SIZE_X);
		ind_y=(int)((balle.getPosition().y + tiers - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y - tiers - tiers && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_x=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.x = ind_x*Data.BRIQUE_SIZE_X+inc_x+Data.BRIQUE_SIZE_X;
			}
		
		// 8eme hitpoint = gauche haut ****************************************************
		
		ind_y=(int)((balle.getPosition().y + tiers + tiers - inc_y)/Data.BRIQUE_SIZE_Y);
		
		if(balle.getPosition().y > inc_y - tiers && ind_y<Level.getVertbriques() && ind_x<Level.getHorbriques() && ind_x>=0)//level1.getBriqueTab()[ind_y][ind_x].setCouleur(Color.BLUE);
			if(level1.getBriqueTab()[ind_y][ind_x]!=null){
				rebond_x=true;
				stage.getRoot().removeActor(level1.getBriqueTab()[ind_y][ind_x]);
				level1.getBriqueTab()[ind_y][ind_x]=null;
				pos.x = ind_x*Data.BRIQUE_SIZE_X+inc_x+Data.BRIQUE_SIZE_X;
			}
		
		if(pos.x >= kasbrik.WIDTH-balle.getWidth()-inc_x || pos.x<=inc_x || rebond_x){		
			balle.getOrientation().x=-balle.getOrientation().x;
			if(pos.x >= kasbrik.WIDTH-balle.getWidth()-inc_x)
				pos.x = kasbrik.WIDTH-balle.getWidth()-inc_x;
			else if(pos.x<=inc_x)
				pos.x=inc_x;
		}
		
		if(pos.y >= kasbrik.HEIGHT-balle.getHeight() || pos.y<=0 || rebond_y)		
			balle.getOrientation().y=-balle.getOrientation().y;		
		
		//collision raquette****************************************************************
				
		Rectangle balleRect=rectPool.obtain();
		balleRect.set(balle.getPosition().x, balle.getPosition().y, Data.BALLE_SIZE_X, Data.BALLE_SIZE_Y);
		Rectangle raqRect=rectPool.obtain();
		raqRect.set(raquette.getPosition().x, raquette.getPosition().y, Data.RAQUETTE_SIZE_X, Data.RAQUETTE_SIZE_Y);
		
		if(balleRect.overlaps(raqRect)){
						
			double dec=((balle.getPosition().x+Data.BALLE_SIZE_X/2) - (raquette.getPosition().x+(Data.RAQUETTE_SIZE_X/2)))/ (Data.RAQUETTE_SIZE_X/2);
			if(dec<0){
				balle.getOrientation().x=-1;
				dec=-dec;
			}
			else
				balle.getOrientation().x=1;
			//balle.getOrientation().y=-balle.getOrientation().y;
			
			if(dec>=-0.95f && dec <=0.95f){	//(-1,1) angle trop aigu
				balle.getOrientation().y=-balle.getOrientation().y;
				double teta= Math.acos(dec);
				double y_vec = Math.sin(teta);
			
				/*Gdx.app.log(Kasbrik.LOG, "position balle : "+balle.getPosition());
				Gdx.app.log(Kasbrik.LOG, "position raquette : "+raquette.getPosition());
				Gdx.app.log(Kasbrik.LOG, "balle vitesse x : "+dec);
				Gdx.app.log(Kasbrik.LOG, "balle vitesse y : "+y_vec);
				Gdx.app.log(Kasbrik.LOG, "angle : "+teta);*/
			
				balle.getVitesse().x=(float)dec*2.5f;
				balle.getVitesse().y=(float)y_vec*2.5f;
				balle.getPosition().y=raquette.getPosition().y+Data.RAQUETTE_SIZE_Y;
				Gdx.app.log(Kasbrik.LOG, "vitesse balle : "+balle.getVitesse());
			}else{ 				
				//colision mais pas sur la surface
				rebond_x=true;
				if(balle.getOrientation().x==1)
					balle.getPosition().x=raquette.getPosition().x+Data.RAQUETTE_SIZE_X;
				else
					balle.getPosition().x=raquette.getPosition().x-Data.BALLE_SIZE_X;
			}
		}
		rectPool.free(balleRect);
		rectPool.free(raqRect);
		
		/*if(pos.x >= kasbrik.WIDTH-balle.getWidth() || pos.x<=0 || rebond_x)		
			balle.getOrientation().x=-balle.getOrientation().x;		
		
		if(pos.y >= kasbrik.HEIGHT-balle.getHeight() || pos.y<=0 || rebond_y)		
			balle.getOrientation().y=-balle.getOrientation().y;		*/	
		
		pos.x+=balle.getVitesse().x*balle.getOrientation().x*balle.getMultiplicateurVitesse();
		pos.y+=balle.getVitesse().y*balle.getOrientation().y*balle.getMultiplicateurVitesse();
		balle.setPosition(pos);
		if(balle.getMultiplicateurVitesse()<3)
			balle.setMultiplicateurVitesse((float)(balle.getMultiplicateurVitesse() + 0.0001));
	}

}
