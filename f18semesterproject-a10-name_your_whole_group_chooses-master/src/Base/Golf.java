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

public class Golf {
	public ArrayList<Card> deck_cards;
	public ArrayList<ArrayList<Card>> tableau;
	public ArrayList<ArrayList<Card>> home;
	public ArrayList<Card> stock;
	public ArrayList<ArrayList<Card>> mover;
	private JFrame frame;
	private JPanel panel;
	
	/*public static void main(String[] args) {
		Golf golf = new Golf();
		golf.move(golf.sp, golf.hp, true);
		for(ArrayList<Card> pile : golf.tp) {
			System.out.println(pile);
		}
		System.out.println("Home: " + golf.hp);
		System.out.println("Stock: " + golf.sp);
	}*/
	/**@return
	 * @param move moving the cards to different from from their original pile
	 * @param spot moving the card and arranging them according to their rank in the same pile
	 * @return boolean Returns a boolean based on if the current move would follow the rules of Golf
	 * 
	 * }
	
	*/
	
	/**
	 * Instantiates the deck of cards and shuffles it
	 * check if the moves are valid and show message if player makes invalid move
	 * 
	 * 
	 * */
	public Golf(JFrame frame) {
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
//					for(ArrayList<Card> pile : home) {
//						for(Card card : pile) {
//							if(label == card.getLabel() && pile.indexOf(card) == 0) {
//								label.setBorder(SELECTED_BORDER);
//							    label.repaint();
//								mover.add(pile);
//							}
//						}
//					}
					for(Card card : stock) {
					
							if(label == card.getLabel() && stock.indexOf(card) == 0) {
								label.setBorder(SELECTED_BORDER);
							    label.repaint();
								mover.add(stock);
							}
						
					}
					for(Card card : home.get(0)) {
						if(label == card.getLabel() && home.get(0).indexOf(card) == 0) {
							if(mover.size() == 0) {
								JOptionPane.showMessageDialog(frame,"Invalid Move");
							} else {
								label.setBorder(SELECTED_BORDER);
								
							    label.repaint();
								mover.add(home.get(0));
							}
							
						}
					}
					System.out.println(mover);
					
					
					if(mover.size() >= 2) {
						if(mover.get(0) == home.get(0) && mover.get(1) == home.get(0)) {
							
						} else {
							final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
							JLabel label1 = mover.get(0).get(0).getLabel();
							JLabel label2 = mover.get(1).get(0).getLabel();
							label1.setBorder(UNSELECTED_BORDER);
							label2.setBorder(UNSELECTED_BORDER);
							label1.repaint();
							label2.repaint();
							if(!move(mover.get(0),mover.get(1),true)) {
								JPanel panel = new JPanel();
								JButton button = new JButton();
								button.setSize(3, 3);
								button.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										ImageIcon icon = new ImageIcon(this.getClass().getResource("../GUI/Cards/MHertz.jpg"));
										JLabel jL = new JLabel();
										jL.setIcon(icon);
										JOptionPane.showMessageDialog(frame, jL);
									}
									
								});
								panel.add(new JLabel("Invalid Move!"));
								panel.add(button);
								JOptionPane.showMessageDialog(frame, panel);
							}
							mover.clear();
							display();
							frame.revalidate();
							frame.repaint();
						}
						
						
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
		
		
		Collections.shuffle(deck_cards);
		
		//Instantiates the tableau ArrayList that will hold all 7 Tableau piles
		tableau = new ArrayList<ArrayList<Card>>();

		//Instantiates all 7 Tableau piles, gives them 5 cards, and puts them into the tableau ArrayList
		for(int i=1; i<=7; i++) {
			ArrayList<Card> tableauPile = new ArrayList<Card>(deck_cards.subList(0, 5));
			for(Card card : tableauPile) {
				deck_cards.remove(card);
			}
			tableau.add(tableauPile);
		}
		//Adds respective cards to their homecell piles, then removes those cards from the deck
		for(int i=0; i<1; i++) {
			
			deck_cards.remove(tableau);
		}
		
		
		//Instantiates the home ArrayList that will hold 1 homecell
		home = new ArrayList<ArrayList<Card>>();
		
		//Instantiates an empty homecell piles 
			ArrayList<Card> homePile = new ArrayList<Card>();
			home.add(homePile);
				
				//Instantiates the stock ArrayList that will hold 1 homecell
				stock = new ArrayList<Card>();
				
				while(!deck_cards.isEmpty()) {
					Card card = deck_cards.get(0);
					stock.add(card);
					deck_cards.remove(0);
				}

			}	

	/*public Golf() {
		//Instantiates the deck of cards and shuffles it
		Deck d = new Deck();
		deck_cards =  d.getCards();
		
		Collections.shuffle(deck_cards);
		
		//Instantiates the home ArrayList that will hold all 1 homecell piles
		home = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 1 homecell piles and puts them into the home ArrayList
		for(int i=0; i<=1; i++) {
			ArrayList<Card> homePile = new ArrayList<Card>();
			home.add(homePile);
		}
		
		
		//Instantiates the tableau ArrayList that will hold all 7 Tableau piles
		tableau = new ArrayList<ArrayList<Card>>();
		
		//Instantiates all 7 Tableau piles, gives them 5 cards, and puts them into the tableau ArrayList
		for(int i=1; i<=7; i++) {
			ArrayList<Card> tableauPile = new ArrayList<Card>(deck_cards.subList(0, 5));
			for(Card card : tableauPile) {
				deck_cards.remove(card);
			}
			tableau.add(tableauPile);
		}
		//Instantiates the stock ArrayList that will hold 1 homecell
		stock = new ArrayList<ArrayList<Card>>();
		
		//Instantiates an empty stock piles 
				for(int i=0; i<=1; i++) {
					ArrayList<Card> stockPile = new ArrayList<Card>();
					deck_cards.remove(tableau);
					stock.add(stockPile);
				}

	}*/
	
	
	/**
	 * @return boolean boolean based on if the current move 
	 * would follow the rules of Spider regarding Homecell piles and stock piles
	 * @param move the card you want to move
	 * @param spot the card at the point you want to put
	 * 
	 * */
	
	public boolean checkLegal(Card move, Card spot) {
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
			int spotInt = Integer.parseInt(spotRank);
			
			if(spotInt == (moveInt + 1) || spotInt == (moveInt - 1)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**@return
	 * @param pickUp picking cards and add to homecell pile
	 * @param destination containing all arraylist of shuffled cards and assigning rest to homecell piles
	 * @param faceUp checking the cards case
	 * @return boolean Moves a card from one pile to another (automatically checks rules by chaining to the checkLegal() method
	 * 
	 * }
	
	*/
	
	public boolean move(ArrayList<Card> pickUp, ArrayList<Card> destination, boolean faceUp) {
		if(pickUp.size() < 1 || home.contains(pickUp) || tableau.contains(destination) || stock.contains(destination) ) {
			return false;
		} else if(stock.contains(pickUp) || destination.size() == 0 || checkLegal(pickUp.get(0),destination.get(0))) {
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
		
	}/**
	 * @return boolean  Setting and adding shuffled cards to Tableau Pile and Stock Pile
	 * 
	 * }
	
	*/
	public Golf() {
		super();
		// TODO Auto-generated constructor stub
		tableau = new ArrayList<ArrayList<Card>>();
		home = new ArrayList<ArrayList<Card>>();
		stock = new ArrayList<>();
		Deck d = new Deck();
		deck_cards =  d.getCards();
		Collections.shuffle(deck_cards);
		for(int i=0; i<7; i++) {
			ArrayList<Card> tpPile = new ArrayList<Card>(deck_cards.subList(0, 5));
			for(Card card : tpPile) {
				deck_cards.remove(card);
			}
			tableau.add(tpPile);
		}
		
		stock=deck_cards;
		
	}
	/**
	 * set the format of each card
	 * and add them into game panel
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
		int xLocHome = 700;
			int offset = 0;
			for(Card card : home.get(0)) {
				JLabel label = card.getLabel();
				panel.add(label);
				label.setLocation(xLocHome, 500 + offset);
				offset -= 15;
			}
			xLocHome += 200;
		System.out.println(stock.size());
		
		int xLocStock = 600;
		for(Card card : stock) {
			JLabel label = card.getLabel();
				panel.add(label);
				label.setLocation(xLocStock, 650);
				
			
		}
		
		
		
		ArrayList<JLabel> pileLabels = new ArrayList<JLabel>();
		JLabel tp1 = new JLabel("Tableau-1");
		JLabel tp2 = new JLabel("Tableau-2");
		JLabel tp3 = new JLabel("Tableau-3");
		JLabel tp4 = new JLabel("Tableau-4");
		JLabel tp5 = new JLabel("Tableau-5");
		JLabel tp6 = new JLabel("Tableau-6");
		JLabel tp7 = new JLabel("Tableau-7");
		pileLabels.add(tp1);
		pileLabels.add(tp2);
		pileLabels.add(tp3);
		pileLabels.add(tp4);
		pileLabels.add(tp5);
		pileLabels.add(tp6);
		pileLabels.add(tp7);
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
		
		
		JLabel hp = new JLabel("HomeCell");
		if(home.get(0).size() > 0) {
			hp.setVisible(false);
		}
			hp.setSize(75,125);
			hp.setFont(hp.getFont().deriveFont(15.0f));
			panel.add(hp);
			hp.setLocation(600,500);
		
			hp.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(mover);
					if(mover.size() > 0) {
						mover.add(home.get(0));	
					} else {
						
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
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
		return panel;

	
	
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	
}
