package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Land;
import core.LaenderGraph;
import core.Fraktion;
import core.Pfad;

public class Brett extends JPanel {

		static final long serialVersionUID = 1L;

		private LaenderGraph laenderGraph;
		private JFrame jframe;
		private int boardOffset;
		private int citySquareSize;
		private Map<Integer, JButton> buttonMap;
		private boolean waitForTurn = true;
		private int clickedId = -1;
		private boolean doneTurn = false;

		private Color neutralColor;
		private Color rot;
		private Color blau;

		private int buttonKlicked;
		
		private BufferedImage bild;

		public Brett(LaenderGraph laenderGraph) {
			
			
			 try {                
		          bild = ImageIO.read(new File("res/karte.jpg"));
		       } catch (IOException ex) {
		    	   System.out.println("fehler beim laden des Bildes");
		    	   ex.printStackTrace();
		       }
			 

			this.laenderGraph = laenderGraph;

			// set Variables
			boardOffset = 200;
			citySquareSize = 40;
			neutralColor = new Color(190, 190, 190);
			rot = new Color(210, 20, 20);
			blau = new Color(20, 20, 210);

			// init jframe
			jframe = new JFrame("test");
			jframe.setSize(bild.getWidth(),
				bild.getHeight());
			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			buttonMap = new HashMap<Integer, JButton>();

			setLayout(null);

			setSize(jframe.getWidth(), jframe.getHeight());

			jframe.add(this);
			jframe.setVisible(false);
			repaint();

		}

		/**
		 * replaces the old cityGraph with the new one and redraws the scene
		 * 
		 * @param new_city_graph
		 *            the new city Graph
		 */
		public void setNewGraph(LaenderGraph new_city_graph) {
			this.laenderGraph = new_city_graph;

			// repaint();
		}

		@Override
		public void paint(Graphics g) {
			
			super.paint(g);

			Iterator<Pfad> pfadIterator = laenderGraph.getPathIterator();

			g.drawImage(bild, 0, 0, null);

			while (pfadIterator.hasNext()) {
				Pfad tmpPathRef = pfadIterator.next();
				Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
				g2.drawLine(tmpPathRef.getFirstLand().getXLocation(), tmpPathRef
						.getFirstLand().getYLocation(), tmpPathRef.getSecondLand()
						.getXLocation(), tmpPathRef.getSecondLand().getYLocation());
			}

			/* JBUTTON */
			for (Land c : laenderGraph) {

				JButton jbutton;

				// math
				int landMidX = c.getXLocation();
				int landMidY = c.getYLocation();
				int squareHalf = (citySquareSize / 2);
				int cityX = landMidX - squareHalf;
				int cityY = landMidY - squareHalf;
				Fraktion owner = c.getOwner();

				// draws the text and the rect
				String drawString = Integer.toString(c.getID());

				if (owner == Fraktion.Blau) {
					drawString += "[" + c.getAnzahlEinheiten() + "]";
				} else if (owner == Fraktion.Rot) {
					drawString += "[" + c.getAnzahlEinheiten() + "]";;
				}

				g.setColor(getCityColor(owner));
				g.fillRect(cityX, cityY, citySquareSize, citySquareSize);

				g.setColor(new Color(0, 0, 0));
				g.drawString(drawString, landMidX - (3 * drawString.length()),
						landMidY + 5);

				// adds button
				jbutton = buttonFactory(cityX, cityY, c.getID());
				buttonMap.put(c.getID(), jbutton);

				add(jbutton);

			}

		}

		private JButton buttonFactory(int x, int y, final int id) {

			JButton result = new JButton();
			result.setBounds(x, y, citySquareSize, citySquareSize);

			result.setContentAreaFilled(false);
			result.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					
					if (waitForTurn) {
						waitForTurn = false;
						doneTurn = true;
						clickedId = id;
						System.out.println(id);
						// Board.this.notify();
					}

				}
			});

			return result;
		}

		public int getID() {
			System.out.println("test");
			this.waitForTurn = true;
			while (!doneTurn) {

				try {
					Thread.sleep(200);
					System.out.println("waiting");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			doneTurn = false;
			return this.clickedId;
		}

		public Color getCityColor(Fraktion f) {
			if (f == Fraktion.Blau) {
				return blau;
			} else if (f == Fraktion.Rot) {
				return rot;
			}
			return neutralColor;
		}

		public void setGraph(LaenderGraph graph) {
			laenderGraph = graph;
		}

		public LaenderGraph getGraph() {
			return laenderGraph;
		}
	

}
