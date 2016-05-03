package RoboRace;

public class Pit implements Tile {
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            // Already handled in Step
	}
	
	public String toXMLString() {
		return "<pit/>";
	}
	
}