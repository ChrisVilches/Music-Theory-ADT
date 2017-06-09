package music;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import music.Chord.ChordType;

@RunWith(Enclosed.class)
public class ChordTest {
	
	
	/**
	 * Compares a chord with an array of String, where each element contains a note (string).
	 * For example, C note, Major type, and ["C4", "E4", "G4"].
	 * @param chord
	 * @param notes
	 */
	private static void assertChord(Note note, ChordType chordType, String[] notes){
		
		Chord chord = new Chord(note, chordType);
		
		Note[] chordNotes = chord.getNotes();
		
		assertEquals(chordNotes.length, notes.length);
		for(int i=0; i<chordNotes.length; i++){			
			assertEquals(chordNotes[i].toString(), notes[i]);			
		}
		
		StringBuilder wholeChordString = new StringBuilder(notes[0]);
		for(int i=1; i<chordNotes.length; i++){
			wholeChordString.append(" ");
			wholeChordString.append(notes[i]);
		}

		assertEquals(chord.toString(), wholeChordString.toString());		
	}
	
	
	private static void assertChord(Chord chord, String[] notes){
		assertChord(chord.getTonic(), chord.getChordType(), notes);
	}
	
	
	
	public static class ChordStructure{
		
				
		@Test
		public final void testMajor() {
			
			assertChord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MAJOR, new String[]{"C4", "E4", "G4"});
			
			assertChord(new Note(Note.D, 5), ChordType.MAJOR, new String[]{"D5", "F#/Gb5", "A5"});
			
			assertChord(new Note(Note.Fsharp, 6), ChordType.MAJOR, new String[]{"F#/Gb6", "A#/Bb6", "C#/Db7"});
		
			assertChord(new Note(Note.Bb, 1), ChordType.MAJOR, new String[]{"A#/Bb1", "D2", "F2"});
			
		}
		
		@Test
		public final void testMinor() {
			
			assertChord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MINOR, new String[]{"C4", "D#/Eb4", "G4"});
			
			assertChord(new Note(Note.D, 5), ChordType.MINOR, new String[]{"D5", "F5", "A5"});
			
			assertChord(new Note(Note.Fsharp, 6), ChordType.MINOR, new String[]{"F#/Gb6", "A6", "C#/Db7"});
			
