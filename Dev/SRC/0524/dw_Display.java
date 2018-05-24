package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import database.DB_insert;
import util.FileLoader;

/*
 * setPath를 통한, path 업데이트가 필요하다.
 * */

public class Display extends JFrame{

	private JPanel panel;
	private JPanel canvas;
	private JTextField field;
	
	public String path;
	private int width, height;
	
	/* 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 */
	private String photoshopPath = "C:/Windows/System32/notepad.exe"//메모장으로 해놨음.
	private String lightroomPath = "C:/Windows/System32/notepad.exe";//메모장으로 해놨음.

	//
	ListPanel listpan;
	ButtonPanel btnpan;
	static Vector<CustomerInfo> c_vec;
	private GridBagConstraints gbc_panel_1;

	Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
	public Display() {
		this.width = res.width;
		this.height = res.height;
		path = "C:/";
		init();
		//default path
		
	}
	
	public Display(String path) {
		this.width = res.width;
		this.height = res.height;
		this.path = path;
		
		init();
	}
	
	public void init() {
		this.setTitle("title");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		c_vec=new Vector(1);
		setSize(1000,800);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{294, 436, 84, 0};
		gridBagLayout.rowHeights = new int[]{472, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		listpan= new ListPanel(this);
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(listpan, gbc_panel);
		
		btnpan=new ButtonPanel(c_vec,listpan);
		gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 0;
		getContentPane().add(btnpan, gbc_panel);
		
		panel = new JPanel();
		gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		getContentPane().add(panel, gbc_panel_1);
		canvas = new JPanel();
		canvas.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		field = new JTextField();
		
		panel.setSize(512, 512);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new BorderLayout());
		
		field.setSize(100, 100);
		field.setText(path);
		field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				path = field.getText();
				System.out.println(path);
			}
		});
		
		panel.add(canvas, "Center");
		panel.add(field, "North");
		
		
		setVisible(true);
		
		DB_insert dbi =new DB_insert();
		c_vec.removeAllElements();
		try {
			dbi.getRecord(c_vec);
		}
		catch(SQLException se) {
			System.out.println(se);
		}
		listpan.setList(c_vec);
	}
	
	public void render(ImageIcon[] icon, String[] filePath, 
			String[] fileName, int count) {
		
		for ( int i = 0 ; i < count ; i++ ) {
			JLabel label = new JLabel();
			// 아이콘
			label.setIcon(icon[i]);
			label.setPreferredSize(new Dimension(90,90));
			
			// 색상 레이블
			JLabel colorLabel = new JLabel(" ");
			colorLabel.setOpaque(true);
			colorLabel.setPreferredSize(new Dimension(20,20));
			colorLabel.setBackground(Color.white);
			
			// 색상 팝업
			JPopupMenu colorPopup = new JPopupMenu();
        	//메뉴아이템		
        	JMenuItem white = new JMenuItem("white");
        	JMenuItem black = new JMenuItem("black");
        	JMenuItem red = new JMenuItem("red");
        	JMenuItem green = new JMenuItem("green");
        	JMenuItem blue = new JMenuItem("blue");
        	
        	colorPopup.add(white);
        	colorPopup.add(black);
        	colorPopup.add(red);
        	colorPopup.add(green);
        	colorPopup.add(blue);
        	
        	class ActionClass implements ActionListener
        	{
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			String obj = e.getActionCommand();
        			System.out.println(obj);
        			switch( obj ) {
        			case "white": colorLabel.setBackground(Color.white); break;
        			case "black": colorLabel.setBackground(Color.black); break;
        			case "red"	: colorLabel.setBackground(Color.red);	 break;
        			case "green": colorLabel.setBackground(Color.green); break;
        			case "blue"	: colorLabel.setBackground(Color.blue);	 break;
        			}
        		}
        	}
        	// 이벤트 리스너
            white.addActionListener(new ActionClass());
            black.addActionListener(new ActionClass());
            red.addActionListener(new ActionClass());
            green.addActionListener(new ActionClass());
            blue.addActionListener(new ActionClass());

            colorLabel.add(colorPopup);
			colorLabel.addMouseListener(new MouseAdapter(){
				//마우스 클릭
			    public void mousePressed(MouseEvent evnt) {
			    	// 우클릭시
			        if (evnt.getModifiers() == MouseEvent.BUTTON3_MASK) {
			        	//팝업
			        	colorPopup.show(label, evnt.getX(), evnt.getY());
			        }
			     }
			});
			
			
			// 파일이름
			JLabel nameLabel = new JLabel(fileName[i]);
			nameLabel.setPreferredSize(new Dimension(70,20));
			
			// 레이블 패널에 통합
			JPanel iconPanel = new JPanel();
			iconPanel.setLayout(new BorderLayout());
			iconPanel.add(label, BorderLayout.CENTER);
			JPanel namePanel = new JPanel();
			namePanel.add(colorLabel);
			namePanel.add(nameLabel);
			iconPanel.add(namePanel, BorderLayout.SOUTH);
			
			//팝업
			JPopupMenu popup = new JPopupMenu();
        	//메뉴아이템		
        	JMenuItem open = new JMenuItem("열기");
        	JMenuItem delete = new JMenuItem("삭제");
        	JMenuItem open_photo = new JMenuItem("포토샵으로 열기");
        	JMenuItem open_light = new JMenuItem("라이트룸으로 열기");
        	JMenuItem add_memo = new JMenuItem("메모");
        	
        	popup.add(open);
        	popup.add(delete);
        	popup.add(open_photo);
        	popup.add(open_light);
        	popup.add(add_memo);
 
        	String filepath = filePath[i];
        	
        	/* 추가 추가 추가 */
        	class PopupActionClass implements ActionListener
        	{
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			String obj = e.getActionCommand();
        			System.out.println(obj);
        			switch( obj ) {
        			case "열기":
        				try {
							Desktop.getDesktop().open(new File(filepath));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
        				break;
        			case "삭제":
        				if(!new File(filepath).delete()) {
							//삭제 불가능
							System.out.println("error::delete failed");
						}else {
							System.out.println("delete successed");
						}
        				break;
        			case "포토샵으로 열기":
        				File photoshop_file = new File(filepath);
        		        ProcessBuilder photoshop_builder = new ProcessBuilder(photoshopPath, photoshop_file.getAbsolutePath());
        		        photoshop_builder.redirectErrorStream();
        		        photoshop_builder.redirectOutput();
        		        Process photoshop_process;
						try {
							photoshop_process = photoshop_builder.start();
							photoshop_process.waitFor();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
        		        break;
        			case "라이트룸으로 열기":
        				File lightroon_file = new File(filepath);
        		        ProcessBuilder lightroon_builder = new ProcessBuilder(photoshopPath, lightroon_file.getAbsolutePath());
        		        lightroon_builder.redirectErrorStream();
        		        lightroon_builder.redirectOutput();
        		        Process lightroon_process;
						try {
							lightroon_process = lightroon_builder.start();
							lightroon_process.waitFor();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
        		        break;
        			case "메모":
        				String input_memo = JOptionPane.showInputDialog("input memo");
        				if (input_memo == null)
        					System.out.println("no input memo");
        				else {
            				System.out.println("input memo is "+input_memo);
            				int pos = filepath.lastIndexOf('.');
            				File memofile = new File( filepath.substring(0,pos) + ".txt");
            				try {
            				      BufferedWriter out = new BufferedWriter(new FileWriter(filepath.substring(0,pos)+".txt"));
            				      out.write(input_memo);
            				      out.newLine();
            				      out.close();
            				} catch (IOException e2) { e2.printStackTrace(); }
        				}
        				break;
        			}
        			FileLoader fileLoader = new FileLoader(getPath());
    				fileLoader.LoadFiles(new File(getPath()));
    				clean_render();
		    		render(fileLoader.getIcon(), fileLoader.getFilePath(), fileLoader.getFileName(), fileLoader.getFileCount());
        		}
        	}
        	/* 추가 추가 추가 */
        	
        	// 이벤트 리스너
            open.addActionListener(new PopupActionClass());
        	delete.addActionListener(new PopupActionClass());
        	open_photo.addActionListener(new PopupActionClass());
        	open_light.addActionListener(new PopupActionClass());
        	add_memo.addActionListener(new PopupActionClass());
        	
        	
        	label.add(popup);
			label.addMouseListener(new MouseAdapter(){
				//마우스 클릭
			    public void mousePressed(MouseEvent evnt) {
			    	// 우클릭시
			        if (evnt.getModifiers() == MouseEvent.BUTTON3_MASK) {
			        	popup.show(label, evnt.getX(), evnt.getY());
			        }
			     }
			    //마우스 안에 메모 툴팁
			    public void mouseEntered(MouseEvent evnt ) {
			    	int pos = filepath.lastIndexOf('.');
			    	// 디렉터리일경우
			    	if ( pos == -1 ) return;
			    	
				    File memofile = new File( filepath.substring(0,pos) + ".txt");
				    // 메모 없을경우
				    if ( memofile.exists() == false) return;
				    
				    StringBuffer memo_sb = new StringBuffer();
				    
				    try {
				        BufferedReader in = new BufferedReader(new FileReader(memofile));
				        String str;
				        // 줄바꿈위해 html 태그적용
				        memo_sb.append("<html>");
				        while ((str = in.readLine()) != null) {
				          memo_sb.append(str + "<br>");
				        }
				        memo_sb.append("</html>");
				        in.close();
				    } catch (IOException e) {
				          e.printStackTrace();
				    }
				    // 툴팁 설정
			    	label.setToolTipText(memo_sb.toString());
			    }

			});
			canvas.add(iconPanel);
		}//end loop
		canvas.validate();
		canvas.repaint();
	}//end render

	public void clean_render() {
		canvas.removeAll();
	}
	
	public JFrame getFrame() {		//추가
		return this;
	}
	public JPanel getPanel() {
		return panel;
	}

	public JPanel getCanvas() {
		return canvas;
	}

	public JTextField getField() {
		return field;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getPath() {
		return path;
	}

	public String getFieldString() { //
		return field.getText();
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPhotoshopPath() {
		return photoshopPath;
	}

	public void setPhotoshopPath(String photoshopPath) {
		this.photoshopPath = photoshopPath;
	}
}
