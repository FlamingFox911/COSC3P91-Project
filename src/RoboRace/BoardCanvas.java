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
        }
}