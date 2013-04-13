package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import model.ConfounderFactory;
import model.ModelFactory;
import presenter.IMainPresenter;
import presenter.MainPresenter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainView implements IMainView {

	/**
	 * The presenter to communicate with.
	 */
	private IMainPresenter mainPresenter;

	private JFrame frmEmissiongenerator;
	private JSpinner spinnerSize;
	private JComboBox comboMovementmodel;
	private JComboBox comboConfounder;
	private JTextArea textReadout;
	private JCheckBox chckbxShow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					MainView mainView = new MainView();
					mainView.setPresenter(new MainPresenter(mainView));
					mainView.frmEmissiongenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmEmissiongenerator = new JFrame();
		this.frmEmissiongenerator.setTitle("EmissionGenerator");
		this.frmEmissiongenerator.setBounds(100, 100, 600, 310);
		this.frmEmissiongenerator
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmEmissiongenerator.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(48dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(66dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(40dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(65dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(49dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC, }));

		JLabel lblModel = new JLabel("Model:");
		this.frmEmissiongenerator.getContentPane().add(lblModel,
				"2, 2, right, default");

		this.comboMovementmodel = new JComboBox(ModelFactory.MODELLIST);
		this.frmEmissiongenerator.getContentPane().add(this.comboMovementmodel,
				"4, 2, fill, default");

		JLabel lblConfounder = new JLabel("Confounder:");
		this.frmEmissiongenerator.getContentPane().add(lblConfounder,
				"6, 2, right, default");

		this.comboConfounder = new JComboBox(ConfounderFactory.CONFOUNDERLIST);
		this.frmEmissiongenerator.getContentPane().add(this.comboConfounder,
				"8, 2, 5, 1, fill, default");

		JLabel lblSizeOfSequence = new JLabel("size of sequence:");
		this.frmEmissiongenerator.getContentPane().add(lblSizeOfSequence,
				"2, 4");

		this.spinnerSize = new JSpinner();
		this.spinnerSize.setModel(new SpinnerNumberModel(new Integer(10),
				new Integer(1), null, new Integer(1)));
		this.frmEmissiongenerator.getContentPane()
				.add(this.spinnerSize, "4, 4");

		JButton btnGenerate = new JButton("Generate!");
		btnGenerate
				.setToolTipText("Generate a emissionsequence without interferences");
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.this.generateSequences();
			}
		});
		this.frmEmissiongenerator.getContentPane().add(btnGenerate, "8, 4");

		JLabel lblEmissionsequence = new JLabel("Emissionsequence:");
		this.frmEmissiongenerator.getContentPane().add(lblEmissionsequence,
				"2, 6");

		this.chckbxShow = new JCheckBox(
				"Show Emissionsequence in following box");
		this.chckbxShow
				.setToolTipText("For better performance the emissionsequence will not shown automatically");
		this.frmEmissiongenerator.getContentPane().add(this.chckbxShow,
				"8, 6, 5, 1");

		JScrollPane scrollPane = new JScrollPane();
		this.frmEmissiongenerator.getContentPane().add(scrollPane,
				"2, 8, 11, 1, fill, fill");

		this.textReadout = new JTextArea();
		scrollPane.setViewportView(this.textReadout);
		this.textReadout.setEditable(false);
		this.textReadout.setLineWrap(true);
		this.textReadout.setWrapStyleWord(true);

		JButton btnHistogram = new JButton("Histogram");
		btnHistogram
				.setToolTipText("Shows a histogram of the generated emissionsequence");
		btnHistogram.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.this.createHistogram();
			}
		});

		JButton btnMatrix = new JButton("Stochastic Matrix");
		btnMatrix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.createStochasticMatrix();
			}
		});

		JButton btnInterfere = new JButton("Interfere");
		btnInterfere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainView.this.interfereSequence();
			}
		});
		btnInterfere
				.setToolTipText("Interfere the emissionsequence with the selected confounder");
		this.frmEmissiongenerator.getContentPane().add(btnInterfere, "6, 10");
		this.frmEmissiongenerator.getContentPane().add(btnMatrix, "8, 10");
		this.frmEmissiongenerator.getContentPane().add(btnHistogram, "10, 10");

		JLabel lblMovementsequence = new JLabel("Movementsequence:");
		this.frmEmissiongenerator.getContentPane().add(lblMovementsequence,
				"2, 12");

		JButton btnLoadMoveSeq = new JButton("load...");
		btnLoadMoveSeq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.loadMovementsequenceFromFile(e);
			}
		});
		;
		btnLoadMoveSeq.setToolTipText("load a movementsequence from a file");
		this.frmEmissiongenerator.getContentPane().add(btnLoadMoveSeq, "4, 12");

		JButton btnSaveMoveSeq = new JButton("save...");
		btnSaveMoveSeq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.saveMovementsequenceToFile(e);
			}
		});
		btnSaveMoveSeq.setToolTipText("Save the movementsequence to a file");
		this.frmEmissiongenerator.getContentPane().add(btnSaveMoveSeq, "6, 12");

		JLabel lblEmissionsequence_1 = new JLabel("Emissionsequence:");
		this.frmEmissiongenerator.getContentPane().add(lblEmissionsequence_1,
				"8, 12");

		JButton btnLoadEmisSeq = new JButton("load...");
		btnLoadEmisSeq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.loadEmissionsequenceFromFile(e);
			}
		});
		btnLoadEmisSeq.setToolTipText("load a emissionsequence from a file");
		this.frmEmissiongenerator.getContentPane()
				.add(btnLoadEmisSeq, "10, 12");

		JButton btnSaveEmisSeq = new JButton("save...");
		btnSaveEmisSeq.setToolTipText("Save the emissionsequence to a file");
		this.frmEmissiongenerator.getContentPane()
				.add(btnSaveEmisSeq, "12, 12");
		btnSaveEmisSeq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainView.this.saveEmissionsequenceToFile(e);
			}
		});
	}

	private void generateSequences() {
		this.mainPresenter.generateSequences();
	}

	private void saveEmissionsequenceToFile(ActionEvent e) {
		this.mainPresenter.saveEmissionsequenceToFile(e);
	}

	private void loadEmissionsequenceFromFile(ActionEvent e) {
		this.mainPresenter.loadEmissionsequenceFromFile(e);
	}

	private void loadMovementsequenceFromFile(ActionEvent e) {
		this.mainPresenter.loadMovementsequenceFromFile(e);
	}

	private void saveMovementsequenceToFile(ActionEvent e) {
		this.mainPresenter.saveMovementsequenceToFile(e);
	}

	private void createHistogram() {
		this.mainPresenter.createHistogram();
	}

	private void interfereSequence() {
		this.mainPresenter.interfereSequence();
	}

	private void createStochasticMatrix() {
		this.mainPresenter.createStochasticMatrix();
	}

	@Override
	public void setPresenter(IMainPresenter mainPresenter) {
		this.mainPresenter = mainPresenter;
	}

	@Override
	public IMainPresenter getMainPresenter() {
		return this.mainPresenter;
	}

	@Override
	public void displayStatus(String s) {
		this.textReadout.setText(s);
	}

	@Override
	public int getLengthOfEmissionsequence() {
		return Integer.valueOf(this.spinnerSize.getValue().toString());
	}

	@Override
	public int getMovementmodelID() {
		return this.comboMovementmodel.getSelectedIndex();
	}

	@Override
	public int getConfounderlID() {
		return this.comboConfounder.getSelectedIndex();
	}

	@Override
	public boolean getBoxStatus() {
		return this.chckbxShow.isSelected();
	}

}
