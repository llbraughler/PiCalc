import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PiCalc {

	private JFrame frmPiCalc;
	private static JLabel lblPi;
	private JButton btnClear;
	private Plot plot;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PiCalc window = new PiCalc();
					window.frmPiCalc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PiCalc() {
		initialize();
	}
	
	private void initialize() {
		frmPiCalc = new JFrame();
		frmPiCalc.setTitle("PiCalc: Calculate Pi using the Monte Carlo method");
		frmPiCalc.setBounds(100, 100, 597, 513);
		frmPiCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		plot = new Plot();
		plot.setPreferredSize(new Dimension(500, 500));
		plot.setSize(new Dimension(500, 500));
		plot.setBorder(null);
		
		// Attach the MonteCarlo simulator to this plot
		final MonteCarlo simulator = new MonteCarlo(plot);
		
		final JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( btnStart.getText().equals("Start") ) {
					btnStart.setText("Stop");
					simulator.start();
				} else {
					btnStart.setText("Start");
					simulator.stop();
				}
			}
		});
		
		lblPi = new JLabel("PI = 0.0");
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearPlot();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmPiCalc.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(plot, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnStart)
								.addComponent(btnClear))
							.addGap(49))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPi)
							.addContainerGap(562, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(btnStart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClear))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(plot, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(lblPi)
					.addContainerGap())
		);
		frmPiCalc.getContentPane().setLayout(groupLayout);
		
		
	}
	
	public static void updatePi(double pi) {
		lblPi.setText("PI = " + pi);
	}
	
	private void clearPlot() {
		plot.setupPlot();
	}
}
