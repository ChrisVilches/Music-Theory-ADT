package music;

import java.util.HashMap;

public class Chord implements Transposable {
	
	public enum ChordType {
		
		MAJOR, MINOR, MAJ7, MIN7, AUG, MAJORMIN7, MAJadd9, MINadd9, DIM
		
	}	
	
	private Note tonic;	
	private ChordType type;
	private int inversion;
	private Note[] notes;
	
	public Note getTonic(){
		return tonic;
	}
	
	public ChordType getChordType(){
		return type;
	}
	
	public Note[] getNotes(){		
		return notes;		
	}
	
	public int getInversion(){
		return inversion;
	}
	
	public Note getBass(){
		return notes[inversion];
	}
	
	
	private static HashMap<ChordType, Interval[]> structure;
	
	static {
		
		structure = new HashMap<>();
		structure.put(ChordType.MAJOR, new Interval[]{Interval.MAJ3, Interval.PERF5});
		structure.put(ChordType.MINOR, new Interval[]{Interval.MIN3, Interval.PERF5});
		structure.put(ChordType.MAJ7, new Interval[]{Interval.MAJ3, Interval.PERF5, Interval.MAJ7});
		structure.put(ChordType.MIN7, new Interval[]{Interval.MIN3, Interval.PERF5, Interval.MIN7});
		structure.put(ChordType.AUG, new Interval[]{Interval.MAJ3, Interval.MIN6});
		structure.put(ChordType.MAJORMIN7, new Interval[]{Interval.MAJ3, Interval.PERF5, Interval.MIN7});		
		structure.put(ChordType.MAJadd9, new Interval[]{Interval.MAJ3, Interval.PERF5, Interval.MAJ9});
		structure.put(ChordType.MINadd9, new Interval[]{Interval.MIN3, Interval.PERF5, Interval.MAJ9});
		structure.put(ChordType.DIM, new Interval[]{Interval.MIN3, Interval.TRITONE});
	}
	
	
	
	public Chord(Note tonic, ChordType type){
		this(tonic, type, 0);
	}
	
	
	
	public Chord(Note tonic, ChordType type, int inversion){
		
		this.tonic = new Note(tonic);
		this.type = type;
		this.inversion = inversion;
		
		Interval[] intervals = structure.get(this.type);
		
		if(inversion < 0){
			throw new IllegalArgumentException("Inversion can't be negative.");
		}
		
		if(inversion > intervals.length){
			throw new IllegalArgumentException("Chord has only " + (intervals.length + 1) + " notes, but inversion " + inversion + " was requested (0 = root inversion).");
		}
		
		// Base note + intervals
		int quantity = 1 + intervals.length;
		
		notes = new Note[quantity];
		notes[0] = new Note(tonic);
		for(int i=1; i<=intervals.length; i++){
			notes[i] = tonic.findNoteByInterval(intervals[i-1]);
		}
	}
	
	
	@Override
	public void transpose(int semitones){
		tonic.transpose(semitones);
		
		for(int i=0; i<notes.length; i++)
			notes[i].transpose(semitones);
	}
	
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder(tonic.toString());
		
		Note printNote = new Note(tonic);
		
		Interval[] intervals = structure.get(ChordType.valueOf(type+""));
		
		int amountNotes = intervals.length;		
		
		for(int i=0; i<amountNotes; i++){
			
			Interval interval = intervals[i];			
			
			printNote.transpose(interval.ordinal());
			sb.append(" ");
			sb.append(printNote.toString());
			printNote.transpose(-interval.ordinal());
			
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o){
		
		return ((Chord) o).getTonic().equals(this.tonic)
				&&
				((Chord) o).getChordType().equals(this.type);
		
	}
	
	
	

}
