package Base;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import GUI.CardImg;

public class Deck {
	public ArrayList<Card> cards = new ArrayList<>();
	
	public static void main(String[] args) {
		Deck d = new Deck();
		System.out.println(d.cards);
	}	
	
	/**@return
	 * @return arrayList of cards with all 52 card instances
	 * also give the label for 52 cards
	 * }
	
	*/
	public Deck() {
		for(int i=1; i<=4; i++) {
			String suit = "";
			String imgSuit = "";
			if(i==1) {
				suit = "Hearts";
				imgSuit = "h";
			} else if (i==2) {
				suit = "Diamonds";
				imgSuit = "d";
			} else if (i==3) {
				suit = "Spades";
				imgSuit = "s";
			} else if (i==4) {
				suit = "Clubs";
				imgSuit = "c";
			}
			for(int x=1;x<=13;x++) {
				String rank;
				String imgRank;
				if(x == 1) {
					rank="A";
					imgRank = "a";
				} else if (x==11) {
					rank="J";
					imgRank = "j";
				} else if (x==12){
					rank="Q";
					imgRank = "q";
				} else if (x==13) {
					rank="K";
					imgRank = "k";
				} else {
					rank = "" + x;
					imgRank = "" + x;
				}
				
				String filename = "Cards/" + imgRank + imgSuit + ".gif";
				CardImg img = new CardImg();
				final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			    final Border SELECTED_BORDER = BorderFactory.createMatteBorder(5, 5, 5, 5,Color.BLACK);
				JLabel label = img.createDisplayImage(filename);
//				label.addMouseListener(new MouseListener() {
//					private boolean selected;
//					
//					@Override
//					public void mouseClicked(MouseEvent arg0) {
//						JLabel label = (JLabel) arg0.getComponent();
//						// TODO Auto-generated method stub
////						if(this.selected) {
////							label.setBorder(UNSELECTED_BORDER);
////						    label.repaint();
////						    this.selected = false;
////						} else {
////							label.setBorder(SELECTED_BORDER);
////						    label.repaint();
////						    this.selected = true;
////						}
//						boolean topCard = false;
//						for()
//						label.setBorder(SELECTED_BORDER);
//					    label.repaint();
//					}
//
//					@Override
//					public void mouseEntered(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void mouseExited(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void mousePressed(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void mouseReleased(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//				});
				cards.add(new Card(suit,rank,label));
			}
		}
	}
	public String[] toArray() {
		String[] s = new String[cards.size()];
		for(int i=0;i<cards.size();i++) {
			s[i]=cards.get(i).getSuitandRank();
		}
		return s;
	}//adding all cards instances to an arraylist 
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public void addCard(Card input) {
		cards.add(input);
	}
}
