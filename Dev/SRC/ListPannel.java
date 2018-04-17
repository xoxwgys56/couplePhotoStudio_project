package BuBuPhotoshop;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ListPannel extends JPanel{
	private JTextField searchTextField;
	private JTextField customerNameField;
	private JTextField cutomerPhoneNumField;
	private JTextField photoTypeField;
	private JLabel photoType;
	private JTextField customerNumField;
	private JTextField numOfPeopleField;
	private JTextField creationDateField;
	private JTextField reservationDateField;
	private JTextField lastDateField;

	public ListPannel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		
		JComboBox howtoSearch = new JComboBox();
		howtoSearch.setModel(new DefaultComboBoxModel(new String[] {"이름", "예약날짜"}));
		searchPanel.add(howtoSearch);
		
		searchTextField = new JTextField();
		searchPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton serchButton = new JButton("검색");
		searchPanel.add(serchButton);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{104, 114, 115, 87, 0};
		gbl_panel.rowHeights = new int[]{0, 21, 21, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel customerNum = new JLabel("고객번호");
		GridBagConstraints gbc_customerNum = new GridBagConstraints();
		gbc_customerNum.anchor = GridBagConstraints.WEST;
		gbc_customerNum.insets = new Insets(0, 0, 5, 5);
		gbc_customerNum.gridx = 0;
		gbc_customerNum.gridy = 0;
		panel.add(customerNum, gbc_customerNum);
		
		customerNumField = new JTextField();
		GridBagConstraints gbc_customerNumField = new GridBagConstraints();
		gbc_customerNumField.insets = new Insets(0, 0, 5, 5);
		gbc_customerNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerNumField.gridx = 1;
		gbc_customerNumField.gridy = 0;
		panel.add(customerNumField, gbc_customerNumField);
		customerNumField.setColumns(10);
		
		JLabel numOfPeople = new JLabel("인원수");
		GridBagConstraints gbc_numOfPeople = new GridBagConstraints();
		gbc_numOfPeople.anchor = GridBagConstraints.WEST;
		gbc_numOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_numOfPeople.gridx = 2;
		gbc_numOfPeople.gridy = 0;
		panel.add(numOfPeople, gbc_numOfPeople);
		
		numOfPeopleField = new JTextField();
		GridBagConstraints gbc_numOfPeopleField = new GridBagConstraints();
		gbc_numOfPeopleField.insets = new Insets(0, 0, 5, 0);
		gbc_numOfPeopleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_numOfPeopleField.gridx = 3;
		gbc_numOfPeopleField.gridy = 0;
		panel.add(numOfPeopleField, gbc_numOfPeopleField);
		numOfPeopleField.setColumns(10);
		
		JLabel customerName = new JLabel("고객이름");
		GridBagConstraints gbc_customerName = new GridBagConstraints();
		gbc_customerName.anchor = GridBagConstraints.WEST;
		gbc_customerName.fill = GridBagConstraints.VERTICAL;
		gbc_customerName.insets = new Insets(0, 0, 5, 5);
		gbc_customerName.gridx = 0;
		gbc_customerName.gridy = 1;
		panel.add(customerName, gbc_customerName);
		
		customerNameField = new JTextField();
		GridBagConstraints gbc_customerNameField = new GridBagConstraints();
		gbc_customerNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerNameField.gridwidth = 3;
		gbc_customerNameField.insets = new Insets(0, 0, 5, 0);
		gbc_customerNameField.gridx = 1;
		gbc_customerNameField.gridy = 1;
		panel.add(customerNameField, gbc_customerNameField);
		customerNameField.setColumns(10);
		
		JLabel cutomerPhoneNum = new JLabel("전화번호");
		GridBagConstraints gbc_cutomerPhoneNum = new GridBagConstraints();
		gbc_cutomerPhoneNum.anchor = GridBagConstraints.WEST;
		gbc_cutomerPhoneNum.fill = GridBagConstraints.VERTICAL;
		gbc_cutomerPhoneNum.insets = new Insets(0, 0, 5, 5);
		gbc_cutomerPhoneNum.gridx = 0;
		gbc_cutomerPhoneNum.gridy = 2;
		panel.add(cutomerPhoneNum, gbc_cutomerPhoneNum);
		
		cutomerPhoneNumField = new JTextField();
		GridBagConstraints gbc_cutomerPhoneNumField = new GridBagConstraints();
		gbc_cutomerPhoneNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cutomerPhoneNumField.gridwidth = 3;
		gbc_cutomerPhoneNumField.insets = new Insets(0, 0, 5, 0);
		gbc_cutomerPhoneNumField.gridx = 1;
		gbc_cutomerPhoneNumField.gridy = 2;
		panel.add(cutomerPhoneNumField, gbc_cutomerPhoneNumField);
		cutomerPhoneNumField.setColumns(10);
		
		photoType = new JLabel("촬영종류");
		GridBagConstraints gbc_photoType = new GridBagConstraints();
		gbc_photoType.anchor = GridBagConstraints.WEST;
		gbc_photoType.fill = GridBagConstraints.VERTICAL;
		gbc_photoType.insets = new Insets(0, 0, 5, 5);
		gbc_photoType.gridx = 0;
		gbc_photoType.gridy = 3;
		panel.add(photoType, gbc_photoType);
		
		photoTypeField = new JTextField();
		GridBagConstraints gbc_photoTypeField = new GridBagConstraints();
		gbc_photoTypeField.insets = new Insets(0, 0, 5, 0);
		gbc_photoTypeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_photoTypeField.gridwidth = 3;
		gbc_photoTypeField.gridx = 1;
		gbc_photoTypeField.gridy = 3;
		panel.add(photoTypeField, gbc_photoTypeField);
		photoTypeField.setColumns(10);
		
		JLabel creationDate = new JLabel("생성날짜");
		GridBagConstraints gbc_creationDate = new GridBagConstraints();
		gbc_creationDate.anchor = GridBagConstraints.WEST;
		gbc_creationDate.insets = new Insets(0, 0, 5, 5);
		gbc_creationDate.gridx = 0;
		gbc_creationDate.gridy = 4;
		panel.add(creationDate, gbc_creationDate);
		
		creationDateField = new JTextField();
		GridBagConstraints gbc_creationDateField = new GridBagConstraints();
		gbc_creationDateField.gridwidth = 3;
		gbc_creationDateField.insets = new Insets(0, 0, 5, 0);
		gbc_creationDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_creationDateField.gridx = 1;
		gbc_creationDateField.gridy = 4;
		panel.add(creationDateField, gbc_creationDateField);
		creationDateField.setColumns(10);
		
		JLabel reservationDate = new JLabel("예약날짜");
		GridBagConstraints gbc_reservationDate = new GridBagConstraints();
		gbc_reservationDate.anchor = GridBagConstraints.WEST;
		gbc_reservationDate.insets = new Insets(0, 0, 5, 5);
		gbc_reservationDate.gridx = 0;
		gbc_reservationDate.gridy = 5;
		panel.add(reservationDate, gbc_reservationDate);
		
		reservationDateField = new JTextField();
		GridBagConstraints gbc_reservationDateField = new GridBagConstraints();
		gbc_reservationDateField.gridwidth = 3;
		gbc_reservationDateField.insets = new Insets(0, 0, 5, 0);
		gbc_reservationDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_reservationDateField.gridx = 1;
		gbc_reservationDateField.gridy = 5;
		panel.add(reservationDateField, gbc_reservationDateField);
		reservationDateField.setColumns(10);
		
		JLabel lastDate = new JLabel("최종날짜");
		GridBagConstraints gbc_lastDate = new GridBagConstraints();
		gbc_lastDate.anchor = GridBagConstraints.WEST;
		gbc_lastDate.insets = new Insets(0, 0, 5, 5);
		gbc_lastDate.gridx = 0;
		gbc_lastDate.gridy = 6;
		panel.add(lastDate, gbc_lastDate);
		
		lastDateField = new JTextField();
		GridBagConstraints gbc_lastDateField = new GridBagConstraints();
		gbc_lastDateField.gridwidth = 3;
		gbc_lastDateField.insets = new Insets(0, 0, 5, 0);
		gbc_lastDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastDateField.gridx = 1;
		gbc_lastDateField.gridy = 6;
		panel.add(lastDateField, gbc_lastDateField);
		lastDateField.setColumns(10);
		
		JLabel calibrationInfo = new JLabel("보정정보");
		GridBagConstraints gbc_calibrationInfo = new GridBagConstraints();
		gbc_calibrationInfo.anchor = GridBagConstraints.WEST;
		gbc_calibrationInfo.insets = new Insets(0, 0, 5, 5);
		gbc_calibrationInfo.gridx = 0;
		gbc_calibrationInfo.gridy = 7;
		panel.add(calibrationInfo, gbc_calibrationInfo);
		
		JLabel charge = new JLabel("요금납부여부");
		GridBagConstraints gbc_charge = new GridBagConstraints();
		gbc_charge.anchor = GridBagConstraints.WEST;
		gbc_charge.insets = new Insets(0, 0, 5, 5);
		gbc_charge.gridx = 2;
		gbc_charge.gridy = 7;
		panel.add(charge, gbc_charge);
		
		JCheckBox chargeCheck = new JCheckBox("");
		GridBagConstraints gbc_chargeCheck = new GridBagConstraints();
		gbc_chargeCheck.insets = new Insets(0, 0, 5, 0);
		gbc_chargeCheck.gridx = 3;
		gbc_chargeCheck.gridy = 7;
		panel.add(chargeCheck, gbc_chargeCheck);
		
		JScrollPane calibrationInfoScrollPane = new JScrollPane();
		GridBagConstraints gbc_calibrationInfoScrollPane = new GridBagConstraints();
		gbc_calibrationInfoScrollPane.gridwidth = 4;
		gbc_calibrationInfoScrollPane.gridheight = 8;
		gbc_calibrationInfoScrollPane.fill = GridBagConstraints.BOTH;
		gbc_calibrationInfoScrollPane.gridx = 0;
		gbc_calibrationInfoScrollPane.gridy = 8;
		panel.add(calibrationInfoScrollPane, gbc_calibrationInfoScrollPane);
		
		JTextArea calibrationInfoTextArea = new JTextArea();
		calibrationInfoScrollPane.setViewportView(calibrationInfoTextArea);
		
		JPanel selectPanel = new JPanel();
		add(selectPanel, BorderLayout.CENTER);
		GridBagLayout gbl_selectPanel = new GridBagLayout();
		gbl_selectPanel.columnWidths = new int[]{53, 124, 175, 0};
		gbl_selectPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_selectPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_selectPanel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		selectPanel.setLayout(gbl_selectPanel);
		
		JLabel selectNumLabel = new JLabel("고객번호");
		selectNumLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_selectNumLabel = new GridBagConstraints();
		gbc_selectNumLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectNumLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectNumLabel.gridx = 0;
		gbc_selectNumLabel.gridy = 0;
		selectPanel.add(selectNumLabel, gbc_selectNumLabel);
		
		JLabel selectNameLabel = new JLabel("고객 이름");
		selectNameLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_selectNameLabel = new GridBagConstraints();
		gbc_selectNameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectNameLabel.gridx = 1;
		gbc_selectNameLabel.gridy = 0;
		selectPanel.add(selectNameLabel, gbc_selectNameLabel);
		
		JLabel selectDateLabel = new JLabel("예약 날짜");
		selectDateLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_selectDateLabel = new GridBagConstraints();
		gbc_selectDateLabel.insets = new Insets(0, 0, 5, 0);
		gbc_selectDateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectDateLabel.gridx = 2;
		gbc_selectDateLabel.gridy = 0;
		selectPanel.add(selectDateLabel, gbc_selectDateLabel);

		JScrollPane selectListScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_selectListScrollPane = new GridBagConstraints();
		gbc_selectListScrollPane.gridwidth = 3;
		gbc_selectListScrollPane.gridheight = 4;
		gbc_selectListScrollPane.fill = GridBagConstraints.BOTH;
		gbc_selectListScrollPane.gridx = 0;
		gbc_selectListScrollPane.gridy = 1;
		selectPanel.add(selectListScrollPane, gbc_selectListScrollPane);
		
		JPanel ListPanel = new JPanel();
		selectListScrollPane.setViewportView(ListPanel);
		GridBagLayout gbl_ListPanel = new GridBagLayout();
		gbl_ListPanel.columnWidths = new int[]{78, 147, 0, 0};
		gbl_ListPanel.rowHeights = new int[]{0, 0};
		gbl_ListPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_ListPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		ListPanel.setLayout(gbl_ListPanel);
		
		JList numList = new JList();
		GridBagConstraints gbc_numList = new GridBagConstraints();
		gbc_numList.insets = new Insets(0, 0, 0, 5);
		gbc_numList.fill = GridBagConstraints.BOTH;
		gbc_numList.gridx = 0;
		gbc_numList.gridy = 0;
		ListPanel.add(numList, gbc_numList);
		
		JList nameList = new JList();
		GridBagConstraints gbc_nameList = new GridBagConstraints();
		gbc_nameList.insets = new Insets(0, 0, 0, 5);
		gbc_nameList.fill = GridBagConstraints.BOTH;
		gbc_nameList.gridx = 1;
		gbc_nameList.gridy = 0;
		ListPanel.add(nameList, gbc_nameList);
		
		JList dateList = new JList();
		GridBagConstraints gbc_dateList = new GridBagConstraints();
		gbc_dateList.fill = GridBagConstraints.BOTH;
		gbc_dateList.gridx = 2;
		gbc_dateList.gridy = 0;
		ListPanel.add(dateList, gbc_dateList);
		
	}
	
}

