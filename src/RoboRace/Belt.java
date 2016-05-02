package RoboRace;

public class Belt implements Tile {
	
	private Direction direction;
	
	public Belt(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
	}
	
	public String toXMLString() {
		return "<belt direction=\"" + direction + "\"/>";
	}
}