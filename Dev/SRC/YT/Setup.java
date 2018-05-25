package framework;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class Setup extends JFrame implements ActionListener{

      private JPanel expanel,setpanel;
      private JTextField textField,textField2,textField3;
      private JLabel label,label1,label2,label3;
      private JButton button1,button2,button3;
     // private FrameWork frame;
      private String changePath;
      private String[] previous;

   
      
      private database.DB_insert db;
      private Display display;
      
      public Setup() {    	  
    	 //System.out.println("setUP"); 여기까지 들어오는 것 확인
        setSize(400,180);
        BoxLayout layout = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
        setLayout(layout);
        
        //previous = display.getFieldString();
        
        expanel = new JPanel();
        
           label = new JLabel("환경설정");
           expanel.add(label);
           
   
        setpanel = new JPanel();
        setpanel.setLayout(new GridLayout(3,3));
          
           label1 = new JLabel("파일저장 기본경로변경");
           textField = new JTextField();
           button1 = new JButton("확인");
           button1.addActionListener(this);
	       setpanel.add(label1);
	       setpanel.add(textField);
	       setpanel.add(button1);
	      
	       
	       label2 = new JLabel("포토샵 경로설정");   //이하 2018.05.05 yt
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
	       
	       
        add(expanel);
        add(setpanel);
        setVisible(true);
        
    }
   
         
   @Override
   public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
      if(e.getSource() == button1) {
    	  
         changePath = textField.getText();                          		//previous값이 안들어가는 오류만 해결하면 끝날 듯.
         try {
			previous = db.getpath();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);;
		}
         System.out.println("previous : "+previous);
         System.out.println("changePath :"+changePath);
         db.updaterecord("pathtable","pathname", previous[0], changePath);
         int choice =JOptionPane.showConfirmDialog(null, "기본 경로를 바꿔 재시작 해야합니다, 죵료하시겠습니까?", "알림창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
         System.out.println("choice : "+choice);
         if(choice==0) {
        	 System.exit(0);
         }
         else if(choice == 1) {
        	 setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
         }
      }
      
      if(e.getSource() == button2) { //포토샵 경로 저장하기
    	  
      try {
			previous[1] = db.p_getpath();
			if (previous[1] == "/") {
	            JOptionPane.showMessageDialog(null, "기본 포토샵 경로가 비어있습니다. 경로를 넣어주세요");
			}
			changePath = textField2.getText();
			 db.updaterecord("pathtable","photopath", previous[1], changePath);
			 int choice =JOptionPane.showConfirmDialog(null, "포토샵 경로를 바꿔 재시작 해야합니다, 죵료하시겠습니까?", "알림창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	         System.out.println("choice : "+choice);
	         if(choice==0) {
	        	 System.exit(0);
	         }
	         else if(choice == 1) {
	        	 setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	         }
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);;	
		}
         System.out.println("previous : "+previous);
         System.out.println("changePath :"+changePath);
      }
      
      if(e.getSource() == button3) { //라이트룸 경로 저장하기
    	  
      try {
			previous[2] = db.l_getpath();
			if (previous[2] == "/") {
	            JOptionPane.showMessageDialog(null, "기본 라이트룸 경로가 비어있습니다. 경로를 넣어주세요");
			}
			changePath = textField2.getText();
			db.updaterecord("pathtable","lightpath", previous[2], changePath);
			 int choice =JOptionPane.showConfirmDialog(null, "라이트룸 경로를 바꿔 재시작 해야합니다, 죵료하시겠습니까?", "알림창", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	         System.out.println("choice : "+choice);
	         if(choice==0) {
	        	 System.exit(0);
	         }
	         else if(choice == 1) {
	        	 setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	         }
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);;	
		}
         System.out.println("previous : "+previous);
         System.out.println("changePath :"+changePath);
      }
   }

}
