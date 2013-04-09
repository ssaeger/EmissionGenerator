package data;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.table.DefaultTableModel;

import org.uncommons.maths.number.NumberGenerator;

/**
 * This class contains the emissionsequence and functions to create and modify
 * the emissionsequence. Furthermore in this class the emissionIDs are defined.
 * 
 * @author Sebastian
 * 
 */
public class Emissionsequence {

	// The IDs of the emissions are encoded as following:
	// First a character for the velocity:
	// A="accelerate", C="constant", B="brake"
	// Next one of the following numbers for the related angle:
	// 0="0°", 30="<30°", 45="<45°", 90="<90°", 135="<135°",
	// 150="<150°", 180="<=180°"
	public static final int A30 = 0;
	public static final int A45 = 1;
	public static final int A90 = 2;
	public static final int A135 = 3;
	public static final int A150 = 4;
	public static final int A180 = 5;
	public static final int C30 = 6;
	public static final int C45 = 7;
	public static final int C90 = 8;
	public static final int C135 = 9;
	public static final int C150 = 10;
	public static final int C180 = 11;
	public static final int B30 = 12;
	public static final int B45 = 13;
	public static final int B90 = 14;
	public static final int B135 = 15;
	public static final int B150 = 16;
	public static final int B180 = 17;
	public static final int A0 = 18;
	public static final int C0 = 19;
	public static final int B0 = 20;

	/**
	 * Number of different emissions.
	 */
	public static final int EMISSIONCOUNT = 21;

	/**
	 * Array with all different emissions as Strings for the table header
	 */
	private String[] emissionList;

	/**
	 * The sequence of emissions
	 */
	private final LinkedList<Integer> sequence;

	/**
	 * Markovchain with absolute frequencies.
	 */
	private int[][] absMarkovChain;

	/**
	 * Markovchain with relative frequencies.
	 */
	private double[][] relMarkovChain;

	/**
	 * Creates a new emissionsequence
	 */
	public Emissionsequence() {
		this.sequence = new LinkedList<>();
		this.initializeNewMarkovChains();
		this.createEmissionList();
	}

	/**
	 * Creates a new emissionsequence object from a list of emissions.
	 */
	public Emissionsequence(LinkedList<Integer> sequence) {
		this.sequence = sequence;
		this.initializeNewMarkovChains();
		this.createEmissionList();
	}

	/**
	 * Creates a new emissionsequence object from a movementsequence.
	 */
	public Emissionsequence(Movementsequence movSeq) {
		this.sequence = new LinkedList<>();
		this.initializeNewMarkovChains();
		this.createEmissionList();
		this.createEmissionsequenceFrom(movSeq);

	}

	/**
	 * Creates the sequence of emissions from a movementsequence.
	 */
	private void createEmissionsequenceFrom(Movementsequence movSeq) {
		Iterator<?> iterator = movSeq.getSequence().iterator();
		Move m1 = (Move) iterator.next();
		Move m2;
		while (iterator.hasNext()) {
			m2 = (Move) iterator.next();
			this.sequence.add(this.getEmissionId(m1, m2));
			m1 = m2;
		}
	}

	/**
	 * Creates the list of Strings of the emissions
	 */
	private void createEmissionList() {
		this.emissionList = new String[EMISSIONCOUNT + 1];
		this.emissionList[0] = "";
		for (int i = 0; i < EMISSIONCOUNT; i++) {
			this.emissionList[i + 1] = String.valueOf(i);
		}
	}

	/**
	 * Initializes the markovchains
	 */
	private void initializeNewMarkovChains() {
		this.absMarkovChain = new int[EMISSIONCOUNT][EMISSIONCOUNT];
		this.relMarkovChain = new double[EMISSIONCOUNT][EMISSIONCOUNT];
		for (int i = 0; i < this.absMarkovChain.length; i++) {
			for (int j = 0; j < this.absMarkovChain.length; j++) {
				this.absMarkovChain[i][j] = 0;
			}
		}
	}

	/**
	 * Determines a emission from two given moves
	 * 
	 * @param m1
	 *            the first move
	 * @param m2
	 *            the following move
	 * @return the emissionid of the determined emission
	 */
	public int getEmissionId(Move m1, Move m2) {
		// compute the difference between the lengths of the movementvectors
		double diff = m1.lengthDifference(m2);

		// calculate the angle between two moves with scalar product
		// m1 * m2 / |m1| * |m2|
		double angle = (m1.getX() * m2.getX() + m1.getY() * m2.getY())
				/ (Math.sqrt(m1.getX() * m1.getX() + m1.getY() * m1.getY()) * Math
						.sqrt(m2.getX() * m2.getX() + m2.getY() * m2.getY()));

		// round to 3 decimal places and apply arccos
		angle = Math.toDegrees(Math.acos(Math.round(angle * 1000) / 1000.0));

		if (diff == 0) {
			if (angle == 0) {
				return C0;
			} else if (angle < 30) {
				return C30;
			} else if (angle < 45) {
				return C45;
			} else if (angle < 90) {
				return C90;
			} else if (angle < 135) {
				return C135;
			} else if (angle < 150) {
				return C150;
			} else {
				return C180;
			}
		} else if (diff < 0) {
			if (angle == 0) {
				return B0;
			} else if (angle < 30) {
				return B30;
			} else if (angle < 45) {
				return B45;
			} else if (angle < 90) {
				return B90;
			} else if (angle < 135) {
				return B135;
			} else if (angle < 150) {
				return B150;
			} else {
				return B180;
			}
		} else {
			if (angle == 0) {
				return A0;
			} else if (angle < 30) {
				return A30;
			} else if (angle < 45) {
				return A45;
			} else if (angle < 90) {
				return A90;
			} else if (angle < 135) {
				return A135;
			} else if (angle < 150) {
				return A150;
			} else {
				return A180;
			}
		}
	}

