import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Draft {
	protected LinkedList<Player> seats;
	protected Map<Player, Queue<List<Card>>> players;
	protected Set set;
	
	public Draft () {
		players = new HashMap<Player, Queue<List<Card>>>();
	}
	
	public void addPlayer (Player player) {
		players.put(player, new SimpleQueue<List<Card>>());
	}
	
	public void addSet (Set set) {
		this.set = set;
	}
	
	public void start () {
		List<Player> pool;
		int i, pick;
		Player person;
		Queue<List<Card>> queue;

		seats = new LinkedList<Player>();
		
		pool = new ArrayList<Player>();
		pool.addAll(players.keySet());
		
		for (i = 0; i < players.size(); i++) {
			pick = (int)(Math.random() * (pool.size() - 1));
			
			person = pool.get(pick);	
			person.packNumber = 1;
			
			pool.remove(pick);
			seats.add(person);
			
			queue = players.get(person);
			queue.offer(set.generatePack());
		}
	}
	
	public void pick (Player player, List<Card> pack, Card card) {
		int i;
		
		player.takeCard(card);
		
		pack.remove(card);
		
		if (pack.size() == 0) {
			if (player.packNumber < 3) {
				player.packNumber++;
				players.get(player).add(set.generatePack());
			}
		} else {		
			i = seats.indexOf(player);
			
			if (i == seats.size() - 1)
				i = 0;
			else
				i++;
			
			players.get(seats.get(i)).offer(pack);
		}
	}
	
	public boolean isRunning () {
		for (Player player : seats)
			if (players.get(player).size() > 0 || player.packNumber != 3)
				return true;
		
		return false;
	}
	
	public List<Card> getPack (Player player) {
		return players.get(player).poll();
	}
	
	public List<Player> getSeats () {
		return seats;
	}
}