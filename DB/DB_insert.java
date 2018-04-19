package DB;

import java.sql.*;

public class DB_insert{

      public static Connection DB_insert() {
         
         
         String url = "jdbc:mysql://localhost/picdb";
         String id = "root";  //id
         String password = "apmsetup"; //pw
         Connection con = null;
         //jdbc 드라이버 적재 함수
         try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("데이터 베이스 연결 성공");
            
         }
         catch(ClassNotFoundException e) {
            System.out.println("드라이버를 찾을 수 없습니다.");
         } catch (SQLException e) {
           
            System.out.println("연결에 실패 !");
         }
         return con;
      }
      
      public static void main(String arg[]) throws SQLException{
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
            String date = rs.getString("date");
            String update = rs.getString("faux");
            int pay = rs.getInt("paid");
            String bigo = rs.getString("bigo");      
            System.out.println(num+" "+c_name+" "+c_phonenumber+" "+kindofshut+" "+howmany+" "+date+" "+update+" "+pay+" "+bigo);
         	
         }
    	 //삽입
         addrecord("하문식","000-0000-0000","증명사진",1,"12월 30","없음",5000,"없음");
         
         //수정
         //updaterecord("pictable","customname","김대원","하문식");
         
         //단일튜플 삭제
         //deleterecord("pictable","customname","김용태");
         
         //테이플 초기화
         Adeleterecord("pictable");
      }
      //삽입
      private static void addrecord(String c_name, String c_phone, String c_kindofshut, int c_howmany, String c_date, String c_update,int pay, String bigo) 
      {
    	 
    	  Connection con = DB_insert();
          try {
        	  Statement stmt = con.createStatement();
        	  String s = "insert into pictable(customname, tel, type, people, date, faux, paid,bigo)values";
        	  		
        	  s+= "('"+c_name+"','"+c_phone+"','"+c_kindofshut+"','"+ c_howmany+"','"+c_date+"','"+c_update+"','"+pay+"','"+bigo+"')";
        	  System.out.println(s);
        	  int i = stmt.executeUpdate(s);
        	  if(i == 1) System.out.println("삽입 성공");
        	  else System.out.println("삽입 실패");
          }catch (SQLException e) {
        	  System.out.println(e.getMessage());
        	  System.exit(0);
          }
      }
      
      //수정								//테이블 명    	//바뀔 속성		//바뀌기 전 값		//바뀌고난 값
      private static void updaterecord(String table,String column,String before, String after) {
    	  Connection con = DB_insert();
          String s = "update "+table+ " set "+column+"="+"'"+after+"'"+"where "+column+"="+"'"+before+"'";
          
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
      
      //레코드 삭제
     private static void deleterecord(String table,String column,String deleterecord) {
    	 Connection con = DB_insert();
    	 String s = "delete "+"from "+table+" where "+column+"="+"'"+deleterecord+"'";
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
     
     //테이블 초기화
     private static void Adeleterecord(String table) {
    	 Connection con = DB_insert();
    	 String s = "delete "+"from "+table;
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
 
}



