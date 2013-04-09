package data.model;

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
		switch (id) {
		case 0:
			return new MoveModel(MODELLIST[id], id);
		case 1:
			return new EatModel(MODELLIST[id], id);
		default:
			throw new IllegalArgumentException("Wrong model number!");
		}
	}
}