package music;

enum Interval {

	UNISON(0), MIN2(1), MAJ2(2), MIN3(3), MAJ3(4), PERF4(5), TRITONE(6), PERF5(7), MIN6(8), MAJ6(9), MIN7(
			10), MAJ7(11), OCTAVE(12), MIN9(13), MAJ9(14), MIN10(15), MAJ10(16), PERF11(17), TRITONEOCT(18),
	PERF12(19), MIN13(20), MAJ13(21), MIN14(22), MAJ14(23);

	private final int interval;

	Interval(int interval) {
		this.interval = interval;
	}

	public int getValue() {
		return interval;
	}

}
