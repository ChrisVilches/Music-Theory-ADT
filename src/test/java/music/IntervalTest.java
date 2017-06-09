package music;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

	@Test
	public final void testFindNoteByIntervalAsc() {
		
		Note a;
		
		a = new Note(Note.A, 4).findNoteByInterval(Interval.MIN7);
		assertEquals(a.toString(), "G5");
		
		a = new Note(Note.B, 4).findNoteByInterval(Interval.PERF5);
		assertEquals(a.toString(), "F#/Gb5");
		
		a = new Note(Note.G, 4).findNoteByInterval(Interval.PERF4);
		assertEquals(a.toString(), "C5");		

		a = new Note(Note.B, 6).findNoteByInterval(Interval.MIN2);
		assertEquals(a.toString(), "C7");
		
		assertEquals(new Note("C5").findNoteByInterval(Interval.PERF4).toString(), "F5");		
		assertEquals(new Note("C#5").findNoteByInterval(Interval.PERF4).toString(), "F#/Gb5");
		assertEquals(new Note("D5").findNoteByInterval(Interval.PERF4).toString(), "G5");
		assertEquals(new Note("D#5").findNoteByInterval(Interval.MAJ3).toString(), "G5");
		assertEquals(new Note("D#5").findNoteByInterval(Interval.MIN3).toString(), "F#/Gb5");
		assertEquals(new Note("Bb4").findNoteByInterval(Interval.PERF4).toString(), "D#/Eb5");
		assertEquals(new Note("A#5").findNoteByInterval(Interval.PERF4).toString(), "D#/Eb6");
		assertEquals(new Note("C5").findNoteByInterval(Interval.MIN7).toString(), "A#/Bb5");
		assertEquals(new Note("B5").findNoteByInterval(Interval.MAJ7).toString(), "A#/Bb6");
		assertEquals(new Note("G#5").findNoteByInterval(Interval.MIN2).toString(), "A5");
		assertEquals(new Note("C5").findNoteByInterval(Interval.MAJ2).toString(), "D5");		
	}
	
	@Test
	public final void testIntervalsHigherThanOctave(){		
		assertEquals(new Note("C4").distance(new Note("C#5")), Interval.MIN9.getValue());
		assertEquals(new Note("C4").distance(new Note("D5")), Interval.MAJ9.getValue());
		assertEquals(new Note("D5").distance(new Note("C4")), -Interval.MAJ9.getValue());		
		assertEquals(new Note("C4").distance(new Note("D#5")), Interval.MIN10.getValue());
		assertEquals(new Note("C4").distance(new Note("Eb5")), Interval.MIN10.getValue());
		assertEquals(new Note("C4").distance(new Note("E5")), Interval.MAJ10.getValue());
		assertEquals(new Note("C4").distance(new Note("F5")), Interval.PERF11.getValue());
		assertEquals(new Note("C4").distance(new Note("F#5")), Interval.TRITONEOCT.getValue());
		assertEquals(new Note("C4").distance(new Note("G5")), Interval.PERF12.getValue());
		assertEquals(new Note("C4").distance(new Note("Ab5")), Interval.MIN13.getValue());
		assertEquals(new Note("C4").distance(new Note("A5")), Interval.MAJ13.getValue());
		assertEquals(new Note("C4").distance(new Note("Bb5")), Interval.MIN14.getValue());
		assertEquals(new Note("C4").distance(new Note("B5")), Interval.MAJ14.getValue());
		assertEquals(new Note("C#4").distance(new Note("C6")), Interval.MAJ14.getValue());		
	}

	
	@Test
	public final void testFindNoteByIntervalDesc(){
		
		Note a;
		
		a = new Note(Note.C).findNoteByInterval(-Interval.MAJ2.getValue());
		assertEquals(a.toString(), "A#/Bb3");
		
		a = new Note(Note.D).findNoteByInterval(-Interval.MAJ2.getValue());
		assertEquals(a.toString(), "C4");
		
		a = new Note(Note.Fsharp, 5).findNoteByInterval(-Interval.PERF4.getValue());
		assertEquals(a.toString(), "C#/Db5");
		
		a = new Note(Note.Fsharp, 5).findNoteByInterval(-Interval.PERF5.getValue());
		assertEquals(a.toString(), "B4");	
		
		assertEquals(new Note("C5").findNoteByInterval(-Interval.PERF4.getValue()).toString(), "G4");
		assertEquals(new Note("C#5").findNoteByInterval(-Interval.PERF4.getValue()).toString(), "G#/Ab4");
		assertEquals(new Note("D5").findNoteByInterval(-Interval.PERF4.getValue()).toString(), "A4");
		assertEquals(new Note("D#5").findNoteByInterval(-Interval.MAJ3.getValue()).toString(), "B4");
		assertEquals(new Note("D#5").findNoteByInterval(-Interval.MIN3.getValue()).toString(), "C5");
		assertEquals(new Note("Bb4").findNoteByInterval(-Interval.PERF4.getValue()).toString(), "F4");
		assertEquals(new Note("A#5").findNoteByInterval(-Interval.PERF4.getValue()).toString(), "F5");
		assertEquals(new Note("C5").findNoteByInterval(-Interval.MIN7.getValue()).toString(), "D4");
		assertEquals(new Note("B5").findNoteByInterval(-Interval.MAJ7.getValue()).toString(), "C5");
		assertEquals(new Note("G#5").findNoteByInterval(-Interval.MIN2.getValue()).toString(), "G5");
		assertEquals(new Note("C5").findNoteByInterval(-Interval.MAJ2.getValue()).toString(), "A#/Bb4");		
	}
	
	
	@Test
	public final void testFindNoteByIntervalOctave(){
		
		Note a;
		
		a = new Note(Note.B, 6).findNoteByInterval(Interval.OCTAVE);
		assertEquals(a.toString(), "B7");
		
		a = new Note(Note.Fsharp, 4).findNoteByInterval(Interval.OCTAVE);
		assertEquals(a.toString(), "F#/Gb5");
		
		a = new Note(Note.Db, 3).findNoteByInterval(Interval.OCTAVE);
		assertEquals(a.toString(), "C#/Db4");	
		
	}
	
	
	@Test
	public final void testFindNoteByIntervalUnison(){
		
		Note a;
		
		a = new Note(Note.Dsharp, 3).findNoteByInterval(Interval.UNISON);
		assertEquals(a.toString(), "D#/Eb3");
		
		a = new Note(Note.C, 5).findNoteByInterval(Interval.UNISON);
		assertEquals(a.toString(), "C5");
		
		a = new Note(Note.Fsharp, 4).findNoteByInterval(Interval.UNISON);
		assertEquals(a.toString(), "F#/Gb4");		
	}
	
	
	@Test
	public final void testDistanceZero(){
		
		assertEquals(new Note("C5").distance(new Note("c5")), 0);
		assertEquals(new Note("D4").distance(new Note("D4")), 0);
		assertEquals(new Note("E3").distance(new Note("e3")), 0);
		assertEquals(new Note("f2").distance(new Note("F2")), 0);
		assertEquals(new Note("g1").distance(new Note("G1")), 0);
		assertEquals(new Note("a").distance(new Note("A4")), 0);
		assertEquals(new Note("B").distance(new Note("B4")), 0);		
		assertEquals(new Note("C#5").distance(new Note("C#5")), 0);
		assertEquals(new Note("d#4").distance(new Note("eb")), 0);
		assertEquals(new Note("G#3").distance(new Note("ab3")), 0);
		assertEquals(new Note("a#").distance(new Note("bb4")), 0);
		assertEquals(new Note("F#1").distance(new Note("gb1")), 0);		
	}
	
	
	@Test
	public final void testDistancePositive(){		
		assertEquals(new Note("C5").distance(new Note("D5")), 2);
		assertEquals(new Note("C5").distance(new Note("f5")), 5);
		assertEquals(new Note("C5").distance(new Note("C6")), 12);
		assertEquals(new Note("C5").distance(new Note("D6")), 14);
		assertEquals(new Note("C5").distance(new Note("G6")), 19);
		assertEquals(new Note("C5").distance(new Note("c7")), 24);
		assertEquals(new Note("C5").distance(new Note("c7")), 24);
		assertEquals(new Note("C#5").distance(new Note("c7")), 23);
		assertEquals(new Note("D5").distance(new Note("c7")), 22);
		assertEquals(new Note("Eb5").distance(new Note("c7")), 21);
		assertEquals(new Note("E5").distance(new Note("c7")), 20);
		assertEquals(new Note("F5").distance(new Note("c7")), 19);
		assertEquals(new Note("gb5").distance(new Note("c7")), 18);
		assertEquals(new Note("g5").distance(new Note("c7")), 17);
		assertEquals(new Note("g#5").distance(new Note("c7")), 16);
		assertEquals(new Note("a5").distance(new Note("c7")), 15);
		assertEquals(new Note("a#5").distance(new Note("c7")), 14);
		assertEquals(new Note("b5").distance(new Note("c7")), 13);
		assertEquals(new Note("c6").distance(new Note("c7")), 12);
		assertEquals(new Note("db6").distance(new Note("c7")), 11);
		assertEquals(new Note("d6").distance(new Note("c7")), 10);
		assertEquals(new Note("d#6").distance(new Note("c7")), 9);
		assertEquals(new Note("e6").distance(new Note("c7")), 8);	
	}

	
	@Test
	public final void testDistanceNegative(){		
		assertEquals(new Note("D5").distance(new Note("C5")), -2);
		assertEquals(new Note("f5").distance(new Note("C5")), -5);
		assertEquals(new Note("C6").distance(new Note("C5")), -12);
		assertEquals(new Note("D6").distance(new Note("C5")), -14);
		assertEquals(new Note("G6").distance(new Note("C5")), -19);
		assertEquals(new Note("c7").distance(new Note("C5")), -24);
		assertEquals(new Note("c7").distance(new Note("C5")), -24);
		assertEquals(new Note("c7").distance(new Note("C#5")), -23);
		assertEquals(new Note("c7").distance(new Note("D5")), -22);
		assertEquals(new Note("c7").distance(new Note("Eb5")), -21);
		assertEquals(new Note("c7").distance(new Note("E5")), -20);
		assertEquals(new Note("c7").distance(new Note("F5")), -19);
		assertEquals(new Note("c7").distance(new Note("gb5")), -18);
		assertEquals(new Note("c7").distance(new Note("g5")), -17);
		assertEquals(new Note("c7").distance(new Note("g#5")), -16);
		assertEquals(new Note("c7").distance(new Note("a5")), -15);
		assertEquals(new Note("c7").distance(new Note("a#5")), -14);
		assertEquals(new Note("c7").distance(new Note("b5")), -13);
		assertEquals(new Note("c7").distance(new Note("c6")), -12);
		assertEquals(new Note("c7").distance(new Note("db6")), -11);
		assertEquals(new Note("c7").distance(new Note("d6")), -10);
		assertEquals(new Note("c7").distance(new Note("d#6")), -9);
		assertEquals(new Note("c7").distance(new Note("e6")), -8);	
	}
}
