package com.ecs.kasbrik.domain;

public class Level {
	public static final int HorBriques=12;
	public static final int VertBriques=10;
	
	private static int NbLevels=0;
	
	private int id;
	private Brique[][] briqueTab;
	
	public Level(){
		briqueTab=new Brique[VertBriques][HorBriques];
		id=NbLevels;
		NbLevels++;
	}

	public Brique[][] getBriqueTab() {
		return briqueTab;
	}

	public void setBriqueTab(Brique[][] briqueTab) {
		this.briqueTab = briqueTab;
	}

	public static int getHorbriques() {
		return HorBriques;
	}

	public static int getVertbriques() {
		return VertBriques;
	}
}
