package framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readpath {
	public String read1() {
		
		FileReader reader = null;
		BufferedReader bufferreader = null;
		String str = new String();
		String base = "c:/testimg/basic.txt";
		File file = new File(base);
		
		int c;
		
		try {
			reader = new FileReader(file);
			bufferreader = new BufferedReader(reader);
			while((str = bufferreader.readLine()) != null){
				System.out.println(str); //test ¿ë
			}
			bufferreader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}catch(IOException e1) {
			System.out.println(e1);
		}
		
		return str;
	}
	
	public String read2() {
		
		FileReader reader = null;
		BufferedReader bufferreader = null;
		String str = new String();
		String base = "c:/testimg/photo.txt";
		File file = new File(base);
		
		int c;
		
		try {
			reader = new FileReader(file);
			bufferreader = new BufferedReader(reader);
			while((str = bufferreader.readLine()) != null){
				System.out.println(str); //test ¿ë
			}
			bufferreader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}catch(IOException e1) {
			System.out.println(e1);
		}
		
		return str;
	}
	
	public String read3() {
		
		FileReader reader = null;
		BufferedReader bufferreader = null;
		String str = new String();
		String base = "c:/testimg/lightroom.txt";
		File file = new File(base);
		
		int c;
		
		try {
			reader = new FileReader(file);
			bufferreader = new BufferedReader(reader);
			while((str = bufferreader.readLine()) != null){
				System.out.println(str); //test ¿ë
			}
			bufferreader.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}catch(IOException e1) {
			System.out.println(e1);
		}
		
		return str;
	}
	
}
