package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;
/**
 * 
 * @author Intrepid
 *
 */
class SongsCompGenre implements Comparable<String> {
	   
	SongEntry data;
	String genreName;
	public ArrayList<SongEntry> ListGenreNames; 

	public SongsCompGenre (SongEntry e)
	{
	data = e;
	genreName = data.getGenre();
	ListGenreNames = new ArrayList<SongEntry>();
	}
	
	public String toString() 
	{ return ListGenreNames.toString(); }  
	/**
	 * 	@Override
	 */
	public int compareTo(String genreName) {
		
		int result = data.getGenre().compareTo(genreName);

		return result;
	}

	public boolean equals( SongsCompGenre rhs ) 
	{
		return data.equals(rhs.data);
	}
	
	public int hashCode()
	{ 
		return data.getGenre().hashCode();
	}
	
	void addSong(SongEntry e){
		ListGenreNames.add(data);
	}

	public SongEntry getData() {
		return this.data;
	}
    //public SongEntry size() {
    	//int size = ListGenreNames.size();
    	
	//return size();
//}
	String getName() { return this.genreName; }

	public Object size() {
		return ListGenreNames.size();
	}
}