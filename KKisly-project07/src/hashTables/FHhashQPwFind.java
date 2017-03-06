package hashTables;

import java.util.NoSuchElementException;
import cs1c.*;

class FHhashQPwFind<KeyType, E extends Comparable<KeyType>>
extends FHhashQP<E> {
/**
 * 
 * @param key
 * @return E
 * @throws NoSuchElementException
 */
	public E find(KeyType key) throws NoSuchElementException {
	
		E target = mArray[findPosKey(key)].data;
	      if (target != null) {
	         return target;
	      } else
	         throw new NoSuchElementException();
	   }
	/**
	 * 
	 * @param key
	 * @return int possition
	 */
	int findPosKey(KeyType key)
		{
		    int kthOddNum = 1;
		    int index = myHashKey(key);

		    while ( mArray[index].state != EMPTY
		            && mArray[index].data.compareTo(key) != 0)
		    {
		        index += kthOddNum; // k squared = (k-1) squared + kth odd #
		        kthOddNum += 2;     // compute next odd #
		        if ( index >= mTableSize )
		            index -= mTableSize;
		    }
		    return index;
		}
		/**
		 * 
		 * @param key
		 * @return HashKey
		 */
	int myHashKey(KeyType key){
		{
		      int hashVal;

		      hashVal = key.hashCode() % mTableSize;
		      if(hashVal < 0)
		         hashVal += mTableSize;

		      return hashVal;
		   }
	}
}
