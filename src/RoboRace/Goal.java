package RoboRace;

public class Goal implements Tile {
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            events.add(new VictoryEvent(counter, robot.getLocation(), robot.getName()));
	}
	
	public String toXMLString() {
		return "<goal/>";
	}
	
}