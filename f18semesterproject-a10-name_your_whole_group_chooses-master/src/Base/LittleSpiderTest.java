package Base;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LittleSpiderTest {
	@Test
	//test that each game starts with 8 tableaus and 4 homecells
	public void testSpiderInit() {
		LittleSpider spider = new LittleSpider();

		assertEquals(8,spider.tableau.size());
		assertEquals(4,spider.home.size());
	}
	@Test
	//test that each tableau starts with 6 cards
	public void tpInitSizeTest() {
		LittleSpider spider = new LittleSpider();

		for(ArrayList<Card> list : spider.tableau) {
			assertEquals(6,list.size());
		}
	}
	@Test
	//test adding a specific card to a tableau is legal 
	public void tpAddCardLegalTest() {
		LittleSpider spider = new LittleSpider();

			for(ArrayList<Card> pile : spider.tableau) {
				ArrayList<Card> imaginary = new ArrayList<Card>();
				assertEquals(6,pile.size());
				Card topCard = pile.get(0);
				String newRank;
				if(topCard.getRank().equals("A")) {
					newRank = "2";
				} else if(topCard.getRank().equals("K")) {
					newRank = "A";
				} else if(topCard.getRank().equals("Q")) {
					newRank = "K";
				} else if(topCard.getRank().equals("J")) {
					newRank = "Q";
				} else if(topCard.getRank().equals("10")) {
					newRank = "J";
				} else {
					newRank = String.valueOf((Integer.parseInt(topCard.getRank()) + 1));
				}
				Card imaginaryCard = new Card("Hearts",newRank);
				imaginary.add(imaginaryCard);
				assertTrue(spider.move(imaginary, pile, true));
				
			}
		}
	@Test
	//test that removing a card is illegal or legal
	public void tpRemoveCardLegalTest() {
		LittleSpider spider = new LittleSpider();
		for(ArrayList<Card> list : spider.tableau) {
			ArrayList<Card> trash = new ArrayList<Card>();
			assertTrue(spider.move(list, trash, true));
		}
		for(ArrayList<Card> pile : spider.tableau) {
			ArrayList<Card> trash = new ArrayList<Card>();
			pile.clear();
			assertFalse(spider.move(pile, trash, true));
		}
	}
	@Test
	//test that adding a card to a tableau increases the size and that card is the top card
	public void tpAddCardTest() {
		LittleSpider spider = new LittleSpider();

		for(ArrayList<Card> pile : spider.tableau) {
			ArrayList<Card> imaginary = new ArrayList<Card>();
			assertEquals(6,pile.size());
			int prevSize = pile.size();
			Card topCard = pile.get(0);
			String newRank;
			if(topCard.getRank().equals("A")) {
				newRank = "2";
			} else if(topCard.getRank().equals("K")) {
				newRank = "A";
			} else if(topCard.getRank().equals("Q")) {
				newRank = "K";
			} else if(topCard.getRank().equals("J")) {
				newRank = "Q";
			} else if(topCard.getRank().equals("10")) {
				newRank = "J";
			} else {
				newRank = String.valueOf((Integer.parseInt(topCard.getRank()) + 1));
			}
			Card imaginaryCard = new Card("Hearts",newRank);
			imaginary.add(imaginaryCard);
			assertTrue(spider.move(imaginary, pile, true));
			assertEquals(pile.size(),prevSize + 1);
			assertEquals(pile.get(0),imaginaryCard);
		}
	}
	@Test
	//tests that removing a card from a tableau pile decreases size and brings the next card forward
	public void tpRemoveCardTest() {
		LittleSpider spider = new LittleSpider();

		for(ArrayList<Card> list : spider.tableau) {
			ArrayList<Card> trash = new ArrayList<Card>();
			Card nextCard = list.get(1);
			int prevSize = list.size();
			assertTrue(spider.move(list, trash, true));
			assertEquals(prevSize - 1,list.size());
			assertEquals(list.get(0),nextCard);
		}
//	}
	}
	@Test
	//test that HomecellPile initially has 1 card
	public void hpInitSizeTest() {
		LittleSpider spider = new LittleSpider();
		for(ArrayList<Card> list : spider.home) {
			assertEquals(1,list.size());
		}
	}
	@Test
	//test that adding a specific card to a homecell pile is legal
	public void hpAddCardLegalTest() {
		LittleSpider spider = new LittleSpider();
		for(ArrayList<Card> pile : spider.home) {
			ArrayList<Card> imaginary = new ArrayList<Card>();
			Card topCard = pile.get(0);
			String newRank = "";
			String newSuit = "";
			if(topCard.getSuit().equals("Hearts") || topCard.getSuit().equals("Diamonds")) {
				newSuit = topCard.getSuit();
				newRank = "2";
			} else if(topCard.getSuit().equals("Clubs") || topCard.getSuit().equals("Spades")) {
				newSuit = topCard.getSuit();
				newRank = "Q";
			}
			Card newCard = new Card(newSuit,newRank);
			imaginary.add(newCard);
			assertTrue(spider.move(imaginary, pile, true));
		}
	}
	@Test
	//test that the top card of HomecellPile can or cant be removed
	public void hpRemoveCardLegalTest() {
		LittleSpider spider = new LittleSpider();
		for(ArrayList<Card> pile : spider.home) {
			ArrayList<Card> trash = new ArrayList<Card>();
			assertFalse(spider.move(pile, trash, true));
		}
		for(ArrayList<Card> pile : spider.home) {
			ArrayList<Card> imaginary = new ArrayList<Card>();
			ArrayList<Card> trash = new ArrayList<Card>();
			Card topCard = pile.get(0);
			String newRank = "";
			String newSuit = "";
			if(topCard.getSuit().equals("Hearts") || topCard.getSuit().equals("Diamonds")) {
				newSuit = topCard.getSuit();
				newRank = "2";
			} else if(topCard.getSuit().equals("Clubs") || topCard.getSuit().equals("Spades")) {
				newSuit = topCard.getSuit();
				newRank = "Q";
			}
			Card newCard = new Card(newSuit,newRank);
			imaginary.add(newCard);
			assertTrue(spider.move(imaginary, pile, true));
			assertTrue(spider.move(pile, trash, true));
		}
	}
	
	@Test
	//adding a card to HomecellPile results in size()+1, and that card is the new top card
	public void hpAddingCardTest() {
		LittleSpider spider = new LittleSpider();
		for(ArrayList<Card> pile : spider.home) {
			ArrayList<Card> imaginary = new ArrayList<Card>();
			ArrayList<Card> trash = new ArrayList<Card>();
			int prevSize = pile.size();
			Card topCard = pile.get(0);
			String newRank = "";
			String newSuit = "";
			if(topCard.getSuit().equals("Hearts") || topCard.getSuit().equals("Diamonds")) {
				newSuit = topCard.getSuit();
				newRank = "2";
			} else if(topCard.getSuit().equals("Clubs") || topCard.getSuit().equals("Spades")) {
				newSuit = topCard.getSuit();
				newRank = "Q";
			}
			Card newCard = new Card(newSuit,newRank);
			imaginary.add(newCard);
			assertTrue(spider.move(imaginary, pile, true));
			assertEquals(pile.size(), prevSize + 1);
			assertEquals(pile.get(0),newCard);
		}
	}
	@Test
	//checks that removing a card from a homecell pile decreases size and brings the next card to the top
	public void hpRemoveCardTest() {
		LittleSpider spider = new LittleSpider();
		for(ArrayList<Card> pile : spider.home) {
			ArrayList<Card> imaginary = new ArrayList<Card>();
			ArrayList<Card> trash = new ArrayList<Card>();
			int prevSize1 = pile.size();
			Card topCard = pile.get(0);
			String newRank = "";
			String newSuit = "";
			if(topCard.getSuit().equals("Hearts") || topCard.getSuit().equals("Diamonds")) {
				newSuit = topCard.getSuit();
				newRank = "2";
			} else if(topCard.getSuit().equals("Clubs") || topCard.getSuit().equals("Spades")) {
				newSuit = topCard.getSuit();
				newRank = "Q";
			}
			Card newCard = new Card(newSuit,newRank);
			imaginary.add(newCard);
			assertTrue(spider.move(imaginary, pile, true));
			assertEquals(pile.size(), prevSize1 + 1);
			assertEquals(pile.get(0),newCard);
			int prevSize2 = pile.size();
			Card nextCard = pile.get(1);
			assertTrue(spider.move(pile, trash, true));
			assertEquals(pile.size(),prevSize2 - 1);
			assertEquals(pile.get(0),nextCard);
		}
	}
	
	
	
}
