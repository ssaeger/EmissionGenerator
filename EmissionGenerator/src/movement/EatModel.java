package movement;

import java.security.GeneralSecurityException;



import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.ExponentialGenerator;


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
		ExponentialGenerator expGen;
		Movementsequence moveSeq = new Movementsequence();
		AESCounterRNG rnd;
		Move aktMove;

		try {
			rnd = new AESCounterRNG();
			expGen = new ExponentialGenerator(1, rnd);
			for (int i = 0; i <= size; i++) {
				// generate current move
				aktMove = new Move(rnd.nextDouble(), rnd.nextDouble());

				// add current move
				moveSeq.addMove(aktMove);

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
