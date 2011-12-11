import java.util.ArrayList;
import java.util.List;

public class Player {
	public String name;
	protected List<Card> deck;
	public int packNumber;
	
	public Player () {
		deck = new ArrayList<Card>();
	}
	
	public void takeCard (Card card) {
		deck.add(card);
	}
	
	public List<Card> getDeck () {
		return deck;
	}
}
