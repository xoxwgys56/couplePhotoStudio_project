package framework;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.DB_insert;
import util.FileLoader;

public class ListPanel extends JPanel implements ActionListener{
	private JButton serchButton;
	private static JComboBox howtoSearch;
	private JLabel photoType;
	private static JTextField searchTextField;
	private JTextField customerNameField;
	private JTextField cutomerPhoneNumField;
	private JTextField photoTypeField;
	private JTextField customerNumField;
	private JTextField numOfPeopleField;
	private JTextField creationDateFieldyear;
	private JTextField reservationDateFieldyear;
	private JTextField lastDateFieldyear;
	private JTextArea calibrationInfoTextArea;
	private static DefaultTableModel model;
	private static JTable table;
	private JCheckBox chargeCheck;
	private static Vector<CustomerInfo> c_vec;
	private JButton saveChange;
	private JButton cancelChange;
	private JLabel label1;
	private JLabel Label2;
	private JLabel Label3;
	private JLabel Label11;
	private JLabel Label22;
	private JLabel Label33;
	private JTextField creationDateFieldmonth;
	private JTextField creationDateFieldday;
	private JTextField reservationDateFieldmonth;
	private JTextField reservationDateFieldday;
	private JTextField lastDateFieldmonth;
	private JTextField lastDateFieldday;
	
	private Display display;
	public ListPanel(Display display) {
		this.display = display;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 391, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 483, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel topPanel = new JPanel();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.anchor = GridBagConstraints.NORTH;
		gbc_topPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] { 344, 0 };
		gbl_topPanel.rowHeights = new int[] { 23, 93, 0 };
		gbl_topPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_topPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		topPanel.setLayout(gbl_topPanel);

		JPanel serchPanel = new JPanel();
		GridBagConstraints gbc_serchPanel = new GridBagConstraints();
		gbc_serchPanel.anchor = GridBagConstraints.NORTH;
		gbc_serchPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_serchPanel.insets = new Insets(0, 0, 5, 0);
		gbc_serchPanel.gridx = 0;
		gbc_serchPanel.gridy = 0;
		topPanel.add(serchPanel, gbc_serchPanel);
		GridBagLayout gbl_serchPanel = new GridBagLayout();
		gbl_serchPanel.columnWidths = new int[] { 84, 74, 116, 108, 74, 0 };
		gbl_serchPanel.rowHeights = new int[] { 23, 0 };
		gbl_serchPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_serchPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		serchPanel.setLayout(gbl_serchPanel);

		howtoSearch = new JComboBox();
		GridBagConstraints gbc_howtoSearch = new GridBagConstraints();
		gbc_howtoSearch.anchor = GridBagConstraints.WEST;
		gbc_howtoSearch.insets = new Insets(0, 0, 0, 5);
		gbc_howtoSearch.gridx = 0;
		gbc_howtoSearch.gridy = 0;
		serchPanel.add(howtoSearch, gbc_howtoSearch);
		howtoSearch.setModel(new DefaultComboBoxModel(new String[] { "이름", "예약날짜" ,"전화번호"}));

