package coding.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import coding.io.FileIO.Converter;
import coding.math.Randoms;

/**
 * This class is for the operations of arrays or collections
 * 
 * @author Felix
 * 
 */
public class Lists {
	/**
	 * Rearrange the elements of a double array in random order.
	 */
	public static void shaffle(int[] data) {
		int N = data.length;

		if (N <= 1)
			return;
		for (int i = 0; i < N; i++) {
			int j = Randoms.uniform(i, N);

			int swap = data[i];
			data[i] = data[j];
			data[j] = swap;
		}
	}

	public static void shaffle(double[] data) {
		int N = data.length;

		if (N <= 1)
			return;
		for (int i = 0; i < N; i++) {
			int j = Randoms.uniform(i, N);

			double swap = data[i];
			data[i] = data[j];
			data[j] = swap;
		}
	}

	public static <T> void shaffle(List<T> data) {
		int N = data.size();

		if (N <= 1)
			return;
		for (int i = 0; i < N; i++) {
			int j = Randoms.uniform(i, N);

			T swap = data.get(i);
			data.set(i, data.get(j));
			data.set(j, swap);
		}
	}

	/**
	 * @return the top-n subset of list {@code data}
	 */
	public static <T> List<T> subset(List<T> data, int n) {
		List<T> ts = new ArrayList<>();

		for (int i = 0; i < data.size(); i++) {
			ts.add(data.get(i));

			if (ts.size() >= n)
				break;
		}

		return ts;
	}

	/**
	 * @return a new list of the intersection of two lists: list1 and list2
	 */
	public static <T> List<T> intersect(List<T> list1, List<T> list2) {
		List<T> ts = new ArrayList<>();

		for (T t : list1) {
			if (list2.contains(t))
				ts.add(t);
		}

		return ts;
	}

	/**
	 * Note: if you need to operate on the original list, it's better to use the
	 * method "retainAll" or "removeAll"
	 * 
	 * @return a new list with the exception of two lists: list1 and list2
	 */
	public static <T> List<T> except(List<T> list1, List<T> list2) {
		List<T> ts = new ArrayList<>();

		for (T t : list1) {
			if (!list2.contains(t))
				ts.add(t);
		}

		return ts;
	}

	/**
	 * @return whether list is empty: null or no elements insides
	 */
	public static <T> boolean isEmpty(List<T> ts) {
		if (ts == null || ts.size() < 1)
			return true;

		return false;
	}

	@Test
	public void e_shaffle() {
		int N = 10;
		int[] data = Randoms.ints(10, N);
		Logs.debug("Before: " + Strings.toString(data));
		shaffle(data);
		Logs.debug("After : " + Strings.toString(data));

		double[] d = Randoms.doubles(N);

		Logs.debug("Before: " + Strings.toString(d));
		shaffle(d);
		Logs.debug("After : " + Strings.toString(d));
	}

	/**
	 * Turn a collection of data into an double array
	 * 
	 * @param data
	 *            a collection of data
	 * @return an double array
	 */
	public static double[] toArray(Collection<? extends Number> data) {
		if (data == null || data.size() < 1)
			return null;
		double da[] = new double[data.size()];
		int i = 0;
		for (Number d : data)
			da[i++] = d.doubleValue();

		return da;
	}

	/**
	 * Turn an double array into a List<Double> object
	 * 
	 * @param data
	 *            an double array
	 * @return a List<Double> object
	 */
	public static List<Double> toList(double[] data) {
		if (data == null || data.length < 1)
			return null;
		List<Double> da = new ArrayList<>();

		for (double d : data)
			da.add(d);

		return da;
	}

	public static <K, T> List<T> toList(K[] data, Converter<K, T> c) throws Exception {
		if (data == null || data.length < 1)
			return null;

		List<T> da = new ArrayList<>();

		for (K d : data)
			da.add(c.transform(d));

		return da;
	}

	/**
	 * Convert int array to int list
	 */
	public static List<Integer> toList(int[] data) {

		List<Integer> da = new ArrayList<>();

		for (Integer d : data)
			da.add(d);

		return da;
	}

	public static <K> List<KeyValPair<K>> sortMap(Map<K, ? extends Number> data) {
		return sortMap(data, false);
	}

	/**
	 * sort an {@code Map<String, Double>} map object, according to the double
	 * values
	 * 
	 * @param <K>
	 * 
	 * @param data
	 *            map data
	 * @param inverse
	 *            ascending or descending, ascending by default
	 * @return a sorted list
	 */
	public static <K> List<KeyValPair<K>> sortMap(Map<K, ? extends Number> data, boolean inverse) {
		List<KeyValPair<K>> pairs = new ArrayList<>();
		for (Entry<K, ? extends Number> en : data.entrySet())
			pairs.add(new KeyValPair<K>(en.getKey(), en.getValue()));

		Collections.sort(pairs);
		if (inverse)
			Collections.reverse(pairs);
		return pairs;
	}
}
