package core;

public class Land {

	
	private int xLocation;
	private int yLocation;
	private int IDNumber;
	private Fraktion fraktion;
	private int anzahlEinheiten;
	
	public Land(int IDNumber, int xLocation, int yLocation, Fraktion f, int einheiten) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.IDNumber = IDNumber;
		this.fraktion = f;
		this.anzahlEinheiten = einheiten;
	}



	public int getXLocation() {
		return xLocation;
	}

	public int getYLocation() {
		return yLocation;
	}

	public int getID() {
		return IDNumber;
	}

	public Fraktion getOwner() {
		return fraktion;
	}

	public void setOwner(Fraktion f) {
		fraktion = f;
	}
	
	public int getAnzahlEinheiten() {
		return this.anzahlEinheiten;
	}
	
	public void setAnzahlEinheiten(int n) {
		this.anzahlEinheiten = n;
	}

	public boolean equals(Land land) {
		return this.getID() == land.getID();
	}
	
	@Override
	public String toString() {
		return this.getID() + "";
	}
}
