# Music Theory ADT

## Notes

```java
Note c = new Note(Note.C); // C middle octave
Note csharp = new Note(Note.Csharp); // C# middle octave
Note d = new Note(Note.D, 6); // D, 6th octave
Note fsharp = new Note(Note.Fsharp, 3); // F#, 3rd octave
```

Or using a string as a parameter:

```java
Note d = new Note("D");
Note e = new Note("E3");
Note fsharp = new Note("f#");
Note eflat6 = new Note("Eb6");
```

## Chords

```java
Note c = new Note("C");
Chord cMajor = new Chord(c, ChordType.MAJOR); // C4 major
		
Note d = new Note(Note.D, 2);
Chord dAdd9 = new Chord(d, ChordType.MAJadd9); // D2 major with add9
		
Note eb7 = new Note("Eb7");
Chord ebAug = new Chord(eb7, ChordType.AUG); // Eb7 augmented
```
With inversions:

```java
// C major, root inversion (bass = C)
Note c = new Note("C");
Chord cMajor = new Chord(c, ChordType.MAJOR, 0);
	
// D major add9, second inversion (bass = A)	
Note d = new Note(Note.D, 2);
Chord dAdd9 = new Chord(d, ChordType.MAJadd9, 2);
	
// F minor, first inversion (bass = Ab)
Note f7 = new Note("F7");
Chord fMin = new Chord(f7, ChordType.MINOR, 1);
```

## Chord progression

```java
Note f = new Note("F");
ChordProgression basic = new ChordProgression(f, "I IIm IV V I");
// Chord sequence is:
// F major
// G minor
// Bb major
// C major
// F major

Note c = new Note("C");
ChordProgression basic2 = new ChordProgression(c, "I V/1 VIm V I");
// C major
// G major (bass = B)
// A minor
// G major
// C major
```

## Chord progression notation ("Agnostic notation")

All roman numbers mean `major` or `perfect` interval:

| Roman | Interval |
|:-------|------------:|
| I      | Unison        |
| II     | Major second      |
| III    | Major third        |
| IV     | Perfect fourth          |
| V      | Perfect fifth       |
| VI     | Major sixth     |
| VII    | Major seventh     |

Also, all roman numbers mean major chords:

```
If the root note is C, then
I = C major
VI = A major
VII = B major
and so on...
```

If you want to generate all the diatonic chords in the C major scale, then you must do:

```
I IIm IIIm IV V VIm VIIdim
```

Or in case you want the minor scale:

```
Im IIdim IIIb IVm Vm VIb VIIb
```

The `b` and `#` can be used in any roman number, even `I`.

That's why I named this notation `agnostic notation`. It doesn't know about scales or anything.

In the future, new notations can be added, and they could translate to this agnostic notation, for example, something more intelligent like:

```
C minor key: I VI VII
```

Translate to:

```
Im VIb VIIb
```

Which is the equivalent using agnostic notation.
