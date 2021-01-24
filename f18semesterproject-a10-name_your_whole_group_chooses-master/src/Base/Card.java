package Base;
import javax.swing.JLabel;

public class Card {
	private String suit;
	private String rank;
	private boolean faceUp = true;
	private JLabel label;
	
	/**@return
	 * @param assigning specific suit in the cards
	 * @param assigning specific rank cards
	 * @return boolean Setting face up and face down using suit and ranks 
	 * 
	 * }
	
	*/
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Card(String suit, String rank, JLabel inLabel) {
		this.suit = suit;
		this.rank = rank;
		this.label = inLabel;
	}
	
	public JLabel getLabel() {
		return this.label;
	}
	
	public boolean isFaceUp() {
		return faceUp;
	}
	
	public String getSuit() {
		return this.suit;
	}
	public String getRank() {
		return this.rank;
	}
	public String getSuitandRank() {
		return this.suit + " " + this.rank;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	@Override public String toString() {
		if(this.faceUp) {
			return getSuitandRank();
		} else {
			return "Face Down";
		}
		
	}
}
