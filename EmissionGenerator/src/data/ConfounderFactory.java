package data;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;

public class ConfounderFactory {
	public static final String[] CONFOUNDERLIST = {
			"Normal distribution, \u03C3 = 1",
			"Normal distribution, \u03C3 = 2" };

	public NumberGenerator<?> createConfounder(int id) {
		switch (id) {
		case 0:
			return new GaussianGenerator(0, 1, new Random());
		case 1:
			return new GaussianGenerator(0, 2, new Random());
		default:
			throw new IllegalArgumentException("Wrong confounder number!");
		}
	}

}

// Singleton?