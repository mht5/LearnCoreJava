package volume01.chapter08;

/**
 * self defined generic type
 * @author mhts
 * @date 2018��6��19��
 * @param <T>
 */
public class Pair<T> {
	private T first;
	private T second;
	
	public Pair() {
		this.first = null;
		this.second = null;
	}
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	
	
}
