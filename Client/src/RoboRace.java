import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.midi.MidiManager;
import COSC3P40.xml.*;
import java.io.File;

import java.net.*;

public class RoboRace {
    
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
	ImageManager.getInstance().setImagePath("./Images/");
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");	
	String name;
        String host = "localhost";
        int port = 10997;
        int i = 0;
        name = GameDialogs.showInputDialog("Player","Name of Player:");
        host = GameDialogs.showInputDialog("Player", "Host address:");
        if (host.equals("")){
            host = "localhost";
        }
        try {
            Socket sock;
            while(true){
                try {
                    sock = new Socket(host, port);
                    break;
                }
                catch(ConnectException e){ 
                    System.out.println("Connection failed. Waiting and retrying...");
                    Thread.sleep(2000);
                }
            }
            NetworkPort net = new NetworkPort(sock);
            // Send and receive data
            net.send(name);
            new Player(name, net);
            MidiManager mm = MidiManager.getInstance();
            mm.setMidiPath("./Sounds&Midi/");
            mm.play(mm.getSequence("MetroidPrime-PhendranaDrifts2-DanceMix.mid"), true);
            // End Send and Receive data
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	   
}
