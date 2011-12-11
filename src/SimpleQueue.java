import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleQueue<T> extends AbstractQueue<T> {
	protected List<T> items;
	
	public SimpleQueue ()  {
		items = new ArrayList<T>();
	}
	
	@Override
	public boolean offer (T e) {
		return items.add(e);
	}

	@Override
	public T peek () {
		return items.get(0);
	}

	@Override
	public T poll () {
		T item;
		
		item = items.get(0);
		items.remove(0);
		
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return items.iterator();
	}

	@Override
	public int size () {
		return items.size();
	}
}