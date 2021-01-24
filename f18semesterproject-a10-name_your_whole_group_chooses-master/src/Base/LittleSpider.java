package Base;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;



public class LittleSpider {
	public ArrayList<Card> deck_cards;
	public ArrayList<ArrayList<Card>> tableau;
	public ArrayList<ArrayList<Card>> home;
	public ArrayList<ArrayList<Card>> mover;
	private JFrame frame;
	private JPanel panel;
	
//	public static void main(String[] args) {
//		LittleSpider deck = new LittleSpider();
//		for(ArrayList<Card> pile : deck.tableau) {
//			System.out.println(pile);
//		}
//		for(ArrayList<Card> pile : deck.home) {
//			System.out.println(pile);
//		}
//		
//	}
	/**
	 * Class constructor which initialize the object
	 * there are 8 tableau each one have 6 cards randomly
	 * 4 Homecell Piles there are 4 cards in homecell when initialize
	 * */
	public LittleSpider(JFrame frame) {
		//Instantiates the deck of cards and shuffles it
		this.frame = frame;
		Deck d = new Deck();
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
					System.out.println(mover);
					if(mover.size() >= 2) {
						final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
						JLabel label1 = mover.get(0).get(0).getLabel();
						JLabel label2 = mover.get(1).get(0).getLabel();
						label1.setBorder(UNSELECTED_BORDER);
						label2.setBorder(UNSELECTED_BORDER);
						label1.repaint();
						label2.repaint();
						if(!move(mover.get(0),mover.get(1),true)) {
							JOptionPane.showMessageDialog(frame,"Invalid Move");
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
		Card CK = deck_cards.get(38);
		Card SK = deck_cards.get(51);
		
		Collections.shuffle(deck_cards);
		
		//Instantiates the home ArrayList that will hold all 4 homecell piles
		home = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 4 homecell piles and puts them into the home ArrayList
		for(int i=1; i<=4; i++) {
			ArrayList<Card> homePile = new ArrayList<Card>();
			home.add(homePile);
		}
		
		
		//Puts homecell starter cards into an array
		Card[] homeStart = new Card[4];
		homeStart[0] = DA;
		homeStart[1] = HA;
		homeStart[2] = CK;
		homeStart[3] = SK;
		
		//Adds respective cards to their homecell piles, then removes those cards from the deck
		for(int i=0; i<4; i++) {
			home.get(i).add(homeStart[i]);
			deck_cards.remove(homeStart[i]);
		}
		
		
		
		//Instantiates the tableau ArrayList that will hold all 8 Tableau piles
		tableau = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 8 Tableau piles, gives them 6 cards, and puts them into the tableau ArrayList
		for(int i=1; i<=8; i++) {
			ArrayList<Card> tableauPile = new ArrayList<Card>(deck_cards.subList(0, 6));
			for(Card card : tableauPile) {
				deck_cards.remove(card);
			}
			tableau.add(tableauPile);
		}

	}
	
	public LittleSpider() {
		//Instantiates the deck of cards and shuffles it
		Deck d = new Deck();
		deck_cards =  d.getCards();
		//Cards that need to start in the homecell piles
		Card DA = deck_cards.get(0);
		Card HA = deck_cards.get(13);
		Card CK = deck_cards.get(38);
		Card SK = deck_cards.get(51);
		
		Collections.shuffle(deck_cards);
		
		//Instantiates the home ArrayList that will hold all 4 homecell piles
		home = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 4 homecell piles and puts them into the home ArrayList
		for(int i=1; i<=4; i++) {
			ArrayList<Card> homePile = new ArrayList<Card>();
			home.add(homePile);
		}
		
		
		//Puts homecell starter cards into an array
		Card[] homeStart = new Card[4];
		homeStart[0] = DA;
		homeStart[1] = HA;
		homeStart[2] = CK;
		homeStart[3] = SK;
		
		//Adds respective cards to their homecell piles, then removes those cards from the deck
		for(int i=0; i<4; i++) {
			home.get(i).add(homeStart[i]);
			deck_cards.remove(homeStart[i]);
		}
		
		
		
		//Instantiates the tableau ArrayList that will hold all 8 Tableau piles
		tableau = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 8 Tableau piles, gives them 6 cards, and puts them into the tableau ArrayList
		for(int i=1; i<=8; i++) {
			ArrayList<Card> tableauPile = new ArrayList<Card>(deck_cards.subList(0, 6));
			for(Card card : tableauPile) {
				deck_cards.remove(card);
			}
			tableau.add(tableauPile);
		}

	}
	
	/**
	 * @param pickUp the card you want to move
	 * @param destination the destination of the card
	 * @param faceUp if the card is face up
	 * @return Moves a card from one pile to another (automatically checks rules by chaining to the checkLegal() method
	*/
	public boolean move(ArrayList<Card> pickUp, ArrayList<Card> destination, boolean faceUp) {
		if((home.contains(pickUp) && pickUp.size() == 1) || pickUp.size() < 1) {
			return false;
		} else if(destination.size() == 0 || (tableau.contains(destination) && checkLegalTableau(pickUp.get(0),destination.get(0))) || (home.contains(destination) && checkLegalHome(pickUp.get(0),destination.get(0)))) {
			Card topCard = pickUp.get(0);
			if(faceUp) {
				topCard.setFaceUp(true);
			} else {
				topCard.setFaceUp(false);
			}
			destination.add(0, topCard);
			pickUp.remove(0);
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
		
		if(moveSuit.equals("Hearts") || moveSuit.equals("Diamonds")) {
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
		} else if(moveSuit.equals("Spades") || moveSuit.equals("Clubs")) {
			if(spotRank.equals("K") && moveRank.equals("Q")) {
				return true;
			} else if(spotRank.equals("Q") && moveRank.equals("J")) {
				return true;
			} else if(spotRank.equals("J") && moveRank.equals("10")) {
				return true;
			} else if(spotRank.equals("2") && moveRank.equals("A")) {
				return true;
			} else if(Integer.parseInt(spotRank) == Integer.parseInt(moveRank) + 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param move the card you want to move
	 * @param spot the card at the point you want to put
	 * @return boolean based on if the current move would follow the rules of Spider regarding Tableau piles
	 * */
	public boolean checkLegalTableau(Card move, Card spot) {
		String moveRank = move.getRank();
		String spotRank = spot.getRank();
		
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
			int spotInt;
			try {
				spotInt = Integer.parseInt(spotRank);
			} catch(NumberFormatException ex) {
				spotInt = 0;
			}
			
			
			if(spotInt == (moveInt + 1) || spotInt == (moveInt - 1)) {
				return true;
			} else {
				return false;
			}
		}
	}
	/**
	 * set the format of each card and add them into game panel
	 * 
	 * */
	public JPanel display() {
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		double width = Math.round(tk.getScreenSize().getWidth());
//		double height = Math.round(tk.getScreenSize().getHeight());
		
		int xLoc = 200;
		for(ArrayList<Card> pile : tableau) {
			int offset = 0;
			for(Card card : pile) {
				JLabel label = card.getLabel();
				panel.add(label);
				label.setLocation(xLoc, 100 + offset);
				offset -= 15;
			}
			xLoc += 200;
		}
		int xLocHome = 600;
		for(ArrayList<Card> pile : home) {
			int offset = 0;
			for(Card card : pile) {
				JLabel label = card.getLabel();
				panel.add(label);
				label.setLocation(xLocHome, 500 + offset);
				offset -= 15;
			}
			xLocHome += 200;
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
		pileLabels.add(tp1);
		pileLabels.add(tp2);
		pileLabels.add(tp3);
		pileLabels.add(tp4);
		pileLabels.add(tp5);
		pileLabels.add(tp6);
		pileLabels.add(tp7);
		pileLabels.add(tp8);
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
					}
					
					if(mover.size() >= 2) {
						final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
						JLabel label1 = mover.get(0).get(0).getLabel();
						
						label1.setBorder(UNSELECTED_BORDER);
						
						label1.repaint();
						
						move(mover.get(0),mover.get(1),true);
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
		
		
		
		return panel;
		
	}
	/**
	 * getter for panel
	 * */
	public JPanel getPanel() {
		return this.panel;
	}
	
}
