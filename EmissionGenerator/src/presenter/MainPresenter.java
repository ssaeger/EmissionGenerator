package presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import model.ConfounderFactory;
import model.emission.EmissionsequenceModel;
import model.emission.IEmissionsequenceModel;
import model.movement.Movementmodel;
import model.movement.MovementmodelFactory;
import model.movement.Movementsequence;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.uncommons.maths.number.NumberGenerator;

import view.HistogramView;
import view.IMainView;
import view.IMatrixView;
import view.MatrixView;

public class MainPresenter implements IMainPresenter {

	private IMainView mainView;
	private IEmissionsequenceModel emissionsequenceModel;

	private final MovementmodelFactory modelFactory;
	private final ConfounderFactory confounderFactory;
	private Movementmodel model;
	private NumberGenerator<?> confounder;
	private Movementsequence movSeq;

	public MainPresenter(IEmissionsequenceModel model) {
		this.emissionsequenceModel = model;
		this.modelFactory = MovementmodelFactory.getInstance();
		this.confounderFactory = ConfounderFactory.getInstance();
	}

	@Override
	public void generateSequences() {
		this.model = this.modelFactory.createModel(this.mainView
				.getMovementmodelID());
		this.movSeq = this.model.generateMovementsequence(Integer
				.valueOf(this.mainView.getLengthOfEmissionsequence()));
		this.emissionsequenceModel = new EmissionsequenceModel(this.movSeq);
		this.displayStatus("Emissionsequence was generated successfully!");

	}

	@Override
	public void interfereSequence() {
		this.confounder = this.confounderFactory.createConfounder(this.mainView
				.getConfounderlID());
		this.emissionsequenceModel = this.emissionsequenceModel
				.interfereWith(this.confounder);
		this.displayStatus("Emissionsequence interfered!");
	}

	@Override
	public void displayStatus(String s) {
		if (this.mainView.getBoxStatus()) {
			this.mainView.displayStatus(this.emissionsequenceModel.toString());
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
				long before = System.nanoTime();
				fw.write(this.emissionsequenceModel.toString());
				long after = System.nanoTime();
				this.displayStatus((after - before) / 1e9 + " ms");
				fw.flush();
				fw.close();
				// this.displayStatus("File was written successfully!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// @Override
	// public void saveEmissionsequenceToFile(ActionEvent e) {
	// JFileChooser fc = new JFileChooser();
	//
	// int confounder = 1;
	// int count = 10;
	//
	// GaussianGenerator gNGR = new GaussianGenerator(0, confounder,
	// new Random());
	// Movementsequence mov;
	// IEmissionsequenceModel emi = this.emissionsequenceModel;
	// if (fc.showSaveDialog((Component) e.getSource()) ==
	// JFileChooser.APPROVE_OPTION) {
	// File file = fc.getSelectedFile();
	// try {
	// FileWriter fw = new FileWriter(file);
	// for (int i = 0; i < count; i++) {
	// fw = new FileWriter(file.getParentFile().getAbsolutePath()
	// + "\\"
	// + file.getName().subSequence(0,
	// file.getName().indexOf("_") + 1)
	// + (Integer.parseInt(file.getName().substring(
	// file.getName().indexOf("_") + 1)) + i));
	// fw.write(emi.toString());
	// fw.flush();
	// fw.close();
	// fw = new FileWriter(file.getParentFile().getAbsolutePath()
	// + "\\"
	// + file.getName().subSequence(0,
	// file.getName().indexOf("_"))
	// + confounder
	// + "n_"
	// + (Integer.parseInt(file.getName().substring(
	// file.getName().indexOf("_") + 1)) + i));
	//
	// fw.write(emi.interfereWith(gNGR).toString());
	// fw.flush();
	// fw.close();
	// mov = new Movementsequence();
	// mov = this.model.generateMovementsequence(this.movSeq
	// .size());
	// emi = new EmissionsequenceModel(mov);
	// }
	// this.displayStatus("Files were written successfully!");
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }
	// }

	// @Override
	// public void saveEmissionsequenceToFile(ActionEvent e) {
	// JFileChooser fc = new JFileChooser();
	//
	// if (fc.showSaveDialog((Component) e.getSource()) ==
	// JFileChooser.APPROVE_OPTION) {
	// File file = fc.getSelectedFile();
	// try {
	// double[] stds = { 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 };
	//
	// // file
	// FileWriter fw = new FileWriter(file);
	// fw.write(this.emissionsequenceModel.toString());
	// fw.flush();
	// fw.close();
	//
	// for (double d : stds) {
	// fw = new FileWriter(file.getParentFile().getAbsolutePath()
	// + "\\"
	// + file.getName().subSequence(0,
	// file.getName().indexOf("k") + 1)
	// + (int) d
	// + "n"
	// + file.getName().subSequence(
	// file.getName().indexOf("k") + 1,
	// file.getName().length()));
	// fw.write(this.emissionsequenceModel.interfereWith(
	// new GaussianGenerator(0, d, new Random()))
	// .toString());
	// fw.flush();
	// fw.close();
	// }
	// this.displayStatus("Files were written successfully!");
	//
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }
	// }

	@Override
	public void loadEmissionsequenceFromFile(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		if (fc.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				this.emissionsequenceModel = new EmissionsequenceModel(
						new Scanner(file).useDelimiter("\\A").next());
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
				this.emissionsequenceModel = new EmissionsequenceModel(
						this.movSeq);
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
		double[] values = this.emissionsequenceModel.getEmissionsAsArray();
		histData.addSeries("H1", values, EmissionsequenceModel.EMISSIONCOUNT);

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
		IMatrixPresenter matrixPresenter = new MatrixPresenter();
		matrixPresenter.setModel(this.emissionsequenceModel);
		IMatrixView matrixView = new MatrixView();
		matrixPresenter.setView(matrixView);
		matrixView.setPresenter(matrixPresenter);
		matrixPresenter.createMatrix();
	}

	@Override
	public void setView(IMainView view) {
		this.mainView = view;
	}

}
