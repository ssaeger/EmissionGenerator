package data.model;

import java.security.GeneralSecurityException;

import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.ExponentialGenerator;

import data.Move;
import data.Movementsequence;

public class EatModel extends Model {

	public EatModel() {
		this.name = "EatModel";
		this.id = 1;
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
			for (int i = 0; i < size; i++) {
				// generate current move
				aktMove = new Move(rnd.nextDouble(), rnd.nextDouble());

				// add current move
				moveSeq.addMove(aktMove);

				// // befor generation of a new move slow down the old move
				// // +0.01 to avoid a move with (0,0)
				// moveSeq.addMove(aktMove.divideBy(rnd.nextInt(3) + 1));
				// i++;
			}
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		return moveSeq;
	}
}
