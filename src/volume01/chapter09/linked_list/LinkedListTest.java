package volume01.chapter09.linked_list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * test linked list
 * @author mhts
 * @date 2018Äê6ÔÂ20ÈÕ
 */
public class LinkedListTest {

	public static void main(String[] args) {
		List<Integer> list1 = new LinkedList<>();
		List<Integer> list2 = new LinkedList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		list2.add(7);
		
		ListIterator<Integer> iter1 = list1.listIterator();
		Iterator<Integer> iter2 = list2.iterator();
		
		while (iter2.hasNext()) {
			if (iter1.hasNext()) {
				iter1.next();
			}
			iter1.add(iter2.next());
		}
		System.out.println(list1);
		
		/**
		 * remove every second word
		 */
		iter2 = list2.iterator();
		while (iter2.hasNext()) {
			iter2.next();
			if (iter2.hasNext()) {
				iter2.next();
				iter2.remove();
			}
		}
		System.out.println(list2);
		
		list1.removeAll(list2);
		System.out.println(list1);
	}
	
}
