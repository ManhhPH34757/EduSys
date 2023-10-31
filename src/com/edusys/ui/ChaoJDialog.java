package com.edusys.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class ChaoJDialog extends JDialog {

	private JProgressBar pgbLoading;

	/**
	 * Create the dialog.
	 */
	public ChaoJDialog() {
		
	}

	public ChaoJDialog(EduSysJFrame owner, boolean modal) {
		super(owner, modal);
		setUndecorated(true);
		setBounds(100, 100, 700, 390);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setOpaque(true);
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(ChaoJDialog.class.getResource("/com/edusys/icon/logo.jpg")));
		lblLogo.setBounds(0, 0, 700, 355);
		getContentPane().add(lblLogo);
		
		pgbLoading = new JProgressBar();
		pgbLoading.setForeground(new Color(104, 176, 89));
		pgbLoading.setOpaque(true);
		pgbLoading.setStringPainted(true);
		pgbLoading.setBounds(0, 356, 700, 34);
		getContentPane().add(pgbLoading);
		initProgressBar();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void initProgressBar() {
		new Timer(200, new ActionListener() {
			private int value;
			@Override
			public void actionPerformed(ActionEvent e) {
				value  = pgbLoading.getValue();
				if (value < pgbLoading.getMaximum()) {
					pgbLoading.setValue(value+15);
				}else {
					ChaoJDialog.this.dispose();
				}
			}
		}).start();
	}
}
