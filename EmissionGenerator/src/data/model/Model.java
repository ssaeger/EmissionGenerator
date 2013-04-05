package data.model;

import data.Movementsequence;

public abstract class Model {

	String name;
	int id;

	public abstract Movementsequence generateMovementsequence(int size);
}
