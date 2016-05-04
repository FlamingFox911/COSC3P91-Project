package RoboRace;

import COSC3P40.xml.*;
import org.w3c.dom.*;

import static COSC3P40.xml.XMLTools.*;
import javax.xml.parsers.DocumentBuilderFactory;

public class EventReader implements XMLNodeConverter<GameEvent> {
	
    public GameEvent convertXMLNode(Node node) {
        GameEvent gameEvent = null;
        int step = getIntAttribute(node,"step");
        int action = getIntAttribute(node,"action");
        int x = getIntAttribute(node,"x");
        int y = getIntAttribute(node,"y");
        EventCounter eventCounter = new EventCounter(step, action);
        if (node.getNodeName().equals("bump")) {
            Direction direction = (Direction)getEnumAttribute(Direction.class, node, "direction");
            gameEvent = new BumpEvent(eventCounter, x, y, direction);
        }
        else if (node.getNodeName().equals("destroyed")) {
            gameEvent = new DestroyedEvent(eventCounter, x, y);
        }
        else if (node.getNodeName().equals("halfturn")) {
            gameEvent = new HalfturnEvent(eventCounter, x, y);
        }
        else if (node.getNodeName().equals("move")) {
            Direction direction = (Direction)getEnumAttribute(Direction.class, node, "direction");
            gameEvent = new MoveEvent(eventCounter, x, y, direction);
        }
        else if (node.getNodeName().equals("turn")) {
            boolean clockwise = getBoolAttribute(node,"clockwise");
            gameEvent = new TurnEvent(eventCounter, x, y, clockwise);
        }
        else if (node.getNodeName().equals("victory")) {
            String name = getStringAttribute(node, "name");
            gameEvent = new VictoryEvent(eventCounter, x, y, name);
        }
        return gameEvent;
    }
}