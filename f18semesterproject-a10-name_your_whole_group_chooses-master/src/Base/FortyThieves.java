package Base;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FortyThieves {
	public ArrayList<Card> deck_cards;
	public ArrayList<ArrayList<Card>> tableau;
	public ArrayList<ArrayList<Card>> home;
	public ArrayList<ArrayList<Card>> waste;
	public ArrayList<ArrayList<Card>> stock;
	public ArrayList<ArrayList<Card>> mover;
	JLabel wasteL;
	private JFrame frame;
	private JPanel panel;
	/**
	 * Class constructor which initialize the object
	 * check if the moves are valid and show message if player makes invalid move
	 * @param frame Instantiates the frame
	 * */
	public FortyThieves(JFrame frame) {
		//Instantiates the deck of cards and shuffles it
		this.frame = frame;
		Deck d = new Deck();
		Deck d2 = new Deck();
		for(Card card : d2.getCards()) {
			d.addCard(card);
		}
		panel = new JPanel();
		panel.setLayout(null);
		deck_cards =  d.getCards();
		for(Card card : deck_cards) {
			JLabel label = card.getLabel();
			label.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					// TODO Auto-generated method stub
					JLabel label = (JLabel) arg0.getComponent();
					final Border SELECTED_BORDER = BorderFactory.createMatteBorder(5, 5, 5, 5,Color.BLACK);
					for(ArrayList<Card> pile : tableau) {
						for(Card card : pile) {
							if(label == card.getLabel() && pile.indexOf(card) == 0) {
								label.setBorder(SELECTED_BORDER);
							    label.repaint();
								mover.add(pile);
							}
						}
					}
					for(ArrayList<Card> pile : home) {
						for(Card card : pile) {
							if(label == card.getLabel() && pile.indexOf(card) == 0) {
								label.setBorder(SELECTED_BORDER);
							    label.repaint();
								mover.add(pile);
							}
						}
					}
					for(ArrayList<Card> pile : stock) {
						for(Card card : pile) {
							if(label == card.getLabel() && pile.indexOf(card) == 0) {
								label.setBorder(SELECTED_BORDER);
							    label.repaint();
								mover.add(pile);
							}
						}
					}
					for(ArrayList<Card> pile : waste) {
						for(Card card : pile) {
							if(label == card.getLabel() && pile.indexOf(card) == 0) {
								label.setBorder(SELECTED_BORDER);
							    label.repaint();
								mover.add(pile);
							}
						}
					}
					System.out.println(mover);
					if(mover.size() >= 2) {
						final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
						JLabel label1 = mover.get(0).get(0).getLabel();
						JLabel label2 = mover.get(1).get(0).getLabel();
						label1.setBorder(UNSELECTED_BORDER);
						label2.setBorder(UNSELECTED_BORDER);
						label1.repaint();
						label2.repaint();
					
						//once done with moving methods unhide this code block
						
						
						if(!move(mover.get(0),mover.get(1),true)) {
//							JOptionPane.showMessageDialog(frame,"Invalid Move");
							JPanel panel = new JPanel();
							JButton button = new JButton();
							button.setSize(3, 3);
							button.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									ImageIcon icon = new ImageIcon(this.getClass().getResource("../GUI/Cards/MHertz02.jpg"));
									JLabel jL = new JLabel();
									jL.setIcon(icon);
									JOptionPane.showMessageDialog(frame, jL);
								}
								
							});
							panel.add(new JLabel("Invalid Move!"));
							panel.add(button);
							JOptionPane.showMessageDialog(frame, panel);
						}
						
						if(waste.get(0).size() > 0) {
							wasteL.setVisible(false);
						}
						
						mover.clear();
						display();
						frame.revalidate();
						frame.repaint();
						
					}
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		mover = new ArrayList<ArrayList<Card>>();
		//Cards that need to start in the homecell piles
		Card DA = deck_cards.get(0);
		Card HA = deck_cards.get(13);
		Card CA = deck_cards.get(26);
		Card SA = deck_cards.get(39);
		Card ODA = deck_cards.get(52);
		Card OHA = deck_cards.get(65);
		Card OCA = deck_cards.get(78);
		Card OSA = deck_cards.get(91);
		Collections.shuffle(deck_cards);
		
		//Instantiates the home ArrayList that will hold all 8 homecell piles
		home = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 8 homecell piles and puts them into the home ArrayList
		for(int i=1; i<=8; i++) {
			ArrayList<Card> homePile = new ArrayList<Card>();
			home.add(homePile);
		}
		
		
		//Puts homecell starter cards into an array
		Card[] homeStart = new Card[8];
		homeStart[0] = DA;
		homeStart[1] = HA;
		homeStart[2] = CA;
		homeStart[3] = SA;
		homeStart[4] = ODA;
		homeStart[5] = OHA;
		homeStart[6] = OCA;
		homeStart[7] = OSA;
		//Adds respective cards to their homecell piles, then removes those cards from the deck
		for(int i=0; i<8; i++) {
			home.get(i).add(homeStart[i]);
			deck_cards.remove(homeStart[i]);
		}
		
		
		
		//Instantiates the tableau ArrayList that will hold all 13 Tableau piles
				tableau = new ArrayList<ArrayList<Card>>();
				
				//Instantiates all 13 Tableau piles, gives them 3 cards, and puts them into the tableau ArrayList
				for(int i=1; i<=13; i++) {
					ArrayList<Card> tableauPile = new ArrayList<Card>(deck_cards.subList(0, 3));
					for(Card card : tableauPile) {
						deck_cards.remove(card);
					}
					tableau.add(tableauPile);
				}
				
				//Instantiates the waste ArrayList that will hold 1 Stockpile
				stock = new ArrayList<ArrayList<Card>>();
				
				//Instantiates an empty Stockpile piles 
					ArrayList<Card> stockPile = new ArrayList<Card>();
					stock.add(stockPile);
						
						
				
				
				
				
				
				//Instantiates the waste ArrayList that will hold 1 WastePile
						
						
						//Instantiates an empty WastePile piles 
							
								
								//Instantiates the stock ArrayList that will hold 1 WastePile
								waste = new ArrayList<ArrayList<Card>>();
								ArrayList<Card> waste1 = new ArrayList<Card>();
								waste.add(waste1);
								
								while(!deck_cards.isEmpty()) {
									Card card = deck_cards.get(0);
									stock.get(0).add(card);
									deck_cards.remove(0);
								}
				
				

			}

	
	
	
	
	
	
	
	
