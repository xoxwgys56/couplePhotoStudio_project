package DB;

import java.sql.*;

public class DB_insert{

      public static Connection DB_insert() {
         
         
         String url = "jdbc:mysql://localhost/picdb";
         String id = "root";  //id
         String password = "apmsetup"; //pw
         Connection con = null;
         //jdbc ����̹� ���� �Լ�
         try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����̹� ���� ����");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("������ ���̽� ���� ����");
            
         }
         catch(ClassNotFoundException e) {
            System.out.println("����̹��� ã�� �� �����ϴ�.");
         } catch (SQLException e) {
           
            System.out.println("���ῡ ���� !");
         }
         return con;
      }
      
      public static void main(String arg[]) throws SQLException{
         Connection con = DB_insert();
         Statement stmt = con.createStatement();  
          
         // ��ü�˻�
         ResultSet rs = stmt.executeQuery("Select * from PICTABLE"); 
         while(rs.next()) {
        	 
        	int num = rs.getInt("customnum");
            String c_name = rs.getString("customname");
            String c_phonenumber = rs.getString("tel");
            String kindofshut = rs.getString("type");
            int howmany = rs.getInt("people");
            String date = rs.getString("date");
            String update = rs.getString("faux");
            int pay = rs.getInt("paid");
            String bigo = rs.getString("bigo");      
            System.out.println(num+" "+c_name+" "+c_phonenumber+" "+kindofshut+" "+howmany+" "+date+" "+update+" "+pay+" "+bigo);
         	
         }
    	 //����
         addrecord("�Ϲ���","000-0000-0000","�������",1,"12�� 30","����",5000,"����");
         
         //����
         //updaterecord("pictable","customname","����","�Ϲ���");
         
         //����Ʃ�� ����
         //deleterecord("pictable","customname","�����");
         
         //������ �ʱ�ȭ
         Adeleterecord("pictable");
      }
      //����
      private static void addrecord(String c_name, String c_phone, String c_kindofshut, int c_howmany, String c_date, String c_update,int pay, String bigo) 
      {
    	 
    	  Connection con = DB_insert();
          try {
        	  Statement stmt = con.createStatement();
        	  String s = "insert into pictable(customname, tel, type, people, date, faux, paid,bigo)values";
        	  		
        	  s+= "('"+c_name+"','"+c_phone+"','"+c_kindofshut+"','"+ c_howmany+"','"+c_date+"','"+c_update+"','"+pay+"','"+bigo+"')";
        	  System.out.println(s);
        	  int i = stmt.executeUpdate(s);
        	  if(i == 1) System.out.println("���� ����");
        	  else System.out.println("���� ����");
          }catch (SQLException e) {
        	  System.out.println(e.getMessage());
        	  System.exit(0);
          }
      }
      
      //����								//���̺� ��    	//�ٲ� �Ӽ�		//�ٲ�� �� ��		//�ٲ�� ��
      private static void updaterecord(String table,String column,String before, String after) {
    	  Connection con = DB_insert();
          String s = "update "+table+ " set "+column+"="+"'"+after+"'"+"where "+column+"="+"'"+before+"'";
          
         try {
        	 Statement stmt = con.createStatement();
        	 System.out.println(s);
             int i = stmt.executeUpdate(s);
             if(i == 1) System.out.println("���� ����");
             else System.out.println("���� ����");
         }catch (SQLException e) {
       	  System.out.println(e.getMessage());
       	  System.exit(0);
         }
         
      }
      
      //���ڵ� ����
     private static void deleterecord(String table,String column,String deleterecord) {
    	 Connection con = DB_insert();
    	 String s = "delete "+"from "+table+" where "+column+"="+"'"+deleterecord+"'";
    	 try {
        	 Statement stmt = con.createStatement();
        	 System.out.println(s);
             int i = stmt.executeUpdate(s);
             if(i == 1) System.out.println("Ʃ�� ���� ����");
             else System.out.println("Ʃ�� ���� ����");
         }catch (SQLException e) {
       	  System.out.println(e.getMessage());
       	  System.exit(0);
         }
     
     }
     
     //���̺� �ʱ�ȭ
     private static void Adeleterecord(String table) {
    	 Connection con = DB_insert();
    	 String s = "delete "+"from "+table;
    	 try {
        	 Statement stmt = con.createStatement();
        	 System.out.println(s);
             int i = stmt.executeUpdate(s);
             if(i == 1) System.out.println("Ʃ�� ���� ����");
             else System.out.println("Ʃ�� ���� ����");
         }catch (SQLException e) {
       	  System.out.println(e.getMessage());
       	  System.exit(0);
         }
     
     }
 
}



