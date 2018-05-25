package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane; //18.05.25 

import framework.CustomerInfo;

public class DB_insert{
      public static Connection DB_insert() {
         
         
         String url = "jdbc:mysql://localhost/PICDB";
         String id = "root";  //id
         String password = "apmsetup"; //pw
         Connection con = null;
         String[] path;
         //jdbc 드라이버 적재 함수
         try {
        	 path = new String[3];
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("데이터 베이스 연결 성공");
            
         }
         catch(ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
            JOptionPane.showMessageDialog(null, "JDBC 드라이버가 필요합니다.");
         } catch (SQLException e) {
           
            System.out.println("연결에 실패 !");
            JOptionPane.showMessageDialog(null, "APMSetup을 실행시켜주세요.");

         }
         return con;
      }

      public static void getRecord(Vector<CustomerInfo> vec) throws SQLException{
    	  Connection con = DB_insert();
          Statement stmt = con.createStatement();  
          // 전체검색
          ResultSet rs = stmt.executeQuery("Select * from PICTABLE"); 
          while(rs.next()) {
         	 
         	int num = rs.getInt("customnum");
             String c_name = rs.getString("customname");
             String c_phonenumber = rs.getString("tel");
             String kindofshut = rs.getString("type");
             int howmany = rs.getInt("people");
             String createdate = rs.getString("createdate");
             String date = rs.getString("date");
             String finaldate = rs.getString("finaldate");
             int pay = rs.getInt("paid");
             String bigo = rs.getString("bigo");

             CustomerInfo ci =new CustomerInfo(num, c_name, c_phonenumber, kindofshut, 
            		 howmany, createdate, date, finaldate, pay, bigo);

             vec.addElement(ci);
          }
      }
      //경로 값 얻기
      public static String[] getpath() throws SQLException{
    	Connection con = DB_insert();
        Statement stmt = con.createStatement();
        try {
        ResultSet rs = stmt.executeQuery("Select pathname from pathtable");
        String[] path = null;
		        while(rs.next()) {
				path[0] = rs.getString("pathname");
		        path[1] = rs.getString("photopath");
		        path[2] = rs.getString("lightpath");
		        }
        }
        catch(SQLException e){
        	System.out.println(e);
        }
        
        return getpath();
        
      }
     
         //18.05.25 kim
       public static String p_getpath() throws SQLException{
            Connection con = DB_insert();
             Statement stmt = con.createStatement();
             
             ResultSet rs = stmt.executeQuery("Select photopath from pathtable");
             String path = null;

             if(rs.next()) {
           path = rs.getString("photopath");
             }
             else {
                System.out.println("속성이 없습니다");
             }
             return path; 
           }
       
       public static String l_getpath() throws SQLException{
           Connection con = DB_insert();
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("Select lightpath from pathtable");
            String path = null;
            
            if(rs.next()) {
             path = rs.getString("lightpath");
            }
            else {
               System.out.println("속성이 없습니다");
            }
            return path; 
          }
      //삽입
      public static void addrecord(String c_name, String c_phone, String c_kindofshut, int c_howmany, String c_date, int pay, String bigo) 
      {
    	 
    	  Connection con = DB_insert();
          try {
        	  Statement stmt = con.createStatement();
        	  String s = "insert into pictable(customname, tel, type, people, createdate, date, finaldate, paid, bigo)values";
        	  Date today = new Date();
        	  SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        	  s+= "('"+c_name+"','"+c_phone+"','"+c_kindofshut+"','"+ c_howmany+"','"+ date.format(today)+"','"+c_date+"','"+ " "+"','"+pay+"','"+bigo+"')";
        	  System.out.println(s);
        	  int i = stmt.executeUpdate(s);
        	  if(i == 1) System.out.println("삽입 성공");
        	  else System.out.println("삽입 실패");
          }catch (SQLException e) {
        	  System.out.println(e.getMessage());
        	  System.exit(0);
          }
      }
      //경로삽입
      public static void addpath(String pathname) {
    	  
   
    	  Connection con = DB_insert();
          try {
        	  Statement stmt = con.createStatement();
        	  String s = "insert into pathtable values";
        	  		
        	  s+= "('"+pathname+"')";
        	  System.out.println(s);
        	  int i = stmt.executeUpdate(s);
        	  if(i == 1) System.out.println("삽입 성공");
        	  else System.out.println("삽입 실패");
          }catch (SQLException e) {
        	  System.out.println(e.getMessage());
        	  System.exit(0);
          }
      }
      //레코드 삭제
      public static void deleterecord(String column,String deleterecord) {
     	 Connection con = DB_insert();
     	 String s = "delete "+"from pictable where "+column+"="+"'"+deleterecord+"'";
     	 try {
         	 Statement stmt = con.createStatement();
         	 System.out.println(s);
              int i = stmt.executeUpdate(s);
              if(i == 1) System.out.println("튜플 삭제 성공");
              else System.out.println("튜플 삭제 실패");
          }catch (SQLException e) {
        	  System.out.println(e.getMessage());
        	  System.exit(0);
          }
      
      }
      //pictable 수정 0516추가
      public static void updatecustomer(String pathname, String after,String c_num) {
    	  Connection con = DB_insert();
          String s = "update pictable set "+pathname+"="+"'"+after+"'"+" where customnum="+"'"+c_num+"'";
          
         try {
        	 Statement stmt = con.createStatement();
        	 System.out.println(s);
             int i = stmt.executeUpdate(s);
             if(i == 1) System.out.println("수정 성공");
             else System.out.println("수정 실패");
         }catch (SQLException e) {
       	  System.out.println(e.getMessage());
       	  System.exit(0);
         }
         
      }
      //수정								//테이블 명    	//바뀔 속성		//바뀌기 전 값		//바뀌고난 값
      public static void updaterecord(String pathtable,String pathname,String before, String after) {
    	  Connection con = DB_insert();
    	  System.out.println("before : "+before);
          String s = "update "+pathtable+ " set "+pathname+"="+"'"+after+"'"+" where "+pathname+"="+"'"+before+"'";
          
         try {
        	 Statement stmt = con.createStatement();
        	 System.out.println(s);
             int i = stmt.executeUpdate(s);
             if(i == 1) System.out.println("수정 성공");
             else System.out.println("수정 실패");
         }catch (SQLException e) {
       	  System.out.println(e.getMessage());
       	  System.exit(0);
         }
         
      }

}
