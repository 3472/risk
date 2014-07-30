package core;


public class Pfad {

	public Pfad(Land a, Land b) {
		first_land = a;
		second_land = b;
	}

	private Land first_land;
	private Land second_land;

	public Land getFirstLand() {
		return first_land;
	}

	public Land getSecondLand() {
		return second_land;
	}

	public int getFirstID() {
		return first_land.getID();
	}

	public int getSecondID() {
		return second_land.getID();
	}

	public boolean containsID(int id) {
		return (first_land.getID() == id || second_land.getID() == id);
	}

	/**
	 * 
	 * @param c
	 *            the new land with overrides the land in this Path with the
	 *            !same! id
	 * 
	 *            containsID() should be called first if the path does not
	 *            contain the landID, nothing happens
	 */
	public Pfad updateLand(Land c) {
		Land first = first_land;
		Land second = second_land;
		if (first_land.getID() == c.getID()) {
			first = c;
		} else if (second_land.getID() == c.getID()) {
			second = c;
		}
		return new Pfad(first, second);
	}
}
