package core;

import java.awt.Color;

public class Spieler {
	
	private Color farbe;
	private String name;
	Fraktion fraktion;
	
	public Spieler(Color c, String n, Fraktion f) {
		farbe = c;
		name = n;
		fraktion = f;
				
			
	}
	
	public int würfeln() {
		return (int) ((Math.random())*6+1);
	}
	
	
	public boolean angreifen() {
		return false;
	}
	
	public boolean makeMove(Land von, Land nach, int anzahlEinheiten, LaenderGraph laenderGraph) {
		
		// nur erlaubt falls von Land aktuellem spieler gehört und nach Land anderem spieler
		// ausserdem muss eine kante zwischen den zwei ländern existieren
		if(von.getOwner().equals(this.getFraktion()) &&
		   !nach.getOwner().equals(this.getFraktion()) &&
		   laenderGraph.getNachbarn(von).containsID(nach.getID())) {
			while(von.getAnzahlEinheiten() > 1 && nach.getAnzahlEinheiten() > 0) {
				
				int angriff = würfeln();
				int verteidigung = würfeln();
				
				if(verteidigung >= angriff)
					von.setAnzahlEinheiten(von.getAnzahlEinheiten() - 1);
				else 
					nach.setAnzahlEinheiten(nach.getAnzahlEinheiten() - 1);
				
				//angreifen()
				
				
			}
			
			if(nach.getAnzahlEinheiten() == 0) {
				laenderGraph.getLaenderList().changeLandFraktion(nach.getID(), this.getFraktion(), (int) (von.getAnzahlEinheiten() / 2 ));
				laenderGraph.getLaenderList().changeLandFraktion(von.getID(), this.getFraktion(), von.getAnzahlEinheiten() - (int) (von.getAnzahlEinheiten() / 2 ));
				
			}
			else {
				laenderGraph.getLaenderList().changeLandFraktion(nach.getID(), nach.getOwner(), nach.getAnzahlEinheiten());
				laenderGraph.getLaenderList().changeLandFraktion(von.getID(), von.getOwner(), von.getAnzahlEinheiten());
			}
			
			return true;
			
		}
		else 
			return false;
			
	}
	
	public boolean einheitenSetzen() {
		return false;
	}
	
	public Fraktion getFraktion()  {
		return this.fraktion;
	}

}
