package music;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

	@Test
	public final void testFindNoteByIntervalAsc() {
		
		Note a, b;
		
		a = new Note(Note.A, 4);
		b = Interval.findNoteByInterval(a, Interval.MIN7, Interval.ASC);
		assertEquals(b.toString(), "G5");
		
		a = new Note(Note.B, 4);
		b = Interval.findNoteByInterval(a, Interval.PERF5, Interval.ASC);
		assertEquals(b.toString(), "F#/Gb5");
		
		a = new Note(Note.G, 4);
		b = Interval.findNoteByInterval(a, Interval.PERF4, Interval.ASC);
		assertEquals(b.toString(), "C5");
		
		a = new Note(Note.B, 6);
		b = Interval.findNoteByInterval(a, Interval.MIN2, Interval.ASC);
		assertEquals(b.toString(), "C7");
		
		assertEquals(Interval.findNoteByInterval(new Note("C5"), Interval.PERF4).toString(), "F5");
		assertEquals(Interval.findNoteByInterval(new Note("C#5"), Interval.PERF4).toString(), "F#/Gb5");
		assertEquals(Interval.findNoteByInterval(new Note("D5"), Interval.PERF4).toString(), "G5");
		assertEquals(Interval.findNoteByInterval(new Note("D#5"), Interval.MAJ3).toString(), "G5");
		assertEquals(Interval.findNoteByInterval(new Note("D#5"), Interval.MIN3).toString(), "F#/Gb5");
		assertEquals(Interval.findNoteByInterval(new Note("Bb4"), Interval.PERF4).toString(), "D#/Eb5");
		assertEquals(Interval.findNoteByInterval(new Note("A#5"), Interval.PERF4).toString(), "D#/Eb6");
		assertEquals(Interval.findNoteByInterval(new Note("C5"), Interval.MIN7).toString(), "A#/Bb5");
		assertEquals(Interval.findNoteByInterval(new Note("B5"), Interval.MAJ7).toString(), "A#/Bb6");
		assertEquals(Interval.findNoteByInterval(new Note("G#5"), Interval.MIN2).toString(), "A5");
		assertEquals(Interval.findNoteByInterval(new Note("C5"), Interval.MAJ2).toString(), "D5");		
	}
	

	
	@Test
	public final void testFindNoteByIntervalDesc(){
		
		Note a;
		
		a = Interval.findNoteByInterval(new Note(Note.C), Interval.MAJ2, Interval.DESC);
		assertEquals(a.toString(), "A#/Bb3");
		
		a = Interval.findNoteByInterval(new Note(Note.D), Interval.MAJ2, Interval.DESC);
		assertEquals(a.toString(), "C4");
		
		a = Interval.findNoteByInterval(new Note(Note.Fsharp, 5), Interval.PERF4, Interval.DESC);
		assertEquals(a.toString(), "C#/Db5");
		
		a = Interval.findNoteByInterval(new Note(Note.Fsharp, 5), Interval.PERF5, Interval.DESC);
		assertEquals(a.toString(), "B4");	
		
		assertEquals(Interval.findNoteByInterval(new Note("C5"), Interval.PERF4, Interval.DESC).toString(), "G4");
		assertEquals(Interval.findNoteByInterval(new Note("C#5"), Interval.PERF4, Interval.DESC).toString(), "G#/Ab4");
		assertEquals(Interval.findNoteByInterval(new Note("D5"), Interval.PERF4, Interval.DESC).toString(), "A4");
		assertEquals(Interval.findNoteByInterval(new Note("D#5"), Interval.MAJ3, Interval.DESC).toString(), "B4");
		assertEquals(Interval.findNoteByInterval(new Note("D#5"), Interval.MIN3, Interval.DESC).toString(), "C5");
		assertEquals(Interval.findNoteByInterval(new Note("Bb4"), Interval.PERF4, Interval.DESC).toString(), "F4");
		assertEquals(Interval.findNoteByInterval(new Note("A#5"), Interval.PERF4, Interval.DESC).toString(), "F5");
		assertEquals(Interval.findNoteByInterval(new Note("C5"), Interval.MIN7, Interval.DESC).toString(), "D4");
		assertEquals(Interval.findNoteByInterval(new Note("B5"), Interval.MAJ7, Interval.DESC).toString(), "C5");
		assertEquals(Interval.findNoteByInterval(new Note("G#5"), Interval.MIN2, Interval.DESC).toString(), "G5");
		assertEquals(Interval.findNoteByInterval(new Note("C5"), Interval.MAJ2, Interval.DESC).toString(), "A#/Bb4");		
	}
	
	@Test
	public final void testFindNoteByIntervalOctave(){
		
		Note a;
		
		a = Interval.findNoteByInterval(new Note(Note.B, 6), Interval.OCTAVE, Interval.ASC);
		assertEquals(a.toString(), "B7");
		
		a = Interval.findNoteByInterval(new Note(Note.Fsharp, 4), Interval.OCTAVE, Interval.ASC);
		assertEquals(a.toString(), "F#/Gb5");
		
		a = Interval.findNoteByInterval(new Note(Note.Db, 3), Interval.OCTAVE, Interval.ASC);
		assertEquals(a.toString(), "C#/Db4");	
		
	}

	
	@Test
	public final void testFindNoteByIntervalUnison(){
		
		Note a;
		
		a = Interval.findNoteByInterval(new Note(Note.Dsharp, 3), Interval.UNISON);
		assertEquals(a.toString(), "D#/Eb3");
		
		a = Interval.findNoteByInterval(new Note(Note.C, 5), Interval.UNISON);
		assertEquals(a.toString(), "C5");
		
		a = Interval.findNoteByInterval(new Note(Note.Fsharp, 4), Interval.UNISON);
		assertEquals(a.toString(), "F#/Gb4");		
	}
	
	
	@Test
	public final void testDistanceZero(){
		
		assertEquals(Interval.distance(new Note("C5"), new Note("c5")), 0);
		assertEquals(Interval.distance(new Note("D4"), new Note("D4")), 0);
		assertEquals(Interval.distance(new Note("E3"), new Note("e3")), 0);
		assertEquals(Interval.distance(new Note("f2"), new Note("F2")), 0);
		assertEquals(Interval.distance(new Note("g1"), new Note("G1")), 0);
		assertEquals(Interval.distance(new Note("a"), new Note("A4")), 0);
		assertEquals(Interval.distance(new Note("B"), new Note("B4")), 0);		
		assertEquals(Interval.distance(new Note("C#5"), new Note("C#5")), 0);
		assertEquals(Interval.distance(new Note("d#4"), new Note("eb")), 0);
		assertEquals(Interval.distance(new Note("G#3"), new Note("ab3")), 0);
		assertEquals(Interval.distance(new Note("a#"), new Note("bb4")), 0);
		assertEquals(Interval.distance(new Note("F#1"), new Note("gb1")), 0);		
	}
	
	
	@Test
	public final void testDistancePositive(){		
		assertEquals(Interval.distance(new Note("C5"), new Note("D5")), 2);
		assertEquals(Interval.distance(new Note("C5"), new Note("f5")), 5);
		assertEquals(Interval.distance(new Note("C5"), new Note("C6")), 12);
		assertEquals(Interval.distance(new Note("C5"), new Note("D6")), 14);
		assertEquals(Interval.distance(new Note("C5"), new Note("G6")), 19);
		assertEquals(Interval.distance(new Note("C5"), new Note("c7")), 24);
		assertEquals(Interval.distance(new Note("C5"), new Note("c7")), 24);
		assertEquals(Interval.distance(new Note("C#5"), new Note("c7")), 23);
		assertEquals(Interval.distance(new Note("D5"), new Note("c7")), 22);
		assertEquals(Interval.distance(new Note("Eb5"), new Note("c7")), 21);
		assertEquals(Interval.distance(new Note("E5"), new Note("c7")), 20);
		assertEquals(Interval.distance(new Note("F5"), new Note("c7")), 19);
		assertEquals(Interval.distance(new Note("gb5"), new Note("c7")), 18);
		assertEquals(Interval.distance(new Note("g5"), new Note("c7")), 17);
		assertEquals(Interval.distance(new Note("g#5"), new Note("c7")), 16);
		assertEquals(Interval.distance(new Note("a5"), new Note("c7")), 15);
		assertEquals(Interval.distance(new Note("a#5"), new Note("c7")), 14);
		assertEquals(Interval.distance(new Note("b5"), new Note("c7")), 13);
		assertEquals(Interval.distance(new Note("c6"), new Note("c7")), 12);
		assertEquals(Interval.distance(new Note("db6"), new Note("c7")), 11);
		assertEquals(Interval.distance(new Note("d6"), new Note("c7")), 10);
		assertEquals(Interval.distance(new Note("d#6"), new Note("c7")), 9);
		assertEquals(Interval.distance(new Note("e6"), new Note("c7")), 8);	
	}
	
	
	@Test
	public final void testDistanceNegative(){		
		assertEquals(Interval.distance(new Note("D5"), new Note("C5")), -2);
		assertEquals(Interval.distance(new Note("f5"), new Note("C5")), -5);
		assertEquals(Interval.distance(new Note("C6"), new Note("C5")), -12);
		assertEquals(Interval.distance(new Note("D6"), new Note("C5")), -14);
		assertEquals(Interval.distance(new Note("G6"), new Note("C5")), -19);
		assertEquals(Interval.distance(new Note("c7"), new Note("C5")), -24);
		assertEquals(Interval.distance(new Note("c7"), new Note("C5")), -24);
		assertEquals(Interval.distance(new Note("c7"), new Note("C#5")), -23);
		assertEquals(Interval.distance(new Note("c7"), new Note("D5")), -22);
		assertEquals(Interval.distance(new Note("c7"), new Note("Eb5")), -21);
		assertEquals(Interval.distance(new Note("c7"), new Note("E5")), -20);
		assertEquals(Interval.distance(new Note("c7"), new Note("F5")), -19);
		assertEquals(Interval.distance(new Note("c7"), new Note("gb5")), -18);
		assertEquals(Interval.distance(new Note("c7"), new Note("g5")), -17);
		assertEquals(Interval.distance(new Note("c7"), new Note("g#5")), -16);
		assertEquals(Interval.distance(new Note("c7"), new Note("a5")), -15);
		assertEquals(Interval.distance(new Note("c7"), new Note("a#5")), -14);
		assertEquals(Interval.distance(new Note("c7"), new Note("b5")), -13);
		assertEquals(Interval.distance(new Note("c7"), new Note("c6")), -12);
		assertEquals(Interval.distance(new Note("c7"), new Note("db6")), -11);
		assertEquals(Interval.distance(new Note("c7"), new Note("d6")), -10);
		assertEquals(Interval.distance(new Note("c7"), new Note("d#6")), -9);
		assertEquals(Interval.distance(new Note("c7"), new Note("e6")), -8);	
	}
}
