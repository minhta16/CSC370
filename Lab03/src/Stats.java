import java.util.*;
import java.io.*;
import java.text.*;
/**
 * This programs computes the average of a list of numbers
 * after the outliers have been removed.
 * 
 * @author Minh Ta
 * @date 09/14/2018
 * @version  CSC370 Lab3--Stats 
 *
 */
public class Stats {

	public static void main(String[] args) throws FileNotFoundException {
		DecimalFormat df = new DecimalFormat("##.###");

		Scanner fileInput = new Scanner(new File("data.txt"));
		List<Double> numbers = new LinkedList<Double>();

		while (fileInput.hasNextDouble()) {
			numbers.add(fileInput.nextDouble());
		}

		double average = computeAverage(numbers);
		double standDev = computeStandardDev(numbers, average);
		
		System.out.println("Stats for list");
		System.out.println("The number of values is " + numbers.size());
		System.out.println("The average is " + df.format(average));
		System.out.println("The standard deviation is " + df.format(standDev));

		removeOutliers(numbers, average, standDev);

		System.out.println("\n\nRemoving outliers, the adjusted list has " + numbers.size() + " values.");
		System.out.println("Without outliers, the average is " + df.format(computeAverage(numbers)));
		
		fileInput.close();

	}
	
	/**
	 * computes the average of a list of double numbers
	 * @param list- the list of double numbers
	 * @return the average of the list
	 * @throws IllegalArgumentException when the list is empty or null.
	 */
	public static double computeAverage(List<Double> list){
		if (list.size() == 0 || list == null) {
			throw new IllegalArgumentException("Invalid list.");
		}
		
		Iterator<Double> itr = list.iterator();
		double sum = 0;
		while (itr.hasNext()) {
			sum += itr.next();
		}
		return sum / list.size();
	}
	
	/**
	 * computes the standard deviation of a list of double numbers
	 * @param list-the list of double numbers
	 * @param average- the average of that list
	 * @return the standard deviation of that list
	 * @throws IllegalArgumentException when the list is empty or null or when the average is negative.
	 */
	public static double computeStandardDev(List<Double> list, double average){
		if (list.size() == 0 || list == null) {
			throw new IllegalArgumentException("Invalid list.");
		}
		
		if (average < 0) {
			throw new IllegalArgumentException("Invalid average.");
		}
		
		Iterator<Double> itr = list.iterator();
		double numerator = 0;
		while (itr.hasNext()) {
			double num = itr.next();
			numerator += (num - average) * (num - average);
		}
		
		return Math.sqrt(numerator / list.size());
	}
	
	/**
	 * Removes all numbers more than two standard deviations from the average
	 * from the list.
	 * @param list - the list of double numbers
	 * @param average - the average of that list
	 * @param standDev - the standard deviation of that list
	 * @throws IllegalArgumentException when the list is empty or null, when the average is negative, or when the standard deviation is negative.
	 */
	public static void removeOutliers(List<Double> list, double average, double standDev){

		if (list.size() == 0 || list == null) {
			throw new IllegalArgumentException("Invalid list.");
		}

		if (average < 0) {
			throw new IllegalArgumentException("Invalid average.");
		}
		
		if (standDev < 0) {
			throw new IllegalArgumentException("Invalid standard deviation.");
		}
		
		double lowerBound = average - 2 * standDev;
		double upperBound = average + 2 * standDev;
		Iterator<Double> itr = list.iterator();
		while (itr.hasNext()) {
			double num = itr.next();
			if (num <= lowerBound || upperBound <= num) {
				itr.remove();
			}
		}
	}
	
}