			assertChord(new Note(Note.Bb, 1), ChordType.MINOR, new String[]{"A#/Bb1", "C#/Db2", "F2"});
			
		}
		
		@Test
		public final void testMaj7() {
			
			assertChord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MAJ7, new String[]{"C4", "E4", "G4", "B4"});
			assertChord(new Note(Note.D, 2), ChordType.MAJ7, new String[]{"D2", "F#/Gb2", "A2", "C#/Db3"});
			assertChord(new Note(Note.E, 3), ChordType.MAJ7, new String[]{"E3", "G#/Ab3", "B3", "D#/Eb4"});		
			assertChord(new Note(Note.Fsharp, 4), ChordType.MAJ7, new String[]{"F#/Gb4", "A#/Bb4", "C#/Db5", "F5"});
			assertChord(new Note(Note.Ab, 5), ChordType.MAJ7, new String[]{"G#/Ab5", "C6", "D#/Eb6", "G6"});
			assertChord(new Note(Note.Bb, 1), ChordType.MAJ7, new String[]{"A#/Bb1", "D2", "F2", "A2"});		
			
		}
		
		
		@Test
		public final void testMin7() {		
			assertChord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MIN7, new String[]{"C4", "D#/Eb4", "G4", "A#/Bb4"});
			assertChord(new Note(Note.D, 2), ChordType.MIN7, new String[]{"D2", "F2", "A2", "C3"});
			assertChord(new Note(Note.E, 3), ChordType.MIN7, new String[]{"E3", "G3", "B3", "D4"});		
			assertChord(new Note(Note.Fsharp, 4), ChordType.MIN7, new String[]{"F#/Gb4", "A4", "C#/Db5", "E5"});
			assertChord(new Note(Note.Ab, 5), ChordType.MIN7, new String[]{"G#/Ab5", "B5", "D#/Eb6", "F#/Gb6"});
			assertChord(new Note(Note.Bb, 1), ChordType.MIN7, new String[]{"A#/Bb1", "C#/Db2", "F2", "G#/Ab2"});		
		}
		
		
		@Test
		public final void testAug() {		
			assertChord(new Note(Note.Csharp, Note.MIDDLE_OCTAVE), ChordType.AUG, new String[]{"C#/Db4", "F4", "A4"});
			assertChord(new Note(Note.Dsharp, 1), ChordType.AUG, new String[]{"D#/Eb1", "G1", "B1"});
			assertChord(new Note(Note.Eb, 2), ChordType.AUG, new String[]{"D#/Eb2", "G2", "B2"});
			assertChord(new Note(Note.Fsharp, 3), ChordType.AUG, new String[]{"F#/Gb3", "A#/Bb3", "D4"});
			assertChord(new Note(Note.Gb, 4), ChordType.AUG, new String[]{"F#/Gb4", "A#/Bb4", "D5"});
			assertChord(new Note(Note.Ab, 5), ChordType.AUG, new String[]{"G#/Ab5", "C6", "E6"});
			assertChord(new Note(Note.Bb, 6), ChordType.AUG, new String[]{"A#/Bb6", "D7", "F#/Gb7"});		
		}
		
		@Test
		public final void testMajAdd9() {
			
			assertChord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MAJadd9, new String[]{"C4", "E4", "G4", "D5"});
			
			assertChord(new Note(Note.D, 5), ChordType.MAJadd9, new String[]{"D5", "F#/Gb5", "A5", "E6"});
			
			assertChord(new Note(Note.Fsharp, 6), ChordType.MAJadd9, new String[]{"F#/Gb6", "A#/Bb6", "C#/Db7", "G#/Ab7"});
		
			assertChord(new Note(Note.Bb, 1), ChordType.MAJadd9, new String[]{"A#/Bb1", "D2", "F2", "C3"});
		}
		
		@Test
		public final void testMinAdd9() {		
			assertChord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MINadd9, new String[]{"C4", "D#/Eb4", "G4", "D5"});
			
			assertChord(new Note(Note.D, 5), ChordType.MINadd9, new String[]{"D5", "F5", "A5", "E6"});
			
			assertChord(new Note(Note.Fsharp, 6), ChordType.MINadd9, new String[]{"F#/Gb6", "A6", "C#/Db7", "G#/Ab7"});
			
			assertChord(new Note(Note.Bb, 1), ChordType.MINadd9, new String[]{"A#/Bb1", "C#/Db2", "F2", "C3"});
		}
		
		
	}
	
	
	
	
	
	/**
	 * The new chord doesn't hold a reference to the note passed by argument.
	 * Because that note is copied into a new note.
	 */
	@Test
	public final void independentConstructor(){
		Note c = new Note(Note.C);
		Chord cMaj7 = new Chord(c, ChordType.MAJ7);
		c.transpose(1);
		assertEquals(c.toString(), "C#/Db4");
		assertEquals(cMaj7.getNotes()[0].toString(), "C4");
	}
	
	
	
	
	@Test
	public final void testChordNotTooHigh(){		
		Note note = new Note(Note.E, Note.MAX_OCTAVE);
		new Chord(note, ChordType.MAJOR);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testChordTooHigh(){		
		Note note = new Note(Note.F, Note.MAX_OCTAVE);
		new Chord(note, ChordType.MAJOR);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testChordTooHigh2(){		
		Note note = new Note(Note.Csharp, Note.MAX_OCTAVE);
		new Chord(note, ChordType.MAJ7);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testChordTooHigh3(){		
		Note note = new Note(Note.D, Note.MAX_OCTAVE);
		new Chord(note, ChordType.MIN7);
	}
	
	
	@Test
	public final void testEqualsOverride(){
		
		Chord cMajor3 = new Chord(new Note(Note.C, 3), ChordType.MAJOR);
		Chord cMajor4 = new Chord(new Note(Note.C), ChordType.MAJOR);
		cMajor4.transpose(-12);
		assertTrue(cMajor3.equals(cMajor4));
		
		Chord fMajor = new Chord(new Note(Note.F), ChordType.MAJOR);
		Chord gMajor = new Chord(new Note(Note.G), ChordType.MAJOR);
		gMajor.transpose(-2);		
		assertTrue(fMajor.equals(gMajor));
		
		Chord dMajor = new Chord(new Note(Note.D), ChordType.MAJOR);
		Chord dMinor = new Chord(new Note(Note.D), ChordType.MINOR);
		assertFalse(dMajor.equals(dMinor));
		
		Chord aMajor = new Chord(new Note(Note.A), ChordType.MAJOR);
		Chord bMajor = new Chord(new Note(Note.B), ChordType.MAJOR);
		assertFalse(aMajor.equals(bMajor));
		bMajor.transpose(-2);		
		assertTrue(aMajor.equals(bMajor));		
	}
	
	
	public static class ChordTranspose{
	
		
		@Test
		public final void testTranspose(){
			Chord ch = new Chord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MAJOR);
	
			ch.transpose(1);
			assertChord(ch, new String[]{"C#/Db4", "F4", "G#/Ab4"});
			
			ch.transpose(1);
			assertChord(ch, new String[]{"D4", "F#/Gb4", "A4"});
			
			ch.transpose(1);
			assertChord(ch, new String[]{"D#/Eb4", "G4", "A#/Bb4"});
			
			ch.transpose(2);
			assertChord(ch, new String[]{"F4", "A4", "C5"});
			
			ch.transpose(2);
			assertChord(ch, new String[]{"G4", "B4", "D5"});
			
			ch.transpose(-7);
			assertChord(ch, new String[]{"C4", "E4", "G4"});
			
			ch.transpose(-12);
			assertChord(ch, new String[]{"C3", "E3", "G3"});
			
			ch.transpose(-12);
			assertChord(ch, new String[]{"C2", "E2", "G2"});		
		}
		
		
		@Test
		public final void testTranspose2(){
			
			int[] notes = {Note.C, Note.Db, Note.D, Note.Dsharp, Note.E, Note.F, Note.Fsharp, Note.G, Note.Gsharp, Note.A, Note.Bb, Note.B};
			
			/*
			 * Compare a chord with every single chord, by transposing it +1 continually
			 * for each type
			 * * for each octave
			 * * * for each note
			 */
			for (ChordType type : ChordType.values()) {
				Chord ch = new Chord(new Note(Note.C, 1), type);
				Chord comp;
				/*
				 * Since there are intervals longer than one octave, be careful
				 * not to go off boundaries with chords like MajAdd9, etc.
				 * That's why I use MAX_OCTAVE-1
				 */
				for(int i=1; i<Note.MAX_OCTAVE-1; i++){
					for(int j=0; j<notes.length; j++){					
						comp = new Chord(new Note(notes[j], i), type);					
						assertArrayEquals(ch.getNotes(), comp.getNotes());
						assertTrue(ch.equals(comp));										
						ch.transpose(-2);
						ch.transpose(3);
					}
				}
			}				
		}
		
		
		@Test(expected=IllegalArgumentException.class)
		public final void testTransposeTooHigh(){		
			Note note = new Note(Note.C, Note.MAX_OCTAVE);
			Chord chord = new Chord(note, ChordType.MAJOR);
			chord.transpose(5);
		}
		
		@Test(expected=IllegalArgumentException.class)
		public final void testTransposeTooHigh2(){		
			Note note = new Note(Note.C, Note.MAX_OCTAVE - 1);
			Chord chord = new Chord(note, ChordType.MAJOR);
			chord.transpose(17);
		}
		
		@Test(expected=IllegalArgumentException.class)
		public final void testTransposeTooLow(){		
			Note note = new Note(Note.Csharp, Note.MIN_OCTAVE);
			Chord chord = new Chord(note, ChordType.MAJOR);
			chord.transpose(-2);
		}
		
	
	}
	
	
	/* Needs to test other chords */

}
