package core;

public class Spiel {

	Spieler spieler1;
	Spieler spieler2;
	LaenderGraph laenderGraph;
	
	public Spiel(Spieler p1, Spieler p2, LaenderGraph graph) {
		spieler1 = p1;
		spieler2 = p2;
		laenderGraph = graph;
		
		p2.makeMove(laenderGraph.getLaenderList().getLandByID(1), laenderGraph.getLaenderList().getLandByID(0), 5, laenderGraph);
		
		//System.out.println("kontrolliert Afrika? ");
		//System.out.println(kontroliertAfrika(spieler1));
		//System.out.println(kontroliertAfrika(spieler2));
		
		//System.out.println("Verstaerkungen: ");
		//System.out.println(ermittleVerstaerkung(spieler1));
		//System.out.println(ermittleVerstaerkung(spieler2));
	}
	
	
	
	// funktion soll am Anfang erlauben Land zu bestetzen 
	// falls ungültige auswahl nochmale wählen
	public Land besetzeLand(Spieler s) {
		return null;
	}
	
	//zufallsfaktor für angriff / verteidigung
	

	
	public int ermittleVerstaerkung(Spieler s) {
		int verstaerkungen = 3;
		
		if(kontroliertAfrika(s)) {
			verstaerkungen += 3;
		}
		if(kontroliertSüdamerika(s)) {
			verstaerkungen += 2;
		}
		if(kontroliertNordamerika(s)) {
			verstaerkungen += 5;
		}
		if(kontroliertAsien(s)) {
			verstaerkungen += 7;
		}
		if(kontroliertEuropa(s)) {
			verstaerkungen += 5;
		}
		if(kontroliertAustralien(s)) {
			verstaerkungen += 2;
		}
		
		return verstaerkungen;
	}
	
	public boolean kontroliertAfrika(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(4).getOwner())) {
			res = false;
		}
		
		return res;
	}
	
	public boolean kontroliertAustralien(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(5).getOwner())) {
			res = false;
		}
		
		return res;	}
	
	public boolean kontroliertNordamerika(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(0).getOwner())) {
			res = false;
		}
		
		return res;	}
	
	public boolean kontroliertSüdamerika(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(2).getOwner())) {
			res = false;
		}
		
		return res;	}
	
	public boolean kontroliertEuropa(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(1).getOwner())) {
			res = false;
		}
		
		return res;	}
	
	public boolean kontroliertAsien(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(3).getOwner())) {
			res = false;
		}
		
		return res;	}
}
