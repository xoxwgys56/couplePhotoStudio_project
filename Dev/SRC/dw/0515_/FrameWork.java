package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import input.MouseManager;

/*
 * Loop를 이용한 Framework 이미지 출력
 * 
 * */

public class FrameWork implements Runnable{

	public FrameWork() {
		
	}

	private Display display;
	private MouseManager mouseManager;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;

	private FileLoader fileLoader;
	private String path = "C:/testImg/";
	
	//애들은 다 임시로 값 받는 애들이다. 받아서 바로 IconObject한테 줌.
	private BufferedImage[] imageList;
	private String[] fileName;
	private String[] filePath;
	
	private int icon_width, icon_height;//set 50
	private boolean temp_check = false;
	class IconObject {
		boolean focus;
		int icon_x, icon_y;
		BufferedImage image;
		String name;
		String path;
	}
	private IconObject[] iconObj;
	private int focusedObjIdx = -1;
	
	private void init() {
		display = new Display(path);
		mouseManager = new MouseManager(this);
		fileLoader = new FileLoader(path);
		
		this.fileLoader();
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	}
	
	//현재 이 함수는 초기화될 때 한번만 호출되는데, 그게 아니라 틱()에서 필요할 때마다 호출하도록 바뀌어야 한다.
	//코드 진짜 막 짜다 보니까 이런 경우가 생김.
	private void fileLoader() {		
		fileLoader.LoadFiles();
		int count = fileLoader.getFileCount();

		System.out.println(count);
		
		imageList = new BufferedImage[count];
		imageList = fileLoader.getImageList();
		filePath = fileLoader.getFilePath();
		fileName = fileLoader.getFileName();
		
		iconObj = new IconObject[count];
		for (int i=0; i<count; i++) {
			iconObj[i] = new IconObject();
			iconObj[i].image = imageList[i];
			iconObj[i].name = fileName[i];
			iconObj[i].path = filePath[i];
		}
	}
	
	//틱 함수 아직 정리 안된거라, 걍 기능이 막 꾸겨져서 넣어 있는 상태.
	//현재 하는 일은 그냥 초마다 이미지 계속 불러오는거. flag 넣어서 필요할 때마다 하도록 변경해야 된다.
	//아니면 너무 과부하 심하자너
	private void tick(){
		fileLoader.setPath(display.getPath());
		fileLoader.LoadFiles();
		imageList = fileLoader.getImageList();
		fileName = fileLoader.getFileName();
		display.setCanvasSize();
		
		mouseManager.tick();
	}

	private void render_thumbnail(Graphics g) {
			int x=0, y=0;
			int x_offset, y_offset;
			
			icon_width = icon_height = 50; //icon size
			x_offset = icon_width + 10; //default offset is 10
			y_offset = icon_height + 10;
			int width_limit = (display.getPanel().getWidth() /x_offset) - 1;
			
			for (int i=0; i<fileLoader.getFileCount(); i++) {
				iconObj[i].icon_x = (x++)*x_offset;
				iconObj[i].icon_y = y;
				
				g.drawImage(iconObj[i].image, iconObj[i].icon_x, iconObj[i].icon_y, icon_width, icon_height, null);
				g.drawString(iconObj[i].name, iconObj[i].icon_x, iconObj[i].icon_y+icon_height+10);
				
				//임시로 tick에 대한 내용을 이 곳에 놔둠, temp_check 이용하려다가 말았다.
				if ( mouseManager.cursor_in && 
						(mouseManager.cursor_x >=iconObj[i].icon_x && mouseManager.cursor_x <= iconObj[i].icon_x+icon_width) &&
						(mouseManager.cursor_y >= iconObj[i].icon_y && mouseManager.cursor_y <= iconObj[i].icon_y+icon_height)) {
					g.setColor(new Color(0, 0, 255));
					g.fillRect(iconObj[i].icon_x, iconObj[i].icon_y, 50, 50);
					g.setColor(new Color(0, 0, 0));
					this.focusedObjIdx = i; //
				}
				
				if (x > width_limit) {
					y += y_offset;
					x = 0;
				}
			}
		}
	
	//g.clearRect() 함수가 병신같이 되어 있었다. width,height를 시발 고정해서 주면 어떡하니..
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, 512, 1024);
		
		this.render_thumbnail(g);
		mouseManager.render(g);
		
		bs.show();
		g.dispose();
	}
	
	
	
	@Override
	public void run(){
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getIconObjectPath(int idx) {
		return iconObj[idx].path;
	}

	public int getFocusedObjIdx() {
		return focusedObjIdx;
	}

	public void setFocusedObjIdx(int focusedObjIdx) {
		this.focusedObjIdx = focusedObjIdx;
	}
	
}
