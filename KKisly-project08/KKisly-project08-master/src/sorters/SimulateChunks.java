package sorters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;

class SimulateChunks {
	
	public static void splitFileIntoArrayChunks(int memSize, String string, ArrayList<Integer[]> fileChunksAsArrays) throws FileNotFoundException {
		//read each element of array fileNames and apply scanner and populate filechunksarray linkedlist
		//Allocate  Int[] array by memory_size number
		File file = new File(string);
		Scanner inputStream = new Scanner(file).useDelimiter(",");

		while (inputStream.hasNext()) {
			int numbersCount = 0;
			int i = 0;
			Integer[] chunk = new Integer[memSize];
			while (inputStream.hasNext()&&numbersCount < memSize){
				String data = inputStream.next();
				int numData = Integer.parseInt(data);					
				chunk[i] = numData; 
				numbersCount++;
				i++;
			}

			int end = Arrays.asList(chunk).indexOf(null);
			if (end >= 0) {
				Integer[] tmp = new Integer[end];
				System.arraycopy(chunk, 0, tmp, 0, end);
				chunk = tmp;
				fileChunksAsArrays.add(chunk);

			} else 
				fileChunksAsArrays.add(chunk);

		}
		inputStream.close();
	}
}

