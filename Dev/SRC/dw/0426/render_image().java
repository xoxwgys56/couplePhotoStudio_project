//edited 0426
//inside of Framework class
	private void render_thumbnail(Graphics g) {
		int x=0, y=0;
		int x_offset, y_offset;
		int icon_width, icon_height;
		
		icon_width = icon_height = 50; //icon size
		x_offset = icon_width + 10; //default offset is 10
		y_offset = icon_height + 10;
		int width_limit = (display.getPanel().getWidth() /x_offset) - 1;
		
		System.out.println(display.getPanel().getWidth() + ", " + width_limit);
		
		for (BufferedImage img : imageList) {
			g.drawImage(img, (x++)*x_offset, y, icon_width, icon_height, null);
			if (x > width_limit) {
				y += y_offset;
				x = 0;
			}
		}
	}