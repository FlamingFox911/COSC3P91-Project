package RoboRace;

public class Pit implements Tile {
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            events.add(new DestroyedEvent(counter, robot.getLocation()));
	}
	
	public String toXMLString() {
		return "<pit/>";
	}
	
}