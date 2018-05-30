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

		label = new JLabel("환경설정");
		expanel.add(label);

		setpanel = new JPanel();
		setpanel.setLayout(new GridLayout(4, 3));

		label1 = new JLabel("파일저장 기본경로변경");
		textField = new JTextField();
		button1 = new JButton("확인");
		button1.addActionListener(this);
		setpanel.add(label1);
		setpanel.add(textField);
		setpanel.add(button1);

		label2 = new JLabel("포토샵 경로설정"); // 이하 2018.05.25 kyt
		textField2 = new JTextField();
		button2 = new JButton("확인");
		button2.addActionListener(this);
		setpanel.add(label2);
		setpanel.add(textField2);
		setpanel.add(button2);

		label3 = new JLabel("라이트룸 경로설정");
		textField3 = new JTextField();
		button3 = new JButton("확인");
		button3.addActionListener(this);
		setpanel.add(label3);
		setpanel.add(textField3);
		setpanel.add(button3);

		// label4 = new JLabel("라이트룸 경로설정");
		button4 = new JButton("배경이미지");
		button4.addActionListener(this);
		setpanel.add(button4);

		add(expanel);
		add(setpanel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {

			changePath = textField.getText(); // previous값이 안들어가는 오류만 해결하면 끝날 듯.
			try {
				previous = db.getpath();
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			System.out.println("previous : " + previous);
			System.out.println("changePath :" + changePath);
			int choice = JOptionPane.showConfirmDialog(null, "기본 경로를 바꿔 재시작 해야합니다, 죵료하시겠습니까?", "알림창",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			System.out.println("choice : " + choice);
			if (choice == 0) {
				db.updaterecord("pathtable", "pathname", previous, changePath);
				System.exit(0);
			} else if (choice == 1) {
				setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
			}
		}

		if (e.getSource() == button2) { // 포토샵 경로 저장하기 //18.05.25 kyt

			try {
				previous = db.p_getpath();

				changePath = textField2.getText();
				int choice = JOptionPane.showConfirmDialog(null, "포토샵 경로를 바꿔 재시작 해야합니다, 죵료하시겠습니까?", "알림창",
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

		if (e.getSource() == button3) { // 라이트룸 경로 저장하기 //18.05.25 kyt

			try {
				previous = db.L_getpath();

				changePath = textField3.getText();
				int choice = JOptionPane.showConfirmDialog(null, "라이트룸 경로를 바꿔 재시작 해야합니다, 죵료하시겠습니까?", "알림창",
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
						JOptionPane.showMessageDialog(this, "이미지 파일이 아닙니다.");
					} else {
						int choice = JOptionPane.showConfirmDialog(null, "배경 이미지를 변경하시겠습니까?", "알림창",
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