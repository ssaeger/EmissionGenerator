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

//	@Override
//	public Movementsequence generateMovementsequence(int size) {
//		Movementsequence moveSeq = new Movementsequence();
//		AESCounterRNG rnd;
//
//		try {
//			rnd = new AESCounterRNG();
//			for (int i = 0; i <= size; i++) {
//
//				// generate and add current move
//				moveSeq.addMove(new Move(rnd.nextDouble(), rnd.nextDouble()));
//
//			}
//		} catch (GeneralSecurityException e1) {
//			e1.printStackTrace();
//		}
//		return moveSeq;
//	}

	 @Override
	 public Movementsequence generateMovementsequence(int size) {
	 Movementsequence moveSeq = new Movementsequence();
	 AESCounterRNG rnd;
	 double x;
	 double y;
	
	 try {
	 rnd = new AESCounterRNG();
	 for (int i = 0; i <= size; i++) {
	
	 x = rnd.nextDouble();
	 y = rnd.nextDouble();
	 if ((int) (x * 100) % 2 == 0) {
	 x *= -1;
	 }
	 if ((int) (y * 100) % 2 == 0) {
	 y *= -1;
	 }
	
	 // generate and add current move
	 moveSeq.addMove(new Move(x, y));
	
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
