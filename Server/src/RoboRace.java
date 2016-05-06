import RoboRace.*;
import javax.swing.*;
import COSC3P40.graphics.*;
import COSC3P40.midi.MidiManager;
import COSC3P40.xml.*;
import java.io.File;

import java.net.*;
import java.io.IOException;

public class RoboRace {
    
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
	ImageManager.getInstance().setImagePath("./Images/");
	XMLReader.setXMLPath("./");
	XMLReader.setXSDPath("./XSD/");	
    	int nHuman = 0;
    	while (nHuman==0 || nHuman>4) {
            try {
                nHuman = Integer.parseInt(GameDialogs.showInputDialog(InetAddress.getLocalHost().toString(),"Please, input the number of human players (1-4):"));
            } catch(Exception e) {};
	};
        int port = 10997;
        ServerSocket server;
        Socket sock;
	String[] names = new String[nHuman];
	NetworkPort[] ports = new NetworkPort[nHuman];
        try {
            server = new ServerSocket(port);
            for (int i=0; i<nHuman; i++){
                sock = server.accept();
                ports[i] = new NetworkPort(sock);
                names[i] = ports[i].recieve();
            }
        }
        catch (IOException e){
            
        }
    	(new GameMaster(nHuman,names,ports)).run();
    }	   
}
