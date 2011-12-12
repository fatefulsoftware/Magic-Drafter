import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	public Card pick (List<Card> pack) {
		BufferedReader in;
		int i;
		String str;
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		i = 0;
		
		while (i == 0) {
			try {
				str = in.readLine();
				
				try {
					i = new Integer(str).intValue();
				} catch (NumberFormatException e) {
				}
			} catch (IOException e) {						
			}
		}
		
		return pack.get(i - 1);
	}
}
