package presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import model.ConfounderFactory;
import model.Emissionsequence;
import model.ModelFactory;
import movement.Movementmodel;
import movement.Movementsequence;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.uncommons.maths.number.NumberGenerator;

import view.HistogramView;
import view.IMainView;
import view.MatrixView;

public class MainPresenter implements IMainPresenter {

	private final IMainView mainView;

	private final ModelFactory modelFactory;
	private final ConfounderFactory confounderFactory;
	private Movementmodel model;
	private NumberGenerator<?> confounder;
	private Emissionsequence emisSeq;
	private Movementsequence movSeq;

	public MainPresenter(IMainView mainView) {
		this.mainView = mainView;
		this.modelFactory = ModelFactory.getInstance();
		this.confounderFactory = ConfounderFactory.getInstance();
	}

	@Override
	public void generateSequences() {
		this.model = this.modelFactory.createModel(this.mainView
				.getMovementmodelID());
		this.movSeq = this.model.generateMovementsequence(Integer
				.valueOf(this.mainView.getLengthOfEmissionsequence()));
		this.emisSeq = new Emissionsequence(this.movSeq);
		this.displayStatus("Emissionsequence was generated successfully!");

	}

	@Override
	public void interfereSequence() {
		this.confounder = this.confounderFactory.createConfounder(this.mainView
				.getConfounderlID());
		this.emisSeq = this.emisSeq.interfereWith(this.confounder);
		this.displayStatus("Emissionsequence interfered!");
	}

	@Override
	public void displayStatus(String s) {
		if (this.mainView.getBoxStatus()) {
			this.mainView.displayStatus(this.emisSeq.toString());
		} else {
			this.mainView.displayStatus(s);
		}
	}

	@Override
	public void saveEmissionsequenceToFile(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		if (fc.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(this.emisSeq.toString());
				fw.flush();
				fw.close();
				this.displayStatus("File was written successfully!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void loadEmissionsequenceFromFile(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		if (fc.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				this.emisSeq = new Emissionsequence(new Scanner(file)
						.useDelimiter("\\A").next());
				this.model = null;
				this.displayStatus("File was read successfully!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void loadMovementsequenceFromFile(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		if (fc.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				this.movSeq = new Movementsequence(new Scanner(file)
						.useDelimiter("\\A").next());
				this.emisSeq = new Emissionsequence(this.movSeq);
				this.displayStatus("File was read successfully!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void saveMovementsequenceToFile(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		if (fc.showSaveDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(this.movSeq.toString());
				fw.flush();
				fw.close();
				this.displayStatus("File was written successfully!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void createHistogram() {
		HistogramDataset histData = new HistogramDataset();
		histData.setType(HistogramType.FREQUENCY);
		double[] values = this.emisSeq.getEmissionsAsArray();
		histData.addSeries("H1", values, Emissionsequence.EMISSIONCOUNT);

		JFreeChart chart;
		if (this.model != null) {
			chart = ChartFactory.createHistogram(this.model.getName(),
					"EmissionID", "Frequency", histData,
					PlotOrientation.VERTICAL, false, false, false);
		} else {
			chart = ChartFactory.createHistogram("unknown Model", "EmissionID",
					"Frequency", histData, PlotOrientation.VERTICAL, false,
					false, false);
		}
		new HistogramView(new ChartPanel(chart));
	}

	@Override
	public void createStochasticMatrix() {
		new MatrixView(this.emisSeq);
	}

}
