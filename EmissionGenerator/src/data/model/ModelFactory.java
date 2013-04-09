package data.model;

import java.util.Arrays;

/**
 * This class is a factory which creates the selected model. It's implemented as
 * a sigleton.
 * 
 * @author Sebastian
 * 
 */
public class ModelFactory {
	/**
	 * Array with the names of all models
	 */
	public static final String[] MODELLIST = { "MoveModel", "EatModel" };

	private static ModelFactory instance;

	private ModelFactory() {
	}

	public static ModelFactory getInstance() {
		if (instance == null) {
			instance = new ModelFactory();
		}
		return instance;
	}

	/**
	 * Creates the selected model.
	 * 
	 * @param id
	 *            the id of the model
	 * @return the created model
	 */
	public Model createModel(int id) {
		if (id == Arrays.asList(MODELLIST).indexOf("MoveModel")) {
			return new MoveModel(MODELLIST[id], id);
		} else if (id == Arrays.asList(MODELLIST).indexOf("EatModel")) {
			return new EatModel(MODELLIST[id], id);
		} else {
			throw new IllegalArgumentException("Wrong model number!");
		}
	}
}