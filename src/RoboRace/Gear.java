package RoboRace;

public class Gear implements Tile {
	
	private boolean clockwise;
	
	public Gear(boolean clockwise) {
		this.clockwise = clockwise;
	}
	
	public boolean isClockwise() {
		return clockwise;
	}
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            robot.turn(clockwise);
            events.add(new TurnEvent(counter, robot.getLocation(), clockwise));
            counter.increase();
	}
	
	public String toXMLString() {
		return "<gear clockwise=\"" + clockwise + "\"/>";
	}
}