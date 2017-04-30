package music;

import java.util.HashMap;

public class Chord {
	
	public enum ChordType {
		
		MAJOR, MINOR, MAJ7, MIN7, AUG, MAJORMIN7
		
	}	
	
	private Note tonic;
	private ChordType type;
	
	
	private static HashMap<ChordType, Interval[]> structure;
	
	static {
		
		structure = new HashMap<>();
		structure.put(ChordType.MAJOR, new Interval[]{Interval.MAJ3, Interval.PERF5});
		structure.put(ChordType.MINOR, new Interval[]{Interval.MIN3, Interval.PERF5});
		structure.put(ChordType.MAJ7, new Interval[]{Interval.MAJ3, Interval.PERF5, Interval.MAJ7});
		structure.put(ChordType.AUG, new Interval[]{Interval.MAJ3, Interval.MIN6});
		structure.put(ChordType.MAJORMIN7, new Interval[]{Interval.MAJ3, Interval.PERF5, Interval.MIN7});		
	}
	
	
	
	public Chord(Note tonic, ChordType type){
		
		this.tonic = tonic;
		this.type = type;
		
		
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
	
	
	

}
