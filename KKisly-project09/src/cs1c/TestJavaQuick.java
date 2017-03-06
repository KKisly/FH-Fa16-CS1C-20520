package cs1c;

import cs1c.TimeConverter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 * Public class which hendles main method to output/write files with performance data
 * and  hendles method which measures quicksort perfomance
 * @author Intrepid
 */

public class TestJavaQuick {

	public int QC_RECURSION_LIMIT;
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

/**
 * method that populates test Arrays, runs several Quicksort sortings and records average time.
 * @param testSize
 * @return average quickSort time
 */
	public static double testSortAlgos(int testSize)
	{
		int k, randomInt;
		long startTime, estimatedTime1, estimatedTime2, estimatedTime3, estimatedTime4, estimatedTime5;

		//Integer[] arrayOfInts1 = new Integer[testSize];
		Integer[] arrayOfInts2 = new Integer[testSize];

		// build array for measure quicksort
		for (k = 0; k < testSize; k++)
		{
			randomInt = (int) (Math.random() * testSize);
			arrayOfInts2[k] = randomInt;
		}
		
		// Copying Arrays --------------------------------------------start
		Integer[] arrayOfInts3 = new Integer[testSize];
		arrayOfInts3 = Arrays.copyOf(arrayOfInts2, arrayOfInts2.length);
		
		Integer[] arrayOfInts4 = new Integer[testSize];
		arrayOfInts4 = Arrays.copyOf(arrayOfInts2, arrayOfInts2.length);
		// Copying Arrays --------------------------------------------stop
		
		//running first run with same parameters 
		startTime = System.nanoTime();  // ------------------ start 

		FHsort.quickSort(arrayOfInts2);

		estimatedTime2 = System.nanoTime() - startTime;    // ---------------------- stop
		System.out.println("Quick sort First Run Elapsed Time:  "
				+ " size: " + testSize + ", "
				+ TimeConverter.convertTimeToString(estimatedTime2) 
				+ " = " + estimatedTime2 + "ns");

		//running second run with same parameters
		startTime = System.nanoTime();  // ------------------ start 

		FHsort.quickSort(arrayOfInts3);

		estimatedTime3 = System.nanoTime() - startTime;    // ---------------------- stop
		System.out.println("Quick sort Second Run Elapsed Time: "
				+ " size: " + testSize + ", "
				+ TimeConverter.convertTimeToString(estimatedTime3) 
				+ " = " + estimatedTime3 + "ns");
		//running third run with same parameters 
		startTime = System.nanoTime();  // ------------------ start 

		FHsort.quickSort(arrayOfInts4);

		estimatedTime4 = System.nanoTime() - startTime;    // ---------------------- stop
		System.out.println("Quick sort Third Run Elapsed Time:  "
				+ " size: " + testSize + ", "
				+ TimeConverter.convertTimeToString(estimatedTime4) 
				+ " = " + estimatedTime4 + "ns");
		//running fourth run with same parameters
		startTime = System.nanoTime();  // ------------------ start 

		// Note: un-comment  to verify sort
		//displayArray(arrayOfInts2, "Quick");

		estimatedTime1 = ((estimatedTime2 + estimatedTime3 + estimatedTime4)/3);
		System.out.println("Quick sort Average Elapsed Time:    "
				+ " size: " + testSize + ", "
				+ TimeConverter.convertTimeToString(estimatedTime1) 
				+ " = " + estimatedTime1 + "ns");
		return (double) estimatedTime1;
	}

/**
 * Displays array for debugging purposes
 * @param theArray
 * @param message
 */
	public static void displayArray(Integer [] theArray, String message)
	{
		for (int k = 0; k < theArray.length; k+= theArray.length/10)
		{
			System.out.println( message + " #" + k + ": " + theArray[k] + "");
		}
	}


	// -------  main --------------
	/**
	 * Main method with test Array declaration, calls testSort Algos method and writes output in each file for particular Array size
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{   //From previous set:
		//int newLim = 2;
		//FHsort token = new FHsort(newLim);
		//FHsort.setRecursionLimit(newLim);

		final int [] ARRAY_SIZES = {20000, 40000, 80000, 160000, 320000, 640000, 1280000, 2560000};


		FileWriter fileWriter = null;
		int suffix = 0;
		//looping through the Array_size
		for (int test = 0; test < ARRAY_SIZES.length; test++ )
		{
			int currentSize = ARRAY_SIZES[test];
			for (int test2 = 0; test2 < ARRAY_SIZES.length; test2++)
				suffix = ARRAY_SIZES[test];
			String fileName = "output/Array_size_"+ suffix +".csv";
			fileWriter = new FileWriter(fileName);


			//Looping through the Recursion Limit
			for (int token = 2; token < 300; token += 2)
			{
				FHsort.setRecursionLimit(token);
				//writing to the file
				fileWriter.append(String.valueOf(token));
				System.out.println("Recursion Limit:" + token);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(testSortAlgos(currentSize)));
				fileWriter.append(NEW_LINE_SEPARATOR);

			}
			fileWriter.flush();
			fileWriter.close();
		}

	}

}

