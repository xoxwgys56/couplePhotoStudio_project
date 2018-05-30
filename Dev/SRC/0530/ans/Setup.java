package framework;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Handling;

public class Setup extends JFrame implements ActionListener {

	private JPanel expanel, setpanel;
	private JTextField textField, textField2, textField3;
	private JLabel label, label1, label2, label3, label4;
	private JButton button1, button2, button3, button4;
	// private FrameWork frame;
	private String changePath;
	private String previous;

	private database.DB_insert db;

	public Setup() {
		setSize(400, 180);
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(layout);

		expanel = new JPanel();

		label = new JLabel("ȯ�漳��");
		expanel.add(label);

		setpanel = new JPanel();
		setpanel.setLayout(new GridLayout(4, 3));

		label1 = new JLabel("�������� �⺻��κ���");
		textField = new JTextField();
		button1 = new JButton("Ȯ��");
		button1.addActionListener(this);
		setpanel.add(label1);
		setpanel.add(textField);
		setpanel.add(button1);

		label2 = new JLabel("���伥 ��μ���"); // ���� 2018.05.25 kyt
		textField2 = new JTextField();
		button2 = new JButton("Ȯ��");
		button2.addActionListener(this);
		setpanel.add(label2);
		setpanel.add(textField2);
		setpanel.add(button2);

		label3 = new JLabel("����Ʈ�� ��μ���");
		textField3 = new JTextField();
		button3 = new JButton("Ȯ��");
		button3.addActionListener(this);
		setpanel.add(label3);
		setpanel.add(textField3);
		setpanel.add(button3);

		// label4 = new JLabel("����Ʈ�� ��μ���");
		button4 = new JButton("����̹���");
		button4.addActionListener(this);
		setpanel.add(button4);

		add(expanel);
		add(setpanel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {

			changePath = textField.getText(); // previous���� �ȵ��� ������ �ذ��ϸ� ���� ��.
			try {
				previous = db.getpath();
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			System.out.println("previous : " + previous);
			System.out.println("changePath :" + changePath);
			int choice = JOptionPane.showConfirmDialog(null, "�⺻ ��θ� �ٲ� ����� �ؾ��մϴ�, �շ��Ͻðڽ��ϱ�?", "�˸�â",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			System.out.println("choice : " + choice);
			if (choice == 0) {
				db.updaterecord("pathtable", "pathname", previous, changePath);
				System.exit(0);
			} else if (choice == 1) {
				setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
			}
		}

		if (e.getSource() == button2) { // ���伥 ��� �����ϱ� //18.05.25 kyt

			try {
				previous = db.p_getpath();

				changePath = textField2.getText();
				int choice = JOptionPane.showConfirmDialog(null, "���伥 ��θ� �ٲ� ����� �ؾ��մϴ�, �շ��Ͻðڽ��ϱ�?", "�˸�â",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				System.out.println("choice : " + choice);
				if (choice == 0) {
					db.updaterecord("pathtable", "photopath", previous, changePath);
					System.exit(0);
				} else if (choice == 1) {
					setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
				}

			} catch (SQLException e1) {

				System.out.println(e1);
				;
			}
			System.out.println("previous : " + previous);
			System.out.println("changePath :" + changePath);
		}

		if (e.getSource() == button3) { // ����Ʈ�� ��� �����ϱ� //18.05.25 kyt

			try {
				previous = db.L_getpath();

				changePath = textField3.getText();
				int choice = JOptionPane.showConfirmDialog(null, "����Ʈ�� ��θ� �ٲ� ����� �ؾ��մϴ�, �շ��Ͻðڽ��ϱ�?", "�˸�â",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				System.out.println("choice : " + choice);
				if (choice == 0) {
					db.updaterecord("pathtable", "lightpath", previous, changePath);
					System.exit(0);
				} else if (choice == 1) {
					setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
				}

			} catch (SQLException e1) {
				System.out.println(e1);
				;
			}
			System.out.println("previous : " + previous);
			System.out.println("changePath :" + changePath);
		}
		if (e.getSource() == button4) { 
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				try {
					if (ImageIO.read(selectedFile) == null) {
						JOptionPane.showMessageDialog(this, "�̹��� ������ �ƴմϴ�.");
					} else {
						int choice = JOptionPane.showConfirmDialog(null, "��� �̹����� �����Ͻðڽ��ϱ�?", "�˸�â",
								JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						System.out.println("choice : " + choice);
						if (choice == 0) {
							Handling.getDisplay().setBackImage(selectedFile);
						}
						setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
						this.dispose();
					}
				} catch (IOException ioe) {
				}
			}
		}

	}

}