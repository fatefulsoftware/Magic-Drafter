import java.util.List;


public class DraftBot extends Player {
	List<Card> cards;
	
	public DraftBot (List<Card> cards) {
		super();
		
		this.cards = cards;	
	}
	
	@Override
	public Card pick (List<Card> pack) {
		int i, minIndex;
		
		i = 0;
		
		minIndex = cards.size();
		
		for (i = 0; i < pack.size(); i++)
			if (cards.indexOf(pack.get(i)) < minIndex)
				minIndex = cards.indexOf(pack.get(i));
		
		return cards.get(minIndex);
	}

}
