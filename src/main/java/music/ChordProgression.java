package music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import music.Chord.ChordType;

public class ChordProgression implements Transposable{
	
	private ArrayList<Chord> chordSequence;	
	private static final String romanRegex = "^(I|II|III|IV|V|VI|VII)(#|b)?([-a-z0-9]+)?$";
	private static final Pattern romanChordPattern = Pattern.compile(romanRegex);
	private Note baseNote;
	
	public List<Chord> getChordSequence(){
		return chordSequence;
	}
	
	public Note getBaseNote(){
		return baseNote;
	}
	
	private static HashMap<String, ChordType> chordTypes;
	
	static {		
		chordTypes = new HashMap<>();
		chordTypes.put("maj", ChordType.MAJOR);
		chordTypes.put("min", ChordType.MINOR);
		chordTypes.put("m", ChordType.MINOR);
		chordTypes.put("maj7", ChordType.MAJ7);
		chordTypes.put("min7", ChordType.MIN7);
		chordTypes.put("-7", ChordType.MIN7);
		chordTypes.put("aug", ChordType.AUG);
		chordTypes.put("7", ChordType.MAJORMIN7);
		chordTypes.put("majmin7", ChordType.MAJORMIN7);
	}
	
	
	
	
	/**
	 * This method doesn't know about scales. Every chord in the notation is major, and
	 * the intervals are in ionan mode (for instance III means major third). So in order to make 
	 * different chords, it must be
	 * specified as, for example: IIb, IIImin, etc. Using C as the base note, the chords
	 * I, II, III, IV, V, VI, VII are C major, D major, E major, F major, G major, A major and
	 * B major. Examples of parameters: "I", "II-7", "IIIbmin", "V-7", "VImaj7", "IV7".
	 * @param baseNote
	 * @param roman
	 * @return
	 */
	public static Chord getChordByRomanScaleAgnostic(Note baseNote, String roman){		
		
		Matcher m = romanChordPattern.matcher(roman);

		if(!m.find()){			
			throw new IllegalArgumentException("Wrong format: " + roman + ".");
		}
		
		int semitonesUp = -100000;
		
		if(m.group(1).equals("I")) semitonesUp = Interval.UNISON.getValue();
		else if(m.group(1).equals("II")) semitonesUp = Interval.MAJ2.getValue();
		else if(m.group(1).equals("III")) semitonesUp = Interval.MAJ3.getValue();
		else if(m.group(1).equals("IV")) semitonesUp = Interval.PERF4.getValue();
		else if(m.group(1).equals("V")) semitonesUp = Interval.PERF5.getValue();
		else if(m.group(1).equals("VI")) semitonesUp = Interval.MAJ6.getValue();
		else if(m.group(1).equals("VII")) semitonesUp = Interval.MAJ7.getValue();
		
		if(m.group(2) != null){			
			if(m.group(2).equals("#")) semitonesUp++;
			else if(m.group(2).equals("b")) semitonesUp--;			
		}
		
		Note base = new Note(baseNote);
		base.transpose(semitonesUp);
		
		ChordType type = null;
		
		if(m.group(3) == null){
			type = chordTypes.get("maj");
		}		
		else if(chordTypes.containsKey(m.group(3))){
			type = chordTypes.get(m.group(3));
		} else {
			throw new IllegalArgumentException("Chord type not known: " + m.group(3) + ".");
		}
		
		return new Chord(base, type);		
	}
	
	
	
	public ChordProgression(Note baseNote, String strNotation){
		
		this.baseNote = new Note(baseNote);
		
		String[] split = strNotation.split(" ");
		
		chordSequence = new ArrayList<>();
		
		for(String s : split){
			chordSequence.add(getChordByRomanScaleAgnostic(baseNote, s));
		}		
		
		
	}



	@Override
	public void transpose(int semitones) {
		this.baseNote.transpose(semitones);
		for(Chord c : chordSequence){
			c.transpose(semitones);			
		}	
	}
	
	
	
	

}
