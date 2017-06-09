package music;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note implements Transposable {

	public static final int MIDDLE_OCTAVE = 4;
	public static final int MIN_OCTAVE = 0;
	public static final int MAX_OCTAVE = 9;

	private static final String[] noteNames = { "C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A",
			"A#/Bb", "B" };

	public static final int C = 0;
	public static final int Csharp = 1;
	public static final int Db = 1;
	public static final int D = 2;
	public static final int Dsharp = 3;
	public static final int Eb = 3;
	public static final int E = 4;
	public static final int F = 5;
	public static final int Fsharp = 6;
	public static final int Gb = 6;
	public static final int G = 7;
	public static final int Gsharp = 8;
	public static final int Ab = 8;
	public static final int A = 9;
	public static final int Asharp = 10;
	public static final int Bb = 10;
	public static final int B = 11;
	
	private static final String regex = "^([a-gA-G])([#b])?([0-9]+)?$";
	private static final Pattern notePattern = Pattern.compile(regex);

	private int note;
	private int octave;

	/**
	 * Creates a note by specifying its note value and octave.
	 * 
	 * @param note
	 *            An int between accepted values. Use the static constants, e.g.
	 *            {@code new Note(Note.C, 4) }
	 * @param octave
	 *            Octave value ({@value #MIN_OCTAVE} - {@value #MAX_OCTAVE})
	 */
	public Note(int note, int octave) {
		setNote(note, octave);
	}

	/**
	 * Creates a middle octave note.
	 * 
	 * @param note
	 *            An int between accepted values. Use the static constants, e.g.
	 *            {@code new Note(Note.C) }
	 */
	public Note(int note) {
		setNote(note, MIDDLE_OCTAVE);
	}
	
	
	public Note(Note n){
		this.note = n.note;
		this.octave = n.octave;
	}
	
	
	/**
	 * Creates a note from a string. It must follow the correct format.
	 * @param note Format must be {@code C4}, {@code C#4}, or {@code Db4}
	 */
	public Note(String note){
				
		Matcher m = notePattern.matcher(note);
		char sharpFlat = '\0';
		int octave = MIDDLE_OCTAVE;
		int noteValue = -1;
		char letter;
		
		if(!m.find()){			
			throw new IllegalArgumentException("Wrong format: " + note + ".");
		}				
		
		letter = m.group(1).charAt(0);
		letter = Character.toUpperCase(letter);
		
				
		if(m.group(2) != null)
			sharpFlat = m.group(2).charAt(0);		
		
		if(m.group(3) != null)
			octave = Integer.parseInt(m.group(3));
		
		
		
		if(sharpFlat == '#'){
			
			switch(letter){			
			case 'C': noteValue = Csharp; break;
			case 'D': noteValue = Dsharp; break;
			case 'F': noteValue = Fsharp; break;
			case 'G': noteValue = Gsharp; break;
			case 'A': noteValue = Asharp; break;	
			}	
			
			
		} else if(sharpFlat == 'b'){
			
			switch(letter){			
			case 'D': noteValue = Db; break;
			case 'E': noteValue = Eb; break;
			case 'G': noteValue = Gb; break;
			case 'A': noteValue = Ab; break;
			case 'B': noteValue = Bb; break;
			}
			
		} else {
			switch(letter){			
			case 'C': noteValue = C; break;
			case 'D': noteValue = D; break;
			case 'E': noteValue = E; break;
			case 'F': noteValue = F; break;
			case 'G': noteValue = G; break;
			case 'A': noteValue = A; break;
			case 'B': noteValue = B; break;			
			}			
		}

		setNote(noteValue, octave);		
	}
	


	public int getNote() {
		return this.note;
	}

	public int getOctave() {
		return this.octave;
	}

	public void setNote(int note) {
		setNote(note, this.octave);
	}
	
	public void setOctave(int octave) {
		setNote(this.note, octave);
	}
	
	
	public void transpose(int semitones){
		
		int newNote = Math.floorMod(getNote() + semitones, 12);

		int newOctave = getOctave() + Math.floorDiv(getNote() + semitones, 12);
		
		if(noteIsValid(newNote) && octaveIsValid(newOctave)){
			
			setNote(newNote, newOctave);
			
		} else {
			throw new IllegalArgumentException("Wrong transposition arguments.");			
		}
	}
	
	
	/**
	 * Checks whether a note value is valid or not.
	 * 
	 * @param note
	 * @return
	 */
	public static boolean noteIsValid(int note) {
		return !(note < C || B < note);
	}

	/**
	 * Checks whether an octave value is valid or not.
	 * 
	 * @param note
	 * @return
	 */
	public static boolean octaveIsValid(int octave) {
		return !(octave < MIN_OCTAVE || octave > MAX_OCTAVE);
	}

	public void setNote(int note, int octave) {

		if (!octaveIsValid(octave)) {
			throw new IllegalArgumentException(
					"Octave must be a number between " + MIN_OCTAVE + " and " + MAX_OCTAVE + ".");
		}

		if (!noteIsValid(note)) {
			throw new IllegalArgumentException(
					String.format("Illegal note value %d, must be between %d and %d.", note, C, B));
		}

		this.note = note;
		this.octave = octave;

	}
	
	
	
	
	
	
	/**
	 * Pass a note and an interval, and this method will find the resulting
	 * note.
	 * 
	 * For example, if the first note is C, and the interval is a perfect fifth
	 * ({@code Interval.PERF5 }) then the result will be a G note.
	 * 
	 * @param firstNote
	 *            The interval's first note
	 * @param interval
	 *            Use the constants {@code Interval.DIM5}, etc
	 * @return The interval's second note
	 */
	public Note findNoteByInterval(int interval) {			
		
		Note newNote = new Note(this);
		
		newNote.transpose(interval);
		
		return newNote;
	}
	
	public Note findNoteByInterval(Interval interval) {		
		return findNoteByInterval(interval.getValue());
	}
	
	

	/**
	 * Distance (in semitones) from the first note to the second one.
	 * @param first
	 * @param second
	 * @return
	 */
	public int distance(Note second){		
		int octaves = second.getOctave() - getOctave();
		int notes = second.getNote() - getNote();
		return notes + octaves * 12;		
	}
	
	
	

	@Override
	public String toString() {
		return noteNames[this.note] + octave;
	}
	
	@Override
	public boolean equals(Object o){
		if(o.getClass() == this.getClass()){
			
			return ((Note)o).note == this.note && ((Note)o).octave == this.octave;
			
		}
		return false;
		
	}
	
	

}
