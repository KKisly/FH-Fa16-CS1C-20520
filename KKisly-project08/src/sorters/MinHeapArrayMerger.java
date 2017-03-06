package sorters;

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

import cs1c.FHbinHeap;


class MinHeapArrayMerger {

	public static void mergeSortedArrays(ArrayList<Integer[]> fileChunksAsArrays, HeapTuple[] minHeap, String outfile,
			boolean enableDebugPhase02) {
		
		/**
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer[]> lists = new ArrayList<Integer[]>();

        for (int i = 0; i < fileChunksAsArrays.size; i++) {
            ArrayList<Integer[]> tmpArray = new ArrayList<Integer[]>(fileChunksAsArrays);
            Queue<Integer> tmpQueue = new LinkedList<Integer>(tmpArray);
            lists.add(tmpQueue);
        }

        FHbinHeap heap = new FHbinHeap();

        // initialize minheap
        for (int i = 0; i < lists.size(); i++) {
            heap.insert(new ArrayNode(lists.get(i).poll(), i));
        }

        boolean done = false;
        while (!done) {

            ArrayNode minNode = heap.removeMin();
            result.add(minNode.value);
            int nextIndex = minNode.arrayIndex;

            Queue<Integer> nextQueue = lists.get(nextIndex);
            while (nextQueue.size() == 0) {
                nextIndex = (nextIndex < lists.size() - 1) ? nextIndex + 1 : 0;
                nextQueue = lists.get(nextIndex);
                if (nextIndex == minNode.arrayIndex) {
                    done = true;
                    break;
                }
            }

            if (!done)
                heap.insert(new ArrayNode(nextQueue.poll(), nextIndex));

        }
        while (!heap.isEmpty())
            result.add(heap.removeMin().value);

        return result;
    }
    */
		
	}

}
