import java.util.ArrayList;
import java.util.List;


public class InnistradSet extends Set {
	protected List<Card> doubleSidedCommons, doubleSidedUncommons, doubleSidedRares, doubleSidedMythics;
	
	public InnistradSet () {
		super();
		
		doubleSidedCommons = new ArrayList<Card>();
		doubleSidedUncommons = new ArrayList<Card>();
		doubleSidedRares = new ArrayList<Card>();
		doubleSidedMythics = new ArrayList<Card>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Card> generatePackCommons () {
		List<Card> cards, pool;
		int i, j;
		
		cards = new ArrayList<Card>();
		pool = (List<Card>)((ArrayList<Card>)commons).clone();
		
		for (i = 0; i < 9; i++) {
			j = (int)(Math.random() * (pool.size() - 1));
			cards.add(pool.get(j));
			pool.remove(j);
		}

		i = (int)(Math.random() * 14);
		
		if (i < 10) {
			pool = doubleSidedCommons;
		} else if (i < 13) {
			pool = doubleSidedUncommons;
		} else {
			if ((Math.random() * 7) < 7)
				pool = doubleSidedRares;
			else
				pool = doubleSidedMythics;
		}
		
		cards.add(pool.get((int)(Math.random() * (pool.size() - 1))));
		
		return cards;
	}
	
	public void addCards (List<Card> cards) {
		for (Card card : cards) {
			if (card.doubleSided) {
				if (card.rarity == Rarity.Common)
					doubleSidedCommons.add(card);
				else if (card.rarity == Rarity.Uncommon)
					doubleSidedUncommons.add(card);
				else if (card.rarity == Rarity.Rare)
					doubleSidedRares.add(card);
				else if (card.rarity == Rarity.MythicRare)
					doubleSidedMythics.add(card);
			} else {
				if (card.rarity == Rarity.Common)
					commons.add(card);
				else if (card.rarity == Rarity.Uncommon)
					uncommons.add(card);
				else if (card.rarity == Rarity.Rare)
					rares.add(card);
				else if (card.rarity == Rarity.MythicRare)
					mythics.add(card);
			}
		}
	}
}