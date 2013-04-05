package ui;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;

public class Histogram extends JFrame {

	/**
	 * Create the frame.
	 */
	public Histogram(ChartPanel chart) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setContentPane(chart);
		this.setVisible(true);
	}

}
