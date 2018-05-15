package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

	private BufferedImage[] imageList;
	private ImageIcon[] icon;
	
	private int size = 100;
	private File folder;
	private int fileCount;
	
	private BufferedImage folderImage;
	private BufferedImage rawImage;

	public FileLoader(String path) {
		imageList = new BufferedImage[size];
		fileName = new String[size];
		filePath = new String[size];
		icon = new ImageIcon[size];

		this.path = path;
		
		try {
			folderImage = ImageIO.read(new File("res/folder.png"));
			rawImage = ImageIO.read(new File("res/raw.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * path 예외 처리해줘야 함. 1. 디렉토리일 경우 2. 파일일 경우 3. 잘못된 경로일 경우 4. 루트가 있는지 없는지
	 */

	public void LoadFiles(File folder) {
		folder = this.folder;
		imageList = new BufferedImage[size];

		int i = 0;
		this.path = folder.getAbsolutePath();
		
		fileCount = 0;
		for (File fileEntry : folder.listFiles()) {
			this.fileCount++;
			if (fileEntry.isDirectory()) {
				filePath[i] = fileEntry.getAbsolutePath();
				fileName[i] = fileEntry.getName();
				imageList[i++] = folderImage;
				
			} else {
				fileName[i] = fileEntry.getName();
				filePath[i] = fileEntry.getAbsolutePath();
				int pos = fileName[i].lastIndexOf('.');
				if ( fileName[i].substring(pos+1) == "raw" || fileName[i].substring(pos+1) == "RAW" ) {
					imageList[i++] = rawImage;
				}else {
					try {
						imageList[i++] = ImageIO.read(fileEntry);
					} catch (IOException e) {
					}
				}
			}
		}

	}

	public void LoadFiles() {
		imageList = new BufferedImage[size];

		folder = new File(path);

		if (!folder.exists()) { // 디렉토리 없으면 생성.
			folder.mkdirs();
		}

		int i = 0;
		fileCount = 0;
		for (File fileEntry : folder.listFiles()) {
			this.fileCount++;
			if (fileEntry.isDirectory()) {
				//fileList[i] = fileEntry.getAbsolutePath();
				fileName[i] = fileEntry.getName();
				filePath[i] = fileEntry.getAbsolutePath();
				imageList[i++] = folderImage;
				
			} else {
				
				filePath[i] = fileEntry.getAbsolutePath();
				fileName[i] = fileEntry.getName();
				int pos = fileName[i].lastIndexOf('.');
				if ( fileName[i].substring(pos+1).equals("raw") || fileName[i].substring(pos+1).equals("RAW") ) {
					imageList[i++] = rawImage;
				}else {
					try {
						imageList[i++] = ImageIO.read(fileEntry);
					} catch (IOException e) {
					}
				}
				
			}
		}

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BufferedImage[] getImageList() {
		return imageList;
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
