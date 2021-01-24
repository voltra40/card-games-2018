	package Base;
	
	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertFalse;
	import static org.junit.Assert.assertNotNull;
	import static org.junit.Assert.assertTrue;
	
	import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
	
	public class FortyThievesTest {
	
		//Tableau holds 3 cards, Homecell holds 1 card, Stockpile holds 57 cards, Wastepile holds 0 cards.
		@Test
		public void FortyThievesInitialSizeTest() {
			//FortyThieves ft = new FortyThieves();
			FortyThieves ft = new FortyThieves(null);
			for(int i=0; i<13; i++) {
				assertEquals(3,ft.tableau.get(i).size());
			}
			for(int i=0; i<8; i++) {
				assertEquals(1,ft.home.get(i).size());
			}
			assertEquals(57,ft.stock.get(0).size());
			assertEquals(0,ft.waste.get(0).size());
		}
		//Determines if adding a card is legal or illegal. Always false for Stockpile and Wastepile
		@Test
		public void FortyThievesAddingCardsTest() {
			FortyThieves ft = new FortyThieves(null);
			//waste pile, add only works from stock to waste, does not work for tableau and home
			Random rnd = new Random();
			assertTrue(ft.move(ft.stock.get(0), ft.waste.get(0), true));
			assertFalse(ft.move(ft.tableau.get(rnd.nextInt(13)), ft.waste.get(0), true));
			assertFalse(ft.move(ft.home.get(rnd.nextInt(8)), ft.waste.get(0), true));
			//stockpile, always false!
			assertFalse(ft.move(ft.deck_cards, ft.waste.get(0), true));
			
		}
		//Determines if removing a card is legal or illegal. Always false for Homecell, Legal for stockpile if not empty
		//Always legal for Wastepile
			@Test (expected = IndexOutOfBoundsException.class)
			public void FortyThievesRemovingCardsTest() {
				FortyThieves ft = new FortyThieves(null);
				ArrayList<Card> dump = new ArrayList<Card>();
				//tableau, always true
				for(int i=0;i<13;i++) {
					while(ft.tableau.get(i).size()>0) {
						int prevSize = ft.tableau.get(i).size();
						//(ignore) second Card throws indexOutofBounds when only 1 card left
						Card secondCard = ft.tableau.get(i).get(1);
						assertEquals(true,ft.move(ft.tableau.get(i), dump, true));
						if(ft.tableau.get(i).size()>0) {
							assertEquals(ft.tableau.get(i).get(0),secondCard);
						}
						assertEquals(ft.tableau.get(i).size(),prevSize-1);
						dump.clear();
					}
				}
				//home, always false
				assertFalse(ft.move(ft.home.get(0), dump, true));
				//stockpile always true
				for(int i=0;i<57;i++) {
					Card secondCard = ft.stock.get(0).get(1);
					assertEquals(true,ft.move(ft.stock.get(0), ft.waste.get(0), true));
					if(ft.stock.get(0).size()>0) {
						assertEquals(ft.stock.get(0).get(0),secondCard);
					}
				}
				//waste pile, always true
				ft.waste.get(0).clear();
				ft.waste.get(0).addAll(ft.deck_cards);
				for(int i=0;i<52;i++) {
					Card secondCard = ft.waste.get(0).get(1);
					assertEquals(true,ft.move(ft.waste.get(0), dump, true));
					if(ft.waste.get(0).size()>0) {
						assertEquals(ft.waste.get(0).get(0),secondCard);
					}
					dump.clear();
				}
			}
	
			@Test 
			public void FortyThievesAddingToTab() {
				FortyThieves f = new FortyThieves(null);
				ArrayList<Card> a = new ArrayList<Card>();
				String rankofx  = "4";
				if(f.tableau.get(1).get(0).getRank() == "K" || f.tableau.get(1).get(0).getRank().equals("K")||
						 f.tableau.get(1).get(0).getRank() == "Q" || f.tableau.get(1).get(0).getRank().equals("Q")||
						 f.tableau.get(1).get(0).getRank() == "J" || f.tableau.get(1).get(0).getRank().equals("J")||
						 f.tableau.get(1).get(0).getRank() == "A" || f.tableau.get(1).get(0).getRank().equals("A")){
					
					if(f.tableau.get(1).get(0).getRank() == "K" || f.tableau.get(1).get(0).getRank().equals("K")){
						rankofx = "A";
						
					}
					else if(f.tableau.get(1).get(0).getRank() == "Q" || f.tableau.get(1).get(0).getRank().equals("Q")){
						rankofx = "K";
						
					}else if(f.tableau.get(1).get(0).getRank() == "J" || f.tableau.get(1).get(0).getRank().equals("J")){
						rankofx = "Q";
						
					}else if(f.tableau.get(1).get(0).getRank() == "A" || f.tableau.get(1).get(0).getRank().equals("A")){
						rankofx = "2";
						
					}
					
				}
				 else if(Integer.parseInt(f.tableau.get(1).get(0).getRank()) >0 && Integer.parseInt(f.tableau.get(1).get(0).getRank()) < 13) {
					 rankofx = Integer.toString(Integer.parseInt(f.tableau.get(1).get(0).getRank())+1);
					}
				 		Card x = new Card(f.tableau.get(1).get(0).getSuit(), rankofx);
						int prevSize = f.tableau.get(1).size();
						a.add(x);
						Card secondCard = f.tableau.get(1).get(1);
						assertEquals(true,f.move(a, f.tableau.get(1) , true));
						assertEquals(f.tableau.get(1).size(),prevSize+1);
						a.clear();
					}
				
			@Test
			public void FortyThievesAddingToHomecell() {
				FortyThieves f = new FortyThieves(null);
				ArrayList<Card> a = new ArrayList<Card>();
				Card x = new Card("Hearts", "2");
				int prevSize = f.home.get(0).size();
				a.add(x);
				assertEquals(true,f.move(a, f.home.get(0) , true));
				assertEquals(f.home.get(0).size(),prevSize+1);
				a.clear();
					}
			}
	
	
	
	
			
	
