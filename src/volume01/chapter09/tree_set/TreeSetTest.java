package volume01.chapter09.tree_set;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * test tree set
 * tree set is a sorted collection
 * component type must implement "Comparable" interface
 * @author mhts
 * @date 2018Äê6ÔÂ20ÈÕ
 */
public class TreeSetTest {
	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<>();
		parts.add(new Item("c-toaster", 1234));
		parts.add(new Item("a-widget", 123));
		parts.add(new Item("b-modem", 12));
		System.out.println(parts);
		
		NavigableSet<Item> sortByDescription = new TreeSet<>(
				Comparator.comparing(Item::getDescription));
		
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
}
