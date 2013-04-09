package data;

import java.util.LinkedList;

/**
 * This class contains the movementsequence and functions to create and modify
 * the movementsequence.
 * 
 * @author Sebastian
 * 
 */
public class Movementsequence {
	LinkedList<Move> sequence;

	public Movementsequence() {
		this.sequence = new LinkedList<>();
	}

	/**
	 * Creates a movementsequence from a list of moves
	 * 
	 * @param sequence
	 */
	public Movementsequence(LinkedList<Move> sequence) {
		this.sequence = sequence;
	}

	/**
	 * Adds a move to the movementsequence
	 * 
	 * @param move
	 *            the move to add
	 */
	public void addMove(Move move) {
		this.sequence.add(move);
	}

	public LinkedList<Move> getSequence() {
		return this.sequence;
	}

}
