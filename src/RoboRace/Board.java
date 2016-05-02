package RoboRace;

import COSC3P40.xml.*;
import java.awt.*;
import java.io.*;

public class Board implements XMLObject {
	
	private Factory factory;
	private int numberRobots;
	private Robot[] robots;
	
	public static Board read(Reader input) {
		XMLReader<Board> reader = new XMLReader<Board>();
        reader.setXMLSchema("board.xsd");
        reader.setXMLNodeConverter(new BoardReader());
        return reader.readXML(input);
	}
	
	public Board(Factory factory, int numberRobots, Robot[] robots) {
		this.factory = factory;
		this.numberRobots = numberRobots;
		this.robots = robots;
	}
	
	public Location getLocation(int x, int y) {
		return factory.getLocation(x,y);
	}
	
	public Location getLocation(Point p) {
		return factory.getLocation(p);
	}
	
	public Robot robotAt(int x, int y) {
		for(Robot robot : robots)
			if (x == robot.getLocation().x && y == robot.getLocation().y) 
				return robot;
		return null;
	}
	
	public void step(EventCounter counter, EventList events, Robot robot, Direction direction) {
            // Set locations and directions
            Point sLoc = robot.getLocation();
            Direction dir = robot.getDirection();
            int x = (int)sLoc.getX();
            int y = (int)sLoc.getY();
            int finX = x;
            int finY = y;
            switch (dir){
                case North:
                    finY -= 1;
                    break;
                case East:
                    finX += 1;
                    break;
                case South:
                    finY += 1;
                    break;
                case West:
                    finX -= 1;
                    break;
            }
            Location fLoc = getLocation(finX, finY);
            if (getLocation(sLoc).hasWall(dir)){
                events.add(new BumpEvent(counter, x, y, dir));
            }
            else if (robotAt(finX, finY) != null){
                step(counter, events, robotAt(finX, finY), dir);
                if (robotAt(finX, finY) != null){
                    events.add(new BumpEvent(counter, x, y, dir));
                }
                else {
                    robot.move(dir);
                    events.add(new MoveEvent(counter, x, y, dir));
                }
            }
            else if (fLoc.isPit()){
                events.add(new MoveEvent(counter, x, y, dir));
                events.add(new DestroyedEvent(counter, finX, finY));
            }
            else {
                robot.move(dir);
                events.add(new MoveEvent(counter, x, y, dir));
                if (finX < 0 || finY < 0 || finX >= factory.getXSize() || finY >= factory.getYSize()){
                    events.add(new DestroyedEvent(counter, finX, finY));
                }
            }
	}
	
	public void revitalize() {
		for(Robot robot : robots)
			if (!robot.isAlive() && robotAt(0,robot.getStart())==null) 
				robot.revitalize();
	}

	public String toXMLString() {
		String result = "<board>\n";
		result += "<robots number=\"" + numberRobots + "\">\n";
		for(Robot robot : robots) 
			result += robot.toXMLString() + "\n"; 
		result += "</robots>\n";
		result += factory.toXMLString() + "\n";
		return result + "</board>";
	}
	
	public Dimension getDisplaySize() {
		return factory.getDisplaySize();
	}
	
	public void start() {
		factory.start();
	}
	
	public void update(long delta) {
		factory.update(delta);
		for(Robot robot : robots)
			robot.update(delta);
	}
	
	public void draw(Graphics graphics) {
		factory.draw(graphics);
		for(Robot robot : robots)
			graphics.drawImage(robot.getImage(),robot.getScreenX(),robot.getScreenY(),null);
	}
	
	public void waitOnRobots() {
		for(Robot robot : robots) 
			robot.waitOnRobot();
		
	}
	
}