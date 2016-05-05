package RoboRace;

import java.awt.*;
import java.awt.image.*;
import COSC3P40.graphics.*;
import java.util.List;

public class BoardCanvas extends Canvas implements Runnable {
	
	private Board board;
	private boolean running;
	private Thread thread;
        
	public BoardCanvas(Board board) {
		this.board = board;
		Dimension dim = board.getDisplaySize();
		setSize(dim.width,dim.height);
	}
	
	public void start() {
		thread = new Thread(this);
		running = true;
		board.start();
		thread.start();
	}
	
	public void stop() {
		running = false;
		try{
			thread.join();
		} catch(Exception e) {}
	}
	
	public void run() {
            long currTime = System.currentTimeMillis();
            this.createBufferStrategy(2);
            while (true) {
                long elapsedTime = System.currentTimeMillis() - currTime;
                currTime += elapsedTime;
                board.update(elapsedTime);
                BufferStrategy s = this.getBufferStrategy();
                Graphics g = s.getDrawGraphics();
                board.draw(g);
                g.dispose();
                s.show();
                try {
                    Thread.sleep(20);
                }
                catch (InterruptedException ex) {
                    
                }
            }
        }
}