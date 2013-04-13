package view;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;

public class HistogramView extends JFrame {

	/**
	 * Create the frame.
	 */
	public HistogramView(ChartPanel chart) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setContentPane(chart);
		this.setVisible(true);
	}

}
