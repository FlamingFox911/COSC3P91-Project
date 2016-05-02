package RoboRace;

import java.awt.*;
import COSC3P40.graphics.ImageManager;

public class CardBack extends Card {

	public CardBack(int priority) {
		super(priority);
		image = ImageManager.getInstance().loadImage("Cards/Back.png");
		Graphics g = image.getGraphics();
		g.drawString("Priority: "+ getPriority(),15,15);
		g.dispose();
	}
	
	public void execute(EventCounter counter, EventList events, Robot robot, Board board) {
            Direction dir = robot.getDirection();
            Point loc = robot.getLocation();
            events.add(new MoveEvent(counter, loc, dir.halfturn()));
            board.step(counter, events, robot, dir.halfturn());
            counter.increase();
	}
	
	public String toXMLString() {
		return "<back priority=\"" + getPriority() + "\"/>";
	}
	
}