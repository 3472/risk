package core;

import java.util.ArrayList;
import java.util.Iterator;

public class LaenderGraph implements Iterable<Land> {

	private ArrayList<Pfad> pfadList;
	private VertexSet laenderList;
	
	public LaenderGraph() {
		this.initKarte();
	}

	private boolean initKarte() {

		pfadList = new ArrayList<>();
		laenderList = new VertexSet();

		laenderList.addVertex(new Land(0, 150, 200, Fraktion.Grau, 2));
		laenderList.addVertex(new Land(1, 400, 200, Fraktion.Grau, 10));
		laenderList.addVertex(new Land(2, 200, 400, Fraktion.Grau, 0));
		laenderList.addVertex(new Land(3, 600, 200, Fraktion.Grau, 0));
		laenderList.addVertex(new Land(4, 400, 400, Fraktion.Grau, 0));
		laenderList.addVertex(new Land(5, 650, 400, Fraktion.Grau, 0));

		pfadList.add(new Pfad(laenderList.getLandByID(0), laenderList
				.getLandByID(1)));
		pfadList.add(new Pfad(laenderList.getLandByID(1), laenderList
				.getLandByID(2)));
		pfadList.add(new Pfad(laenderList.getLandByID(2), laenderList
				.getLandByID(0)));
		pfadList.add(new Pfad(laenderList.getLandByID(3), laenderList
				.getLandByID(5)));
		pfadList.add(new Pfad(laenderList.getLandByID(3), laenderList
				.getLandByID(1)));
		pfadList.add(new Pfad(laenderList.getLandByID(3), laenderList
				.getLandByID(0)));
		pfadList.add(new Pfad(laenderList.getLandByID(3), laenderList
				.getLandByID(4)));
		pfadList.add(new Pfad(laenderList.getLandByID(1), laenderList
				.getLandByID(4)));
		pfadList.add(new Pfad(laenderList.getLandByID(2), laenderList
				.getLandByID(4)));

		return true;
	}
	
	public VertexSet getNachbarn(Land land) {

		if (laenderList.containsID(land.getID())) {

			VertexSet result = new VertexSet();
			Land landOne;
			Land landTwo;
			for (Pfad p : pfadList) {
				landOne = p.getFirstLand();
				landTwo = p.getSecondLand();
				if (landOne.equals(land)) {
					if (!result.containsID(landTwo.getID())) {
						result.addVertex(landTwo);
					}

				} else if (landTwo.equals(land)) {
					if (!result.containsID(landOne.getID())) {
						result.addVertex(landOne);
					}
				}
			}

			return result;
		} else {
			return null;
		}
	}
	
	public int getAnzahlLaender(Fraktion f) {
		
		int res = 0;
		
		Iterator<Land> it = iterator();
		
		while(it.hasNext()) {
			if(it.next().getOwner().equals(f)) {
				res++;
			}
		}
		return res;
	}
	
	//public changeFraktionOfLand(Land land, Fraktion fraktion) {
	//	
	//}

	public Iterator<Land> iterator() {
		return laenderList.iterator();
	}

	public Iterator<Pfad> getPathIterator() {
		return pfadList.iterator();
	}
	
	public VertexSet getLaenderList() {
		return this.laenderList;
	}

}