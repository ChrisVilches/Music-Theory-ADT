package music;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import music.Chord.ChordType;

@RunWith(Enclosed.class)
public class ChordProgressionTest {

	@RunWith(Parameterized.class)
	public static class WithWrongParametersAgnosticRoman {

		@Parameters
		public static Collection<Object> wrongFormatRoman() {
			return Arrays.asList(new Object[] { "a", "b", "", "34", "VII-6", "Imayor", " II ", "III ", "i#", "ii", "#", "b", "maj7" });
		}

		@Parameter
		public String wrongFormatParameter;

		@Test(expected = IllegalArgumentException.class)
		public final void testGetChordByRomanScaleAgnosticWrong() {
			ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), wrongFormatParameter);
		}
	}

	@RunWith(Parameterized.class)
	public static class WithCorrectParametersAgnosticRoman {

		@Parameters
		public static Collection<Object> correctFormatRoman() {
			return Arrays.asList(new Object[] { "I", "II", "III", "IV", "V", "VI", "VII", "Ib", "IIb", "IIIb", "IVb",
					"Vb", "VIb", "VIIb", "I#", "II#", "III#", "IV#", "V#", "VI#", "VII#", "IIbmaj", "II#min", "Ib-7",
					"I#-7", "Vmaj7", "V#maj7", "Vbmin7", "VIm", "VIIm", "Im", "IIm" });
		}

		@Parameter
		public String correctFormatParameter;

		@Test
		public final void testGetChordByRomanScaleAgnosticCorrect() {
			ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), correctFormatParameter);
		}
	}

	@RunWith(Parameterized.class)
	public static class AgnosticRomanProgressionCreation {

		@Parameters
		public static Collection<Object[]> progressions() {
			return Arrays.asList(new Object[][] { 
				{ Note.C, 4, "I II IV", new int[]{Note.C, Note.D, Note.F}, new int[]{4, 4, 4}, new ChordType[]{ChordType.MAJOR, ChordType.MAJOR, ChordType.MAJOR}},
				
				{ Note.C, 4, "Im IIm IVm", new int[]{Note.C, Note.D, Note.F}, new int[]{4, 4, 4}, new ChordType[]{ChordType.MINOR, ChordType.MINOR, ChordType.MINOR}},
				
				{ Note.C, 4, "II IV V", new int[]{Note.D, Note.F, Note.G}, new int[]{4, 4, 4}, new ChordType[]{ChordType.MAJOR, ChordType.MAJOR, ChordType.MAJOR}},
				
				{ Note.C, 4, "V V V", new int[]{Note.G, Note.G, Note.G}, new int[]{4, 4, 4}, new ChordType[]{ChordType.MAJOR, ChordType.MAJOR, ChordType.MAJOR}},
				
				{ Note.C, 4, "Imin IImin IVmin", new int[]{Note.C, Note.D, Note.F}, new int[]{4, 4, 4}, new ChordType[]{ChordType.MINOR, ChordType.MINOR, ChordType.MINOR}},
				
				{ Note.B, 4, "I-7 IIb IV#", new int[]{Note.B, Note.C, Note.F}, new int[]{4, 5, 5}, new ChordType[]{ChordType.MIN7, ChordType.MAJOR, ChordType.MAJOR}},
				
				{ Note.B, 4, "I#-7 IIb IV#", new int[]{Note.C, Note.C, Note.F}, new int[]{5, 5, 5}, new ChordType[]{ChordType.MIN7, ChordType.MAJOR, ChordType.MAJOR}},

				{ Note.Bb, 5, "VII VIbmaj7 Vaug", new int[]{Note.A, Note.Fsharp, Note.F}, new int[]{6, 6, 6}, new ChordType[]{ChordType.MAJOR, ChordType.MAJ7, ChordType.AUG}},
				
				{ Note.D, 5, "VIIb-7 VIbmaj7 V#7", new int[]{Note.C, Note.Bb, Note.Bb}, new int[]{6, 5, 5}, new ChordType[]{ChordType.MIN7, ChordType.MAJ7, ChordType.MAJORMIN7}},
			});
		}

		@Parameter(0)
		public int baseNote;
		
		@Parameter(1)
		public int baseNoteOctave;

		@Parameter(2)
		public String notation;
		
		@Parameter(3)
		public int[] notes;
		
		@Parameter(4)
		public int[] notesOctaves;
		
		@Parameter(5)
		public ChordType[] chordTypes;
		
		@Test
		public final void testExpectedArrayLength() {			
			ChordProgression cp = new ChordProgression(new Note(baseNote, baseNoteOctave), notation);			
			List<Chord> chords = cp.getChordSequence();			
			assertEquals(chords.size(), notes.length);
			assertEquals(chords.size(), chordTypes.length);			
		}
		
		@Test
		public final void testCorrectProgressionBaseNote(){
			Note note = new Note(baseNote, baseNoteOctave);
			ChordProgression cp = new ChordProgression(note, notation);
			assertEquals(cp.getBaseNote(), note);
		}

		@Test
		public final void testCorrectProgressionCreationAgnostic() {
			ChordProgression cp = new ChordProgression(new Note(baseNote, baseNoteOctave), notation);
			List<Chord> chords = cp.getChordSequence();	
			for(int i=0; i<chords.size(); i++){				
				Chord c = chords.get(i);
				assertEquals(c, new Chord(new Note(notes[i], notesOctaves[i]), chordTypes[i]));				
			}			
		}				
	}

	public static class WithoutParameters {
		
		
		@Test
		public final void testProgressionTranspose(){
			
			Note c = new Note(Note.C, 4);
			ChordProgression cp = new ChordProgression(c, "IV#aug V-7 VIIbmin7");
			
			assertEquals(cp.getBaseNote(), new Note(Note.C));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Fsharp, 4), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.G, 4), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.Bb, 4), ChordType.MIN7));
			
			cp.transpose(2);			
			assertEquals(cp.getBaseNote(), new Note(Note.D));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Gsharp, 4), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.A, 4), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.C, 5), ChordType.MIN7));
			
			cp.transpose(2);			
			assertEquals(cp.getBaseNote(), new Note(Note.E));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Asharp, 4), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.B, 4), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.D, 5), ChordType.MIN7));
			
			cp.transpose(4);			
			assertEquals(cp.getBaseNote(), new Note(Note.Gsharp));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.D, 5), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.Dsharp, 5), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.Fsharp, 5), ChordType.MIN7));
			
			cp.transpose(4);			
			assertEquals(cp.getBaseNote(), new Note(Note.C, 5));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Fsharp, 5), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.G, 5), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.Bb, 5), ChordType.MIN7));
			
			cp.transpose(4);			
			assertEquals(cp.getBaseNote(), new Note(Note.E, 5));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Bb, 5), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.B, 5), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.D, 6), ChordType.MIN7));
			
			cp.transpose(-12);			
			assertEquals(cp.getBaseNote(), new Note(Note.E));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Bb, 4), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.B, 4), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.D, 5), ChordType.MIN7));
			
			cp.transpose(-24);			
			assertEquals(cp.getBaseNote(), new Note(Note.E, 2));
			assertEquals(cp.getChordSequence().get(0), new Chord(new Note(Note.Bb, 2), ChordType.AUG));
			assertEquals(cp.getChordSequence().get(1), new Chord(new Note(Note.B, 2), ChordType.MIN7));
			assertEquals(cp.getChordSequence().get(2), new Chord(new Note(Note.D, 3), ChordType.MIN7));			
		}
		
		
		

		@Test
		public final void testGetChordByRomanScaleAgnostic() {
			Chord ch;
			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "I");
			assertEquals(ch, new Chord(new Note(Note.C, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "II");
			assertEquals(ch, new Chord(new Note(Note.D, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "III");
			assertEquals(ch, new Chord(new Note(Note.E, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "IV");
			assertEquals(ch, new Chord(new Note(Note.F, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "V");
			assertEquals(ch, new Chord(new Note(Note.G, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "VI");
			assertEquals(ch, new Chord(new Note(Note.A, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "VII");
			assertEquals(ch, new Chord(new Note(Note.B, 4), ChordType.MAJOR));
		}

		@Test
		public final void testGetChordByRomanScaleAgnosticFlat() {
			Chord ch;
			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "Ib");
			assertEquals(ch, new Chord(new Note(Note.B, 3), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "IIb");
			assertEquals(ch, new Chord(new Note(Note.Db, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "IIIb");
			assertEquals(ch, new Chord(new Note(Note.Eb, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "IVb");
			assertEquals(ch, new Chord(new Note(Note.E, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "Vb");
			assertEquals(ch, new Chord(new Note(Note.Gb, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "VIb");
			assertEquals(ch, new Chord(new Note(Note.Ab, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "VIIb");
			assertEquals(ch, new Chord(new Note(Note.Bb, 4), ChordType.MAJOR));
		}

		@Test
		public final void testGetChordByRomanScaleAgnosticSharp() {
			Chord ch;
			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "I#");
			assertEquals(ch, new Chord(new Note(Note.Csharp, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "II#");
			assertEquals(ch, new Chord(new Note(Note.Dsharp, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "III#");
			assertEquals(ch, new Chord(new Note(Note.F, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "IV#");
			assertEquals(ch, new Chord(new Note(Note.Fsharp, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "V#");
			assertEquals(ch, new Chord(new Note(Note.Gsharp, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "VI#");
			assertEquals(ch, new Chord(new Note(Note.Asharp, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 4), "VII#");
			assertEquals(ch, new Chord(new Note(Note.C, 5), ChordType.MAJOR));
		}

		@Test
		public final void testGetChordByRomanScaleAgnosticNextOctave() {
			Chord ch;
			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "I");
			assertEquals(ch, new Chord(new Note(Note.A, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "II");
			assertEquals(ch, new Chord(new Note(Note.B, 4), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "III");
			assertEquals(ch, new Chord(new Note(Note.Csharp, 5), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "IV");
			assertEquals(ch, new Chord(new Note(Note.D, 5), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "V");
			assertEquals(ch, new Chord(new Note(Note.E, 5), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "VI");
			assertEquals(ch, new Chord(new Note(Note.Fsharp, 5), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 4), "VII");
			assertEquals(ch, new Chord(new Note(Note.Ab, 5), ChordType.MAJOR));
		}

		@Test
		public final void testGetChordByRomanScaleAgnosticOtherChordTypes() {
			Chord ch;

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "Imin");
			assertEquals(ch, new Chord(new Note(Note.C, 2), ChordType.MINOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "IIaug");
			assertEquals(ch, new Chord(new Note(Note.D, 2), ChordType.AUG));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "IIImaj");
			assertEquals(ch, new Chord(new Note(Note.E, 2), ChordType.MAJOR));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "IV#maj7");
			assertEquals(ch, new Chord(new Note(Note.Fsharp, 2), ChordType.MAJ7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "Vmajmin7");
			assertEquals(ch, new Chord(new Note(Note.G, 2), ChordType.MAJORMIN7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "VIbmajmin7");
			assertEquals(ch, new Chord(new Note(Note.Ab, 2), ChordType.MAJORMIN7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "VIaug");
			assertEquals(ch, new Chord(new Note(Note.A, 2), ChordType.AUG));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.C, 2), "VII7");
			assertEquals(ch, new Chord(new Note(Note.B, 2), ChordType.MAJORMIN7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 3), "V-7");
			assertEquals(ch, new Chord(new Note(Note.E, 4), ChordType.MIN7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 3), "VIIb-7");
			assertEquals(ch, new Chord(new Note(Note.G, 4), ChordType.MIN7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 3), "VII#-7");
			assertEquals(ch, new Chord(new Note(Note.A, 4), ChordType.MIN7));

			ch = ChordProgression.getChordByRomanScaleAgnostic(new Note(Note.A, 3), "VII#");
			assertEquals(ch, new Chord(new Note(Note.A, 4), ChordType.MAJOR));
		}
	}
}
