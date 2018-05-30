package util;

import java.sql.SQLException;

import database.DB_insert;
import framework.Display;

public class Handling {

	private static Display display;
	private String path;
	private DB_insert db;
	public Handling() {		
		try {
			path = db.getpath();
			System.out.print(path);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		display = new Display(path);
	}
	
	public static Display getDisplay() {
		return display;
	}
}
