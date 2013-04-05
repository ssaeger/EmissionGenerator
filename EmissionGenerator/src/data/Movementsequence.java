package data;

import java.util.LinkedList;

public class Movementsequence {
	LinkedList<Move> sequence;

	public Movementsequence() {
		this.sequence = new LinkedList<>();
	}

	public Movementsequence(LinkedList<Move> sequence) {
		this.sequence = sequence;
	}

	public void addMove(Move move) {
		this.sequence.add(move);
	}
}
