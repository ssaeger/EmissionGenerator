package data.model;

import java.security.GeneralSecurityException;

import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.ExponentialGenerator;

import data.Move;
import data.Movementsequence;

/**
 * This class represents a model which generates a particular movementsequence.
 * 
 * @author Sebastian
 * 
 */
public class MoveModel extends Model {

	public MoveModel(String name, int id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public Movementsequence generateMovementsequence(int size) {
		ExponentialGenerator expGen;
		Movementsequence moveSeq = new Movementsequence();
		AESCounterRNG rnd;
		Move aktMove;

		try {
			rnd = new AESCounterRNG();
			expGen = new ExponentialGenerator(1, rnd);
			int steps;
			for (int i = 0; i < size;) {
				// generate number of steps with the same move
				steps = (int) Math.round(expGen.nextValue());

				// generate current move
				aktMove = new Move(rnd.nextDouble(), rnd.nextDouble());

				// add current move
				for (int j = 0; j < steps && i < size; j++) {
					moveSeq.addMove(aktMove);
					i++;
				}
				// before generation of a new move slow down the old move
				// +0.01 to avoid a move with (0,0)
				moveSeq.addMove(aktMove.divideBy(rnd.nextInt(3) + 1));
				i++;
			}
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		return moveSeq;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
