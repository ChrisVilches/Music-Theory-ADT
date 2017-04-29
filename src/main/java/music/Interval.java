package music;

public class Interval {

	public static final int UNISON = 0;
	public static final int MIN2 = 1;
	public static final int MAJ2 = 2;
	public static final int MIN3 = 3;
	public static final int MAJ3 = 4;
	public static final int PERF4 = 5;
	public static final int AUG4 = 6;
	public static final int DIM5 = 6;
	public static final int PERF5 = 7;
	public static final int MIN6 = 8;
	public static final int MAJ6 = 9;
	public static final int MIN7 = 10;
	public static final int MAJ7 = 11;
	public static final int OCTAVE = 12;

	public static final int ASC = 1;
	public static final int DESC = -1;

	/**
	 * Pass a note and an interval, and this method will find the resulting
	 * note.
	 * 
	 * For example, if the first note is C, and the interval is an ascending perfect fifth
	 * ({@code Interval.PERF5 }) then the result will be a G note.
	 * 
	 * @param firstNote
	 *            The interval's first note
	 * @param interval
	 *            Use the constants {@code Interval.DIM5}, etc
	 * @param ascDesc
	 *            Either 1 (ascending) or -1 (descending). Use static constants
	 *            {@code Interval.ASC} and {@code Interval.DESC}
	 * @return The interval's second note
	 */
	public static Note findNoteByInterval(Note firstNote, int interval, int ascDesc) {
			
		if (ascDesc != 1 && ascDesc != -1)
			throw new IllegalArgumentException(
					"Second parameter must be either 1 (ascending interval) or -1 (descending interval).");

		int newNote = Math.floorMod(firstNote.getNote() + interval * ascDesc, 12);

		int newOctave = firstNote.getOctave() + Math.floorDiv(firstNote.getNote() + interval * ascDesc, 12);

		if (Note.noteIsValid(newNote) && Note.octaveIsValid(newOctave))
			return new Note(newNote, newOctave);
		else
			throw new IllegalArgumentException("Invalid resulting note.");
	}
	
	/**
	 * Distance (in semitones) from the first note to the second one.
	 * @param first
	 * @param second
	 * @return
	 */
	public static int distance(Note first, Note second){		
		int octaves = second.getOctave() - first.getOctave();
		int notes = second.getNote() - first.getNote();
		return notes + octaves * 12;		
	}
	
	
	
	public static Note findNoteByInterval(Note firstNote, int interval) {		
		return findNoteByInterval(firstNote, interval, ASC);
	}

}
