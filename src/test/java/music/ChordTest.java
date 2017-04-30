package music;

import static org.junit.Assert.*;

import org.junit.Test;

import music.Chord.ChordType;

public class ChordTest {

	@Test
	public final void testMajor() {
		
		Chord cMajor = new Chord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MAJOR);
		assertEquals(cMajor.toString(), "C4 E4 G4");
		
		Chord dMajor = new Chord(new Note(Note.D, 5), ChordType.MAJOR);
		assertEquals(dMajor.toString(), "D5 F#/Gb5 A5");
		
		Chord fSharpMajor = new Chord(new Note(Note.Fsharp, 6), ChordType.MAJOR);
		assertEquals(fSharpMajor.toString(), "F#/Gb6 A#/Bb6 C#/Db7");
		
		Chord bFlatMajor = new Chord(new Note(Note.Bb, 1), ChordType.MAJOR);
		assertEquals(bFlatMajor.toString(), "A#/Bb1 D2 F2");
		
	}
	
	@Test
	public final void testMinor() {
		
		Chord cMinor = new Chord(new Note(Note.C, Note.MIDDLE_OCTAVE), ChordType.MINOR);
		assertEquals(cMinor.toString(), "C4 D#/Eb4 G4");
		
		Chord dMinor = new Chord(new Note(Note.D, 5), ChordType.MINOR);
		assertEquals(dMinor.toString(), "D5 F5 A5");
		
		Chord fSharpMinor = new Chord(new Note(Note.Fsharp, 6), ChordType.MINOR);
		assertEquals(fSharpMinor.toString(), "F#/Gb6 A6 C#/Db7");
		
		Chord bFlatMinor = new Chord(new Note(Note.Bb, 1), ChordType.MINOR);
		assertEquals(bFlatMinor.toString(), "A#/Bb1 C#/Db2 F2");
		
	}
	
	
	/* Needs to test other chords */

}
