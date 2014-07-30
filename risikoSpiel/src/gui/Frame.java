package gui;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

import core.Spiel;
import core.Fraktion;
import core.Spieler;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class Frame extends JFrame {



		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JLabel rom;
		private JLabel karthago;
		private Brett brett;
		private Spiel spiel;
		private JMenuBar menuBar;
		private JMenu mnStart;

		/**
		 * Launch the application.
		 */

		@Override
		public void paint(Graphics g) {
			
			
			

			String romText = "Rom: Punkte: ";
			String karText = "Karthago: Punkte: ";
			
			brett.repaint();
			super.paint(g);
			/*rom.setText(romText + brett.getGraph().getScore(Owner.Rom));
			karthago.setText(karText
					+ brett.getGraph().getScore(Owner.Cathargo));
			PlayerAbs curPlayer = spiel.getCurrentPlayer();
			amZug.setText("Am Zug ist: " + curPlayer.toString());
			brett.repaint();
			super.paint(g);
			if (spiel.isGameOver()) {
				if (brett.getGraph().getScore(Owner.Cathargo) < brett
						.getGraph().getScore(Owner.Rom))
					rom.setText(romText + brett.getGraph().getScore(Owner.Rom)
							+ " \t Gewinner");
				else if (brett.getGraph().getScore(Owner.Cathargo) > brett
						.getGraph().getScore(Owner.Rom))
					karthago.setText(karText
							+ brett.getGraph().getScore(Owner.Cathargo)
							+ " \t Gewinner");

				lblGameOver.setVisible(true);
			}*/
		}

		/**
		 * Create the frame.
		 */
		public Frame(Brett spielBrett, Spiel s) {			 
			 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[] { 2 };
			gbl_contentPane.rowHeights = new int[] { 1 };
			gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
			gbl_contentPane.rowWeights = new double[] { 1.0 };
			contentPane.setLayout(gbl_contentPane);

			brett = spielBrett;
			spiel = s;
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.weightx = 3.0;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;

			brett.setPreferredSize(brett.getSize());
			final JScrollPane scrollPane = new JScrollPane(brett);
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(800, 800));
			contentPane.add(scrollPane, gbc_panel);
			
			menuBar = new JMenuBar();
			scrollPane.setColumnHeaderView(menuBar);
			
			mnStart = new JMenu("Start");
			menuBar.add(mnStart);

			JPanel buttonPanel = new JPanel();
			GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
			gbc_buttonPanel.fill = GridBagConstraints.BOTH;
			gbc_buttonPanel.weightx = 1.0;
			gbc_buttonPanel.ipady = 1;
			gbc_buttonPanel.ipadx = 1;
			gbc_buttonPanel.gridx = 1;
			gbc_buttonPanel.gridy = 0;
			contentPane.add(buttonPanel, gbc_buttonPanel);
			buttonPanel.setLayout(new GridLayout(12, 0, 0, 0));

			this.setSize(1000, 600);
			this.setVisible(true);
			this.setAlwaysOnTop(true);

		}

	

}
