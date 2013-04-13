package model.movement;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class contains the movementsequence and functions to create and modify
 * the movementsequence.
 * 
 * @author Sebastian
 * 
 */
public class Movementsequence {
	private final LinkedList<Move> sequence;

	public Movementsequence() {
		this.sequence = new LinkedList<>();
	}

	public Movementsequence(String movSeq) {
		this.sequence = this.fromString(movSeq);
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

	@Override
	public String toString() {
		String s = "";
		Iterator<Move> iterator = this.sequence.iterator();

		s += iterator.next().toString();

		while (iterator.hasNext()) {
			s += "," + iterator.next().toString();
		}
		return s + "\n";
	}

	private LinkedList<Move> fromString(String moveString) {
		LinkedList<Move> sequenceFromString = new LinkedList<Move>();

		// remove \n at the last position of the string
		moveString = moveString.substring(0, moveString.length() - 1);
		String[] moveArray = moveString.split(",");
		String[] moveCoordinates;
		for (int i = 0; i < moveArray.length; i++) {
			// remove the braces ( )
			moveArray[i] = moveArray[i].substring(1, moveArray[i].length() - 1);
			moveCoordinates = moveArray[i].split("\\|");
			// convert to Double
			sequenceFromString.add(new Move(Double
					.parseDouble(moveCoordinates[0]), Double
					.parseDouble(moveCoordinates[1])));
		}
		return sequenceFromString;
	}
}
