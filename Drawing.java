package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Drawing extends Canvas{
	JFrame frame;
	static int color = 0x000000;
	static int refresh = 30; // # of refresh times / second
	static Color pixArr[];
	static int width;
	static int height;
	static boolean updateNow = false;
	public void Draw() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		frame = new JFrame("Mr. Game");
	    Canvas canvas = new Drawing();
	    canvas.setSize(width, height);
	    frame.add(canvas);
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pixArr = new Color[height*width];
	    for(int y = 0; y < height;y++) {
	    	for(int x = 0; x < width; x++) {
	    		pixArr[height*width+width] = new Color(color);
	    	}
	    }
	}
	public void paint(Graphics g) {
		//super.paint(g);
        //g.setColor(new Color(0x000000));
        for(int y = 0; y < Drawing.height;y++) {
        	for(int x = 0; x < Drawing.width;x++) {
        		g.setColor(pixArr[y*Drawing.width+x]);
        		g.drawLine(x,y,x,y);
        	}
        }
        //g.setColor(Color.black);
        //g.fillRect(0, 0, Drawing.width, Drawing.height);
        if(updateNow) {
        	updateNow = false;
        	update(g);
        }
    }
	public void end() {
		frame.dispose();
	}
	public static Color[] grid(Grid g) {
		int gHeight = g.grid.length;
		int gWidth = g.grid[0].length;
		double hSplit = height / gHeight;
		double wSplit = width / gWidth;
		Color out[] = new Color[height*width];
		for(int y = 0; y < height;y++) {
			for(int x = 0; x < width; x++) {
				//out[y*width+x] = g.grid[(int)Math.round(y/hSplit)]
				//		[(int)Math.round(x/wSplit)].color;
				out[y*width+x] = new Color((int) Math.round(Math.random()*0xFFFFFF));
			}
		}
		return out;
	}
}
