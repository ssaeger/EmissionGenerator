package model.movement;




/**
 * This class is a factory which creates the selected model. It's implemented as
 * a sigleton.
 * 
 * @author Sebastian
 * 
 */
public class MovementmodelFactory {
	/**
	 * Array with the names of all models
	 */
	public static final String[] MODELLIST = { "MoveModel", "EatModel" };

	private static MovementmodelFactory instance;

	private MovementmodelFactory() {
	}

	public static MovementmodelFactory getInstance() {
		if (instance == null) {
			instance = new MovementmodelFactory();
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
	public Movementmodel createModel(int id) {
		String modelName = MODELLIST[id];
		if (modelName.equals("MoveModel")) {
			return new MoveModel(MODELLIST[id], id);
		} else if (modelName.equals("EatModel")) {
			return new EatModel(MODELLIST[id], id);
		} else {
			throw new IllegalArgumentException("Wrong model number!");
		}
	}
}