package Base;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestCases {

	@Test
	public void testDeck() {
		Deck deck = new Deck();
		assertEquals(52,deck.cards.size()); //test there are 52 cards
		for(int i=1;i<14;i++) {
			String s = ""+i;
			if(i==1) s = "A";
			if(i==11) s = "J";
			if(i==12) s = "Q";
			if(i==13) s = "K";
			assertEquals(s,deck.cards.get(i-1).getRank()); //i-1 because that is the actual position in the arraylist (0 to 12)
		}
		//test for duplicated, returns true if duplicate found
		boolean bool = false;
	   	Set<String> set = new HashSet<>();
	   	for(String s: deck.toArray()) {
	   		if(!set.add(s)) {
	   			bool = true;
	   		}
	   	}
	   	assertEquals(false,bool);
	}
}
