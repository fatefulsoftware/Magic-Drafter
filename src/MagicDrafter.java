import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MagicDrafter {
	Draft draft;
	
	public MagicDrafter () {
		draft = new Draft();
	}
	
	public static void main (String[] args) {
		MagicDrafter drafter;
		
		drafter = new MagicDrafter();
		drafter.start();
	}
	
	public void start () {
		Player player;
		Set set;
		
		set = new InnistradSet();
		draft = new Draft();

		try {
			set.addCards(getCards());
			draft.addSet(set);
			
			player = new Player();
			player.name = "Player 1";
			draft.addPlayer(player);
			
			player = new Player();
			player.name = "Player 2";
			draft.addPlayer(player);/*
			
			player = new Player();
			player.name = "Player 3";
			draft.addPlayer(player);
			
			player = new Player();
			player.name = "Player 4";
			draft.addPlayer(player);
			
			player = new Player();
			player.name = "Player 5";
			draft.addPlayer(player);
			
			player = new Player();
			player.name = "Player 6";
			draft.addPlayer(player);
			
			player = new Player();
			player.name = "Player 7";
			draft.addPlayer(player);
			
			player = new Player();
			player.name = "Player 8";
			draft.addPlayer(player);*/
			
			run();
		} catch (FileNotFoundException e) {
			System.out.println("Cards file not found");
		} catch (IOException e) {
			System.out.println("IOException thrown");
		}
	}
	
	public static List<Card> getCards () throws FileNotFoundException, IOException {
		BufferedReader reader;
		String str;
		Card card;
		List<Card> cards;
		int i, pos;
		final String MYTHIC_RARE = "Mythic Rare", RARE = "Rare", UNCOMMON = "Uncommon", COMMON = "Common";
		
		reader = new BufferedReader(new FileReader("cards.csv"));
		cards = new ArrayList<Card>();
		
		while ((str = reader.readLine()) != null) {
			i = 0;
			pos = -1;
			
			while (i != -1) {
				i = str.indexOf(",", i + 1);
				
				if (i != -1)
					pos = i;
			}
			
			if (pos == -1)
				continue;
			
			card = new Card();
			card.name = str.substring(0, pos);
			
			str = str.substring(pos + 1);
			
			if (str.equals(COMMON))
				card.rarity = Rarity.Common;
			else if (str.equals(UNCOMMON))
				card.rarity = Rarity.Uncommon;
			else if (str.equals(RARE))
				card.rarity = Rarity.Rare;
			else if (str.equals(MYTHIC_RARE))
				card.rarity = Rarity.MythicRare;
			
			card.doubleSided = card.name.contains("//");
			
			cards.add(card);
		}
		
		return cards;
	}
	
	public void run () {
		List<Card> pack;
		int i;
		BufferedReader in;
		String str;
		StringBuilder builder;
		
		builder = new StringBuilder();
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		draft.start();
		
		while (draft.isRunning()) {
			for (Player player : draft.getSeats()) {
				pack = draft.getPack(player);
				
				builder.delete(0, builder.length());
				
				if (player.getDeck().size() > 0) {
					builder.append(player.getDeck().get(0).name);
					
					for (i = 1; i < player.getDeck().size(); i++)
						builder.append(", " + player.getDeck().get(i).name);
					
					System.out.println(builder.toString());
				}
				
				i = 1;
				
				for (Card card : pack) {
					System.out.println(String.format("%d: %s", i, card.name));
					
					i++;
				}
				
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
				
				draft.pick(player, pack, pack.get(i - 1));
			}
		}
		
		for (Player player : draft.getSeats()) {
			System.out.println(player.name + ":");
			
			for (Card card : player.getDeck())
				System.out.println(card.name);

			System.out.println();
		}
	}
}
