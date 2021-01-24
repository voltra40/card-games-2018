package Base;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;



public class GolfTestCases {
	//Checks if the 7 tableau piles exist, along with the home and stock piles
	@Test
	public void testGolfInit() {
		Golf golf = new Golf();
		assertEquals(7,golf.tableau.size());
		assertNotNull(golf.home);
		assertNotNull(golf.stock);
	}
	
	//Checks that all 7 tableau piles start with 5 cards initally
	@Test
	public void tableauInitSizeTest() {
		Golf golf = new Golf();
		for(ArrayList<Card> list : golf.tableau) {
			assertEquals(5,list.size());
		}
	}
	
	//Checks that it should always be illegal to add a card to a tableau pile
	@Test
	public void tableauAddCardTest() {
		Golf golf = new Golf();
		for(ArrayList<Card> pile : golf.tableau) {
			assertFalse(golf.move(golf.stock, pile, true));
			assertEquals(pile.size(),5);
		}
		assertTrue(golf.home.size() == 0);
	}
	
	//Checks that it should be legal to remove a card from a tableau pile that is not empty
	@Test
	public void tableauRemoveCardLegalTest() {
		Golf golf = new Golf();
		for(ArrayList<Card> list : golf.tableau) {
			assertTrue(golf.move(list, golf.home.get(0), true));
			golf.home.get(0).clear();
		}
		for(ArrayList<Card> pile : golf.tableau) {
			pile.clear();
			assertFalse(golf.move(pile, golf.home.get(0), true));
		}
	}
	
	//Checks that removing a card from a tableau pile reduces the size by 1, and the second card becomes the top card
	@Test
	public void tableauRemoveCardTest() {
		Golf golf = new Golf();
		for(ArrayList<Card> list : golf.tableau) {
			Card nextCard = list.get(1);
			int prevSize = list.size();
			assertTrue(golf.move(list, golf.home.get(0), true));
			assertEquals(prevSize - 1,list.size());
			assertEquals(list.get(0),nextCard);
			golf.home.clear();
		}
	}
	@Test
	//test that HomecellPile initially has 0 cards!
	public void hpInitSizeTest() {
		Golf golf = new Golf();
		assertEquals(0, golf.home.size());
	}
	@Test
	//determines if adding card to HomecellPile is legal using move method.
	public void hpAddingCardTest1() {
		Golf golf = new Golf();
		Random rnd = new Random();
		//use of random to choose any tableau pile
		assertEquals(true,golf.move(golf.tableau.get(rnd.nextInt(7)), golf.home.get(0), true));
		assertEquals(true,golf.move(golf.stock, golf.home.get(0), true));
	}
	@Test
	//test that the top card of HomecellPile cannot be removed(i.e false)
	public void hpRemoveCardTest() {
		Golf golf = new Golf();
		assertEquals(false,golf.move(golf.home.get(0), golf.deck_cards, true));
	}
	@Test
	//adding a card to HomecellPile results in size()+1
	public void hpAddingCardTest2() {
		Golf golf = new Golf(); {
		int currentSize = golf.home.size();
		golf.move(golf.stock, golf.home.get(0), true);
			assertEquals(currentSize+1, golf.home.size());
		}
	}
	@Test
	//adding a card to HomecellPile becomes new top card
	public void hpAddingCardTest3() {
		Golf golf = new Golf();
		Deck testDeck = new Deck();
		Card topCard = testDeck.getCards().get(0);
		//moves first and second card of brand new deck to HomecellPile
		golf.move(testDeck.getCards(), golf.home.get(0), true);
		assertEquals(topCard,golf.home.get(0).get(0));
	}
	@Test
	//stock pile should start with 17 cards!
	public void spInitSizeTest() {
		Golf golf = new Golf();
		assertEquals(17,golf.stock.size());
	}
	@Test
	//you cannot add to stock pile!
	public void spAddingCardsTest() {
		Golf golf = new Golf();
		assertEquals(false,golf.move(golf.deck_cards, golf.stock, true));
	}
	@Test
	//removing topcard from stock pile should be valid as long as its size is >0
	public void spRemoveCardTest1() {
		Golf golf = new Golf();
		Deck d= new Deck();
		for(int i=0; i<17;i++) {
			golf.move(golf.stock, d.getCards(), true);
			if(golf.stock.size()>0) {
				//move method works for stockpile 17 times
				assertEquals(true,golf.move(golf.stock, d.getCards(), true));
			}else if(golf.stock.size()==0) {
				//move method will return false after no more cards available to move
				assertEquals(false,golf.move(golf.stock, d.getCards(), true));
			}
		}
	}
	@Test
	//removing a card from stockpile results in size decreasing by 1
	public void spRemoveCardTest2() {
		Golf golf = new Golf(); {
		int currentSize = golf.stock.size();
		golf.move(golf.stock, golf.home.get(0), true);
			assertEquals(currentSize-1, golf.stock.size());
		}
	}
	@Test
	//after removing a card from SP, the following card will become the top card.
	public void spRemoveCardTest3() {
		Golf golf = new Golf();
		Card nextCard = golf.stock.get(1);
		golf.move(golf.stock, golf.home.get(0), true);
		assertEquals(nextCard,golf.stock.get(0));
	}
//	public class Golf{
//		private Deck deck;
//		private ArrayList<ArrayList<Card>> tableaus;
//		private ArrayList<Card> homecell;
//		private ArrayList<Card> stock;
//		
//		//This constructor begins the setup for the game of golf
//		public Golf() {
//			this.deck = new Deck();
//			Collections.shuffle(this.deck.cards);
//			createPiles();
//			tableauDeal();
//			fillStock();
//		}
//		
//		//This method deals all remaining deck cards into the stock pile
//		public void fillStock() {
//			while(this.deck.cards.size() >= 1) {
//				deal(this.deck.cards,this.stock,true);
//			}
//		}
//		
//		//This method deals 5 cards into each of the 7 tableau piles
//		public void tableauDeal() {
//			for(int i=0; i<7; i++) {
//				ArrayList<Card> currentableauile = this.tableaus.get(i);
//				for(int x=1; x<=5; x++) {
//					deal(this.deck.cards,currentableauile,true);
//				}
//			}
//		}
//		
//		//This method instantiates all of the necessary piles
//		public void createPiles() {
//			this.tableaus = new ArrayList<ArrayList<Card>>();
//			for(int i = 1; i<=7; i++) {
//				ArrayList<Card> tableau = new ArrayList<Card>();
//				this.tableaus.add(tableau);
//			}
//			this.homecell = new ArrayList<Card>();
//			this.stock = new ArrayList<Card>();
//		}
//		
//		//This method moves the top card from one pile to another
//		public void deal(ArrayList<Card> pickUp, ArrayList<Card> destination, boolean faceUp) {
//			Card topCard = pickUp.get(0);
//			if(faceUp) {
//				topCard.setFaceUp(true);
//			} else {
//				topCard.setFaceUp(false);
//			}
//			destination.add(0, topCard);
//			pickUp.remove(0);
//		}
//		
//		
//		public void display() {
//			for(int i=1; i<=7; i++) {
//				System.out.println("Tableau Pile " + i + ": " + this.tableaus.get(i-1));
//			}
//			System.out.println("");
//			System.out.println("Stock Pile: " + this.stock);
//			System.out.println("Deck: " + this.deck.cards);
//		}
//		
//	}
	
	
	
	
	
	
	public GolfTestCases() {
		
	}
}
