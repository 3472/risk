package core;

import gui.Brett;
import gui.Frame;

public class Spiel {

	Spieler spieler1;
	Spieler spieler2;
	Spieler aktuellerSpieler;
	LaenderGraph laenderGraph;
	Frame frame;
	Brett brett;
	public Spiel(Spieler p1, Spieler p2, LaenderGraph graph, Brett b) {
		spieler1 = p1;
		spieler2 = p2;
		aktuellerSpieler = spieler1;
		laenderGraph = graph;
		brett = b;
		frame = new Frame(brett, this);
		
		//p2.makeMove(laenderGraph.getLaenderList().getLandByID(1), laenderGraph.getLaenderList().getLandByID(0), 5, laenderGraph);
		//System.out.println("kontrolliert Afrika? ");
		//System.out.println(kontroliertAfrika(spieler1));
		//System.out.println(kontroliertAfrika(spieler2));
		
		//System.out.println("Verstaerkungen: ");
		//System.out.println(ermittleVerstaerkung(spieler1));
		//System.out.println(ermittleVerstaerkung(spieler2));
	}
	
	
	public void hauptPhase() {
		
		aktuellerSpieler = spieler1;
		 System.out.println("hauptphase");
		while(laenderGraph.getAnzahlLaender(spieler1.getFraktion()) != 0 && laenderGraph.getAnzahlLaender(spieler2.getFraktion()) != 0) {
			
			int verfuegbareEinheiten = ermittleVerstaerkung(aktuellerSpieler);
			aktuellerSpieler.einheitenSetzen(verfuegbareEinheiten, laenderGraph);
			aktuellerSpieler.makeMove(laenderGraph);
			wechsleSpieler(aktuellerSpieler);
			aktuellerSpieler.zugZuende = false;
			frame.repaint();
		}
		
	}
	
	public void wechsleSpieler(Spieler s) {
		System.out.println("spieler wechseln");
		if(s.fraktion.equals(spieler1.fraktion))
			aktuellerSpieler = spieler2;
		else if (s.fraktion.equals(spieler2.fraktion))
			aktuellerSpieler = spieler1;
		
		frame.getBeendenButton().doClick();
	}
	
	// funktion soll am Anfang erlauben Land zu bestetzen 
	// falls ungültige auswahl nochmale wählen
	public void setzPhase(int startEinheiten) {
		while(startEinheiten > 0) {
			spieler1.einheitenSetzen(1, laenderGraph);
			wechsleSpieler(aktuellerSpieler);
			spieler2.einheitenSetzen(1, laenderGraph);
			wechsleSpieler(aktuellerSpieler);
			startEinheiten--;
		}
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
		
		return res;	
	}
	
	public boolean kontroliertAsien(Spieler s) {
		boolean res = true;
		if(!s.fraktion.equals(laenderGraph.getLaenderList().getLandByID(3).getOwner())) {
			res = false;
		}
		
		return res;	
	}
	
	public Spieler getAktuellerSpieler() {
		return this.aktuellerSpieler;
	}
	
	
}
