package input;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import test.FrameWork;

public class PopUp {

	private boolean activated, focus;
	private int cursor_x, cursor_y;
	private int x, y;
	private int width, height;
	private int div_level, div_size;
	private int current_level;
	
	public PopUp() {
		this.activated = false;
		this.focus = false;
		width = 100;
		height = 200;
		div_level = 5;
		div_size = height/div_level;
	}
	
	private void init() {
		activated = true;
	}
	
	private void reset() {
		activated = false;
	}
	
	public void tick(FrameWork framework, MouseManager mouse, int cursor_x, int cursor_y) {
		this.cursor_x = cursor_x;
		this.cursor_y = cursor_y;
		
		if (!activated && mouse.right_clicked) {
			init();
			x = cursor_x;
			y = cursor_y;
		}
		//else if (!activated reutnr
		
		if ( (cursor_x>=x && cursor_x<=x+width) && (cursor_y>=y && cursor_y<=y+height)) {
			this.focus = true;
			//System.out.println("x, "+x+" y, "+y+"  cursor_x : "+cursor_x+", cursor_y : "+cursor_y);
		}else if (mouse.left_clicked || mouse.right_clicked) {
			reset();
		}else {
			this.focus = false;
		}
		
		if (activated && mouse.left_clicked) {
			int idx = framework.getFocusedObjIdx();
			
			if (idx == -1) {
				System.out.println("error::focused object's idx is -1");
				reset();
			}
			else {
				try {
					switch (current_level) {
					case 0: 
						Desktop.getDesktop().open(new File(framework.getIconObjectPath(idx)));
						break;
					case 1:
						if(!new File(framework.getIconObjectPath(idx)).delete()) {
							//삭제 불가능
							System.out.println("error::delete failed");
						}else {
							System.out.println("delete successed");
						}
						break;
					}
				}//end try
				catch (IOException e) { e.printStackTrace(); }
				framework.setFocusedObjIdx(-1);
				reset();
			}
		}
	}
	
	public void render(Graphics g) {
		if (!activated)
			return ;
		
		g.setColor(new Color(255, 0, 0));
		g.fillRect(x, y, width, height);
		
		if (focus) {
			current_level = (cursor_y-y)/div_size;//tick() 함수가 먼저 호출되지만, 들어간 뒤 클릭하기 때문에 연관관계에서 문제가 없을 것으로 예상.
			g.setColor(new Color(0, 255, 255));
			switch (current_level) {
			//open
			case 0: g.fillRect(x, y, width, div_size); break;
			case 1: g.fillRect(x, y+div_size*1, width, div_size); break;
			case 2: g.fillRect(x, y+div_size*2, width, div_size); break;
			case 3: g.fillRect(x, y+div_size*3, width, div_size); break;
			case 4: g.fillRect(x, y+div_size*4, width, div_size); break;
			}
		}
		g.setColor(new Color(0, 0, 0));
		g.drawString("Open", x, y+10);
		g.drawString("Delete", x, y+div_size*1+10);
		g.drawString("DO WHAT", x, y+div_size*2+10);
		g.drawString("DO WHAT", x, y+div_size*3+10);
		g.drawString("DO WHAT", x, y+div_size*4+10);
	}
}