//	
//	public FortyThieves() {
//		//Instantiates the deck of cards and shuffles it
//		Deck d = new Deck();
//		deck_cards =  d.getCards();
//		//Cards that need to start in the homecell piles
//		Card DA = deck_cards.get(0);
//		Card HA = deck_cards.get(13);
//		Card CA = deck_cards.get(26);
//		Card SA = deck_cards.get(39);
//		Card ODA = deck_cards.get(54);
//		Card OHA = deck_cards.get(67);
//		Card OCA = deck_cards.get(80);
//		Card OSA = deck_cards.get(93);
//		
//		Collections.shuffle(deck_cards);
//		
//		//Instantiates the home ArrayList that will hold all 8 homecell piles
//		home = new ArrayList<ArrayList<Card>>();
//		
//		//Instantiates all 8 homecell piles and puts them into the home ArrayList
//		for(int i=1; i<=8; i++) {
//			ArrayList<Card> homePile = new ArrayList<Card>();
//			home.add(homePile);
//		}
//		
//		
//		//Puts homecell starter cards into an array
//		Card[] homeStart = new Card[8];
//		homeStart[0] = DA;
//		homeStart[1] = HA;
//		homeStart[2] = CA;
//		homeStart[3] = SA;
//		homeStart[4] = ODA;
//		homeStart[5] = OHA;
//		homeStart[6] = OCA;
//		homeStart[7] = OSA;
//		
//		//Adds respective cards to their homecell piles, then removes those cards from the deck
//		for(int i=0; i<8; i++) {
//			home.get(i).add(homeStart[i]);
//			deck_cards.remove(homeStart[i]);
//		}
//		
//		
//		
//		//Instantiates the tableau ArrayList that will hold all 13 Tableau piles
//		tableau = new ArrayList<ArrayList<Card>>();
//		
//		//Instantiates all 13 Tableau piles, gives them 3 cards, and puts them into the tableau ArrayList
//		for(int i=1; i<=13; i++) {
//			ArrayList<Card> tableauPile = new ArrayList<Card>(deck_cards.subList(0, 3));
//			for(Card card : tableauPile) {
//				deck_cards.remove(card);
//			}
//			tableau.add(tableauPile);
//		}
//		
//		//Instantiates the waste ArrayList that will hold 1 Stockpile
//		stock = new ArrayList<ArrayList<Card>>();
//		
//		//Instantiates an empty Stockpile piles 
//			ArrayList<Card> stockPile = new ArrayList<Card>();
//			stock.add(stockPile);
//				
//				
//		
//		
//		
//		
//		
//		//Instantiates the waste ArrayList that will hold 1 WastePile
//				home = new ArrayList<ArrayList<Card>>();
//				
//				//Instantiates an empty WastePile piles 
//					ArrayList<Card> wastePile = new ArrayList<Card>();
//					home.add(wastePile);
//						
//						//Instantiates the stock ArrayList that will hold 1 WastePile
//						waste = new ArrayList<Card>();
//						
//						while(!deck_cards.isEmpty()) {
//							Card card = deck_cards.get(0);
//							waste.add(card);
//							deck_cards.remove(0);
//						}
//		
//		
//
//	}
	/**@return
	 * @param pickUp picking cards and add to homecell pile
	 * @param destination containing all arraylist of shuffled cards and assigning rest to homecell piles
	 * @param faceUp checking the cards case
	 * @return boolean Moves a card from one pile to another (automatically checks rules by chaining to the checkLegal() method
	 * 
	 */
	public boolean move(ArrayList<Card> pickUp, ArrayList<Card> destination, boolean faceUp) {
		if(home.contains(pickUp) || destination == stock.get(0) || (pickUp == stock.get(0) && destination != waste.get(0)) || (destination == waste.get(0) && pickUp != stock.get(0))) {
			System.out.println("False");
			return false;
		} else if((pickUp == stock.get(0) && destination == waste.get(0)) || destination.size() == 0 || (tableau.contains(destination) && checkLegalTableau(pickUp.get(0),destination.get(0))) || (home.contains(destination) && checkLegalHome(pickUp.get(0),destination.get(0)))) {
			Card topCard = pickUp.get(0);
			if(faceUp) {
				topCard.setFaceUp(true);
			} else {
				topCard.setFaceUp(false);
			}
			destination.add(0, topCard);
			pickUp.remove(0);
			System.out.println("True");
			return true;
		}
		return false;
		
	}
	/**
	 * @return boolean boolean based on if the current move 
	 * would follow the rules of Spider regarding Homecell piles.
	 * @param move the card you want to move
	 * @param spot the card at the point you want to put
	 * 
	 * */
	public boolean checkLegalHome(Card move, Card spot) {
		String moveRank = move.getRank();
		String moveSuit = move.getSuit();
		String spotRank = spot.getRank();
		String spotSuit = spot.getSuit();
		
		if(moveSuit.equals(spotSuit) == false) {
			return false;
		}
		
		if(spotRank.equals("A") && moveRank.equals("2")) {
			return true;
		} else if(spotRank.equals("10") && moveRank.equals("J")) {
			return true;
		} else if(spotRank.equals("J") && moveRank.equals("Q")) {
			return true;
		} else if(spotRank.equals("Q") && moveRank.equals("K")) {
			return true;
		} else if(Integer.parseInt(spotRank) == Integer.parseInt(moveRank) - 1) {
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * @param move the card you want to move
	 * @param spot the card at the point you want to put
	 * @return boolean based on if the current move would follow the rules of Spider regarding Tableau piles
	 * */
	public boolean checkLegalTableau(Card move, Card spot) {
		String moveRank = move.getRank();
		String moveSuit = move.getSuit();
		String spotRank = spot.getRank();
		String spotSuit = spot.getSuit();
		
		if(!moveSuit.equals(spotSuit)) {
			return false;
		} else {
			if(moveRank.equals("2")) {
				if(spotRank.equals("3") || spotRank.equals("A")) {
					return true;
				} else {
					return false;
				}
			} else if(moveRank.equals("A")) {
				if(spotRank.equals("2") || spotRank.equals("K")) {
					return true;
				} else {
					return false;
				}
			} else if(moveRank.equals("K")) {
				if(spotRank.equals("A") || spotRank.equals("Q")) {
					return true;
				} else {
					return false;
				}
			} else if(moveRank.equals("Q")) {
				if(spotRank.equals("K") || spotRank.equals("J")) {
					return true;
				} else {
					return false;
				}
			} else if(moveRank.equals("J")) {
				if(spotRank.equals("Q") || spotRank.equals("10")) {
					return true;
				} else {
					return false;
				}
			} else if(moveRank.equals("10")) {
				if(spotRank.equals("J") || spotRank.equals("9")) {
					return true;
				} else {
					return false;
				}
			} else {
				int moveInt = Integer.parseInt(moveRank);
				int spotInt = Integer.parseInt(spotRank);
				
				if(spotInt == (moveInt + 1) || spotInt == (moveInt - 1)) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		
	}
	/**
	 * set the format of each card and add them into game panel
	 * 
	 * */
	public JPanel display() {

		int xLoc = 200;
		for(ArrayList<Card> pile : tableau) {
			int offset = 0;
			for(Card card : pile) {
				JLabel label = card.getLabel();
				panel.add(label);
				label.setLocation(xLoc, 100 + offset);
				offset -= 15;
			}
			xLoc += 100;
		}
		int xLocHome = 400;
		for(ArrayList<Card> pile : home) {
			int offset = 0;
			for(Card card : pile) {
				JLabel label = card.getLabel();
				
				panel.add(label);
				label.setLocation(xLocHome, 400 + offset);
				offset -= 15;
			}
			xLocHome += 100;
		}
		
		int xLocStock = 400;
		for(ArrayList<Card> pile : stock) {
			int offset = 0;
			for(Card card : pile) {
				JLabel label = card.getLabel();
				panel.add(label);
				label.setLocation(xLocStock, 600);
				offset -= 15;
			}
			xLocStock += 200;
		}
			int xLocWaste = 600;
			for(ArrayList<Card> pile : waste) {
				int offset = 0;
				for(Card card : pile) {
					JLabel label = card.getLabel();
					panel.add(label);
					label.setLocation(xLocWaste, 600);
					offset -= 15;
				}
				xLocWaste += 200;
			}
		
		ArrayList<JLabel> pileLabels = new ArrayList<JLabel>();
		JLabel tp1 = new JLabel("Tableau-1");
		JLabel tp2 = new JLabel("Tableau-2");
		JLabel tp3 = new JLabel("Tableau-3");
		JLabel tp4 = new JLabel("Tableau-4");
		JLabel tp5 = new JLabel("Tableau-5");
		JLabel tp6 = new JLabel("Tableau-6");
		JLabel tp7 = new JLabel("Tableau-7");
		JLabel tp8 = new JLabel("Tableau-8");
		JLabel tp9 = new JLabel("Tableau-9");
		JLabel tp10 = new JLabel("Tableau-10");
		JLabel tp11 = new JLabel("Tableau-11");
		JLabel tp12 = new JLabel("Tableau-12");
		JLabel tp13 = new JLabel("Tableau-13");
		pileLabels.add(tp1);
		pileLabels.add(tp2);
		pileLabels.add(tp3);
		pileLabels.add(tp4);
		pileLabels.add(tp5);
		pileLabels.add(tp6);
		pileLabels.add(tp7);
		pileLabels.add(tp8);
		pileLabels.add(tp9);
		pileLabels.add(tp10);
		pileLabels.add(tp11);
		pileLabels.add(tp12);
		pileLabels.add(tp13);
		
		int xStart = 200;
		for(JLabel label : pileLabels) {
			label.setSize(100,100);
			panel.add(label);
			label.setLocation(xStart,100);
			xStart += 200;
			label.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(mover);
					JLabel selLab = (JLabel) arg0.getSource();
					if(selLab == tp1) {
						mover.add(tableau.get(0));
					} else if(selLab == tp2) {
						mover.add(tableau.get(1));
					} else if(selLab == tp3) {
						mover.add(tableau.get(2));
					} else if(selLab == tp4) {
						mover.add(tableau.get(3));
					} else if(selLab == tp5) {
						mover.add(tableau.get(4));
					} else if(selLab == tp6) {
						mover.add(tableau.get(5));
					} else if(selLab == tp7) {
						mover.add(tableau.get(6));
					} else if(selLab == tp8) {
						mover.add(tableau.get(7));
					}else if(selLab == tp9) {
						mover.add(tableau.get(8));
					}else if(selLab == tp10) {
						mover.add(tableau.get(9));
					}else if(selLab == tp11) {
						mover.add(tableau.get(10));
					}else if(selLab == tp12) {
						mover.add(tableau.get(11));
					}else if(selLab == tp13) {
						mover.add(tableau.get(12));
					}
					
					if(mover.size() >= 2) {
						final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
						JLabel label1 = mover.get(0).get(0).getLabel();
						
						label1.setBorder(UNSELECTED_BORDER);
						
						label1.repaint();
//once done with moving methods unhide this code block
						
			
			//			move(mover.get(0),mover.get(1),true);
				
						mover.clear();
						display();
						frame.revalidate();
						frame.repaint();
						
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		for(int i=0; i<pileLabels.size();i++) {
			if(tableau.get(i).size() > 0) {
				pileLabels.get(i).setVisible(false);
			}
		}
		
		JLabel wasteLabel = new JLabel("Waste");
		wasteL = wasteLabel;
		wasteLabel.setSize(100,100);
		panel.add(wasteLabel);
		wasteLabel.setLocation(600,600);
		wasteLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(mover);
				JLabel selLab = (JLabel) arg0.getSource();
				mover.add(waste.get(0));
				
				if(mover.size() >= 2) {
					final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
					JLabel label1 = mover.get(0).get(0).getLabel();
					label1.setBorder(UNSELECTED_BORDER);
					label1.repaint();
				
					//once done with moving methods unhide this code block
					
					
					if(!move(mover.get(0),mover.get(1),true)) {
						JOptionPane.showMessageDialog(frame,"Invalid Move");
					}
					
					if(waste.get(0).size() > 0) {
						wasteLabel.setVisible(false);
					}
					
					mover.clear();
					display();
					frame.revalidate();
					frame.repaint();
					
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		return panel;
		
	}
	
}
