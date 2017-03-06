package hashTables;

import cs1c.SongEntry;
/**
 * 
 * @author Intrepid
 *
 */
class SongCompInt implements Comparable<Integer>{

	SongEntry data;
	/**
	 * 
	 * @param e
	 */
	
	public SongCompInt(SongEntry e)
	{ data = e; }
	
	public String toString() 
	{ return data.toString(); }
	
	@Override
	public int compareTo(Integer key) {	
		return data.getID() - key;
	}

	public boolean equals(SongCompInt rhs){
		
		return data.equals(rhs.data);
	}
	
	public int hashCode(){
		return data.getID();
	}
}