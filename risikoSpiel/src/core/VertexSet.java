package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VertexSet implements Iterable<Land> {

	private Map<Integer, Land> laenderMap;

	public VertexSet() {
		laenderMap = new HashMap<Integer, Land>();
	}

	public VertexSet(Land... laender) {
		for (Land c : laender) {
			addVertex(c);
		}
	}

	/**
	 * adds a land to the Container
	 * 
	 * @param city
	 */
	public void addVertex(Land land) {
		laenderMap.put(land.getID(), land);
	}

	/**
	 * adds all lands from the VertexSet, witch are not already in the set
	 * 
	 * @param s
	 */
	public void addVertexSet(VertexSet s) {
		Iterator<Land> it = s.iterator();
		while (it.hasNext()) {
			Land tmp = it.next();
			if (!containsID(tmp.getID())) {
				addVertex(tmp);
			}
		}
	}

	/**
	 * if needed "containsID()" should be checked first, if it's possible that
	 * the id is not inside the container
	 * 
	 * returns null if the Container does not contain the id
	 * 
	 * @param id
	 * @return the Land with the given id
	 */
	public Land getLandByID(int id) {
		if (containsID(id)) {
			return laenderMap.get(id);
		}
		return null;
	}

	/**
	 * removes given id if id is part of Set
	 * 
	 * @param id
	 */
	public void removeID(int id) {
		if (laenderMap.containsKey(id)) {
			laenderMap.remove(id);
		}
	}

	/**
	 * 
	 * @return Iterator over all LaenderIDs inside this container
	 */
	public Iterator<Integer> getIDIterator() {
		return laenderMap.keySet().iterator();
	}

	public Iterator<Land> iterator() {
		return laenderMap.values().iterator();
	}

	/**
	 * 
	 * @param id
	 * @return true if the VertexSet Contains the given id, if not: false
	 */
	public boolean containsID(int id) {
		return laenderMap.containsKey(id);
	}

	/**
	 * 
	 * @param that
	 *            VertexSet to compare
	 * @return true if the VertexSets contain the same Citys
	 */
	public boolean equals(VertexSet that) {
		return this.laenderMap.keySet().equals(that.laenderMap.keySet());
	}

	public int hashCode() {
		return this.laenderMap.hashCode();
	}

	public VertexSet copy() {
		VertexSet tmp = new VertexSet();
		tmp.addVertexSet(this);
		return tmp;
	}

	/**
	 * 
	 * @param LandID
	 *            ID of the Land the Owner got changed
	 * @param newOwner
	 *            New Owner of the City
	 * @return returns the new Land(but the Land is already added in the
	 *         function)
	 */
	public Land changeLandFraktion(int landID, Fraktion newOwner, int anzahlEinheiten) {
		int tmpX = laenderMap.get(landID).getXLocation();
		int tmpY = laenderMap.get(landID).getYLocation();
		this.removeID(landID);
		Land c = new Land(landID, tmpX, tmpY, newOwner, anzahlEinheiten);
		this.addVertex(c);
		return c;
	}

	public int getSize() {
		return laenderMap.size();
	}
	
	@Override
	
	public String toString() {
		String res = "";
		
		Iterator<Integer> iterator = this.getIDIterator();
		
		while(iterator.hasNext()) {
			res = res + iterator.next() + " ";
		}
		return res;
		
	}
}
