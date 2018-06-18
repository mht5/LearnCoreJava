package chapter06.static_inner_class;

/**
 * test static inner class
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class StaticInnerClassTest {

	public static void main(String[] args) {
		double[] array = new double[20];
		for (int i = 0; i < array.length; i++) {
			array[i] = 100 * Math.random();
		}
		ArrayAlg.Pair pair = ArrayAlg.minMax(array);
		System.out.println("min=" + pair.getFirst());
		System.out.println("max=" + pair.getSecond());
	}

}

class ArrayAlg{
	public static class Pair{
		private double first;
		private double second;
		
		public Pair(double first, double second) {
			this.first = first;
			this.second = second;
		}
		
		public double getFirst() {
			return this.first;
		}
		
		public double getSecond() {
			return this.second;
		}
	}
	
	public static Pair minMax(double[] value) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for (double v : value) {
			if (v < min) {
				min = v;
			}
			if (v > max) {
				max = v;
			}
		}
		return new Pair(min, max);
	}
}
