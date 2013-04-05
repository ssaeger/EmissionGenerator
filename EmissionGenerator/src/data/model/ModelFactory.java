package data.model;

public class ModelFactory {
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

	public Model createModel(int id) {
		switch (id) {
		case 0:
			return new MoveModel();
		case 1:
			return new EatModel();
		default:
			throw new IllegalArgumentException("Wrong model number!");
		}
	}
}