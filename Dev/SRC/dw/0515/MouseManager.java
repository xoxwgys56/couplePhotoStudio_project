package input;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import test.FrameWork;

public class MouseManager extends MouseAdapter implements MouseListener{

	public int cursor_x = -100, cursor_y = -100;//0으로 초기화되어, 첫번째 아이콘이 선택되는 것을 막음.
	public boolean cursor_in;
	public boolean left_clicked, right_clicked;
	private PopUp popUp;
	private FrameWork framework;
	
	public MouseManager(FrameWork framework) {
		this.framework = framework;
		popUp = new PopUp();
	}
	
	public void tick() {
		popUp.tick(framework, this, cursor_x, cursor_y);
	}
	
	public void render(Graphics g) {
		popUp.render(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stu
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.cursor_in = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.cursor_in = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
			left_clicked = true;
			System.out.println("left");	
		}else if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			right_clicked = true;
			System.out.println("right");
		} else if (e.getModifiers() == MouseEvent.BUTTON2_MASK)
			System.out.println("middle");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		left_clicked = right_clicked = false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		this.cursor_x = e.getX();
		this.cursor_y = e.getY();
	}

}
