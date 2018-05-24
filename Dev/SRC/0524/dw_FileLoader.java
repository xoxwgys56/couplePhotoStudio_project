package util;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FileLoader {

	private String path;
	private String[] fileName;
	private String[] filePath;
	public String[] getFileName() {
		return fileName;
	}

	public String[] getFilePath() {
		return filePath;
	}

	private ImageIcon[] icon;
	
	private int size = 100;
	private File folder;
	private int fileCount;
	
	private ImageIcon folderImage;
	private ImageIcon rawImage;

	public FileLoader(String path) {
		icon = new ImageIcon[size];
		fileName = new String[size];
		filePath = new String[size];
		this.path = path;

		//이미지 크기 조정
		ImageIcon originIcon = new ImageIcon("res/folder.png");  
		Image originImg = originIcon.getImage(); 
		Image changedImg= originImg.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		folderImage = new ImageIcon(changedImg);
		
		originIcon = new ImageIcon("res/raw.png");  
		originImg = originIcon.getImage(); 
		changedImg= originImg.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		rawImage = new ImageIcon(changedImg);
	}

	/*
	 * path 예외 처리해줘야 함. 1. 디렉토리일 경우 2. 파일일 경우 3. 잘못된 경로일 경우 4. 루트가 있는지 없는지
	 */

	/* LoadFiles() 함수는 오버로딩되어 있다.
	 * 하나는 경로 넣어주는거, 하나는 안넣어주는거
	 * 경로를 넣어주면, FileLoader 객체가 갖고 있는 파일 객체가 해당 경로에 따른 파일 객체로 바뀐다.
	 * 
	 * FileLoader 객체 내의 파일이름/파일경로/파일이미지 배열에 해당 경로에 있는 파일들을 넣어준다.
	 * fileCount 변수는 해당 경로에 있는 파일 수를 보관
	 * */
	
	/* 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 */
	public void LoadFiles(File folder) {
		
		if (!folder.exists())
			return;
		icon = new ImageIcon[size];

		int i = 0;
		this.path = folder.getAbsolutePath();
		
		fileCount = 0;
		for (File fileEntry : folder.listFiles()) {
			this.fileCount++;
			if (fileEntry.isDirectory()) {
				filePath[i] = fileEntry.getAbsolutePath();
				fileName[i] = fileEntry.getName();
				icon[i++] = folderImage;
				
			} else {
				fileName[i] = fileEntry.getName();
				filePath[i] = fileEntry.getAbsolutePath();
				// 확장자 검사
				int pos = fileName[i].lastIndexOf('.');
				// 파일확장자가 raw 혹은 RAW라면
				if ( fileName[i].substring(pos+1).equals("raw") 
					|| fileName[i].substring(pos+1).equals("RAW") ) {
					icon[i++] = rawImage;
				}else {
					switch( fileName[i].substring(pos+1) ) {
					// 사진 파일만 표시되게
					case "jpg" :
					case "bmp" :
					case "gif" :
					case "png" : 
						Image originImg = new ImageIcon(filePath[i]).getImage();  
						Image changedImg= originImg.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
						icon[i++] = new ImageIcon(changedImg);
						break;
					default :
						fileCount--;
						break;
					}
				}
			}
		}
	}
	/* 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 추가 */

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ImageIcon[] getIcon() {
		return icon;
	}
	
	public File getFolder() {
		return this.folder;
	}
	
	public int getFileCount() {
		return fileCount;
	}
}
