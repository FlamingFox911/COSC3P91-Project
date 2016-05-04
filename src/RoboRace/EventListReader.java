package RoboRace;

import java.util.*;
import org.w3c.dom.*;
import COSC3P40.xml.*;

import static COSC3P40.xml.XMLTools.getChildNodes;

public class EventListReader implements XMLNodeConverter<EventList> {

    public EventList convertXMLNode(Node node) {
        List<Node> in = getChildNodes(node);
        EventList eventList = new EventList();
        EventReader eventReader = new EventReader();
        for (Node i : in){
            eventList.add(eventReader.convertXMLNode(i));
        }
        return eventList;	
    }
}