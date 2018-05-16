package input;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import test.FrameWork;

/* activated는 팝업이 생겼는지 아닌지에 대한 정보
 * focus는 팝업에 포커스가 되었는지 아닌지에 대한 정보
 * 
 * */

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
	
	//팝업 생성시 초기화해주는 거
	private void init() {
		activated = true;
	}
	
	//팝업 제거시 초기화해주는 거, 소멸자가 있으면 거기에 넣는데..
	private void reset() {
		activated = false;
		focus = false;
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
		
		//커서가 팝업 내에 있을 경우 포커스된다.
		if ( (cursor_x>=x && cursor_x<=x+width) && (cursor_y>=y && cursor_y<=y+height)) {
			this.focus = true;
		//포커스 되어있지 않은 상태에서 마우스 왼/오른쪽 버튼 클릭시 팝업 비활성환
		}else if (mouse.left_clicked || mouse.right_clicked) {
			reset();
		//팝업은 있지만, 포커스되지 않은 상태
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
					//current max_level is 4, start by 0
					switch (current_level) {
					case 0: //파일 열기
						Desktop.getDesktop().open(new File(framework.getIconObjectPath(idx)));
						break;
					case 1: //파일 지우기
						if(!new File(framework.getIconObjectPath(idx)).delete()) {
							//삭제 불가능
							System.out.println("error::delete failed");
						}else {
							System.out.println("delete successed");
						}
						break;
					case 2: //기능 추가 바람
						break;
					case 3: //기능 추가 바람
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
		
		/* 커서가 팝업 내에 있을 경우, 포커스된 것.
		 * 포커스된 상태에만 사용됨.
		 * */
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
		//기능 별로 출력되는 텍스트, 포커스가 안되어 있어도 그려져야 겠지?
		g.setColor(new Color(0, 0, 0));
		g.drawString("Open", x, y+10);
		g.drawString("Delete", x, y+div_size*1+10);
		g.drawString("DO WHAT", x, y+div_size*2+10);
		g.drawString("DO WHAT", x, y+div_size*3+10);
		g.drawString("DO WHAT", x, y+div_size*4+10);
		//구분선 생성바람.
	}
}
