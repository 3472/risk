package core;

import gui.Brett;

import java.awt.Color;

public class Spieler {
	
	private Color farbe;
	private String name;
	Fraktion fraktion;
	private Brett brett;
	boolean zugZuende;
	
	public Spieler(Color c, String n, Fraktion f, Brett b) {
		farbe = c;
		name = n;
		fraktion = f;
		brett = b;
				
			
	}
	
	public int würfeln() {
		return (int) ((Math.random())*6+1);
	}
	
	
	public boolean angreifen() {
		return false;
	}
	
	public void makeMove(LaenderGraph laenderGraph) {
		



		
		while(!zugZuende) {
			
			int vonID = brett.getID();
			int nachID = brett.getID();
			System.out.println("Von: ");
			
			Land von = laenderGraph.getLaenderList().getLandByID(vonID);
			System.out.println("Nach");
			Land nach = laenderGraph.getLaenderList().getLandByID(nachID);
			
			System.out.println("Zug machen");
			
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
					
				}
				
				if(nach.getAnzahlEinheiten() == 0) {
					laenderGraph.getLaenderList().changeLandFraktion(nach.getID(), this.getFraktion(), (int) (von.getAnzahlEinheiten() / 2 ));
					laenderGraph.getLaenderList().changeLandFraktion(von.getID(), this.getFraktion(), von.getAnzahlEinheiten() - laenderGraph.getLaenderList().getLandByID(nach.getID()).getAnzahlEinheiten());
					
				}
				else {
					laenderGraph.getLaenderList().changeLandFraktion(nach.getID(), nach.getOwner(), nach.getAnzahlEinheiten());
					laenderGraph.getLaenderList().changeLandFraktion(von.getID(), von.getOwner(), von.getAnzahlEinheiten());
				}
			}
		}
		
		
	}
	
	public void einheitenSetzen(int verfuegbareEinheiten, LaenderGraph laenderGraph) {		
		
		while(verfuegbareEinheiten > 0) {
			
			System.out.println("einheiten setzen");
			
			int id = brett.getID();
			
			Land land = laenderGraph.getLaenderList().getLandByID(id);
			
			if(land.getOwner().equals(this.getFraktion())) {
				laenderGraph.getLaenderList().getLandByID(id).setAnzahlEinheiten(land.getAnzahlEinheiten() + 1);
				verfuegbareEinheiten--;
			}
			else if(land.getOwner().equals(Fraktion.Grau)) {
				laenderGraph.getLaenderList().getLandByID(id).setOwner(this.getFraktion());
				laenderGraph.getLaenderList().getLandByID(id).setAnzahlEinheiten(1);	
				verfuegbareEinheiten--;
			}
			
		}
		
	}
	
	public Fraktion getFraktion()  {
		return this.fraktion;
	}
	
public void setZugZuende(Boolean b) {
		zugZuende = b;
	}

@Override
public String toString() {
	return fraktion.toString();
}

}