		searchTextField = new JTextField();
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridwidth = 3;
		gbc_searchTextField.insets = new Insets(0, 0, 0, 5);
		gbc_searchTextField.gridx = 1;
		gbc_searchTextField.gridy = 0;
		serchPanel.add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);

		serchButton = new JButton("검색");
		serchButton.addActionListener(this);
		GridBagConstraints gbc_serchButton = new GridBagConstraints();
		gbc_serchButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_serchButton.anchor = GridBagConstraints.NORTH;
		gbc_serchButton.gridx = 4;
		gbc_serchButton.gridy = 0;
		serchPanel.add(serchButton, gbc_serchButton);

		Vector<String> head = new Vector<String>();
		head.addElement("고객번호");
		head.addElement("고객이름");
		head.addElement("예약날짜");
		model = new DefaultTableModel(head, 0) {
			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter(){
		    public void mouseClicked(MouseEvent evnt) {
		    	if (evnt.getClickCount() == 1) {
		            setInfo( (table.getValueAt(table.getSelectedRow(),0)) );
		            display.setPath("C:/testImg/"+(table.getValueAt(table.getSelectedRow(),0))+(table.getValueAt(table.getSelectedRow(),1))+"/");
		            display.getField().setText(display.getPath());
		            
		            FileLoader fileLoader = new FileLoader(display.getPath());
		            File file = new File(display.getPath());
		            if (!file.exists()) {
		            	file.mkdirs();
		            }
		            fileLoader.LoadFiles(file);
		    		display.clean_render();
		    		display.render(fileLoader.getIcon(), fileLoader.getFilePath(), 
		    						fileLoader.getFileName(), fileLoader.getFileCount());
		            
		         }
		     }
		});
		table.setGridColor(Color.WHITE);
		JScrollPane listScrollPane = new JScrollPane(table);
		GridBagConstraints gbc_listScrollPane = new GridBagConstraints();
		gbc_listScrollPane.fill = GridBagConstraints.BOTH;
		gbc_listScrollPane.gridx = 0;
		gbc_listScrollPane.gridy = 1;
		topPanel.add(listScrollPane, gbc_listScrollPane);


		JPanel infoPanel = new JPanel();
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 1;
		add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 104, 104, 0, 114, 0, 97, 0 };
		gbl_infoPanel.rowHeights = new int[] { 0, 21, 21, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_infoPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_infoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 0.0, Double.MIN_VALUE };
		infoPanel.setLayout(gbl_infoPanel);

		JLabel customerNum = new JLabel("고객번호");
		GridBagConstraints gbc_customerNum = new GridBagConstraints();
		gbc_customerNum.anchor = GridBagConstraints.WEST;
		gbc_customerNum.insets = new Insets(0, 0, 5, 5);
		gbc_customerNum.gridx = 0;
		gbc_customerNum.gridy = 0;
		infoPanel.add(customerNum, gbc_customerNum);
		
		customerNumField = new JTextField();
		GridBagConstraints gbc_customerNumField = new GridBagConstraints();
		gbc_customerNumField.insets = new Insets(0, 0, 5, 5);
		gbc_customerNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerNumField.gridx = 1;
		gbc_customerNumField.gridy = 0;
		infoPanel.add(customerNumField, gbc_customerNumField);
		customerNumField.setColumns(10);
		customerNumField.setEditable(false);
		
		JLabel numOfPeople = new JLabel("인원수");
		GridBagConstraints gbc_numOfPeople = new GridBagConstraints();
		gbc_numOfPeople.fill = GridBagConstraints.HORIZONTAL;
		gbc_numOfPeople.insets = new Insets(0, 0, 5, 5);
		gbc_numOfPeople.gridx = 3;
		gbc_numOfPeople.gridy = 0;
		infoPanel.add(numOfPeople, gbc_numOfPeople);

		numOfPeopleField = new JTextField();
		GridBagConstraints gbc_numOfPeopleField = new GridBagConstraints();
		gbc_numOfPeopleField.insets = new Insets(0, 0, 5, 0);
		gbc_numOfPeopleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_numOfPeopleField.gridx = 5;
		gbc_numOfPeopleField.gridy = 0;
		infoPanel.add(numOfPeopleField, gbc_numOfPeopleField);
		numOfPeopleField.setColumns(10);
		numOfPeopleField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,numOfPeopleField);
			}
		});

		JLabel customerName = new JLabel("고객이름");
		GridBagConstraints gbc_customerName = new GridBagConstraints();
		gbc_customerName.anchor = GridBagConstraints.WEST;
		gbc_customerName.fill = GridBagConstraints.VERTICAL;
		gbc_customerName.insets = new Insets(0, 0, 5, 5);
		gbc_customerName.gridx = 0;
		gbc_customerName.gridy = 1;
		infoPanel.add(customerName, gbc_customerName);
		
		customerNameField = new JTextField();
		GridBagConstraints gbc_customerNameField = new GridBagConstraints();
		gbc_customerNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_customerNameField.gridwidth = 5;
		gbc_customerNameField.insets = new Insets(0, 0, 5, 0);
		gbc_customerNameField.gridx = 1;
		gbc_customerNameField.gridy = 1;
		infoPanel.add(customerNameField, gbc_customerNameField);
		customerNameField.setColumns(10);

		JLabel cutomerPhoneNum = new JLabel("전화번호");
		GridBagConstraints gbc_cutomerPhoneNum = new GridBagConstraints();
		gbc_cutomerPhoneNum.anchor = GridBagConstraints.WEST;
		gbc_cutomerPhoneNum.fill = GridBagConstraints.VERTICAL;
		gbc_cutomerPhoneNum.insets = new Insets(0, 0, 5, 5);
		gbc_cutomerPhoneNum.gridx = 0;
		gbc_cutomerPhoneNum.gridy = 2;
		infoPanel.add(cutomerPhoneNum, gbc_cutomerPhoneNum);

		cutomerPhoneNumField = new JTextField();
		GridBagConstraints gbc_cutomerPhoneNumField = new GridBagConstraints();
		gbc_cutomerPhoneNumField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cutomerPhoneNumField.gridwidth = 5;
		gbc_cutomerPhoneNumField.insets = new Insets(0, 0, 5, 0);
		gbc_cutomerPhoneNumField.gridx = 1;
		gbc_cutomerPhoneNumField.gridy = 2;
		infoPanel.add(cutomerPhoneNumField, gbc_cutomerPhoneNumField);
		cutomerPhoneNumField.setColumns(10);

		photoType = new JLabel("촬영종류");
		GridBagConstraints gbc_photoType = new GridBagConstraints();
		gbc_photoType.anchor = GridBagConstraints.WEST;
		gbc_photoType.fill = GridBagConstraints.VERTICAL;
		gbc_photoType.insets = new Insets(0, 0, 5, 5);
		gbc_photoType.gridx = 0;
		gbc_photoType.gridy = 3;
		infoPanel.add(photoType, gbc_photoType);

		photoTypeField = new JTextField();
		GridBagConstraints gbc_photoTypeField = new GridBagConstraints();
		gbc_photoTypeField.insets = new Insets(0, 0, 5, 0);
		gbc_photoTypeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_photoTypeField.gridwidth = 5;
		gbc_photoTypeField.gridx = 1;
		gbc_photoTypeField.gridy = 3;
		infoPanel.add(photoTypeField, gbc_photoTypeField);
		photoTypeField.setColumns(10);

		JLabel creationDate = new JLabel("생성날짜");
		GridBagConstraints gbc_creationDate = new GridBagConstraints();
		gbc_creationDate.anchor = GridBagConstraints.WEST;
		gbc_creationDate.insets = new Insets(0, 0, 5, 5);
		gbc_creationDate.gridx = 0;
		gbc_creationDate.gridy = 4;
		infoPanel.add(creationDate, gbc_creationDate);

		creationDateFieldyear = new JTextField();
		GridBagConstraints gbc_creationDateFieldyear = new GridBagConstraints();
		gbc_creationDateFieldyear.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateFieldyear.fill = GridBagConstraints.HORIZONTAL;
		gbc_creationDateFieldyear.gridx = 1;
		gbc_creationDateFieldyear.gridy = 4;
		infoPanel.add(creationDateFieldyear, gbc_creationDateFieldyear);
		creationDateFieldyear.setColumns(10);
		creationDateFieldyear.setEditable(false);
		creationDateFieldyear.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,creationDateFieldyear);
			}
		});
		
		label1 = new JLabel("-");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.anchor = GridBagConstraints.EAST;
		gbc_label1.insets = new Insets(0, 0, 5, 5);
		gbc_label1.gridx = 2;
		gbc_label1.gridy = 4;
		infoPanel.add(label1, gbc_label1);
		
		creationDateFieldmonth = new JTextField();
		GridBagConstraints gbc_creationDateFieldmonth = new GridBagConstraints();
		gbc_creationDateFieldmonth.insets = new Insets(0, 0, 5, 5);
		gbc_creationDateFieldmonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_creationDateFieldmonth.gridx = 3;
		gbc_creationDateFieldmonth.gridy = 4;
		infoPanel.add(creationDateFieldmonth, gbc_creationDateFieldmonth);
		creationDateFieldmonth.setColumns(10);
		creationDateFieldmonth.setEditable(false);
		creationDateFieldmonth.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,creationDateFieldmonth);
			}
		});
		
		Label11 = new JLabel("-");
		GridBagConstraints gbc_Label11 = new GridBagConstraints();
		gbc_Label11.anchor = GridBagConstraints.EAST;
		gbc_Label11.insets = new Insets(0, 0, 5, 5);
		gbc_Label11.gridx = 4;
		gbc_Label11.gridy = 4;
		infoPanel.add(Label11, gbc_Label11);
		
		creationDateFieldday = new JTextField();
		GridBagConstraints gbc_creationDateFieldday = new GridBagConstraints();
		gbc_creationDateFieldday.insets = new Insets(0, 0, 5, 0);
		gbc_creationDateFieldday.fill = GridBagConstraints.HORIZONTAL;
		gbc_creationDateFieldday.gridx = 5;
		gbc_creationDateFieldday.gridy = 4;
		infoPanel.add(creationDateFieldday, gbc_creationDateFieldday);
		creationDateFieldday.setColumns(10);
		creationDateFieldday.setEditable(false);
		creationDateFieldday.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,creationDateFieldday);
			}
		});

		JLabel reservationDate = new JLabel("예약날짜");
		GridBagConstraints gbc_reservationDate = new GridBagConstraints();
		gbc_reservationDate.anchor = GridBagConstraints.WEST;
		gbc_reservationDate.insets = new Insets(0, 0, 5, 5);
		gbc_reservationDate.gridx = 0;
		gbc_reservationDate.gridy = 5;
		infoPanel.add(reservationDate, gbc_reservationDate);

		reservationDateFieldyear = new JTextField();
		GridBagConstraints gbc_reservationDateFieldyear = new GridBagConstraints();
		gbc_reservationDateFieldyear.insets = new Insets(0, 0, 5, 5);
		gbc_reservationDateFieldyear.fill = GridBagConstraints.HORIZONTAL;
		gbc_reservationDateFieldyear.gridx = 1;
		gbc_reservationDateFieldyear.gridy = 5;
		infoPanel.add(reservationDateFieldyear, gbc_reservationDateFieldyear);
		reservationDateFieldyear.setColumns(10);
		reservationDateFieldyear.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,reservationDateFieldyear);
			}
		});
		
		Label2 = new JLabel("-");
		GridBagConstraints gbc_Label2 = new GridBagConstraints();
		gbc_Label2.anchor = GridBagConstraints.EAST;
		gbc_Label2.insets = new Insets(0, 0, 5, 5);
		gbc_Label2.gridx = 2;
		gbc_Label2.gridy = 5;
		infoPanel.add(Label2, gbc_Label2);
		
		reservationDateFieldmonth = new JTextField();
		GridBagConstraints gbc_reservationDateFieldmonth = new GridBagConstraints();
		gbc_reservationDateFieldmonth.insets = new Insets(0, 0, 5, 5);
		gbc_reservationDateFieldmonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_reservationDateFieldmonth.gridx = 3;
		gbc_reservationDateFieldmonth.gridy = 5;
		infoPanel.add(reservationDateFieldmonth, gbc_reservationDateFieldmonth);
		reservationDateFieldmonth.setColumns(10);
		reservationDateFieldmonth.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,reservationDateFieldmonth);
			}
		});
		
		Label22 = new JLabel("-");
		GridBagConstraints gbc_Label22 = new GridBagConstraints();
		gbc_Label22.anchor = GridBagConstraints.EAST;
		gbc_Label22.insets = new Insets(0, 0, 5, 5);
		gbc_Label22.gridx = 4;
		gbc_Label22.gridy = 5;
		infoPanel.add(Label22, gbc_Label22);
		
		reservationDateFieldday = new JTextField();
		GridBagConstraints gbc_reservationDateFieldday = new GridBagConstraints();
		gbc_reservationDateFieldday.insets = new Insets(0, 0, 5, 0);
		gbc_reservationDateFieldday.fill = GridBagConstraints.HORIZONTAL;
		gbc_reservationDateFieldday.gridx = 5;
		gbc_reservationDateFieldday.gridy = 5;
		infoPanel.add(reservationDateFieldday, gbc_reservationDateFieldday);
		reservationDateFieldday.setColumns(10);
		reservationDateFieldday.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,reservationDateFieldday);
			}
		});

		JLabel lastDate = new JLabel("최종날짜");
		GridBagConstraints gbc_lastDate = new GridBagConstraints();
		gbc_lastDate.anchor = GridBagConstraints.WEST;
		gbc_lastDate.insets = new Insets(0, 0, 5, 5);
		gbc_lastDate.gridx = 0;
		gbc_lastDate.gridy = 6;
		infoPanel.add(lastDate, gbc_lastDate);

		lastDateFieldyear = new JTextField();
		GridBagConstraints gbc_lastDateFieldyear = new GridBagConstraints();
		gbc_lastDateFieldyear.insets = new Insets(0, 0, 5, 5);
		gbc_lastDateFieldyear.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastDateFieldyear.gridx = 1;
		gbc_lastDateFieldyear.gridy = 6;
		infoPanel.add(lastDateFieldyear, gbc_lastDateFieldyear);
		lastDateFieldyear.setColumns(10);
		lastDateFieldyear.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,lastDateFieldyear);
			}
		});
		
		Label3 = new JLabel("-");
		GridBagConstraints gbc_Label3 = new GridBagConstraints();
		gbc_Label3.anchor = GridBagConstraints.EAST;
		gbc_Label3.insets = new Insets(0, 0, 5, 5);
		gbc_Label3.gridx = 2;
		gbc_Label3.gridy = 6;
		infoPanel.add(Label3, gbc_Label3);
		
		lastDateFieldmonth = new JTextField();
		GridBagConstraints gbc_lastDateFieldmonth = new GridBagConstraints();
		gbc_lastDateFieldmonth.insets = new Insets(0, 0, 5, 5);
		gbc_lastDateFieldmonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastDateFieldmonth.gridx = 3;
		gbc_lastDateFieldmonth.gridy = 6;
		infoPanel.add(lastDateFieldmonth, gbc_lastDateFieldmonth);
		lastDateFieldmonth.setColumns(10);
		lastDateFieldmonth.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,lastDateFieldmonth);
			}
		});
		
		Label33 = new JLabel("-");
		GridBagConstraints gbc_Label33 = new GridBagConstraints();
		gbc_Label33.anchor = GridBagConstraints.EAST;
		gbc_Label33.insets = new Insets(0, 0, 5, 5);
		gbc_Label33.gridx = 4;
		gbc_Label33.gridy = 6;
		infoPanel.add(Label33, gbc_Label33);
		
		lastDateFieldday = new JTextField();
		lastDateFieldday.setText("");
		GridBagConstraints gbc_lastDateFieldday = new GridBagConstraints();
		gbc_lastDateFieldday.insets = new Insets(0, 0, 5, 0);
		gbc_lastDateFieldday.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastDateFieldday.gridx = 5;
		gbc_lastDateFieldday.gridy = 6;
		infoPanel.add(lastDateFieldday, gbc_lastDateFieldday);
		lastDateFieldday.setColumns(10);
		lastDateFieldday.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				keytyped(ev,lastDateFieldday);
			}
		});

		JLabel calibrationInfo = new JLabel("보정정보");
		GridBagConstraints gbc_calibrationInfo = new GridBagConstraints();
		gbc_calibrationInfo.anchor = GridBagConstraints.WEST;
		gbc_calibrationInfo.insets = new Insets(0, 0, 5, 5);
		gbc_calibrationInfo.gridx = 0;
		gbc_calibrationInfo.gridy = 7;
		infoPanel.add(calibrationInfo, gbc_calibrationInfo);

		JLabel charge = new JLabel("요금납부여부");
		GridBagConstraints gbc_charge = new GridBagConstraints();
		gbc_charge.fill = GridBagConstraints.HORIZONTAL;
		gbc_charge.insets = new Insets(0, 0, 5, 5);
		gbc_charge.gridx = 3;
		gbc_charge.gridy = 7;
		infoPanel.add(charge, gbc_charge);

		chargeCheck = new JCheckBox("");
		GridBagConstraints gbc_chargeCheck = new GridBagConstraints();
		gbc_chargeCheck.insets = new Insets(0, 0, 5, 0);
		gbc_chargeCheck.gridx = 5;
		gbc_chargeCheck.gridy = 7;
		infoPanel.add(chargeCheck, gbc_chargeCheck);

		JScrollPane calibrationInfoScrollPane = new JScrollPane();
		GridBagConstraints gbc_calibrationInfoScrollPane = new GridBagConstraints();
		gbc_calibrationInfoScrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_calibrationInfoScrollPane.gridwidth = 6;
		gbc_calibrationInfoScrollPane.gridheight = 7;
		gbc_calibrationInfoScrollPane.fill = GridBagConstraints.BOTH;
		gbc_calibrationInfoScrollPane.gridx = 0;
		gbc_calibrationInfoScrollPane.gridy = 8;
		infoPanel.add(calibrationInfoScrollPane, gbc_calibrationInfoScrollPane);

		calibrationInfoTextArea = new JTextArea();
		calibrationInfoScrollPane.setViewportView(calibrationInfoTextArea);
		
		saveChange = new JButton("변경저장");
		GridBagConstraints gbc_saveChange = new GridBagConstraints();
		gbc_saveChange.gridwidth = 2;
		gbc_saveChange.insets = new Insets(0, 0, 0, 5);
		gbc_saveChange.gridx = 0;
		gbc_saveChange.gridy = 15;
		gbc_saveChange.fill = GridBagConstraints.BOTH;
		infoPanel.add(saveChange, gbc_saveChange);
		saveChange.addActionListener(this);
		
		cancelChange = new JButton("변경취소");
		GridBagConstraints gbc_cancelChange = new GridBagConstraints();
		gbc_cancelChange.gridwidth = 3;
		gbc_cancelChange.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelChange.insets = new Insets(0, 0, 0, 5);
		gbc_cancelChange.gridx = 3;
		gbc_cancelChange.gridy = 15;
		gbc_cancelChange.fill = GridBagConstraints.BOTH;
		infoPanel.add(cancelChange, gbc_cancelChange);
		cancelChange.addActionListener(this);
	}

	private void keytyped(KeyEvent ev, JTextField jtf) {
		try {
			Integer.parseInt(Character.toString(ev.getKeyChar()));		//정수인 경우에만
	    } catch (NumberFormatException e) {
	    	jtf.setText("");
	    	JOptionPane.showMessageDialog(this, "숫자만 입력하시기 바랍니다.");
	    }
	}

	public void setInfo(Object c_num) {
		int cnum=Integer.parseInt(c_num.toString());
		for (int i = 0; i < c_vec.size(); i++) {
			if(c_vec.elementAt(i).num==cnum) {
				customerNameField.setText(c_vec.elementAt(i).name);;
				cutomerPhoneNumField.setText(c_vec.elementAt(i).phoneNum);
				photoTypeField.setText(c_vec.elementAt(i).photoType);
				customerNumField.setText(String.valueOf(c_vec.elementAt(i).num));
				numOfPeopleField.setText(String.valueOf(c_vec.elementAt(i).numOfPeople));
				String c_date[]=c_vec.elementAt(i).creationDate.split("-");
				creationDateFieldyear.setText(c_date[0]);
				creationDateFieldmonth.setText(c_date[1]);
				creationDateFieldday.setText(c_date[2]);
				String r_date[]=c_vec.elementAt(i).reservationDate.split("-");
				reservationDateFieldyear.setText(r_date[0]);
				reservationDateFieldmonth.setText(r_date[1]);
				reservationDateFieldday.setText(r_date[2]);
				if(!c_vec.elementAt(i).lastDate.equals("")) {
					String l_date[]=c_vec.elementAt(i).lastDate.split("-");
					lastDateFieldyear.setText(l_date[0]);
					lastDateFieldmonth.setText(l_date[1]);
					lastDateFieldday.setText(l_date[2]);
				}
				else {
					lastDateFieldyear.setText("");
					lastDateFieldmonth.setText("");
					lastDateFieldday.setText("");
				}
				calibrationInfoTextArea.setText((c_vec.elementAt(i).calibrationInfo));
				if(c_vec.elementAt(i).charge==1) {
					chargeCheck.setSelected(true);
				}
				else {
					chargeCheck.setSelected(false);
				}
			}
		}
	}
	public void setList(Vector<CustomerInfo> vec) {
		c_vec=vec;
		removeTable();
		for (int i = 0; i < vec.size(); i++) {
			Vector<String> rowvec = new Vector<String>();
			rowvec.addElement(String.valueOf(vec.elementAt(i).num));
			rowvec.addElement(vec.elementAt(i).name);
			rowvec.addElement(vec.elementAt(i).reservationDate);
			model.addRow(rowvec);
		}
	}
	//추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가
	public static void dateserch(String str) {
		Date date;
		SimpleDateFormat before = new SimpleDateFormat("yyyy-M-dd");
		SimpleDateFormat after = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = before.parse(str); 
		}catch(ParseException pe) {
			System.out.println("Asdasdasd");
			date=new Date();
		}
		String d_str=after.format(date);
		howtoSearch.setSelectedIndex(1);
		searchTextField.setText(d_str);
		removeTable();
		for (int i = 0; i < c_vec.size(); i++) {
			if(c_vec.elementAt(i).reservationDate.contains(d_str)) {
				Vector<String> rowvec = new Vector<String>();
				rowvec.addElement(String.valueOf(c_vec.elementAt(i).num));
				rowvec.addElement(c_vec.elementAt(i).name);
				rowvec.addElement(c_vec.elementAt(i).reservationDate);
				model.addRow(rowvec);
			}
		}
	}
	//추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가추가
	public void serchList(Vector<CustomerInfo> vec,String na) {				
		c_vec=vec;
		removeTable();
		if(howtoSearch.getSelectedItem().toString()=="이름") {
			for (int i = 0; i < vec.size(); i++) {
				if(vec.elementAt(i).name.contains(na)) {
					Vector<String> rowvec = new Vector<String>();
					rowvec.addElement(String.valueOf(vec.elementAt(i).num));
					rowvec.addElement(vec.elementAt(i).name);
					rowvec.addElement(vec.elementAt(i).reservationDate);
					model.addRow(rowvec);
				}
			}
		}
		else if(howtoSearch.getSelectedItem().toString()=="예약날짜") {
				for (int i = 0; i < vec.size(); i++) {
					if(vec.elementAt(i).reservationDate.contains(na)) {
						Vector<String> rowvec = new Vector<String>();
						rowvec.addElement(String.valueOf(vec.elementAt(i).num));
						rowvec.addElement(vec.elementAt(i).name);
						rowvec.addElement(vec.elementAt(i).reservationDate);
						model.addRow(rowvec);
					}
				}
			}
		else if(howtoSearch.getSelectedItem().toString()=="전화번호") {
				for (int i = 0; i < vec.size(); i++) {
					if(vec.elementAt(i).phoneNum.contains(na)) {
						Vector<String> rowvec = new Vector<String>();
						rowvec.addElement(String.valueOf(vec.elementAt(i).num));
						rowvec.addElement(vec.elementAt(i).name);
						rowvec.addElement(vec.elementAt(i).reservationDate);
						model.addRow(rowvec);
					}
				}
			}
	}
	public static String getSelectTable() {			
		return (String)table.getValueAt(table.getSelectedRow(),0);
	}
	public static boolean isSelectTable() {		
		if(table.getSelectedRow()==-1) {
			return false;
		}
		else {
			return true;
		}
	}
	public static void removeTable() {
		for (int i = 0; i < model.getRowCount();) {
			model.removeRow(0);
		}
	}
	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == serchButton) {
			if(searchTextField.getText().equals("")) {
				setList(c_vec);
			}
			else{
				serchList(c_vec,searchTextField.getText());
			}
		}
		if(e.getSource() == saveChange) {
			if(isSelectTable()) {
				database.DB_insert.updatecustomer("customname", customerNameField.getText(),this.getSelectTable());
				database.DB_insert.updatecustomer("tel", cutomerPhoneNumField.getText(),this.getSelectTable());
				database.DB_insert.updatecustomer("type", photoTypeField.getText(),this.getSelectTable());
				database.DB_insert.updatecustomer("people", numOfPeopleField.getText(),this.getSelectTable());
				database.DB_insert.updatecustomer("date", reservationDateFieldyear.getText()+"-"+reservationDateFieldmonth.getText()+"-"+reservationDateFieldday.getText(),this.getSelectTable());
				database.DB_insert.updatecustomer("finaldate",lastDateFieldyear.getText()+"-"+lastDateFieldmonth.getText()+"-"+lastDateFieldday.getText(),this.getSelectTable());
				database.DB_insert.updatecustomer("bigo", calibrationInfoTextArea.getText(),this.getSelectTable());
				if(chargeCheck.isSelected()) {
					database.DB_insert.updatecustomer("paid", "1",this.getSelectTable());
				}
				else {
					database.DB_insert.updatecustomer("paid", "0",this.getSelectTable());
				}
				DB_insert dbi =new DB_insert();
				c_vec.removeAllElements();
				try {
					dbi.getRecord(c_vec);
				}
				catch(SQLException se) {
					System.out.println(se);
				}
				setList(c_vec);
			}
			else {
				JOptionPane.showMessageDialog(this, "고객을 선택하시기 바랍니다.");
			}
		}
		if(e.getSource() == cancelChange) {
			if(isSelectTable()) {
				setInfo((table.getValueAt(table.getSelectedRow(),0)));
			}
			else {
				JOptionPane.showMessageDialog(this, "고객을 선택하시기 바랍니다.");
			}
		}
    }
}
