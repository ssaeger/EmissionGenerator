package model.movement;

import java.security.GeneralSecurityException;

import org.uncommons.maths.random.AESCounterRNG;

/**
 * This class represents a model which generates a particular movementsequence.
 * 
 * @author Sebastian
 * 
 */
public class EatModel extends Movementmodel {

	public EatModel(String name, int id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public Movementsequence generateMovementsequence(int size) {
		Movementsequence moveSeq = new Movementsequence();
		AESCounterRNG rnd;

		try {
			rnd = new AESCounterRNG();
			for (int i = 0; i <= size; i++) {

				// generate and add current move
				moveSeq.addMove(new Move(rnd.nextDouble(), rnd.nextDouble()));

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
