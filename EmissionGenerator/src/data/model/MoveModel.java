package data.model;

import java.security.GeneralSecurityException;

import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.ExponentialGenerator;

import data.Move;
import data.Movementsequence;

public class MoveModel extends Model {

	public MoveModel() {
		this.name = "MoveModel";
		this.id = 0;
	}

	@Override
	public Movementsequence generateMovementsequence(int size) {
		// TODO Auto-generated method stub
		ExponentialGenerator expGen;
		Movementsequence moveSeq = new Movementsequence();
		AESCounterRNG rnd;
		Move aktMove;

		try {
			rnd = new AESCounterRNG();
			expGen = new ExponentialGenerator(1, rnd);
			int steps;
			for (int i = 0; i < size;) {
				steps = (int) Math.round(expGen.nextValue() * 10);

				aktMove = new Move(rnd.nextDouble(), rnd.nextDouble());

				for (int j = 0; j < steps && i < size; j++) {
					if (j == (steps / 2)) {
						aktMove = aktMove.multiplyWith(rnd.nextInt(2) + 1);
					}
					moveSeq.addMove(aktMove);
					i++;
				}
				// +0.01 to avoid a move with (0,0)
				moveSeq.addMove(aktMove.divideBy(rnd.nextInt(3) + 1));
				i++;
			}
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return moveSeq;
	}
}
