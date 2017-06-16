package music;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import music.Chord.ChordType;

public class ReadmeExamples {
	
	@Test
	@SuppressWarnings("unused")
	public final void testNotes() {
		Note c = new Note(Note.C); // C middle octave
		Note csharp = new Note(Note.Csharp); // C# middle octave
		Note d = new Note(Note.D, 6); // D, 6th octave
		Note fsharp = new Note(Note.Fsharp, 3); // F#, 3rd octave
	}
	
	@Test
	@SuppressWarnings("unused")
	public final void testNotesString(){
		Note d = new Note("D");
		Note e = new Note("E3");
		Note fsharp = new Note("f#");
		Note eflat6 = new Note("Eb6");
	}
	
	@Test
	@SuppressWarnings("unused")
	public final void testChords(){
		Note c = new Note("C");
		Chord cMajor = new Chord(c, ChordType.MAJOR); // C4 major
				
		Note d = new Note(Note.D, 2);
		Chord dAdd9 = new Chord(d, ChordType.MAJadd9); // D2 major with add9
				
		Note eb7 = new Note("Eb7");
		Chord ebAug = new Chord(eb7, ChordType.AUG); // Eb7 augmented
	}
	
	@Test
	@SuppressWarnings("unused")
	public final void testChordInversions(){
		// C major, root inversion (bass = C)
		Note c = new Note("C");
		Chord cMajor = new Chord(c, ChordType.MAJOR, 0);
			
		// D major add9, second inversion (bass = A)	
		Note d = new Note(Note.D, 2);
		Chord dAdd9 = new Chord(d, ChordType.MAJadd9, 2);
			
		// F minor, first inversion (bass = Ab)
		Note f7 = new Note("F7");
		Chord fMin = new Chord(f7, ChordType.MINOR, 1);
	}
	
	@Test
	@SuppressWarnings("unused")
	public final void testChordProgressions(){
		Note f = new Note("F");
		ChordProgression basic = new ChordProgression(f, "I IIm IV V I");
		
		Note c = new Note("C");
		ChordProgression basic2 = new ChordProgression(c, "I V/1 VIm V I");
		
		ChordProgression basic3 = new ChordProgression(c, "I IIm IIIm IV V VIm VIIdim");
		
		ChordProgression basic4 = new ChordProgression(c, "Im IIdim IIIb IVm Vm VIb VIIb");
		
		assertEquals(basic3.getChordSequence().get(0).toString(), "C4 E4 G4");
		assertEquals(basic3.getChordSequence().get(1).toString(), "D4 F4 A4");
		assertEquals(basic3.getChordSequence().get(2).toString(), "E4 G4 B4");
		assertEquals(basic3.getChordSequence().get(3).toString(), "F4 A4 C5");
		assertEquals(basic3.getChordSequence().get(4).toString(), "G4 B4 D5");
		assertEquals(basic3.getChordSequence().get(5).toString(), "A4 C5 E5");
		assertEquals(basic3.getChordSequence().get(6).toString(), "B4 D5 F5");
		
		assertEquals(basic4.getChordSequence().get(0).toString(), "C4 D#/Eb4 G4");
		assertEquals(basic4.getChordSequence().get(1).toString(), "D4 F4 G#/Ab4");
		assertEquals(basic4.getChordSequence().get(2).toString(), "D#/Eb4 G4 A#/Bb4");
		assertEquals(basic4.getChordSequence().get(3).toString(), "F4 G#/Ab4 C5");
		assertEquals(basic4.getChordSequence().get(4).toString(), "G4 A#/Bb4 D5");
		assertEquals(basic4.getChordSequence().get(5).toString(), "G#/Ab4 C5 D#/Eb5");
		assertEquals(basic4.getChordSequence().get(6).toString(), "A#/Bb4 D5 F5");
		
	}

}
