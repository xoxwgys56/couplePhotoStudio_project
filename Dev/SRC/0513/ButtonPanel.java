package test;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener{
	private JButton customerAddBtn;
	private JButton customerDeleteBtn;
	private JButton calenderBtn;
	private JButton configBtn;
	private Vector<CustomerInfo> c_vec;
	private ListPanel lp;
	public ButtonPanel(Vector<CustomerInfo> vec,ListPanel l) {
		c_vec=vec;
		lp=l;
		this.setSize(84, 472);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{46, 46, 46, 46, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		customerAddBtn = new JButton("°í°´Ãß°¡");
		customerAddBtn.addActionListener(this);
		GridBagConstraints gbc_customerAddBtn = new GridBagConstraints();
		gbc_customerAddBtn.insets = new Insets(0, 0, 5, 0);
		gbc_customerAddBtn.fill = GridBagConstraints.BOTH;
		gbc_customerAddBtn.gridx = 0;
		gbc_customerAddBtn.gridy = 0;
		add(customerAddBtn, gbc_customerAddBtn);
		
		customerDeleteBtn = new JButton("°í°´»èÁ¦");
		customerDeleteBtn.addActionListener(this);
		GridBagConstraints gbc_customerDeleteBtn = new GridBagConstraints();
		gbc_customerDeleteBtn.insets = new Insets(0, 0, 5, 0);
		gbc_customerDeleteBtn.fill = GridBagConstraints.BOTH;
		gbc_customerDeleteBtn.gridx = 0;
		gbc_customerDeleteBtn.gridy = 1;
		add(customerDeleteBtn, gbc_customerDeleteBtn);
		
		calenderBtn = new JButton("Ä¶¸°´õ");
		calenderBtn.addActionListener(this);
		GridBagConstraints gbc_calenderBtn = new GridBagConstraints();
		gbc_calenderBtn.insets = new Insets(0, 0, 5, 0);
		gbc_calenderBtn.fill = GridBagConstraints.BOTH;
		gbc_calenderBtn.gridx = 0;
		gbc_calenderBtn.gridy = 2;
		add(calenderBtn, gbc_calenderBtn);
		
		configBtn = new JButton("¼³Á¤");
		configBtn.addActionListener(this);
		GridBagConstraints gbc_configBtn = new GridBagConstraints();
		gbc_configBtn.insets = new Insets(0, 0, 5, 0);
		gbc_configBtn.fill = GridBagConstraints.BOTH;
		gbc_configBtn.gridx = 0;
		gbc_configBtn.gridy = 3;
		add(configBtn, gbc_configBtn);
	}
	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == customerAddBtn) {
			AddFrame adfr=new AddFrame(c_vec,lp);
		}
    }

}
