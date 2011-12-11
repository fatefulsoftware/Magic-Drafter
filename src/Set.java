import java.util.ArrayList;
import java.util.List;

public class Set {
	protected List<Card> commons, uncommons, rares, mythics, doubleSided;
	
	public Set () {
		commons = new ArrayList<Card>();
		uncommons = new ArrayList<Card>();
		rares = new ArrayList<Card>();
		mythics = new ArrayList<Card>();
	}
	
	public void addCards (List<Card> cards) {
		for (Card card : cards) {
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
	
	@SuppressWarnings("unchecked")
	protected List<Card> generatePackCommons () {
		List<Card> cards, pool;
		int i, j;
		
		pool = (List<Card>)((ArrayList<Card>)commons).clone();
		cards = new ArrayList<Card>();
		
		for (i = 0; i < 10; i++) {
			j = (int)(Math.random() * (pool.size() - 1));
			cards.add(pool.get(j));
			pool.remove(j);
		}
		
		return cards;
	}

	@SuppressWarnings("unchecked")
	protected List<Card> generatePackUncommons () {
		List<Card> cards, pool;
		int i, j;

		pool = (List<Card>)((ArrayList<Card>)uncommons).clone();
		cards = new ArrayList<Card>();

		for (i = 0; i < 3; i++) {
			j = (int)(Math.random() * (pool.size() - 1));
			cards.add(pool.get(j));
			pool.remove(j);
		}
		
		return cards;
	}

	protected Card generatePackRare () {
		List<Card> pool;
				
		if ((Math.random() * 7) < 7)
			pool = rares;
		else
			pool = mythics;

		return pool.get((int)(Math.random() * (pool.size() - 1)));
	}
	
	public List<Card> generatePack () {
		List<Card> cards;
		
		cards = new ArrayList<Card>();
		
		cards.addAll(generatePackCommons());
		cards.addAll(generatePackUncommons());
		cards.add(generatePackRare());
		
		return cards;
	}
}