	/**
	 * Converts the sequence of emissions from a list into an array of doubles
	 * 
	 * @return array with the emissions as doubles
	 */
	public double[] getEmissionsAsArray() {
		double[] emissionArray = new double[this.sequence.size()];
		int i = 0;
		for (Integer emission : this.sequence) {
			emissionArray[i++] = emission;
		}
		return emissionArray;
	}

	/**
	 * Interferes the emissionsequence with a given confounder
	 * 
	 * @param confounder
	 *            the confounder which should be used for the interference
	 * @return the interfered emissionsequence
	 */
	public Emissionsequence interfereWith(NumberGenerator<?> confounder) {
		LinkedList<Integer> interferedSequence = new LinkedList<>();
		int tmpEmission;
		for (Integer i : this.sequence) {
			tmpEmission = i + confounder.nextValue().intValue();
			if (tmpEmission >= EMISSIONCOUNT) {
				// -1 because 21 emissions, but highest id is 20
				tmpEmission = EMISSIONCOUNT - 1;
			} else if (tmpEmission < 0) {
				tmpEmission = 0;
			}
			interferedSequence.add(tmpEmission);
		}
		return new Emissionsequence(interferedSequence);
	}

	/**
	 * Generates the markovchains for this emissionsequence. It generates the
	 * markovchain with the absolute frequencies first and then call the
	 * function for the relative frequencies.
	 */
	private void generateMarkovChains() {

		Iterator<Integer> iterator = this.sequence.iterator();

		int e1 = iterator.next();
		int e2;
		while (iterator.hasNext()) {
			e2 = iterator.next();
			this.absMarkovChain[e1][e2] += 1;
			e1 = e2;
		}
		this.generateRelMarkovChain();
	}

	/**
	 * Compute the relative frequencies for the markovchain.
	 */
	private void generateRelMarkovChain() {
		for (int i = 0; i < this.absMarkovChain.length; i++) { // rows
			int sum = 0;
			for (int j = 0; j < this.absMarkovChain.length; j++) { // columns
				// sum all values of a row
				sum += this.absMarkovChain[i][j];
			}
			for (int j = 0; j < this.absMarkovChain.length; j++) {
				// divide all values of a row by the previous calculated sum
				if (this.absMarkovChain[i][j] != 0) {

					this.relMarkovChain[i][j] = Math
							.rint((this.absMarkovChain[i][j] / (double) sum) * 100) / 100;
				}
			}
		}
	}

	/**
	 * Creates a DefaultTableModel from the absolute markovchain. This function
	 * is only called when the GUI creates a new matrix and it's needed that the
	 * markovchains are generated for the first time.
	 * 
	 * @return the DefaulttableModel with the absolute frequencies
	 */
	public DefaultTableModel createMatrix() {
		// +1 because the first column contains the caption
		DefaultTableModel tableModel = new DefaultTableModel(EMISSIONCOUNT,
				EMISSIONCOUNT + 1);

		this.generateMarkovChains();

		for (int i = 0; i < this.absMarkovChain.length; i++) { // rows
			tableModel.setValueAt(i, i, 0);
			for (int j = 0; j < this.absMarkovChain.length; j++) { // columns
				tableModel.setValueAt(this.absMarkovChain[i][j], i, j + 1);
			}
		}

		tableModel.setColumnIdentifiers(this.emissionList);

		return tableModel;
	}

	/**
	 * Creates a DefaultTableModel from the absolute markovchain.
	 * 
	 * @return the DefaulttableModel with the absolute frequencies
	 */
	public DefaultTableModel getAbsMatrix() {
		// +1 because the first column contains the caption
		DefaultTableModel tableModel = new DefaultTableModel(EMISSIONCOUNT,
				EMISSIONCOUNT + 1);

		for (int i = 0; i < this.absMarkovChain.length; i++) { // rows
			tableModel.setValueAt(i, i, 0);
			for (int j = 0; j < this.absMarkovChain.length; j++) { // columns
				tableModel.setValueAt(this.absMarkovChain[i][j], i, j + 1);
			}
		}

		tableModel.setColumnIdentifiers(this.emissionList);

		return tableModel;
	}

	/**
	 * Creates a DefaultTableModel from the relative markovchain.
	 * 
	 * @return the DefaulttableModel with the relative frequencies
	 */
	public DefaultTableModel getRelMatrix() {
		// +1 because the first column contains the caption
		DefaultTableModel tableModel = new DefaultTableModel(EMISSIONCOUNT,
				EMISSIONCOUNT + 1);

		for (int i = 0; i < this.relMarkovChain.length; i++) { // rows
			tableModel.setValueAt(i, i, 0);
			for (int j = 0; j < this.relMarkovChain.length; j++) { // columns
				tableModel.setValueAt(this.relMarkovChain[i][j], i, j + 1);
			}
		}

		tableModel.setColumnIdentifiers(this.emissionList);

		return tableModel;
	}

	public LinkedList<Integer> getSequence() {
		return this.sequence;
	}

	@Override
	public String toString() {
		String s = "";
		Iterator<Integer> iterator = this.sequence.iterator();

		s += "\"" + iterator.next() + "\"";

		while (iterator.hasNext()) {
			s += ",\"" + String.valueOf(iterator.next()) + "\"";
		}
		return s + "\n";
	}

}
