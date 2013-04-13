package test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import model.Emissionsequence;

import org.junit.Test;


/**
 * @author Sebastian
 * 
 */
public class TestEmissionsequence {

	/**
	 * Test method for {@link model.Emissionsequence#Emissionsequence(String)}.
	 */
	@Test
	public void testEmissionsequence() {
		// set up SUT
		String emissionString = "\"1\",\"2\",\"3\"\n";
		// execute SUT
		Emissionsequence emisSeq = new Emissionsequence(emissionString);
		// verify outcome
		assertEquals(emissionString, emisSeq.toString());
		// tear down

	}

	/**
	 * Test method for {@link model.Emissionsequence#getEmissionsAsArray()}.
	 */
	@Test
	public void testGetEmissionsAsArray() {
		// set up SUT
		double[] emissionArray;
		LinkedList<Integer> sequence = new LinkedList<>();
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);

		// execute SUT
		Emissionsequence emisSeq = new Emissionsequence(sequence);
		emissionArray = emisSeq.getEmissionsAsArray();
		// verify outcome
		assertEquals(emissionArray.length, sequence.size());
		// tear down

	}

	/**
	 * Test method for {@link model.Emissionsequence#getAbsMatrix()}.
	 */
	@Test
	public void testGetAbsMatrix() {
		// set up SUT
		LinkedList<Integer> sequence = new LinkedList<>();
		sequence.add(0);
		sequence.add(0);
		sequence.add(1);

		// execute SUT
		Emissionsequence emisSeq = new Emissionsequence(sequence);
		emisSeq.createMatrix();
		int firstAbs = (int) emisSeq.getAbsMatrix().getValueAt(0, 1);
		// verify outcome
		assertEquals(firstAbs, 1);
		// tear down

	}

	/**
	 * Test method for {@link model.Emissionsequence#getRelMatrix()}.
	 */
	@Test
	public void testGetRelMatrix() {
		// set up SUT
		LinkedList<Integer> sequence = new LinkedList<>();
		sequence.add(0);
		sequence.add(0);
		sequence.add(1);

		// execute SUT
		Emissionsequence emisSeq = new Emissionsequence(sequence);
		emisSeq.createMatrix();

		// to avoid assertEquals with doubles
		int firstRel = (int) ((double) (emisSeq.getRelMatrix().getValueAt(0, 1)) * 10);

		// verify outcome
		assertEquals(firstRel, 5);
		// tear down

	}
}
