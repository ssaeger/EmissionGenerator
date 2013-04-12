package data;

import java.util.Random;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;

/**
 * This class is a factory which creates the selected confounder. It's
 * implemented as a sigleton.
 * 
 * @author Sebastian
 * 
 */
public class ConfounderFactory {
	public static final String[] CONFOUNDERLIST = {
			"Normal distribution, \u03C3 = 1",
			"Normal distribution, \u03C3 = 2" };

	private static ConfounderFactory instance;

	private ConfounderFactory() {
	}

	public static ConfounderFactory getInstance() {
		if (instance == null) {
			instance = new ConfounderFactory();
		}
		return instance;
	}

	/**
	 * Creates the selected confounder.
	 * 
	 * @param id
	 *            id of the selected confounder
	 * @return the created confounder
	 */
	public NumberGenerator<?> createConfounder(int id) {
		String confounderName = CONFOUNDERLIST[id];
		if (confounderName.equals("Normal distribution, \u03C3 = 1")) {
			return new GaussianGenerator(0, 1, new Random());
		} else if (confounderName.equals("Normal distribution, \u03C3 = 2")) {
			return new GaussianGenerator(0, 2, new Random());
		} else {
			throw new IllegalArgumentException("Wrong confounder number!");
		}

	}

}