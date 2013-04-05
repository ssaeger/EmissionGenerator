package data.model;

import java.security.GeneralSecurityException;
import java.util.Random;

import org.uncommons.maths.random.AESCounterRNG;
import org.uncommons.maths.random.ExponentialGenerator;

import data.Movementsequence;

public class EatModel extends Model {

	public EatModel() {
		this.name = "EatModel";
		this.id = 1;
	}

	@Override
	public Movementsequence generateMovementsequence(int size) {
		// TODO Auto-generated method stub
		ExponentialGenerator expGen;
		Movementsequence moveSeq;
		try {
			expGen = new ExponentialGenerator(1, new AESCounterRNG());
		} catch (GeneralSecurityException e) {
			expGen = new ExponentialGenerator(1, new Random());
		}

		int steps = (int) Math.round(expGen.nextValue() * 10);

		// ArrayList<Double> d = new ArrayList<>();
		// for (int i = 0; i < 11; i++) {
		// d.add(expGen.nextValue());
		// }
		return null;
	}
}
