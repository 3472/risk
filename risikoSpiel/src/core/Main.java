package core;

import java.awt.Color;

import gui.Brett;
import gui.Frame;

public class Main {
	static LaenderGraph laenderGraph;
	static Brett brett;
	static Frame frame;
	static Spiel spiel;
	static Spieler spieler1 = new Spieler(new Color(0,0,0), "spieler1", Fraktion.Blau);
	static Spieler spieler2 = new Spieler(new Color(0,0,0), "spieler2", Fraktion.Rot);
	public static void main(String[] args) {
		System.out.println("test");
		laenderGraph = new LaenderGraph();
		
		System.out.println("Nachbarn von land 0");
		System.out.println(laenderGraph.getNachbarn(laenderGraph.getLaenderList().getLandByID(0)));
		brett = new Brett(laenderGraph);
		spiel = new Spiel(spieler1, spieler2, laenderGraph);
		frame = new Frame(brett, spiel);
	}
}
