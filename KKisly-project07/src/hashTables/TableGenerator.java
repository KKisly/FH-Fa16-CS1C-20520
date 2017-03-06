package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;
/**
 * 
 * @author Intrepid
 *
 */
class TableGenerator {

	private FHhashQPwFind<String, SongsCompGenre> tableOfGenres;
	private FHhashQPwFind<Integer, SongCompInt>  tableOfSongIDs;
	private ArrayList<String> genreNames;
/**
 * 
 * @param allSongs
 * @return table
 */
	public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs) {

		int arraySize;
		tableOfSongIDs = new FHhashQPwFind<Integer, SongCompInt>();

		arraySize = allSongs.length;

		for (int k = 0; k < arraySize; k++) {
			SongEntry Song = allSongs[k];

			SongCompInt CompInt = new SongCompInt(Song);
			tableOfSongIDs.insert(CompInt);
		}
		return tableOfSongIDs;
	}
/**
 * 
 * @param allSongs
 * @return table
 */
	public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {

		tableOfGenres = new FHhashQPwFind<String, SongsCompGenre>();
		int arraySize;
		arraySize = allSongs.length;

		genreNames = new ArrayList<String>();
		String checker = null;
		for (int k = 0; k < arraySize; k++) {
			SongEntry Song = allSongs[k];

			if (Song.getGenre().equals(checker)){

			}else{
				checker = Song.getGenre();
				genreNames.add(Song.getGenre());
			}

			SongsCompGenre CompGenre = new SongsCompGenre(Song);
			tableOfGenres.insert(CompGenre);
		}
		return tableOfGenres;
	}
/**
 * 
 * @return
 */
	public ArrayList<String> getGenreNames() {		
		return this.genreNames;
	}

}
